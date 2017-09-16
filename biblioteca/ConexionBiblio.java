package biblioteca;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class ConexionBiblio extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JLabel lblUsuario, lblPassword;
	private JTextField txtUsuario;
	private JPasswordField passwordField;
	private JButton btnAceptar, btnCancelar;
	private int intentos=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConexionBiblio frame = new ConexionBiblio();
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
	
	public ConexionBiblio() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\images\\iconolibro.png"));
		setResizable(false);
		setTitle("Conexión");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 332, 223);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setBounds(81, 55, 69, 14);
		contentPane.add(lblUsuario);
		
		lblPassword = new JLabel("Contraseña:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(81, 80, 69, 14);
		contentPane.add(lblPassword);
		
		txtUsuario = new JTextField("java");
		txtUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		txtUsuario.setBounds(160, 52, 86, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		passwordField = new JPasswordField("java");
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		passwordField.setBounds(160, 77, 86, 20);
		contentPane.add(passwordField);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(61, 142, 89, 23);
		contentPane.add(btnAceptar);
		btnAceptar.addActionListener(this);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(157, 142, 89, 23);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		if(a.getSource().equals(btnAceptar)){
			Connection conn=conectar();
			if(conn!=null){
				new PrincipalBiblio(this, conn);
			}
			return;
		}
		if(a.getSource().equals(btnCancelar)){
			System.exit(0);
		}
	}
	
	private Connection conectar(){
		Connection conn;
		intentos++;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", txtUsuario.getText(), new String(passwordField.getPassword()));
			return conn;
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "No se encuentra el driver de la base de datos.");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()+" Le quedan "+(3-intentos)+" intentos.");
		}
		if(intentos>2){
			JOptionPane.showMessageDialog(null, "Ha superado el número de intentos.");
			System.exit(0);
		}
		return null;
	}
}
