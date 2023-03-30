package grafica;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventanas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel;
	private String ubicacion;

	/**
	 * Launch the application.
	 */
	public static void run() {
		try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    }catch(Exception ex) {
	        ex.printStackTrace();
	    }
		EventQueue.invokeLater(() -> {
			try {
				Ventanas frame = new Ventanas();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	// Create the frame
	public Ventanas() {
		super("SYNC-SRT");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnNewButton = new JButton("Elegir archivo");
		btnNewButton.setBounds(22, 103, 110, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new Oyente());
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(146, 104, 231, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 240, 434, 21);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("Seleccione el subtitulo");
		lblNewLabel.setBounds(0, 0, 434, 21);
		panel.add(lblNewLabel);
		
		btnNewButton_1 = new JButton("Sincronizar");
		btnNewButton_1.setBounds(175, 187, 89, 23);
		btnNewButton_1.setEnabled(false);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new Oyente());
		getRootPane().setDefaultButton(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("SYNC-SRT!");
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(33, 11, 138, 60);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(146, 145, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Retraso/adelanto:");
		lblNewLabel_2.setBounds(22, 148, 110, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton restart = new JButton("X");
		restart.setFont(new Font("Tahoma", Font.PLAIN, 12));
		restart.setBounds(385, 103, 39, 23);
		contentPane.add(restart);
		restart.addActionListener(new Oyente());
	}
	
	private class Oyente implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnNewButton) {
				FileChooser fileChooser = new FileChooser();
				ubicacion = fileChooser.getPath();
				if(ubicacion != null) {
					textField.setText(ubicacion);
					btnNewButton_1.setEnabled(true);
					lblNewLabel.setText("Ingrese los milisegundos a modificar y presione Sincronizar");
				}
			}
			else if(e.getSource() == btnNewButton_1){
				if(ubicacion == null || textField_1.getText().isEmpty())
					lblNewLabel.setText("Por favor, complete todos los campos.");
				else {
					int milisegundos = Integer.parseInt(textField_1.getText());
					Sincronizador sinc = new Sincronizador(ubicacion);
					sinc.sincSRT(milisegundos);
					sinc.cerrar();
					lblNewLabel.setText("Subtitulo sincronizado");
					JOptionPane.showMessageDialog(null, String.format("Nuevo archivo en:%n%s",
							sinc.obtenerNombre(1)));
				}
			}
			else {
				ubicacion=null;
				lblNewLabel = new JLabel("Seleccione el subtitulo");
				textField.setText("");
				btnNewButton_1.setEnabled(false);
				textField_1.setText("");

			}
		}
		
	}
}
