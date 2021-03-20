package modele;

import java.util.ArrayList;

public class Formation {
	private String nom;
	ArrayList<Mention> lesMentions;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
}
