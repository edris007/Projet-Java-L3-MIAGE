package Vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import modele.main;
public class JavaProjet extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldPath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaProjet frame = new JavaProjet();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//fermer fenetre
	public void fermer() {
		WindowEvent fermer = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(fermer);
	}
	/**
	 * Create the frame.
	 */
	public JavaProjet() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 558, 287);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnDirEt = new JButton("Directeur d'\u00E9tude");
		btnDirEt.setBounds(10, 102, 151, 44);
		btnDirEt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fermer();
				ConnectionDir d=new ConnectionDir();
				d.setVisible(true);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnDirEt);

		JButton btnSecPed = new JButton("Secretariat p\u00E9dagogique");
		btnSecPed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fermer();
				AccueilSecPed d=new AccueilSecPed();
				d.setVisible(true);
			}
		});
		btnSecPed.setBounds(173, 102, 174, 44);
		contentPane.add(btnSecPed);

		JButton btnBurEx = new JButton("Bureau des examens");
		btnBurEx.setBounds(360, 102, 172, 44);
		contentPane.add(btnBurEx);
		btnBurEx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fermer();
				AccueilBurEx d=new AccueilBurEx();
				d.setVisible(true);
			}
		});

		JLabel lblSeCo = new JLabel("Se connecter en tant que :");
		lblSeCo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSeCo.setBounds(173, 23, 214, 44);
		contentPane.add(lblSeCo);

		textFieldPath = new JTextField();

		textFieldPath.setBounds(10, 198, 96, 19);
		contentPane.add(textFieldPath);
		textFieldPath.setColumns(10);

		JButton btnNChoisir = new JButton("Choisir");
		btnNChoisir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.path=textFieldPath.getText();
			}
		});
		btnNChoisir.setBounds(119, 197, 85, 21);
		contentPane.add(btnNChoisir);
	}
}
