package modele;

import java.util.ArrayList;

public class Parcours {

	private int id;
	private String nom;
	private Mention m;
	ArrayList<Semestre> lesSemestres;
	
	public Parcours(int id, String s, Mention m) {
		this.id = id;
		this.nom=s;
		this.m=m;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
}
