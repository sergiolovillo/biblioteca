package biblioteca;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Choice;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class ConsultarPrestamo extends JFrame implements WindowListener, ActionListener{

	private JPanel contentPane;
	private JLabel lblUsuario, lblTipo, lblCodigo, lblPrestamo, lblDevolucion;
	private JTextField txtUsuario, txtCodigo;
	private JButton btnAceptar;
	private Choice choice;
	private JFrame ventanaPadre;
	private Connection conn;
	private Statement stmt;
	private ResultSet rset;
	private JDateChooser dateChooserPrestamo, dateChooserDevolucion;
	
	public ConsultarPrestamo() {
		InitComponents();
	}
	
	public ConsultarPrestamo(JFrame ventanaPadre, Connection conn) {
		InitComponents();
		this.ventanaPadre=ventanaPadre;
		this.conn=conn;
	}

	/**
	 * Create the frame.
	 */
	
	public void InitComponents() {
		setTitle("Consultar préstamo");	
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\images\\iconolibro.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 323, 343);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(135, 11, 46, 14);
		lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(60, 36, 197, 20);
		txtUsuario.setEditable(false);
		txtUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(144, 67, 29, 14);
		lblTipo.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(lblTipo);
		
		choice = new Choice();
		choice.setBounds(89, 87, 139, 20);
		choice.setEnabled(false);
		contentPane.add(choice);
		
		lblCodigo = new JLabel("Código:");
		lblCodigo.setBounds(135, 113, 46, 14);
		lblCodigo.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(60, 138, 197, 20);
		txtCodigo.setEditable(false);
		txtCodigo.setHorizontalAlignment(SwingConstants.LEFT);
		txtCodigo.setColumns(10);
		contentPane.add(txtCodigo);
		
		lblDevolucion = new JLabel("Fecha devolución:");
		lblDevolucion.setBounds(108, 225, 99, 14);
		lblDevolucion.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblDevolucion);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(113, 281, 89, 23);
		btnAceptar.setEnabled(false);
		btnAceptar.addActionListener(this);
		contentPane.add(btnAceptar);
		
		dateChooserPrestamo = new JDateChooser();
		dateChooserPrestamo.setBounds(110, 250, 95, 20);
		contentPane.add(dateChooserPrestamo);
		
		lblPrestamo = new JLabel("Fecha préstamo:");
		lblPrestamo.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrestamo.setBounds(108, 169, 99, 14);
		contentPane.add(lblPrestamo);
		
		dateChooserDevolucion = new JDateChooser();
		dateChooserDevolucion.getCalendarButton().setEnabled(false);
		dateChooserDevolucion.setBounds(110, 194, 95, 20);
		contentPane.add(dateChooserDevolucion);
		
		choice.add("Elija una opción");
		choice.add("Libro");
		choice.add("CD-ROM");
		choice.add("Revista");
		choice.add("Artículo");
		
		addWindowListener(this);
	}
	
	public void prestamos(){
		String sql="Select count(*) from prestamo";
		try {
			stmt=conn.createStatement();
			rset=stmt.executeQuery(sql);
			rset.next();
			int ret=rset.getInt(1);
			if(ret==0){
				JOptionPane.showMessageDialog(null, "No hay préstamos en la base de datos de la biblioteca.");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		ventanaPadre.setEnabled(true);
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		ventanaPadre.setEnabled(false);
		prestamos();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnAceptar)){
			
		}
	}

}
