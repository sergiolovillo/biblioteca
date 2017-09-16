package biblioteca;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class VentanaOriginalBiblio extends JFrame implements WindowListener, ActionListener{

	private JPanel contentPane;
	protected JButton btnPrim, btnAnt, btnSig, btnUlt, btnInsertar, btnModificar, btnBorrar;
	protected JTextField txtTotalReg;
	protected JFrame ventanaPadre;
	protected Connection conn;

	/**
	 * Create the frame.
	 */
	
	public VentanaOriginalBiblio() {
		initComponents();
	}
	
	public VentanaOriginalBiblio(JFrame ventanaPadre, Connection conn) {
		this.ventanaPadre=ventanaPadre;
		this.conn=conn;
		initComponents();
	}
	
	private void initComponents() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\images\\iconolibro.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setVisible(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnInsertar = new JButton("Insertar");
		btnInsertar.setBounds(47, 197, 89, 23);
		contentPane.add(btnInsertar);
		btnInsertar.addActionListener(this);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(175, 197, 89, 23);
		contentPane.add(btnModificar);
		btnModificar.addActionListener(this);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(302, 197, 89, 23);
		contentPane.add(btnBorrar);
		btnBorrar.addActionListener(this);
		
		btnPrim = new JButton("<<");
		btnPrim.setBounds(24, 151, 72, 23);
		contentPane.add(btnPrim);
		btnPrim.addActionListener(this);
		
		btnAnt = new JButton("<");
		btnAnt.setBounds(111, 151, 72, 23);
		contentPane.add(btnAnt);
		btnAnt.addActionListener(this);
		
		btnSig = new JButton(">");
		btnSig.setBounds(249, 151, 72, 23);
		contentPane.add(btnSig);
		btnSig.addActionListener(this);
		
		btnUlt = new JButton(">>");
		btnUlt.setBounds(339, 151, 72, 23);
		contentPane.add(btnUlt);
		btnUlt.addActionListener(this);
		
		txtTotalReg = new JTextField();
		txtTotalReg.setEnabled(false);
		txtTotalReg.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotalReg.setBounds(187, 152, 60, 20);
		contentPane.add(txtTotalReg);
		txtTotalReg.setColumns(10);
		
		addWindowListener(this);
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		setResizable(false);
		
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
	public void actionPerformed(ActionEvent arg0) {
		// TODO Apéndice de método generado automáticamente
		
	}

}
