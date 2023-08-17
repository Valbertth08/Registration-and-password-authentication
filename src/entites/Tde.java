package entites;

import java.util.Scanner;

public class Tde {

	public static void main(String[] args) {
		int i = 4;
		Scanner sc = new Scanner(System.in);
		int valor=0;
		do {
			if(i<4) {
				System.out.println();
				System.out.println("Você só tem mais "+(i)+" tentativas.");
			}
			System.out.println();
			System.out.print("Informe seu usuario: ");
			String user = sc.next();

			System.out.print("Informe sua senha: ");
			String senha = sc.next();

			if (!senha.matches("(?=.*[A-Z]).*")) {
				valor=1;
				metodo(valor);
			}
			if (!senha.matches("(?=.*[a-z]).*")) {
				valor=2;
				metodo(valor);
			} 
			
			if(!senha.matches("(?=.*[0-9]).*")) {
				valor=3;
				metodo(valor);
			}
			
			if(!senha.matches("(?=.*[@#$%^&+=]).*")) {
				valor=4;
				metodo(valor);
			}
			else {
				i = 5;
				System.out.println("Senha cadastrada");
			}
			i--;
		} while (i !=0 && i !=4 );

		if (i == 0) {
			System.out.println();
			System.out.println("Suas tentativas acabaram");
		}
		sc.close();

	}
	
	public static void metodo(int op) {
		
		switch(op){
			
			case 1:
				System.out.println("Ta faltando letra Maisucula");
				break;
			case 2:
				System.out.println("Ta faltando letra Minuscula");	
				break;
			case 3:
				System.out.println("Ta faltando  numeros");
				break;
			case 4:
				System.out.println("Ta faltando um caractere especial");
				break;
			
			}
		
		}	
}
