package Vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controleur.Controleur;
import modele.Etudiant;
import modele.UeValide;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VisuUE extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisuUE frame = new VisuUE("","","");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void fermer() {
		WindowEvent fermer = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(fermer);
	}

	/**
	 * Create the frame.
	 * @param ects 
	 * @param nom 
	 * @param code 
	 */
	public VisuUE(String code, String ects, String nom) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 467, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUECode = new JLabel("Code UE :");
		lblUECode.setBounds(34, 58, 70, 14);
		contentPane.add(lblUECode);

		JLabel lblUENom = new JLabel("Nom :");
		lblUENom.setBounds(34, 83, 46, 14);
		contentPane.add(lblUENom);

		JLabel lblUEEcts = new JLabel("Ects:");
		lblUEEcts.setBounds(34, 108, 70, 14);
		contentPane.add(lblUEEcts);

		JLabel lblResCode = new JLabel("");
		lblResCode.setBounds(114, 50, 79, 31);
		contentPane.add(lblResCode);
		lblResCode.setText(code);

		JLabel lblResNom = new JLabel("");
		lblResNom.setBounds(114, 83, 166, 14);
		contentPane.add(lblResNom);
		lblResNom.setText(nom);

		JLabel lblResEcts = new JLabel("");
		lblResEcts.setBounds(114, 108, 86, 14);
		contentPane.add(lblResEcts);
		lblResEcts.setText(ects);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 158, 366, 90);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Identifiant", "Nom", "Prenom"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Retour");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fermer();
				AccueilBurEx j = new AccueilBurEx();
				j.setVisible(true);
			}
		});
		btnNewButton.setBounds(315, 263, 85, 21);
		contentPane.add(btnNewButton);
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		for (Etudiant e : Controleur.collectionUEEtudiant(lblResCode.getText())) {
			
				String[] tabEtu = { String.valueOf(e.getCodeEtudiant()), e.getNom(), e.getPrenom() };
				model.addRow(tabEtu);
			
		}

	}
}
