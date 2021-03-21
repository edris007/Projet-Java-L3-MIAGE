package Vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import controleur.Controleur;
import modele.Etudiant;
import modele.UE;
import modele.UeValide;

public class VisuEt extends JFrame {

	private JPanel contentPane;
	private JTable tableUEVal;
	private JTable tableUESuivis;
	private JTable tableUEDispo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisuEt frame = new VisuEt("", "", "");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void initComponents() {

	}

	/**
	 * Create the frame.
	 */
	public VisuEt(String id, String nom, String prenom) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 420, 632);
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

		JLabel lblResID = new JLabel("");
		lblResID.setBounds(114, 50, 79, 31);
		contentPane.add(lblResID);
		lblResID.setText(id);

		JLabel lblResNom = new JLabel("");
		lblResNom.setBounds(114, 83, 62, 14);
		contentPane.add(lblResNom);
		lblResNom.setText(nom);

		JLabel lblResPrenom = new JLabel("");
		lblResPrenom.setBounds(114, 108, 62, 14);
		contentPane.add(lblResPrenom);
		lblResPrenom.setText(prenom);

		JLabel lblInfoEt = new JLabel("Informations de l'\u00E9tudiant :");
		lblInfoEt.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInfoEt.setBounds(34, 25, 171, 22);
		contentPane.add(lblInfoEt);

		JLabel lblListeUE = new JLabel("UE valid\u00E9s : ");
		lblListeUE.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblListeUE.setBounds(34, 150, 79, 14);
		contentPane.add(lblListeUE);

		JLabel lblNewLabel = new JLabel("UE actuellement suivis :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(34, 295, 159, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("UE disponibles :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(34, 451, 109, 14);
		contentPane.add(lblNewLabel_1);

		JScrollPane spUEVal = new JScrollPane();
		spUEVal.setBounds(34, 168, 245, 106);
		contentPane.add(spUEVal);

		tableUEVal = new JTable();
		tableUEVal.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Code", "Nom" }));

		spUEVal.setViewportView(tableUEVal);

		DefaultTableModel model = (DefaultTableModel) tableUEVal.getModel();

		for (UeValide e : Controleur.collectionUEValide(Integer.valueOf(lblResID.getText()))) {
			if (e.isValide()) {
				String[] tabEtu = { e.getUe().getCode(), e.getUe().getNom() };
				model.addRow(tabEtu);
			}
		}

		JScrollPane spUESuivis = new JScrollPane();
		spUESuivis.setBounds(34, 320, 245, 106);
		contentPane.add(spUESuivis);

		tableUESuivis = new JTable();
		tableUESuivis.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Code", "Nom" }));
		spUESuivis.setViewportView(tableUESuivis);

		DefaultTableModel model2 = (DefaultTableModel) tableUESuivis.getModel();
		for (UeValide e : Controleur.collectionUEValide(Integer.valueOf(lblResID.getText()))) {
			if (e.isEncours()) {
				String[] tabEtu = { e.getUe().getCode(), e.getUe().getNom() };
				model2.addRow(tabEtu);
			}
		}

		JScrollPane spUEDispo = new JScrollPane();
		spUEDispo.setBounds(34, 476, 245, 106);
		contentPane.add(spUEDispo);

		tableUEDispo = new JTable();
		tableUEDispo.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Mention", "Parcours", "Code", "Nom" }));
		spUEDispo.setViewportView(tableUEDispo);

		DefaultTableModel model3 = (DefaultTableModel) tableUEDispo.getModel();

		for (UE b : Controleur.collectionUE()) {
			if (Controleur.uePossible(b, Controleur.collectionUEValide(Integer.valueOf(lblResID.getText())))) {
				String[] tabEtu = { null, null, b.getCode(), b.getNom() };
				model3.addRow(tabEtu);
			}
		}

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fermer();
				AccueilDir a = new AccueilDir();
				a.setVisible(true);
			}
		});
		btnRetour.setBounds(315, 559, 79, 23);
		contentPane.add(btnRetour);

		JButton btnNewButton = new JButton("Se deconnecter");
		btnNewButton.setBounds(260, 11, 134, 23);
		contentPane.add(btnNewButton);
	}

	// fermer fenetre
	public void fermer() {
		WindowEvent fermer = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(fermer);
	}

}
