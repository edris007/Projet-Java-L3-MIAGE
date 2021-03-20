package modele;

import java.util.ArrayList;

public class Diplome {
	private String nom;
	ArrayList<Formation> lesFormations;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
}
