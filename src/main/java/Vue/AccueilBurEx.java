package Vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controleur.Controleur;
import modele.Etudiant;
import modele.UE;

import javax.swing.JLabel;
import java.awt.Font;

public class AccueilBurEx extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccueilBurEx frame = new AccueilBurEx();
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
	 */
	public AccueilBurEx() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 754, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JButton btnRetour = new JButton("Retour");
						
		btnRetour.setBounds(606, 283, 85, 21);
		contentPane.add(btnRetour);
		
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fermer();
				JavaProjet j = new JavaProjet();
				j.setVisible(true);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 58, 525, 242);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"UE", "Identifiant", "Ects"
			}
		));
		scrollPane.setViewportView(table);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int selectedRowIndex = table.getSelectedRow();
				VisuUE v = new VisuUE(model.getValueAt(selectedRowIndex, 1).toString(),
						model.getValueAt(selectedRowIndex, 2).toString(),
						model.getValueAt(selectedRowIndex, 0).toString());
				v.setVisible(true);
			}
		});
		
		JLabel lblNewLabel = new JLabel("Liste des UE");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(266, 10, 134, 23);
		contentPane.add(lblNewLabel);
		
		// parcours pour ouvrir fichier csv
				JButton btnParcourir = new JButton("Parcourir");
				btnParcourir.setBounds(579, 58, 134, 23);
				contentPane.add(btnParcourir);

				btnParcourir.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						final JFileChooser filechooser = new JFileChooser();
						filechooser.setFileFilter(new FileNameExtensionFilter("CSV FILES", "csv"));

						int result = filechooser.showSaveDialog(null);

						if (result == JFileChooser.APPROVE_OPTION) {
							File selectedFile = filechooser.getSelectedFile();
							String path = selectedFile.getAbsolutePath();

							try {

								DefaultTableModel modelParc = (DefaultTableModel) table.getModel();
								modelParc.setRowCount(0);

								for (UE a : Controleur.collectionUEParc(path)) {
									String[] tabEtu = { String.valueOf(a.getNom()), a.getCode(), String.valueOf(a.getEcts()) };
									modelParc.addRow(tabEtu);
								}

							} catch (Exception ex) {
								Logger.getLogger(AccueilDir.class.getName()).log(Level.SEVERE, null, ex);
							}
						}
					}
				});
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();

				for (UE e : Controleur.collectionUE()) {
					String[] tabEtu = { String.valueOf(e.getNom()), e.getCode(), String.valueOf(e.getEcts()) };
					model.addRow(tabEtu);
				}

	}
}
