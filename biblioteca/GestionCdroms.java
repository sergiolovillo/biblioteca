package biblioteca;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class GestionCdroms extends VentanaOriginalBiblio{
	private JTextField txtCodigo, txtSignatura, txtTitulo, txtAutor, txtMateria, txtEditorial;
	private JLabel lblSignatura, lblTitulo, lblCodigo, lblAutor, lblMateria, lblEditorial;
	private Statement stmt;
	private ResultSet rset;
	private int totalRegistros;
	
	public GestionCdroms() {
		super();
		txtTotalReg.setEnabled(true);
		txtTotalReg.setEditable(false);
		setTitle("Gestión de CD-ROMs");
		
		lblSignatura = new JLabel("Signatura:");
		lblSignatura.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignatura.setBounds(177, 11, 73, 14);
		getContentPane().add(lblSignatura);
		
		lblTitulo = new JLabel("Título:");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(331, 11, 73, 14);
		getContentPane().add(lblTitulo);
		
		lblCodigo = new JLabel("Código:");
		lblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodigo.setBounds(22, 11, 73, 14);
		getContentPane().add(lblCodigo);
		
		lblAutor = new JLabel("Autor:");
		lblAutor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutor.setBounds(22, 74, 73, 14);
		getContentPane().add(lblAutor);
		
		lblMateria = new JLabel("Materia:");
		lblMateria.setHorizontalAlignment(SwingConstants.CENTER);
		lblMateria.setBounds(177, 74, 73, 14);
		getContentPane().add(lblMateria);
		
		lblEditorial = new JLabel("Editorial:");
		lblEditorial.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditorial.setBounds(331, 74, 73, 14);
		getContentPane().add(lblEditorial);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodigo.setBounds(22, 36, 86, 20);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);
		txtCodigo.setEditable(false);
		
		txtSignatura = new JTextField();
		txtSignatura.setEditable(false);
		txtSignatura.setHorizontalAlignment(SwingConstants.CENTER);
		txtSignatura.setBounds(164, 36, 86, 20);
		getContentPane().add(txtSignatura);
		txtSignatura.setColumns(10);
		txtSignatura.setEditable(false);
		
		txtTitulo = new JTextField();
		txtTitulo.setEditable(false);
		txtTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		txtTitulo.setBounds(318, 36, 86, 20);
		getContentPane().add(txtTitulo);
		txtTitulo.setColumns(10);
		txtTitulo.setEditable(false);
		
		txtAutor = new JTextField();
		txtAutor.setEditable(false);
		txtAutor.setHorizontalAlignment(SwingConstants.CENTER);
		txtAutor.setBounds(22, 99, 86, 20);
		getContentPane().add(txtAutor);
		txtAutor.setColumns(10);
		txtAutor.setEditable(false);
		
		txtMateria = new JTextField();
		txtMateria.setEditable(false);
		txtMateria.setHorizontalAlignment(SwingConstants.CENTER);
		txtMateria.setBounds(164, 99, 86, 20);
		getContentPane().add(txtMateria);
		txtMateria.setColumns(10);
		txtMateria.setEditable(false);
		
		txtEditorial = new JTextField();
		txtEditorial.setHorizontalAlignment(SwingConstants.CENTER);
		txtEditorial.setEditable(false);
		txtEditorial.setBounds(318, 99, 86, 20);
		getContentPane().add(txtEditorial);
		txtEditorial.setColumns(10);
		txtEditorial.setEditable(false);
	}

	public GestionCdroms(JFrame ventanaPadre, Connection conn) {
		super(ventanaPadre, conn);
		txtTotalReg.setEnabled(true);
		txtTotalReg.setEditable(false);
		setTitle("Gestión de CD-ROMs");
		
		lblSignatura = new JLabel("Signatura:");
		lblSignatura.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignatura.setBounds(177, 11, 73, 14);
		getContentPane().add(lblSignatura);
		
		lblTitulo = new JLabel("Título:");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(331, 11, 73, 14);
		getContentPane().add(lblTitulo);
		
		lblCodigo = new JLabel("Código:");
		lblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodigo.setBounds(22, 11, 73, 14);
		getContentPane().add(lblCodigo);
		
		lblAutor = new JLabel("Autor:");
		lblAutor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutor.setBounds(22, 74, 73, 14);
		getContentPane().add(lblAutor);
		
		lblMateria = new JLabel("Materia:");
		lblMateria.setHorizontalAlignment(SwingConstants.CENTER);
		lblMateria.setBounds(177, 74, 73, 14);
		getContentPane().add(lblMateria);
		
		lblEditorial = new JLabel("Editorial:");
		lblEditorial.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditorial.setBounds(331, 74, 73, 14);
		getContentPane().add(lblEditorial);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodigo.setBounds(22, 36, 86, 20);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);
		txtCodigo.setEditable(false);
		
		txtSignatura = new JTextField();
		txtSignatura.setEditable(false);
		txtSignatura.setHorizontalAlignment(SwingConstants.CENTER);
		txtSignatura.setBounds(164, 36, 86, 20);
		getContentPane().add(txtSignatura);
		txtSignatura.setColumns(10);
		txtSignatura.setEditable(false);
		
		txtTitulo = new JTextField();
		txtTitulo.setEditable(false);
		txtTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		txtTitulo.setBounds(318, 36, 86, 20);
		getContentPane().add(txtTitulo);
		txtTitulo.setColumns(10);
		txtTitulo.setEditable(false);
		
		txtAutor = new JTextField();
		txtAutor.setEditable(false);
		txtAutor.setHorizontalAlignment(SwingConstants.CENTER);
		txtAutor.setBounds(22, 99, 86, 20);
		getContentPane().add(txtAutor);
		txtAutor.setColumns(10);
		txtAutor.setEditable(false);
		
		txtMateria = new JTextField();
		txtMateria.setEditable(false);
		txtMateria.setHorizontalAlignment(SwingConstants.CENTER);
		txtMateria.setBounds(164, 99, 86, 20);
		getContentPane().add(txtMateria);
		txtMateria.setColumns(10);
		txtMateria.setEditable(false);
		
		txtEditorial = new JTextField();
		txtEditorial.setHorizontalAlignment(SwingConstants.CENTER);
		txtEditorial.setEditable(false);
		txtEditorial.setBounds(318, 99, 86, 20);
		getContentPane().add(txtEditorial);
		txtEditorial.setColumns(10);
		txtEditorial.setEditable(false);
	}
	
	private void cargarDatos(){
		String sql="Select codcdrom, signatura, titulo, autor, materia, editorial from cdrom";
		try {
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rset=stmt.executeQuery(sql);
			rset.next();
			totalRegistros();
			mostrarDatos();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	private void totalRegistros() {
		String sql="Select count(*) from cdrom";
		try {
			Statement stmt=conn.createStatement();
			ResultSet rset=stmt.executeQuery(sql);
			rset.next();
			totalRegistros=rset.getInt(1);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	private void mostrarDatos() throws SQLException {
		txtCodigo.setText(""+rset.getInt("codcdrom"));
		txtSignatura.setText(rset.getNString("signatura"));
		txtTitulo.setText(rset.getNString("titulo"));
		txtAutor.setText(rset.getNString("autor"));
		txtMateria.setText(rset.getNString("materia"));
		txtEditorial.setText(rset.getNString("editorial"));
		controlarBotonesNavegacion();
		txtTotalReg.setText(rset.getRow()+"/"+totalRegistros);
	}
	
	private void controlarBotonesNavegacion() throws SQLException {
		btnPrim.setEnabled(true);
		btnAnt.setEnabled(true);
		btnSig.setEnabled(true);
		btnUlt.setEnabled(true);
		if(rset.isFirst()){
			btnPrim.setEnabled(false);
			btnAnt.setEnabled(false);
		}
		if(rset.isLast()){
			btnSig.setEnabled(false);
			btnUlt.setEnabled(false);
		}
	}
	
	private void setEditableText(boolean activo){
		txtCodigo.setEditable(activo);
		txtSignatura.setEditable(activo);
		txtTitulo.setEditable(activo);
		txtAutor.setEditable(activo);
		txtMateria.setEditable(activo);
		txtEditorial.setEditable(activo);
	}
	
	private void setEnableBotones(boolean activo){
		btnPrim.setEnabled(activo);
		btnAnt.setEnabled(activo);
		btnSig.setEnabled(activo);
		btnUlt.setEnabled(activo);
		btnInsertar.setEnabled(activo);
		btnModificar.setEnabled(activo);
		btnBorrar.setEnabled(activo);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnPrim)){
			try {
				rset.first();
				mostrarDatos();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			return;
		}
		
		if(e.getSource().equals(btnAnt)){
			try {
				rset.previous();
				mostrarDatos();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());				
			}
			return;
		}
		
		if(e.getSource().equals(btnSig)){
			try {
				rset.next();
				mostrarDatos();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			return;
		}
		
		if(e.getSource().equals(btnUlt)){
			try {
				rset.last();
				mostrarDatos();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			return;
		}

		if(e.getSource().equals(btnInsertar)){
			if(btnInsertar.getText().equals("Insertar")){
				setEditableText(true);
				setEnableBotones(false);
				btnInsertar.setEnabled(true);
				btnInsertar.setText("Guardar");
				txtCodigo.setText("");
				txtSignatura.setText("");
				txtTitulo.setText("");
				txtAutor.setText("");
				txtMateria.setText("");
				txtEditorial.setText("");
			}else{
				String sql="Insert into cdrom values("+txtCodigo.getText()+", '"+txtSignatura.getText()+"', '"+
				txtTitulo.getText()+"', '"+txtAutor.getText()+"', '"+txtMateria.getText()+"', '"+
				txtEditorial.getText()+"')";
				try {
					rset.close();
					stmt.executeQuery(sql);
				} catch (SQLException e1) {
					if(e1.getErrorCode()==1){
						JOptionPane.showMessageDialog(null, "Ya existe un CD-ROM con código "+txtCodigo.getText());
					}else{
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
				setEditableText(false);
				setEnableBotones(true);
				btnInsertar.setText("Insertar");
				cargarDatos();
			}
			return;
		}
	
		if(e.getSource().equals(btnModificar)){
			if(btnModificar.getText().equals("Modificar")){
				setEditableText(true);
				setEnableBotones(false);
				btnModificar.setEnabled(true);
				btnModificar.setText("Guardar");
				txtCodigo.setEditable(false);
				txtTotalReg.setText("Modificando");
			}else{
				String sql="Update cdrom set codcdrom="+txtCodigo.getText()+", signatura='"+txtSignatura.getText()+
				"', titulo='"+txtTitulo.getText()+"', autor='"+txtAutor.getText()+"', materia='"+txtMateria.getText()+
				"', editorial='"+txtEditorial.getText()+"' where codcdrom="+txtCodigo.getText();
				try {
					rset.close();
					int ret=stmt.executeUpdate(sql);
					if(ret>0){
						JOptionPane.showMessageDialog(null, "El CD-ROM "+txtCodigo.getText()+" ha sido modificado correctamente.");
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				setEditableText(false);
				setEnableBotones(true);
				btnModificar.setText("Modificar");
				cargarDatos();
			}
			return;
		}

		if(e.getSource().equals(btnBorrar)){
			int ret=JOptionPane.showConfirmDialog(null, "Está seguro de que desea borrar el CD-ROM" + txtCodigo.getText(), "Borrar CD-ROM", JOptionPane.YES_NO_OPTION);
			if(ret==0){
				String sql="Delete from cdrom where codcdrom="+txtCodigo.getText();
				try {
					rset.close();
					ret=stmt.executeUpdate(sql);
					if(ret>0){
						JOptionPane.showMessageDialog(null, "El CD-ROM "+txtCodigo.getText()+" se ha borrado correctamente.");
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				cargarDatos();
			}
			return;
		}
		super.actionPerformed(e);
	}
	
	@Override
	public void windowOpened(WindowEvent arg0) {
		cargarDatos();
		super.windowOpened(arg0);
	}
}
