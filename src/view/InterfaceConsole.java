package view;

import java.util.Observable;
import java.util.Scanner;

import controleur.Controleur;
import model.JoueurArtificiel;

public class InterfaceConsole extends IHM {

	public InterfaceConsole(Controleur ctrl) {
		super(ctrl);
	}

	@Override
	public void notifier(String msg) {
		System.out.println(msg);
	}

	@Override
	public int choixIndexCarte() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void initJoueurs() {
		Scanner sc = new Scanner(System.in);
		System.out.println("---- CREATION DES JOUEURS ARTIFICELS ----");
		
		
		
	}

	
}
