package Negocios;

import Redes.OuvindoUDP;
import Redes.Servidor;
import Ui.ControlServidor;

public class Controlador implements ControlServidor{

	private Servidor servidor;
	OuvindoUDP ouvindoUDP;
	
	
	@Override
	public void inicializar() {
		
		try {
		System.out.println("Inicializando Servidor de Tradu��es...");
		servidor = new Servidor(2525);
		servidor.start();
		ouvindoUDP = new OuvindoUDP(servidor);
		ouvindoUDP.start();
		
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}

	@Override
	public void parar() {
try {
	
			System.out.println("Encerrando Servidor de Tradu��es...");
			ouvindoUDP.stop();
			servidor.stop();
			System.out.println("Servidor de Tradu��es encerrado!");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
