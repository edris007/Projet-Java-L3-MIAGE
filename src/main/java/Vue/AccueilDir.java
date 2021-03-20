package Vue;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import controleur.Controleur;
import modele.Etudiant;
import modele.Parcours;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.ScrollPane;
import java.awt.Choice;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 
 * @author Edris
 *
 */
public class AccueilDir extends JFrame {

	private JPanel contentPane;
	private JTable table_Etudiants;

	/**
	 * Affiche la vue
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccueilDir frame = new AccueilDir();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AccueilDir() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblListeEt = new JLabel("Liste des etudiants");
		lblListeEt.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblListeEt.setBounds(21, 37, 134, 23);
		contentPane.add(lblListeEt);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(true);
		scrollPane.setBounds(21, 71, 391, 248);
		contentPane.add(scrollPane);
		table_Etudiants = new JTable();

		// Selection des lignes du tableau
		table_Etudiants.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				DefaultTableModel model = (DefaultTableModel) table_Etudiants.getModel();
				int selectedRowIndex = table_Etudiants.getSelectedRow();
				VisuEt v = new VisuEt(model.getValueAt(selectedRowIndex, 0).toString(),
						model.getValueAt(selectedRowIndex, 1).toString(),
						model.getValueAt(selectedRowIndex, 2).toString());
				v.setVisible(true);
			}
		});

		// Tableau d'affichage des étudiants
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table_Etudiants);
		table_Etudiants.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_Etudiants.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Identifiant", "Nom", "Prenom", "Mention", "Parcours" }));
		table_Etudiants.setBounds(34, 84, 291, 182);
		table_Etudiants.setAutoCreateRowSorter(true);
		table_Etudiants.setRowSelectionAllowed(true);
		// parcours pour ouvrir fichier csv
		JButton btnParcourir = new JButton("Parcourir");
		btnParcourir.setBounds(438, 74, 95, 23);
		contentPane.add(btnParcourir);

		JButton btnNewButton = new JButton("Se deconnecter");

		btnNewButton.setBounds(399, 11, 134, 23);
		contentPane.add(btnNewButton);

		btnParcourir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				final JFileChooser filechooser = new JFileChooser();
				filechooser.setFileFilter(new FileNameExtensionFilter("CSV FILES", "csv"));

				int result = filechooser.showSaveDialog(null);

				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = filechooser.getSelectedFile();
					String path = selectedFile.getAbsolutePath();

					try {

						DefaultTableModel modelParc = (DefaultTableModel) table_Etudiants.getModel();
						modelParc.setRowCount(0);

						for (Etudiant a : Controleur.collectionEtuParc(path)) {
							String[] tabEtu = { String.valueOf(a.getCodeEtudiant()), a.getNom(), a.getPrenom(),
									a.getMention().getNom(), Controleur.rechercheParcours(a.getMention()).getNom() };
							modelParc.addRow(tabEtu);
						}

					} catch (Exception ex) {
						Logger.getLogger(AccueilDir.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
		});

		DefaultTableModel model = (DefaultTableModel) table_Etudiants.getModel();

		for (Etudiant e : Controleur.collectionEtudiant()) {
			String[] tabEtu = { String.valueOf(e.getCodeEtudiant()), e.getNom(), e.getPrenom(),
					e.getMention().getNom(), Controleur.rechercheParcours(e.getMention()).getNom() };
			model.addRow(tabEtu);
		}

	}

}
