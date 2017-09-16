package biblioteca;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class PrincipalBiblio extends JFrame implements WindowListener, ActionListener{

	private JPanel contentPane;
	private JFrame ventanaPadre;
	private Connection conn;
	private JMenuItem mntmListadoUsuarios, mntmListadoLibros, mntmListadoCdroms, mntmListadoRevistas, mntmListadoArticulos,
	mntmListadoPrestamosPendientes, mntmListadoPrestamosUsuario;
	private int prestamos, prestamosPendientes;
	private Statement stmt;
	private ResultSet rset;

	/**
	 * Create the frame.
	 */
	
	public PrincipalBiblio() {
		initComponents();
	}
	
	public PrincipalBiblio(JFrame ventanaPadre, Connection conn) {
		this.ventanaPadre=ventanaPadre;
		ventanaPadre.dispose();
		this.conn=conn;
		initComponents();
	}	
	
	public void initComponents() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\images\\iconolibro.png"));
		ventanaPadre=this;
		setTitle("Biblioteca");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnGestion = new JMenu("Gestión");
		menuBar.add(mnGestion);
		
		JMenuItem mntmGestionUsuarios = new JMenuItem("Usuarios");
		mntmGestionUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GestionUsuarios(ventanaPadre, conn);
			}
		});
		mnGestion.add(mntmGestionUsuarios);
		
		JMenuItem mntmGestionLibros = new JMenuItem("Libros");
		mntmGestionLibros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GestionLibros(ventanaPadre, conn);
			}
		});
		mnGestion.add(mntmGestionLibros);
		
		JMenuItem mntmGestionCdroms = new JMenuItem("CD-ROMs");
		mntmGestionCdroms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GestionCdroms(ventanaPadre, conn);
			}
		});
		mnGestion.add(mntmGestionCdroms);
		
		JMenuItem mntmGestionRevistas = new JMenuItem("Revistas");
		mntmGestionRevistas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GestionRevistas(ventanaPadre, conn);
			}
		});
		mnGestion.add(mntmGestionRevistas);
		
		JMenuItem mntmGestionArticulos = new JMenuItem("Artículos");
		mntmGestionArticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GestionArticulos(ventanaPadre, conn);
			}
		});
		mnGestion.add(mntmGestionArticulos);
		
		JSeparator separator = new JSeparator();
		mnGestion.add(separator);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desconectar();
				System.exit(0);
			}
		});
		mnGestion.add(mntmSalir);
		
		JMenu mnPrestamos = new JMenu("Préstamos");
		menuBar.add(mnPrestamos);
		
		JMenuItem mntmRealizarPrestamo = new JMenuItem("Realizar préstamo");
		mntmRealizarPrestamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RealizarPrestamo(ventanaPadre, conn);
			}
		});
		mnPrestamos.add(mntmRealizarPrestamo);
		
		JMenuItem mntmConsultarPrestamos = new JMenuItem("Consultar préstamos");
		mntmConsultarPrestamos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql="Select codusuario, codmaterial, tipomaterial, fecha_prestamo, fecha_devolucion from prestamo";
				new TablaConsultarPrestamo(ventanaPadre, conn, sql);
			}
		});
		mnPrestamos.add(mntmConsultarPrestamos);
		
		JMenu mnListados = new JMenu("Listados");
		menuBar.add(mnListados);
		
		mntmListadoUsuarios = new JMenuItem("Usuarios");
		mnListados.add(mntmListadoUsuarios);
		mntmListadoUsuarios.addActionListener(this);
		
		mntmListadoLibros = new JMenuItem("Libros");
		mntmListadoLibros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generarListadoLibros();
			}
		});
		mnListados.add(mntmListadoLibros);
		
		mntmListadoCdroms = new JMenuItem("CD-ROMs");
		mntmListadoCdroms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generarListadoCdroms();
			}
		});
		mnListados.add(mntmListadoCdroms);
		
		mntmListadoRevistas = new JMenuItem("Revistas");
		mntmListadoRevistas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generarListadoRevistas();
			}
		});
		mnListados.add(mntmListadoRevistas);
		
		mntmListadoArticulos = new JMenuItem("Artículos");
		mntmListadoArticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generarListadoArticulos();
			}
		});
		mnListados.add(mntmListadoArticulos);
		
		mntmListadoPrestamosPendientes = new JMenuItem("Préstamos pendientes devolución");
		mntmListadoPrestamosPendientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generarListadoPrestamosPendientes();
			}
		});
		mnListados.add(mntmListadoPrestamosPendientes);
		
		mntmListadoPrestamosUsuario = new JMenuItem("Préstamos por usuario");
		mntmListadoPrestamosUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generarListadoPrestamosUsuario();
			}
		});
		mnListados.add(mntmListadoPrestamosUsuario);
		
		JMenu mnAcercaDe = new JMenu("?");
		menuBar.add(mnAcercaDe);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AcercaDe(ventanaPadre, conn);
			}
		});
		mnAcercaDe.add(mntmAcercaDe);
		
		contentPane = new FondoBiblio();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		addWindowListener(this);
		setVisible(true);
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
		desconectar();
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
		// TODO Apéndice de método generado automáticamente
		
	}
	
	private void desconectar(){
		try {
			conn.close();
		} catch (SQLException e) {
			
		}
	}
	
	private void generarListadoUsuarios(){
		try {
			JFileChooser chooser = new JFileChooser(); 
			chooser.showOpenDialog(this);
			File file = chooser.getSelectedFile();
			if(file!=null){
				generarPdfUsuarios(file);
				Desktop.getDesktop().open(file);
			}
		}catch(IOException ex) {
			JOptionPane.showMessageDialog(null, "No se encuentra el fichero creado");
		}
	}

	private void generarPdfUsuarios(File file) {
		Document documento = new Document();
		FileOutputStream ficheroPdf;
		try {
			ficheroPdf = new FileOutputStream(file);
			PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);
			documento.open();
			documento.add(new Paragraph("Listado de usuarios ",
							FontFactory.getFont("arial",   
							18,                            
							Font.ITALIC,                   
							BaseColor.CYAN)));
			documento.add(new Paragraph(" "));
			try{
				Statement stmt=conn.createStatement();
				String sql="select * from usuario order by codusuario";
				ResultSet rset=stmt.executeQuery(sql);
				ResultSetMetaData metaDatos = rset.getMetaData();
				int numeroColumnas = metaDatos.getColumnCount();
				PdfPTable tabla = new PdfPTable(numeroColumnas);
				for(int i=1;i<=numeroColumnas;i++){
					tabla.addCell(metaDatos.getColumnLabel(i));
				}
				while (rset.next())
				{
				   for (int i=1;i<=numeroColumnas;i++){
				      tabla.addCell(""+rset.getObject(i)); 
				   }
				}
				documento.add(tabla);
			}catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			documento.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "La ruta introducida no es válida");
		} catch (DocumentException e) {
			JOptionPane.showMessageDialog(null, "Error al crear el listado");
		}
	}
	
	private void generarListadoLibros(){
		try {
			JFileChooser chooser = new JFileChooser(); 
			chooser.showOpenDialog(this);
			File file = chooser.getSelectedFile();
			if(file!=null){
				generarPdfLibros(file);
				Desktop.getDesktop().open(file);
			}
		}catch(IOException ex) {
			JOptionPane.showMessageDialog(null, "No se encuentra el fichero creado");
		}
	}

	private void generarPdfLibros(File file) {
		Document documento = new Document();
		FileOutputStream ficheroPdf;
		try {
			ficheroPdf = new FileOutputStream(file);
			PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);
			documento.open();
			documento.add(new Paragraph("Listado de libros ",
							FontFactory.getFont("arial",   
							18,                            
							Font.ITALIC,                   
							BaseColor.CYAN)));
			documento.add(new Paragraph(" "));
			try{
				Statement stmt=conn.createStatement();
				String sql="select * from libro order by isbn";
				ResultSet rset=stmt.executeQuery(sql);
				ResultSetMetaData metaDatos = rset.getMetaData();
				int numeroColumnas = metaDatos.getColumnCount();
				PdfPTable tabla = new PdfPTable(numeroColumnas);
				for(int i=1;i<=numeroColumnas;i++){
					tabla.addCell(metaDatos.getColumnLabel(i));
				}
				while (rset.next())
				{
				   for (int i=1;i<=numeroColumnas;i++){
				      tabla.addCell(""+rset.getObject(i)); 
				   }
				}
				documento.add(tabla);
			}catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			documento.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "La ruta introducida no es válida");
		} catch (DocumentException e) {
			JOptionPane.showMessageDialog(null, "Error al crear el listado");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(mntmListadoUsuarios)){
			generarListadoUsuarios();
			return;
		}
		
		if(e.getSource().equals(mntmListadoLibros)){
			generarListadoLibros();
			return;
		}
	}
	
	private void generarListadoCdroms(){
		try {
			JFileChooser chooser = new JFileChooser(); 
			chooser.showOpenDialog(this);
			File file = chooser.getSelectedFile();
			if(file!=null){
				generarPdfCdroms(file);
				Desktop.getDesktop().open(file);
			}
		}catch(IOException ex) {
			JOptionPane.showMessageDialog(null, "No se encuentra el fichero creado");
		}
	}

	private void generarPdfCdroms(File file) {
		Document documento = new Document();
		FileOutputStream ficheroPdf;
		try {
			ficheroPdf = new FileOutputStream(file);
			PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);
			documento.open();
			documento.add(new Paragraph("Listado de CD-ROMS ",
							FontFactory.getFont("arial",   
							18,                            
							Font.ITALIC,                   
							BaseColor.CYAN)));
			documento.add(new Paragraph(" "));
			try{
				Statement stmt=conn.createStatement();
				String sql="select * from cdrom order by codcdrom";
				ResultSet rset=stmt.executeQuery(sql);
				ResultSetMetaData metaDatos = rset.getMetaData();
				int numeroColumnas = metaDatos.getColumnCount();
				PdfPTable tabla = new PdfPTable(numeroColumnas);
				for(int i=1;i<=numeroColumnas;i++){
					tabla.addCell(metaDatos.getColumnLabel(i));
				}
				while (rset.next())
				{
				   for (int i=1;i<=numeroColumnas;i++){
				      tabla.addCell(""+rset.getObject(i)); 
				   }
				}
				documento.add(tabla);
			}catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			documento.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "La ruta introducida no es válida");
		} catch (DocumentException e) {
			JOptionPane.showMessageDialog(null, "Error al crear el listado");
		}
	}
	
	private void generarListadoRevistas(){
		try {
			JFileChooser chooser = new JFileChooser(); 
			chooser.showOpenDialog(this);
			File file = chooser.getSelectedFile();
			if(file!=null){
				generarPdfRevistas(file);
				Desktop.getDesktop().open(file);
			}
		}catch(IOException ex) {
			JOptionPane.showMessageDialog(null, "No se encuentra el fichero creado");
		}
	}

	private void generarPdfRevistas(File file) {
		Document documento = new Document();
		FileOutputStream ficheroPdf;
		try {
			ficheroPdf = new FileOutputStream(file);
			PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);
			documento.open();
			documento.add(new Paragraph("Listado de revistas ",
							FontFactory.getFont("arial",   
							18,                            
							Font.ITALIC,                   
							BaseColor.CYAN)));
			documento.add(new Paragraph(" "));
			try{
				Statement stmt=conn.createStatement();
				String sql="select * from revista order by codrevista";
				ResultSet rset=stmt.executeQuery(sql);
				ResultSetMetaData metaDatos = rset.getMetaData();
				int numeroColumnas = metaDatos.getColumnCount();
				PdfPTable tabla = new PdfPTable(numeroColumnas);
				for(int i=1;i<=numeroColumnas;i++){
					tabla.addCell(metaDatos.getColumnLabel(i));
				}
				while (rset.next())
				{
				   for (int i=1;i<=numeroColumnas;i++){
				      tabla.addCell(""+rset.getObject(i)); 
				   }
				}
				documento.add(tabla);
			}catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			documento.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "La ruta introducida no es válida");
		} catch (DocumentException e) {
			JOptionPane.showMessageDialog(null, "Error al crear el listado");
		}
	}
	
	
	private void generarListadoArticulos(){
		try {
			JFileChooser chooser = new JFileChooser(); 
			chooser.showOpenDialog(this);
			File file = chooser.getSelectedFile();
			if(file!=null){
				generarPdfArticulos(file);
				Desktop.getDesktop().open(file);
			}
		}catch(IOException ex) {
			JOptionPane.showMessageDialog(null, "No se encuentra el fichero creado");
		}
	}

	private void generarPdfArticulos(File file) {
		Document documento = new Document();
		FileOutputStream ficheroPdf;
		try {
			ficheroPdf = new FileOutputStream(file);
			PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);
			documento.open();
			documento.add(new Paragraph("Listado de artículos ",
							FontFactory.getFont("arial",   
							18,                            
							Font.ITALIC,                   
							BaseColor.CYAN)));
			documento.add(new Paragraph(" "));
			try{
				Statement stmt=conn.createStatement();
				String sql="select * from articulo order by codarticulo";
				ResultSet rset=stmt.executeQuery(sql);
				ResultSetMetaData metaDatos = rset.getMetaData();
				int numeroColumnas = metaDatos.getColumnCount();
				PdfPTable tabla = new PdfPTable(numeroColumnas);
				for(int i=1;i<=numeroColumnas;i++){
					tabla.addCell(metaDatos.getColumnLabel(i));
				}
				while (rset.next())
				{
				   for (int i=1;i<=numeroColumnas;i++){
				      tabla.addCell(""+rset.getObject(i)); 
				   }
				}
				documento.add(tabla);
			}catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			documento.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "La ruta introducida no es válida");
		} catch (DocumentException e) {
			JOptionPane.showMessageDialog(null, "Error al crear el listado");
		}
	}
	
	private void generarListadoPrestamosPendientes(){
		try {
			JFileChooser chooser = new JFileChooser(); 
			chooser.showOpenDialog(this);
			File file = chooser.getSelectedFile();
			if(file!=null){
				totalPrestamos();
				totalPrestamosPendientes();
				generarPdfPrestamosPendientes(file);
				Desktop.getDesktop().open(file);
			}
		}catch(IOException ex) {
			JOptionPane.showMessageDialog(null, "No se encuentra el fichero creado");
		}
	}
	
	private void generarPdfPrestamosPendientes(File file) {
		Document documento = new Document();
		FileOutputStream ficheroPdf;
		try {
			ficheroPdf = new FileOutputStream(file);
			PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);
			documento.open();
			documento.add(new Paragraph("Listado de préstamos pendientes ",
							FontFactory.getFont("arial",   
							18,                            
							Font.ITALIC,                   
							BaseColor.CYAN)));
			documento.add(new Paragraph(" "));
			try{
				Statement stmt=conn.createStatement();
				String sql="select * from prestamo where fecha_devolucion is null order by codusuario";
				ResultSet rset=stmt.executeQuery(sql);
				ResultSetMetaData metaDatos = rset.getMetaData();
				int numeroColumnas = metaDatos.getColumnCount();
				PdfPTable tabla = new PdfPTable(numeroColumnas);
				for(int i=1;i<=numeroColumnas;i++){
					tabla.addCell(metaDatos.getColumnLabel(i));
				}
				while (rset.next())
				{
				   for (int i=1;i<=numeroColumnas;i++){
				      tabla.addCell(""+rset.getObject(i)); 
				   }
				}
				documento.add(tabla);
				documento.add(new Paragraph("Total de préstamos: "+prestamos));
				documento.add(new Paragraph("Total de préstamos pendientes: "+prestamosPendientes));
			}catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			documento.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "La ruta introducida no es válida");
		} catch (DocumentException e) {
			JOptionPane.showMessageDialog(null, "Error al crear el listado");
		}
	}
	
	private void generarListadoPrestamosUsuario(){
		try {
			JFileChooser chooser = new JFileChooser(); 
			chooser.showOpenDialog(this);
			File file = chooser.getSelectedFile();
			if(file!=null){
				totalPrestamos();
				totalPrestamosPendientes();
				generarPdfPrestamosUsuario(file);
				Desktop.getDesktop().open(file);
			}
		}catch(IOException ex) {
			JOptionPane.showMessageDialog(null, "No se encuentra el fichero creado");
		}
	}
	
	private void generarPdfPrestamosUsuario(File file) {
		Document documento = new Document();
		FileOutputStream ficheroPdf;
		try {
			ficheroPdf = new FileOutputStream(file);
			PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);
			documento.open();
			documento.add(new Paragraph("Listado de préstamos de usuario ",
							FontFactory.getFont("arial",   
							18,                            
							Font.ITALIC,                   
							BaseColor.CYAN)));
			documento.add(new Paragraph(" "));
			try{
				Statement stmt=conn.createStatement();
				String sql="select * from prestamo order by codusuario";
				ResultSet rset=stmt.executeQuery(sql);
				ResultSetMetaData metaDatos = rset.getMetaData();
				int numeroColumnas = metaDatos.getColumnCount();
				PdfPTable tabla = new PdfPTable(numeroColumnas);
				for(int i=1;i<=numeroColumnas;i++){
					tabla.addCell(metaDatos.getColumnLabel(i));
				}
				while (rset.next())
				{
				   for (int i=1;i<=numeroColumnas;i++){
				      tabla.addCell(""+rset.getObject(i)); 
				   }
				}
				documento.add(tabla);
				documento.add(new Paragraph("Total de préstamos: "+prestamos));
				documento.add(new Paragraph("Total de préstamos pendientes: "+prestamosPendientes));
			}catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			documento.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "La ruta introducida no es válida");
		} catch (DocumentException e) {
			JOptionPane.showMessageDialog(null, "Error al crear el listado");
		}
	}
	
	private void totalPrestamos(){
		String sql="Select count(*) from prestamo";
		try {
			stmt=conn.createStatement();
			rset=stmt.executeQuery(sql);
			rset.next();
			prestamos=rset.getInt(1);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	private void totalPrestamosPendientes(){
		String sql="Select count(*) from prestamo where fecha_devolucion is null";
		try {
			stmt=conn.createStatement();
			rset=stmt.executeQuery(sql);
			rset.next();
			prestamosPendientes=rset.getInt(1);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
