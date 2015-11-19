package Ui;

import java.util.Scanner;

import Redes.OuvindoUDP;
import Redes.Servidor;

public class Principal {
	
	public static void main(String[] args) {
		
		try {
			Servidor servidor = new Servidor(2525);
			servidor.start();
			OuvindoUDP ouvindoUDP = new OuvindoUDP(servidor);
			ouvindoUDP.start();
			
			
			System.out.println("PRESSIONE <ENTER> para encerrar o Servidor de Traduções.");
			new Scanner(System.in).nextLine();
			
			
			System.out.println("Encerrando Servidor de Traduções.");
			servidor.stop();
			ouvindoUDP.stop();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}

}
