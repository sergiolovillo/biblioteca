package biblioteca;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class TablaRealizarPrestamo extends JFrame implements WindowListener, ActionListener {

	private JPanel contentPane;
	private JTextField txtApellido1;
	private JButton btnBuscar;
	private JFrame ventanaPadre;
	private Connection conn;
	private PanelJtableRealizarPrestamo tabla;
	private JScrollPane scrollPane;
	private String sql, apellido1;
	private Statement stmt;
	private ResultSet rset;

	/**
	 * Create the frame.
	 */
	
	public TablaRealizarPrestamo() {
		initComponents();
	}
	
	public TablaRealizarPrestamo(JFrame ventanaPadre, Connection conn, String sql) {
		this.ventanaPadre=ventanaPadre;
		this.conn=conn;
		this.sql=sql;
		initComponents();
		
	}
	
	public void initComponents() {
		setTitle("Realizar préstamo");
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\images\\iconolibro.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 338);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtApellido1 = new JTextField();
		txtApellido1.setHorizontalAlignment(SwingConstants.LEFT);
		txtApellido1.setEditable(false);
		txtApellido1.setBounds(10, 11, 287, 20);
		txtApellido1.addActionListener(this);
		contentPane.add(txtApellido1);
		txtApellido1.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(307, 10, 117, 23);
		contentPane.add(btnBuscar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 291, 414, -247);
		contentPane.add(scrollPane);
		
		tabla=new PanelJtableRealizarPrestamo(conn, sql);
		tabla.setBounds(10, 42, 414, 246);
		contentPane.add(tabla);
		
		addWindowListener(this);
	}

	public JTextField getTxtPrimaryKey() {
		return txtApellido1;
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
		
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		if(a.getSource().equals(txtApellido1)){
			apellido1="Select apellido1 from usuario where apellido1 like '"+txtApellido1.getText()+"'";
			try {
				stmt=conn.createStatement();
				rset=stmt.executeQuery(apellido1);
				rset.next();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		
	}

}
