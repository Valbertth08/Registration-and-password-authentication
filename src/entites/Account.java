	package entites;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.security.auth.login.CredentialException;
import javax.swing.JOptionPane;

public class Account {

	public static boolean registerAccount() {
		int value = 0;

		StringBuilder sb = new StringBuilder();
		String user = null;
		String password = null;
		sb.append("----------CADASTRO----------");
		JOptionPane.showMessageDialog(null, sb);
		user = JOptionPane.showInputDialog("Digite seu usuario: ");
		password = JOptionPane.showInputDialog("Digite sua senha: ");
		User us = new User(user, password);

		if (password.length() < 6 || password.length() > 6) {
			JOptionPane.showInternalMessageDialog(null, "A senha deve conter 6 caracteres.");
			options();
		}
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
		if (!us.getPassword().matches("(?=.*[@#$%^&+=|//_-]).*")) {
			value = 4;
			verificationPassword(value);
		}

		if (value == 0) {
			String passaword = JOptionPane.showInputDialog("Digite sua senha novamente: ");
			if (passaword.equals(us.getPassword())) {

				File path = new File("C:\\TDE");
				if (!path.exists()) {
					Boolean p = new File("C:\\TDE").mkdir();

				}
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(path + "\\path.txt", true))) {
					bw.write(us.getName() + "," + us.getPassword());
					bw.newLine();
					JOptionPane.showMessageDialog(null, "Registro realizado");

					return true;
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			} else {
				JOptionPane.showMessageDialog(null, "Senhas não coincidem, tente novamente.");

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
			StringBuilder sb = new StringBuilder();

			sb.append(e.getMessage());
			JOptionPane.showMessageDialog(null, sb);
		}
	}

	public static void login() {

		JOptionPane.showMessageDialog(null, "-------------LOGIN-------------");
		int i = 0;
		do {

			String user = JOptionPane.showInputDialog("Digite seu usuario: ");
			String password = JOptionPane.showInputDialog("Digite sua senha: ");

			File path = new File("C:\\TDE\\path.txt");

			try (BufferedReader br = new BufferedReader(new FileReader(path))) {
				String rw = br.readLine();
				Boolean verificador = null;
				while (rw != null) {
					String[] vect = rw.split(",");
					if (user.equals(vect[0]) && password.equals(vect[1])) {
						i = 4;
						JOptionPane.showMessageDialog(null, "Login efetuado");

						verificador = true;
						System.out.println();
						break;
					}
					rw = br.readLine();
				}

				if (verificador == null) {
					i += 1;
					if (i != 3) {
						StringBuilder sb = new StringBuilder();

						sb.append("Usuario ou senha incorretos..informe seu usuario senha novamente.");
						sb.append("Tentativa: " + i + ", você só tem mais " + (3 - i) + " tentativa.");

						JOptionPane.showMessageDialog(null, sb);
					}
				}

			} catch (IOException e) {

				StringBuilder sb = new StringBuilder();

				sb.append(e.getMessage());
				JOptionPane.showMessageDialog(null, sb);

			}
		} while (i != 3 && i != 4);

		if (i == 3) {
			JOptionPane.showMessageDialog(null, "Suas tentativas acabaram, tente novamente mais tarde.");
		}
	}

	public static void options() {
		StringBuilder sb = new StringBuilder();
		sb.append("-------Bem vindo a tela inicial--------\n");
		sb.append("1. Cadastrar usuario\n");
		sb.append("2. Login\n");
		sb.append("3. Sair\n");
		char option = JOptionPane.showInputDialog(sb).charAt(0);
		if (option == '1') {
			registerAccount();
			options();
		} else if (option == '3') {
			JOptionPane.showMessageDialog(null, "saindo");
		} else {
			login();
			options();
		}

	}

}
