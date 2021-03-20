package controleur;

import java.util.ArrayList;
import modele.UeValide;
import modele.ChargementCsv;
import modele.Etudiant;
import modele.Mention;
import modele.Parcours;
import modele.UE;

/**
 * Classe comportant les différentes méthodes permettant de créer les différents
 * objets et collections d'objets, obtenu grâce à du CSV
 * 
 * @author Edris
 *
 */
public class Controleur {

	/**
	 * Cette méthode permet de retourner l'ensemble des étudiants
	 * 
	 * @return
	 */
	public static ArrayList<Etudiant> collectionEtudiant() {
		ArrayList<Etudiant> collectionEtudiant = new ArrayList<Etudiant>();
		ArrayList<String[]> contenuFichier = ChargementCsv.returnTabCsv("etudiant");
		for (String[] colonne : contenuFichier) {
			collectionEtudiant.add(new Etudiant(Integer.valueOf(colonne[0]), colonne[1], colonne[2],
					rechercheMention(Integer.valueOf(colonne[3]))));
		}

		return collectionEtudiant;
	}
	
	/**
	 * 
	 * @return
	 */
	public static ArrayList<UE> collectionUE() {
		ArrayList<UE> collectionUE = new ArrayList<UE>();
		ArrayList<String[]> contenuFichier = ChargementCsv.returnTabCsv("ue");
		for (String[] colonne : contenuFichier) {
			collectionUE.add(new UE(colonne[0], Integer.valueOf(colonne[1]), colonne[2],
					getMention(colonne[3])));
		}

		return collectionUE;
	}

	


	public static ArrayList<Etudiant> collectionUeValide() {
		ArrayList<Etudiant> collectionEtudiant = new ArrayList<Etudiant>();
		ArrayList<String[]> contenuFichier = ChargementCsv.returnTabCsv("etudiant");
		for (String[] colonne : contenuFichier) {
			collectionEtudiant.add(new Etudiant(Integer.valueOf(colonne[0]), colonne[1], colonne[2],
					rechercheMention(Integer.valueOf(colonne[3]))));
		}

		return collectionEtudiant;
	}

	public static ArrayList<Etudiant> collectionEtuParc(String path) {
		ArrayList<Etudiant> collectionEtudiant = new ArrayList<Etudiant>();
		ArrayList<String[]> contenuFichier = ChargementCsv.returnTabCsvParc(path);
		for (String[] colonne : contenuFichier) {
			collectionEtudiant.add(new Etudiant(Integer.valueOf(colonne[0]), colonne[1], colonne[2],
					rechercheMention(Integer.valueOf(colonne[3]))));
		}

		return collectionEtudiant;
	}
	
	public static ArrayList<UE> collectionUEParc(String path) {
		ArrayList<UE> collectionUE = new ArrayList<UE>();
		ArrayList<String[]> contenuFichier = ChargementCsv.returnTabCsvParc(path);
		for (String[] colonne : contenuFichier) {
			collectionUE.add(new UE(colonne[0], Integer.valueOf(colonne[1]), colonne[2],
					getMention(colonne[3])));
		}

		return collectionUE;
	}

	private static Mention rechercheMention(int idMention) {
		Mention returnMention;
		ArrayList<String[]> contenuFichier = ChargementCsv.returnTabCsv("mention");
		for (String[] a : contenuFichier) {

			if (a[0].equals(String.valueOf(idMention))) {
				return new Mention(Integer.valueOf(a[0]), a[1]);
			}
		}
		return null;
	}

	public static Parcours rechercheParcours(Mention mention) {
		ArrayList<String[]> contenuFichier = ChargementCsv.returnTabCsv("parcours");
		for (String[] a : contenuFichier) {
			if (a[0].equals(String.valueOf(mention.getId()))) {
				return new Parcours(Integer.valueOf(a[0]), String.valueOf(a[1]),
						rechercheMention(Integer.valueOf(a[2])));
			}
		}
		return null;
	}

	/**
	 * Cette méthode permet de retourner une collection d'UeValide
	 * 
	 * @see UeValide
	 * @param idEtudiant
	 * @return
	 */
	public static ArrayList<UeValide> collectionUEValide(int idEtudiant) {
		ArrayList<UeValide> collectionUEValide = new ArrayList<UeValide>();
		ArrayList<String[]> contenuFichier = ChargementCsv.returnTabCsv("ueValides");
		for (String[] colonne : contenuFichier) {
			if (colonne[1].equals(String.valueOf(idEtudiant))) {
				collectionUEValide.add(new UeValide(getUE(colonne[0]), getEtudiant(Integer.valueOf(colonne[1])),Integer.valueOf(colonne[2]),
						Integer.valueOf(colonne[3]),Boolean.valueOf(colonne[4]),Boolean.valueOf(colonne[5])));
			}
		}
		return collectionUEValide;
	}
	
	
	public static UE getUE(String codeUE) {
		ArrayList<String[]> contenuFichier = ChargementCsv.returnTabCsv("ue");
		for (String[] colonne : contenuFichier) {
			if (colonne[0].equals(codeUE)) {
				return new UE(colonne[0], Integer.valueOf(colonne[1]), colonne[2],
						getMention(colonne[3]));
			}
		}
		return null;
	}
	
	public static Mention getMention(String codeMention) {
		ArrayList<String[]> contenuFichier = ChargementCsv.returnTabCsv("mention");
		for (String[] colonne : contenuFichier) {
			if (colonne[0].equals(codeMention)) {
				return new Mention(Integer.valueOf(colonne[0]), colonne[1]);
			}
		}
		return null;
	}

	/**
	 * Cette méthode permet de récupérer l'étudiant en question
	 * 
	 * @see Etudiant
	 * @param numEtudiant
	 * @return
	 */
	public static Etudiant getEtudiant(int numEtudiant) {
		ArrayList<Etudiant> collectionEtudiant = new ArrayList<Etudiant>();
		ArrayList<String[]> contenuFichier = ChargementCsv.returnTabCsv("etudiant");
		for (String[] colonne : contenuFichier) {
			if (colonne[0].equals(String.valueOf(numEtudiant))) {
				return new Etudiant(Integer.valueOf(colonne[0]), colonne[1], colonne[2],
						rechercheMention(Integer.valueOf(colonne[3])));
			}
		}
		return null;
	}
	
	
	public static ArrayList<Etudiant> collectionUEEtudiant(String idUE) {
		ArrayList<Etudiant> collectionUEValide = new ArrayList<Etudiant>();
		ArrayList<String[]> contenuFichier = ChargementCsv.returnTabCsv("ueValides");
		for (String[] colonne : contenuFichier) {
			if (colonne[0].equals(String.valueOf(idUE))) {
				collectionUEValide.add(getEtudiant(Integer.valueOf(colonne[1])));
			}
		}
		return collectionUEValide;
	}
}
