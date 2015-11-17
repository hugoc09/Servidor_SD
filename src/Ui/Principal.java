package Ui;


import java.util.Scanner;

import Redes.OuvindoUDP;
import Redes.Servidor;

public class Principal {
	
	public static void main(String[] args) {
		
		try {
			Servidor servidor = new Servidor(2525);
			servidor.start();
			OuvindoUDP ouvindoUDP = new OuvindoUDP();
			ouvindoUDP.start();
			
			
			
			System.out.println("PRESSIONE <ENTER> para encerrar o Servidor.");
			new Scanner(System.in).nextLine();
			
			
			System.out.println("Encerrando servidor.");
			ouvindoUDP.stop();
			servidor.stop();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}

}
