package modele;

import java.util.ArrayList;

public class Etudiant {
	private int codeEtudiant;
	private String nom;
	private String prenom;
	private Mention mention;

	ArrayList<Diplome> lesDiplomes;
	ArrayList<AnneeUniv> lesAnneesUniv;
	
	public Etudiant(int code, String n, String p, Mention m) {
		this.codeEtudiant=code;
		this.nom=n;
		this.prenom=p;
		this.mention=m;
	}
	
	public Mention getMention() {
		return this.mention;
	}

	public void setParcours(Mention m) {
		this.mention = m;
	}
	
	public int getCodeEtudiant() {
		return codeEtudiant;
	}
	public void setCodeEtudiant(int codeEtudiant) {
		this.codeEtudiant = codeEtudiant;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
}
