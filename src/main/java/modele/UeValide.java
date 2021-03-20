package modele;

public class UeValide {
	
	private UE ue;
	private Etudiant e;
	private int annee;
	private int semestre;
	private boolean valide;
	private boolean encours;
	
	public UeValide(UE ue, Etudiant e, int annee, int semestre, boolean valide, boolean encours) {
		this.ue = ue;
		this.e = e;
		this.annee = annee;
		this.semestre = semestre;
		this.valide = valide;
		this.encours = encours;
	}
	
	public UE getUe() {
		return ue;
	}
	public void setUe(UE ue) {
		this.ue = ue;
	}
	public Etudiant getE() {
		return e;
	}
	public void setE(Etudiant e) {
		this.e = e;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public int getSemestre() {
		return semestre;
	}
	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}
	public boolean isValide() {
		return valide;
	}
	public void setValide(boolean valide) {
		this.valide = valide;
	}
	public boolean isEncours() {
		return encours;
	}
	public void setEncours(boolean encours) {
		this.encours = encours;
	}
	
	
	
}
