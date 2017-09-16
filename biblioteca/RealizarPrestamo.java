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

public class RealizarPrestamo extends JFrame implements ItemListener, WindowListener, ActionListener{

	private JPanel contentPane;
	private JLabel lblUsuario, lblTipo, lblCodigo, lblPres;
	private JTextField txtUsuario, txtCodigo;
	private JButton btnBuscarUsuario, btnCodigo, btnAceptar;
	private Choice choice;
	private JFrame ventanaPadre;
	private Connection conn;
	private Statement stmt;
	private ResultSet rset;
	private JDateChooser dateChooser1;
	
	public RealizarPrestamo() {
		InitComponents();
	}
	
	public RealizarPrestamo(JFrame ventanaPadre, Connection conn) {
		InitComponents();
		this.ventanaPadre=ventanaPadre;
		this.conn=conn;
	}

	/**
	 * Create the frame.
	 */
	
	public void InitComponents() {
		setTitle("Realizar préstamo");
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\images\\iconolibro.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 321, 300);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsuario.setBounds(10, 11, 73, 14);
		contentPane.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setEditable(false);
		txtUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		txtUsuario.setBounds(10, 36, 197, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		btnBuscarUsuario = new JButton("...");
		btnBuscarUsuario.addActionListener(this);
		btnBuscarUsuario.setBounds(223, 35, 73, 23);
		contentPane.add(btnBuscarUsuario);
		
		lblTipo = new JLabel("Tipo:");
		lblTipo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTipo.setBounds(10, 67, 46, 14);
		contentPane.add(lblTipo);
		
		choice = new Choice();
		choice.setEnabled(false);
		choice.setBounds(10, 87, 139, 20);
		contentPane.add(choice);
		
		lblCodigo = new JLabel("Código:");
		lblCodigo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCodigo.setBounds(10, 113, 46, 14);
		contentPane.add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setHorizontalAlignment(SwingConstants.LEFT);
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(10, 138, 197, 20);
		contentPane.add(txtCodigo);
		
		btnCodigo = new JButton("...");
		btnCodigo.setEnabled(false);
		btnCodigo.addActionListener(this);
		btnCodigo.setBounds(223, 137, 73, 23);
		contentPane.add(btnCodigo);
		
		lblPres = new JLabel("Fecha préstamo:");
		lblPres.setHorizontalAlignment(SwingConstants.LEFT);
		lblPres.setBounds(10, 169, 99, 14);
		contentPane.add(lblPres);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setEnabled(false);
		btnAceptar.addActionListener(this);
		btnAceptar.setBounds(109, 224, 89, 23);
		contentPane.add(btnAceptar);
		
		dateChooser1 = new JDateChooser();
		dateChooser1.getCalendarButton().setEnabled(false);
		dateChooser1.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		dateChooser1.setBounds(10, 194, 95, 20);
		contentPane.add(dateChooser1);
		
		JLabel lblDev = new JLabel("Fecha devolución:");
		lblDev.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDev.setBounds(207, 169, 89, 14);
		contentPane.add(lblDev);
		
		JDateChooser dateChooser2 = new JDateChooser();
		dateChooser2.getCalendarButton().setEnabled(false);
		dateChooser2.setBounds(207, 194, 95, 20);
		contentPane.add(dateChooser2);
		
		choice.add("Elija una opción");
		choice.add("Libro");
		choice.add("CD-ROM");
		choice.add("Revista");
		choice.add("Artículo");
		choice.addItemListener(this);
		
		addWindowListener(this);
	}
	
	public void nuevoPrestamo(){
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
	public void itemStateChanged(ItemEvent i) {
		if(choice.getSelectedItem().equals("Libro")){
			String sql="Select isbn, signatura, titulo, autor, materia, editorial from libro order by isbn";
			new TablaRealizarPrestamo(this, conn, sql);
			btnCodigo.setEnabled(true);
		}
		
		if(choice.getSelectedItem().equals("CD-ROM")){
			String sql="Select codcdrom, signatura, titulo, autor, materia, editorial from cdrom order by codcdrom";
			new TablaRealizarPrestamo(this, conn, sql);
			btnCodigo.setEnabled(true);
		}
		
		if(choice.getSelectedItem().equals("Revista")){
			String sql="Select codrevista, signatura, nombre, materia from revista order by codrevista";
			new TablaRealizarPrestamo(this, conn, sql);
			btnCodigo.setEnabled(true);
		}
		
		if(choice.getSelectedItem().equals("Artículo")){
			String sql="Select codarticulo, titulo, autor, numPaginas from articulo order by codarticulo";
			new TablaRealizarPrestamo(this, conn, sql);
			btnCodigo.setEnabled(true);
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
		nuevoPrestamo();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnBuscarUsuario)){
			String sql="Select codusuario, nombre, apellido1, apellido2 from usuario order by codusuario";
			new TablaRealizarPrestamo(this, conn, sql);
		}
		
		if(e.getSource().equals(btnCodigo)){
			
		}
		
		if(e.getSource().equals(btnAceptar)){
			
		}
	}
	
	public void setEnableElementos(){
		if(!txtUsuario.equals("")){
			choice.setEnabled(true);
		}
		if(!choice.getSelectedItem().equals("Elija una opción:")){
			btnCodigo.setEnabled(true);
		}
		if(!txtCodigo.equals("")){
			dateChooser1.setEnabled(true);
			btnAceptar.setEnabled(true);
		}
	}
}
