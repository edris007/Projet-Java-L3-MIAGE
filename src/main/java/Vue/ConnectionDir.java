package Vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ConnectionDir extends JFrame {

	private JPanel contentPane;
	private JTextField textField_MDP;
	private JTextField textField_ID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnectionDir frame = new ConnectionDir();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 *///fermer fenetre
	public void fermer() {
		WindowEvent fermer = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(fermer);
	}
	public ConnectionDir() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnRetour = new JButton("retour");
		btnRetour.setBounds(93, 206, 102, 23);
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fermer();
				JavaProjet j = new JavaProjet();
				j.setVisible(true);
			}
		});
		
		contentPane.add(btnRetour);
		contentPane.setLayout(null);
				
		JButton btnSeConnecter = new JButton("Se connecter");
		//Ouverture page d'accueil directeur
		btnSeConnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fermer();
				AccueilDir a=new AccueilDir();
				a.setVisible(true);
			}
		});
		btnSeConnecter.setBounds(228, 206, 110, 23);
		contentPane.add(btnSeConnecter);
		
		JLabel lblID = new JLabel("Identifiant :");
		lblID.setBounds(93, 54, 78, 14);
		contentPane.add(lblID);
		
		JLabel lblMDP = new JLabel("Mot de passe :");
		lblMDP.setBounds(93, 119, 78, 14);
		contentPane.add(lblMDP);
		
		textField_MDP = new JTextField();
		textField_MDP.setBounds(93, 144, 123, 20);
		contentPane.add(textField_MDP);
		textField_MDP.setColumns(10);
		
		textField_ID = new JTextField();
		textField_ID.setBounds(93, 79, 123, 20);
		contentPane.add(textField_ID);
		textField_ID.setColumns(10);
	}
}
