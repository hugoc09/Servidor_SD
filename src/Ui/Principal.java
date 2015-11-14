package Ui;


import java.util.Scanner;

import Negocios.ControleTraducao;
import Negocios.Traducao;

public class Principal {
	
	public static void main(String[] args) {
		
		try {
			
			ControleTraducao control = new Traducao();
			
			System.out.println("PRESSIONE <ENTER> para encerrar o Servidor.");
			new Scanner(System.in).nextLine();
			
			
			System.out.println("Encerrando servidor.");
			control.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}

}
