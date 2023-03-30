package grafica;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;

public class FileChooser extends JFileChooser{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String path;

	public FileChooser() {
		super(FileSystemView.getFileSystemView().getHomeDirectory());
	    setDialogTitle("Seleccione el archivo a modificar: ");
	    setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	    setFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				return "SRT files only";
			}
			
			@Override
			public boolean accept(File f) {
				String path = f.getAbsolutePath();
				String extension = path.substring(path.lastIndexOf('.')+1);
				// returns true if the file is a directory or a srt
				return f.isDirectory() || FileChooser.isAllowedExtension(extension);
			}
		});
	    int returnValue = showOpenDialog(null);
	    if (returnValue == JFileChooser.APPROVE_OPTION) {
	        this.path = getSelectedFile().getPath();
	    }
	}

	public static boolean isAllowedExtension(String extension){
		List<String> allowedExtensions = new ArrayList<>(Arrays.asList("srt"));
		return allowedExtensions.contains(extension);
	}

	public String getPath() {
		return this.path;
	}
	
}
