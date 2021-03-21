package Vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import controleur.Controleur;
import modele.UE;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import javax.swing.JSeparator;
import javax.swing.Box;
import java.awt.Component;

public class VisuEtSecPed extends JFrame {

	private JPanel contentPane;
	private JTable tableUE;
	DefaultTableModel dm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisuEtSecPed frame = new VisuEtSecPed("", "", "");
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
	public VisuEtSecPed(String id, String nom, String prenom) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 505, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblETID = new JLabel("Identifiant :");
		lblETID.setBounds(34, 58, 70, 14);
		contentPane.add(lblETID);

		JLabel lblETNom = new JLabel("Nom :");
		lblETNom.setBounds(34, 83, 46, 14);
		contentPane.add(lblETNom);

		JLabel lblETPrenom = new JLabel("Pr\u00E9nom :");
		lblETPrenom.setBounds(34, 108, 70, 14);
		contentPane.add(lblETPrenom);

		JLabel lblResID = new JLabel("Identifiant :");
		lblResID.setBounds(114, 50, 79, 31);
		contentPane.add(lblResID);
		lblResID.setText(id);

		JLabel lblResNom = new JLabel("New label");
		lblResNom.setBounds(114, 83, 62, 14);
		contentPane.add(lblResNom);
		lblResNom.setText(nom);

		JLabel lblResPrenom = new JLabel("New label");
		lblResPrenom.setBounds(114, 108, 62, 14);
		contentPane.add(lblResPrenom);
		lblResPrenom.setText(prenom);

		JLabel lblInfoEt = new JLabel("Informations de l'\u00E9tudiant :");
		lblInfoEt.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInfoEt.setBounds(10, 11, 171, 22);
		contentPane.add(lblInfoEt);

		JLabel lblListeUE = new JLabel("Liste des UE disponibles :");
		lblListeUE.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblListeUE.setBounds(10, 148, 159, 22);
		contentPane.add(lblListeUE);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 181, 305, 150);
		contentPane.add(scrollPane);

		tableUE = new JTable();
		tableUE.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Mention", "Parcours", "Code", "Nom", "Cr\u00E9dits" }));
		scrollPane.setViewportView(tableUE);
		tableUE.setAutoCreateRowSorter(true);
		tableUE.setRowSelectionAllowed(true);

		DefaultTableModel model = (DefaultTableModel) tableUE.getModel();

		for (UE b : Controleur.collectionUE()) {
			if (Controleur.uePossible(b, Controleur.collectionUEValide(Integer.valueOf(lblResID.getText())))) {
				String[] tabEtu = { b.getCode(), b.getNom() };
				model.addRow(tabEtu);
			}
		}

		final JComboBox comboBox = new JComboBox();

		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String query = comboBox.getSelectedItem().toString();
				filter(query);
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "None", "MIAGE", "MECANIQUE", "ART", "INFORMATIQUE", "RESEAU" }));
		comboBox.setBounds(392, 250, 79, 22);
		contentPane.add(comboBox);

		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String query = comboBox_1.getSelectedItem().toString();
				filter(query);
			}
		});
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "None", "MIASHS" }));
		comboBox_1.setBounds(392, 211, 79, 22);
		contentPane.add(comboBox_1);

		JLabel lblNewLabel = new JLabel("Parcours :");
		lblNewLabel.setBounds(325, 254, 70, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Mention :");
		lblNewLabel_1.setBounds(325, 215, 57, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Filtres ");
		lblNewLabel_2.setBounds(325, 181, 46, 14);
		contentPane.add(lblNewLabel_2);

		JButton btnNewButton = new JButton("Retour");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fermer();
				AccueilSecPed j = new AccueilSecPed();
				j.setVisible(true);
			}
		});
		btnNewButton.setBounds(392, 308, 79, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Se deconnecter");
		btnNewButton_1.setBounds(345, 12, 134, 23);
		contentPane.add(btnNewButton_1);
	}

	public void filter(String query) {
		dm = (DefaultTableModel) tableUE.getModel();
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(dm);
		tableUE.setRowSorter(tr);

		if (query != "None") {
			tr.setRowFilter(RowFilter.regexFilter(query));
		} else {
			tableUE.setRowSorter(tr);
		}
	}

	// fermer fenetre
	public void fermer() {
		WindowEvent fermer = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(fermer);
	}
}
