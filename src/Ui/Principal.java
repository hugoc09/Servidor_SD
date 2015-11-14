package Ui;


import java.util.Scanner;

import Negocios.Control;

public class Principal {
	
	public static void main(String[] args) {
		
		try {
			
			Control control = new Control();
			
			System.out.println("PRESSIONE <ENTER> para encerrar o Servidor.");
			new Scanner(System.in).nextLine();
			
			
			System.out.println("Encerrando servidor.");
			control.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}

}
