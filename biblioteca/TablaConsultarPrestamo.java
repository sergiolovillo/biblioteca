package biblioteca;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.JRadioButton;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;

public class TablaConsultarPrestamo extends JFrame implements WindowListener, ActionListener{

	private JPanel contentPane;
	private JTextField txtPrimaryKey;
	private JFrame ventanaPadre;
	private Connection conn;
	private PanelJtableConsultarPrestamo tabla;
	private JScrollPane scrollPane;
	private String sql;
	private JRadioButton rdbtnUsuario, rdbtnTipo;
	
	/**
	 * Create the frame.
	 */
	
	public TablaConsultarPrestamo() {
		initComponents();
	}
	
	public TablaConsultarPrestamo(JFrame ventanaPadre, Connection conn, String sql) {
		this.ventanaPadre=ventanaPadre;
		this.conn=conn;
		this.sql=sql;
		initComponents();
		
	}

	public void initComponents() {
		setTitle("Consultar préstamo");
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
		
		txtPrimaryKey = new JTextField();
		txtPrimaryKey.setHorizontalAlignment(SwingConstants.LEFT);
		txtPrimaryKey.setEditable(false);
		txtPrimaryKey.setBounds(10, 11, 287, 20);
		contentPane.add(txtPrimaryKey);
		txtPrimaryKey.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 291, 414, -247);
		contentPane.add(scrollPane);
		
		tabla=new PanelJtableConsultarPrestamo(conn, sql);
		tabla.setBounds(20, 42, 414, 246);
		contentPane.add(tabla);

		
		rdbtnUsuario = new JRadioButton("Usuario");
		rdbtnUsuario.setSelected(true);
		rdbtnUsuario.setBounds(303, 10, 79, 23);
		rdbtnUsuario.addActionListener(this);
		contentPane.add(rdbtnUsuario);
		
		rdbtnTipo = new JRadioButton("Tipo");
		rdbtnTipo.setBounds(384, 10, 50, 23);
		rdbtnTipo.addActionListener(this);
		contentPane.add(rdbtnTipo);
		
		addWindowListener(this);
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		ventanaPadre.setEnabled(true);	
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		ventanaPadre.setEnabled(false);

	}

	@Override
	public void actionPerformed(ActionEvent a) {
		if(a.getSource().equals(rdbtnUsuario)){
			rdbtnTipo.setSelected(false);
		}
		
		if(a.getSource().equals(rdbtnTipo)){
			rdbtnUsuario.setSelected(false);
		}
	}
}
