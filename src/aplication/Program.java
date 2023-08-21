package aplication;

import java.util.Scanner;

import javax.security.auth.login.CredentialException;
import javax.swing.JOptionPane;

import entites.Account;
import entites.User;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Account.options(sc);
		sc.close();
	}

}
