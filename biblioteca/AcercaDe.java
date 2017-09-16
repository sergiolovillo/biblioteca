package biblioteca;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;

public class AcercaDe extends JFrame implements WindowListener{
	private JPanel contentPane;
	private JLabel lblAlumno, lblGrupo, lblSergioLovilloFalconde, lbldaw, lblAsignatura, lblProfesor, 
	lblProgramacin, lblAntonioBlzquerPrez, lblCurso, lblCentro, lbl20162017, lblIesPolgonoSur;
	private JFrame ventanaPadre;
	private Connection conn;

	public AcercaDe() {
		initComponents();
	}
	
	public AcercaDe(JFrame ventanaPadre, Connection conn) {
		this.ventanaPadre=ventanaPadre;
		this.conn=conn;
		initComponents();
	}
	
	private void initComponents() {
		setTitle("Acerca de");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 331, 255);
		setLocationRelativeTo(null);
		setVisible(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblAlumno = new JLabel("Alumno:");
		lblAlumno.setFont(new Font("Calibri", Font.BOLD, 12));
		lblAlumno.setBounds(10, 11, 151, 23);
		contentPane.add(lblAlumno);
		
		lblGrupo = new JLabel("Grupo:");
		lblGrupo.setFont(new Font("Calibri", Font.BOLD, 12));
		lblGrupo.setBounds(161, 11, 151, 23);
		contentPane.add(lblGrupo);
		
		lblSergioLovilloFalconde = new JLabel("Sergio Lovillo Fal-Conde");
		lblSergioLovilloFalconde.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblSergioLovilloFalconde.setBounds(10, 45, 151, 23);
		contentPane.add(lblSergioLovilloFalconde);
		
		lbldaw = new JLabel("1\u00BADAW");
		lbldaw.setFont(new Font("Calibri", Font.PLAIN, 12));
		lbldaw.setBounds(161, 45, 151, 23);
		contentPane.add(lbldaw);
		
		lblAsignatura = new JLabel("Asignatura:");
		lblAsignatura.setFont(new Font("Calibri", Font.BOLD, 12));
		lblAsignatura.setBounds(10, 79, 151, 23);
		contentPane.add(lblAsignatura);
		
		lblProfesor = new JLabel("Profesor:");
		lblProfesor.setFont(new Font("Calibri", Font.BOLD, 12));
		lblProfesor.setBounds(161, 79, 151, 23);
		contentPane.add(lblProfesor);
		
		lblProgramacin = new JLabel("Programaci\u00F3n");
		lblProgramacin.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblProgramacin.setBounds(10, 113, 151, 23);
		contentPane.add(lblProgramacin);
		
		lblAntonioBlzquerPrez = new JLabel("Antonio Bl\u00E1zquez P\u00E9rez");
		lblAntonioBlzquerPrez.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblAntonioBlzquerPrez.setBounds(161, 113, 151, 23);
		contentPane.add(lblAntonioBlzquerPrez);
		
		lblCurso = new JLabel("Curso:");
		lblCurso.setFont(new Font("Calibri", Font.BOLD, 12));
		lblCurso.setBounds(10, 147, 151, 23);
		contentPane.add(lblCurso);
		
		lblCentro = new JLabel("Centro:");
		lblCentro.setFont(new Font("Calibri", Font.BOLD, 12));
		lblCentro.setBounds(161, 147, 151, 23);
		contentPane.add(lblCentro);
		
		lbl20162017 = new JLabel("2016-2017");
		lbl20162017.setFont(new Font("Calibri", Font.PLAIN, 12));
		lbl20162017.setBounds(10, 181, 151, 23);
		contentPane.add(lbl20162017);
		
		lblIesPolgonoSur = new JLabel("I.E.S. Pol\u00EDgono Sur");
		lblIesPolgonoSur.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblIesPolgonoSur.setBounds(161, 181, 151, 23);
		contentPane.add(lblIesPolgonoSur);
		
		addWindowListener(this);
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
		
	
	}
}
