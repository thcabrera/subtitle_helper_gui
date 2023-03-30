package grafica;

public class Hora {

	private int hora;
	private int minutos;
	private double segundos;
	
	public Hora(String s, int milisegundos) {
		
		if(s.charAt(0) == ' ') {
			StringBuilder sb = new StringBuilder();
			for(int i = 1;i<s.length();i++) {
				sb.append(s.charAt(i));
			}
			s = sb.toString();
		}
		
		this.hora = Integer.parseInt(String.format("%s%s",s.charAt(0),s.charAt(1)));
		this.minutos = Integer.parseInt(String.format("%s%s",s.charAt(3),s.charAt(4)));
		this.segundos = Double.parseDouble(s.substring(6,s.indexOf(',')) + '.' + s.substring(s.indexOf(',')+1));
		
		aplicar(milisegundos);
		
	}
	
	private void aplicar(int miliseg) {
		double d = (double)miliseg/1000;
		this.segundos+=d;
		if(this.segundos > 59) {
			this.segundos-=60;
			this.minutos++;
			if(this.minutos > 59) {
				this.hora++;
				this.minutos-=60;
			}
		} else if(this.segundos < 0) {
			this.segundos+=60;
			this.minutos--;
			if(this.minutos < 0) {
				this.minutos+=60;
				this.hora--;
				if(this.hora < 0)
					throw new IllegalArgumentException("El adelanto es demasiado grande para el subtitulo");
			}
		}
	}
	
	@Override
	public String toString() {
		// segundos
		String seg = String.format("%05.3f",this.segundos);
		// minutos
		String min = String.format("%02d",this.minutos);
		// horas
		String h = String.format("%02d", this.hora);
		return String.format("%s:%s:%s",h,min,seg);
	}
	
}
