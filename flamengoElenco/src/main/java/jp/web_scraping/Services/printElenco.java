package jp.web_scraping.Services;

import java.util.Scanner;
import java.util.List;
import jp.web_scraping.Classes.capturarDados;




public class printElenco {

	static Scanner scan = new Scanner(System.in);
	
	public printElenco() {
		printInicial();
	}
	
	public static void printInicial() {
		
		System.out.println("1 - TODOS");
		System.out.println("2 - GOLEIROS");
		System.out.println("3 - ZAGUEIROS");
		System.out.println("4 - LATERAIS DIREITOS");
		System.out.println("5 - LATERAIS ESQUERDOS");
		System.out.println("6 - VOLANTES");
		System.out.println("7 - MEIAS");
		System.out.println("8 - ATACANTES");
		System.out.print("QUAL POSIÇÃO VOCÊ DESEJA LISTAR: ");
		
		int qPosition =  scan.nextInt();
    
	    if (qPosition <= 8) {
	    	  printPosition(qPosition);
		} else if (qPosition > 8) {
			  System.err.println("POR FAVOR, ESCOLHA UM NÚMEDO DA LISTA!\n");
			  printInicial();
		}
		
	}
 
	
	public static void printPosition(int nPosition) {
		
	 System.out.println("Carregando..\n");
	 
	 List<String> atletas = capturarDados.cLoading(nPosition);
	 
	 for(String atleta: atletas) { 
	   System.out.println(atleta);	 
	 }
	
	  //System.out.println("\n");
	  //printInicial();
	 
	}


	
	
}
