package Negocios;

import Redes.OuvindoUDP;
import Redes.Servidor;

public class Control {
	
	private Servidor servidor;
	private OuvindoUDP ouvindoUDP;
	
	public Control(){
		
		try {
			servidor = new Servidor(2525);
			servidor.start();
			ouvindoUDP = new OuvindoUDP();
			ouvindoUDP.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
		public void close() {
		
		try {
			servidor.stop();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		try {
			ouvindoUDP.stop();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

}
