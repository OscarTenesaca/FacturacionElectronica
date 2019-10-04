package vista;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.EmpleadoBD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtusuario;
	private JPasswordField txtcontrasena;
	EmpleadoBD EBD= new EmpleadoBD();

	/**
	 * Launch the application.
	 */
	
			public void run() {
				try {
					Login frame = new Login();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		
		setBounds(100, 100, 548, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("USUARIO:");
		lblUsuario.setBounds(213, 56, 107, 33);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("CONTRASENA:");
		lblContrasea.setBounds(213, 117, 107, 33);
		contentPane.add(lblContrasea);
		
		txtusuario = new JTextField();
		txtusuario.setBounds(341, 56, 174, 33);
		contentPane.add(txtusuario);
		txtusuario.setColumns(10);
		
		
		txtcontrasena = new JPasswordField();
		txtcontrasena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				login();
			
			
			}
			
			
			
		});
		txtcontrasena.setBounds(341, 117, 174, 33);
		contentPane.add(txtcontrasena);
		 
		JButton btnEntrar = new JButton("ENTRAR");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
				
			}
		});
		btnEntrar.setBounds(398, 172, 117, 38);
		contentPane.add(btnEntrar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Iconos\\imgSeguridad.png"));
		lblNewLabel.setBounds(22, 29, 179, 179);
		contentPane.add(lblNewLabel);
		
		 	
	      
		
	}

	protected void login() {
		// TODO Auto-generated method stub
		if(EBD.SelectUser(txtusuario.getText(), String.valueOf(txtcontrasena.getPassword()))== true){
			int g;
			
			Menu menu= new Menu();
			
			menu.setVisible(true);}
			else {
				System.out.println("no existe usuario y pass");
			}
	}

}
