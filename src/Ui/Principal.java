package Ui;

import java.util.Scanner;

import Negocios.Controlador;

public class Principal {
	
	public static void main(String[] args) {
			
			ControlServidor control = new Controlador();
			
			control.inicializar();
			
			System.out.println("PRESSIONE <ENTER> para encerrar o Servidor de Tradu��es.");
			new Scanner(System.in).nextLine();
			
			control.parar();
	
		
	}

}
