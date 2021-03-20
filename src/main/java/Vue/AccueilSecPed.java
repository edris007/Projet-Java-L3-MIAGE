package Vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import controleur.Controleur;
import modele.Etudiant;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import java.awt.Font;

public class AccueilSecPed extends JFrame {

	private JPanel contentPane;
	private JTable table_Etudiants;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccueilSecPed frame = new AccueilSecPed();
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
	public AccueilSecPed() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 476, 326);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(true);
		scrollPane.setBounds(20, 73, 317, 203);
		contentPane.add(scrollPane);

		table_Etudiants = new JTable();
		scrollPane.setViewportView(table_Etudiants);

		//Selection des lignes du tableau
		table_Etudiants.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				DefaultTableModel model = (DefaultTableModel)table_Etudiants.getModel();
				int selectedRowIndex=table_Etudiants.getSelectedRow();
				VisuEtSecPed v=new VisuEtSecPed(model.getValueAt(selectedRowIndex, 0).toString(),model.getValueAt(selectedRowIndex, 1).toString(),model.getValueAt(selectedRowIndex, 2).toString());
				v.setVisible(true);
			}
		});

		//Tableau d'affichage des étudiants
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table_Etudiants);
		table_Etudiants.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_Etudiants.setRowSelectionAllowed(false);
		table_Etudiants.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Identifiant", "Nom", "Prenom", "Mention", "Parcours"
				}
				));
		table_Etudiants.setBounds(34, 84, 291, 182);
		table_Etudiants.setAutoCreateRowSorter(true);
		table_Etudiants.setRowSelectionAllowed(true);
		//parcours pour ouvrir fichier csv
		JButton btnParcourir = new JButton("Parcourir");
		btnParcourir.setBounds(358, 73, 89, 23);
		contentPane.add(btnParcourir);

		JButton btnNewButton = new JButton("Retour");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fermer();
				JavaProjet j = new JavaProjet();
				j.setVisible(true);
			}
		});
		btnNewButton.setBounds(358, 253, 89, 23);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Liste des \u00E9tudiants");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(20, 39, 151, 23);
		contentPane.add(lblNewLabel);

		JButton btnNewButton_1 = new JButton("Se deconnecter");
		btnNewButton_1.setBounds(313, 11, 134, 23);
		contentPane.add(btnNewButton_1);
		btnParcourir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				final JFileChooser filechooser = new JFileChooser();
				filechooser.setFileFilter(new FileNameExtensionFilter("CSV FILES", "csv"));

				int result=filechooser.showSaveDialog(null);

				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = filechooser.getSelectedFile();
					String path = selectedFile.getAbsolutePath();
					File file = new File(path);

					try {

						DefaultTableModel modelParc = (DefaultTableModel)table_Etudiants.getModel();
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

		DefaultTableModel model = (DefaultTableModel)table_Etudiants.getModel();
		for (Etudiant e : Controleur.collectionEtudiant()) {
			String[] tabEtu = { String.valueOf(e.getCodeEtudiant()), e.getNom(), e.getPrenom(),
					e.getMention().getNom(), Controleur.rechercheParcours(e.getMention()).getNom() };
			model.addRow(tabEtu);
		}

	}
	//fermer fenetre
	public void fermer() {
		WindowEvent fermer = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(fermer);
	}
}
