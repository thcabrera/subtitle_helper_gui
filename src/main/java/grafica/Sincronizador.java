package grafica;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Sincronizador {

	private final String ubicacion;
	private Scanner entrada;
	private Formatter salida;
	
	public Sincronizador(String ubicacion) {
		this.ubicacion = ubicacion;
		try {
			entrada = new Scanner(Paths.get(ubicacion));
		} catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	
//	public void SBVtoSRT() {
//
//		try {
//
//			salida = new Formatter(this.obtenerNombre(2));
//
//			for(int i = 1;entrada.hasNext();i++) {
//				//escribimos el numero del subtitulo con salto de linea
//				escribir(String.format("%d%n",i));
//				System.out.println(i);
//				String horas = entrada.nextLine();
//				int indexOfComa = horas.indexOf(',');
//				//escribimos la hora de inicio del subtitulo con la flecha y los espacios
//				escribir(String.format("%s --> ",horas.substring(0,indexOfComa)));
//				System.out.print(horas.substring(0,indexOfComa)+" --> ");
//				//escribimos la hora final del subtitulo con el salto de linea
//				escribir(String.format("%s%n", horas.substring(indexOfComa+1)));
//				System.out.print(horas.substring(indexOfComa+1));
//				int j = 0;
//				while(true) {
//					String campo = entrada.nextLine();
//					if(campo.equals("") && j > 0) {
//						escribir(String.format("%n"));
//						break;
//					}
//					else if (!campo.equals("")) escribir(String.format("%s%n",campo));
//					else escribir(String.format("%n"));
//					j++;
//				}
//			}
//		} catch(SecurityException | FileNotFoundException e) {
//			e.printStackTrace();
//			System.exit(1);
//		} catch(NoSuchElementException ignored) {
//		} catch(IllegalStateException e) {
//			e.printStackTrace();
//		}
//
//	}
	
	public void sincSRT(int milisegundos) {
		
		try {
			
			salida = new Formatter(this.obtenerNombre(1));
			
			while(entrada.hasNext()) {
				//escribimos el numero del subtitulo
				escribir(String.format("%s%n",entrada.nextLine()));
				//escribimos la hora de inicio del subtitulo modificada
				escribir(String.format("%s ", new Hora(entrada.next()
						,milisegundos)));
				//escribimos la flecha
				escribir(String.format("%s ", entrada.next()));
				//escribimos la hora final modificada
				escribir(String.format("%s%n", new Hora(entrada.nextLine()
						,milisegundos)));
				int i = 0;
				while(true) {
					String campo = entrada.nextLine();
					if(campo.equals("") && i > 0) {
						escribir(String.format("%n"));
						break;						
					}
					else if (!campo.equals("")) escribir(String.format("%s%n",campo));
					else escribir(String.format("%n")); 
					i++;
				}
			}
		} catch(SecurityException | FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		} catch(NoSuchElementException ignored) {}
		catch(IllegalStateException e) {
			e.printStackTrace();
		}
		
	}
	
	private void escribir(String s) {
		try {
			salida.format("%s",s);
		} catch(FormatterClosedException e) {
			e.printStackTrace();
			System.exit(1);
		} catch(NoSuchElementException e) {
			System.err.println("Entrada invalida. Intente de nuevo.");
			entrada.nextLine();
		}
	}
	
	public void cerrar() {
		if(entrada != null)
			entrada.close();
		if(salida != null)
			salida.close();
	}
	
	public String obtenerNombre(int n) {
		if(n == 1) {
			String s = ubicacion.substring(0, this.ubicacion.lastIndexOf('.'));
			return String.format("%sSINC.srt",s);			
		}
		else {
			return this.ubicacion.substring(0,this.ubicacion.lastIndexOf('.')) + ".srt";
			
		}
	}
	
}
