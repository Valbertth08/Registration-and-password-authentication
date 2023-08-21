package entites;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.security.auth.login.CredentialException;

public class Account {

	public static boolean registerAccount(Scanner sc) {
		int value = 0;

		System.out.println();
		System.out.println("--------CADASTRO-------");
		System.out.println();
		System.out.print("Digite seu usuario: ");
		String user = sc.next();
		System.out.print("Digite sua senha: ");
		String senha = sc.next();

		User us = new User(user, senha);

		if (!us.getPassword().matches("(?=.*[A-Z]).*")) {
			value = 1;
			verificationPassword(value);
		}
		if (!us.getPassword().matches("(?=.*[a-z]).*")) {
			value = 2;
			verificationPassword(value);
		}
		if (!us.getPassword().matches("(?=.*[0-9]).*")) {
			value = 3;
			verificationPassword(value);
		}
		if (!us.getPassword().matches("(?=.*[@#$%^&+=]).*")) {
			value = 4;
			verificationPassword(value);
		}

		if (value == 0) {

			System.out.print("Digite sua senha novamente: ");
			String passaword = sc.next();
			if (passaword.equals(us.getPassword())) {

				File path = new File("C:\\TDE");
				if (!path.exists()) {
					Boolean p = new File("C:\\TDE").mkdir();
				}
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(path + "\\path.txt", true))) {
					bw.write(us.getName() + "," + us.getPassword());
					bw.newLine();
					System.out.print("Registro realizado");
					System.out.println();
					return true;
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			} else {
				System.out.println("Senhas não coincidem, tente novamente.");
			}

		}
		return false;
	}

	public static void verificationPassword(int value) {

		try {
			switch (value) {
			case 1:
				throw new CredentialException("Ta faltando letra Maisucula");
			case 2:
				throw new CredentialException("Ta faltando letra Minuscula");
			case 3:
				throw new CredentialException("Ta faltando  numeros");
			case 4:
				throw new CredentialException("Ta faltando  caractere especial");
			}
		} catch (CredentialException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void login(Scanner sc) {
		System.out.println();
		System.out.println("-----------------LOGIN-------------------");
		System.out.println();
		int i = 0;
		do {
			System.out.print("Digite seu usuario: ");
			String user = sc.next();

			System.out.print("Digite sua senha: ");
			String password = sc.next();
			System.out.println();
			File path = new File("C:\\TDE\\path.txt");

			try (BufferedReader br = new BufferedReader(new FileReader(path))) {
				String rw = br.readLine();
				Boolean verificador = null;
				while (rw != null) {
					String[] vect = rw.split(",");
					if (user.equals(vect[0]) && password.equals(vect[1])) {
						i = 4;
						System.out.print("Login efetuado");
						verificador = true;
						System.out.println();
						break;
					}
					rw = br.readLine();
				}

				if (verificador == null) {
					i += 1;
					if (i != 3) {
						System.out.println("Usuario ou senha incorretos..informe seu usuario senha novamente.");
						System.out.println("Tentativa: " + i + ", você só tem mais " + (3 - i) + " tentativa.");
						System.out.println();
					}
				}

			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		} while (i != 3 && i != 4);

		if (i == 3) {
			System.out.println("Suas tentativas acabaram, tente novamente mais tarde.");
			System.out.println();
		}
	}

	public static void options(Scanner sc) {
		System.out.println();
		System.out.println("-------Bem vindo a tela inicial--------\n" + "1. Cadastra usuario\n2. Login");
		System.out.println();
		System.out.print("Opção: ");
		int option = sc.nextInt();

		if (option == 1) {
			registerAccount(sc);
			options(sc);
		} else {
			login(sc);
			options(sc);
		}

	}

}
