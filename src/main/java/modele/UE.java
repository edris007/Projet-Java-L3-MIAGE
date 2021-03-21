package modele;

import java.util.ArrayList;

public class UE {
	private String code;
	private String nom;
	private int ects;
	private ArrayList<UE> lesPrerequis;
	private Mention m;

	public UE(String code, int eCTS, String nom, Mention m) {
		this.code = code;
		this.nom = nom;
		this.ects = eCTS;
		this.m = m;
		this.lesPrerequis = new ArrayList<UE>();
	}

	public Mention getMention() {
		return this.m;
	}

	public void addPrerequis(UE a) {
		this.lesPrerequis.add(a);
	}

	public ArrayList<UE> getAllPrerequis() {
		return this.lesPrerequis;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getEcts() {
		return ects;
	}

	public void setEcts(int eCTS) {
		this.ects = eCTS;
	}
}
