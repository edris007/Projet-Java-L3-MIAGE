package modele;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import controleur.Controleur;

public class ChargementCsv {

	public static ArrayList<String[]> returnTabCsv(String nomFichier) {
		ArrayList<String[]> tabCsv = new ArrayList<String[]>();
		try {
			// URL resource = main.class.getResource("data/"+ nomFichier +
			// ".csv");C:\\Users\\Edris\\eclipse-workspace\\ProjetJava\\src\\main\\resources\\data\\

			try (CSVReader reader = new CSVReader(new FileReader(main.path + nomFichier + ".csv"))) {

				List<String[]> r = reader.readAll();
				for (String[] a : r) {
					tabCsv.add(a);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (CsvException e1) {
				e1.printStackTrace();
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		return tabCsv;
	}

	public static ArrayList<String[]> returnTabCsvParc(String path) {
		ArrayList<String[]> tabCsv = new ArrayList<>();
		try {
			// URL resource = main.class.getResource("data/"+ nomFichier + ".csv");

			try (CSVReader reader = new CSVReader(new FileReader(path))) {

				List<String[]> r = reader.readAll();
				for (String[] a : r) {
					tabCsv.add(a);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (CsvException e1) {
				e1.printStackTrace();
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		return tabCsv;
	}

	public static void main(String[] args) {

		for (Etudiant e : Controleur.collectionEtudiant()) {
			System.out.println(Controleur.rechercheParcours(e.getMention()).getNom());
			String[] tabEtu = { String.valueOf(e.getCodeEtudiant()), e.getNom(), e.getPrenom(),
					e.getMention().getNom() };
		}
	}

}
