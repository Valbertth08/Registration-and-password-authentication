package aplication;

import java.util.Scanner;

import javax.security.auth.login.CredentialException;

import entites.User;

public class Program {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		int i=0;
		User us = null;
		
		do {
		System.out.print("Digite seu usuario: ");
		String user = sc.next();
		System.out.print("Digite sua senha: ");
		String senha = sc.next();
		
		try {
			if(!senha.matches("(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%^&+=]).*")) {
				i+=1;
				System.out.println();
				throw new CredentialException("A senha deve conter letras maisculas e minusculas, números e caracteres especiais.");
			}
			else {
				us = new User(user,senha);
				System.out.println();
				System.out.println("Cadastro efetuado com sucesso.");
			}
				
		} catch (CredentialException e) {
			
			System.out.println("Erro de usuario e senha: "+ e.getMessage());
			if(i != 3) {
			 System.out.println("Tentativa: "+i+", você só tem mais "+(3-i)+" tentativa.");
			}
			System.out.println();
		}
		
	     }while(i >0 && i<3 && us==null);
		
		if(i==3) {
			System.out.println("Suas tentativas acabaram, tente novamente mais tarde.");
		}
		
		sc.close();
	}

}
