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

public class GestionLibros extends VentanaOriginalBiblio{
	private JTextField txtIsbn, txtSignatura, txtTitulo, txtAutor, txtMateria, txtEditorial;
	private JLabel lblIsbn, lblSignatura, lblTitulo, lblAutor, lblMateria, lblEditorial;
	private Statement stmt;
	private ResultSet rset;
	private int totalRegistros;
	
	public GestionLibros() {
		super();
		txtTotalReg.setEnabled(true);
		txtTotalReg.setEditable(false);
		setTitle("Gestión de libros");
		
		lblIsbn = new JLabel("ISBN:");
		lblIsbn.setHorizontalAlignment(SwingConstants.CENTER);
		lblIsbn.setBounds(39, 22, 46, 14);
		getContentPane().add(lblIsbn);
		
		lblSignatura = new JLabel("Signatura:");
		lblSignatura.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignatura.setBounds(185, 22, 65, 14);
		getContentPane().add(lblSignatura);
		
		lblTitulo = new JLabel("Título:");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(340, 22, 65, 14);
		getContentPane().add(lblTitulo);
		
		lblAutor = new JLabel("Autor:");
		lblAutor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutor.setBounds(27, 82, 65, 14);
		getContentPane().add(lblAutor);
		
		lblMateria = new JLabel("Materia:");
		lblMateria.setHorizontalAlignment(SwingConstants.CENTER);
		lblMateria.setBounds(185, 82, 65, 14);
		getContentPane().add(lblMateria);
		
		lblEditorial = new JLabel("Editorial:");
		lblEditorial.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditorial.setBounds(340, 82, 65, 14);
		getContentPane().add(lblEditorial);
		
		txtIsbn = new JTextField();
		txtIsbn.setHorizontalAlignment(SwingConstants.CENTER);
		txtIsbn.setBounds(20, 51, 86, 20);
		getContentPane().add(txtIsbn);
		txtIsbn.setColumns(10);
		txtIsbn.setEditable(false);
		
		txtSignatura = new JTextField();
		txtSignatura.setHorizontalAlignment(SwingConstants.CENTER);
		txtSignatura.setBounds(173, 51, 86, 20);
		getContentPane().add(txtSignatura);
		txtSignatura.setColumns(10);
		txtSignatura.setEditable(false);
		
		txtTitulo = new JTextField();
		txtTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		txtTitulo.setBounds(326, 51, 86, 20);
		getContentPane().add(txtTitulo);
		txtTitulo.setColumns(10);
		txtTitulo.setEditable(false);
		
		txtAutor = new JTextField();
		txtAutor.setHorizontalAlignment(SwingConstants.CENTER);
		txtAutor.setBounds(20, 107, 86, 20);
		getContentPane().add(txtAutor);
		txtAutor.setColumns(10);
		txtAutor.setEditable(false);
		
		txtMateria = new JTextField();
		txtMateria.setHorizontalAlignment(SwingConstants.CENTER);
		txtMateria.setBounds(173, 107, 86, 20);
		getContentPane().add(txtMateria);
		txtMateria.setColumns(10);
		txtMateria.setEditable(false);
		
		txtEditorial = new JTextField();
		txtEditorial.setHorizontalAlignment(SwingConstants.CENTER);
		txtEditorial.setBounds(326, 107, 86, 20);
		getContentPane().add(txtEditorial);
		txtEditorial.setColumns(10);
		txtEditorial.setEditable(false);
	}
	
	public GestionLibros(JFrame ventanaPadre, Connection conn) {
		super(ventanaPadre, conn);
		txtTotalReg.setEnabled(true);
		txtTotalReg.setEditable(false);
		setTitle("Gestión de libros");
		
		lblIsbn = new JLabel("ISBN:");
		lblIsbn.setHorizontalAlignment(SwingConstants.CENTER);
		lblIsbn.setBounds(39, 22, 46, 14);
		getContentPane().add(lblIsbn);
		
		lblSignatura = new JLabel("Signatura:");
		lblSignatura.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignatura.setBounds(185, 22, 65, 14);
		getContentPane().add(lblSignatura);
		
		lblTitulo = new JLabel("Título:");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(340, 22, 65, 14);
		getContentPane().add(lblTitulo);
		
		lblAutor = new JLabel("Autor:");
		lblAutor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutor.setBounds(27, 82, 65, 14);
		getContentPane().add(lblAutor);
		
		lblMateria = new JLabel("Material:");
		lblMateria.setHorizontalAlignment(SwingConstants.CENTER);
		lblMateria.setBounds(185, 82, 65, 14);
		getContentPane().add(lblMateria);
		
		lblEditorial = new JLabel("Editorial:");
		lblEditorial.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditorial.setBounds(340, 82, 65, 14);
		getContentPane().add(lblEditorial);
		
		txtIsbn = new JTextField();
		txtIsbn.setHorizontalAlignment(SwingConstants.CENTER);
		txtIsbn.setBounds(20, 51, 86, 20);
		getContentPane().add(txtIsbn);
		txtIsbn.setColumns(10);
		txtIsbn.setEditable(false);
		
		txtSignatura = new JTextField();
		txtSignatura.setHorizontalAlignment(SwingConstants.CENTER);
		txtSignatura.setBounds(173, 51, 86, 20);
		getContentPane().add(txtSignatura);
		txtSignatura.setColumns(10);
		txtSignatura.setEditable(false);
		
		txtTitulo = new JTextField();
		txtTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		txtTitulo.setBounds(326, 51, 86, 20);
		getContentPane().add(txtTitulo);
		txtTitulo.setColumns(10);
		txtTitulo.setEditable(false);
		
		txtAutor = new JTextField();
		txtAutor.setHorizontalAlignment(SwingConstants.CENTER);
		txtAutor.setBounds(20, 107, 86, 20);
		getContentPane().add(txtAutor);
		txtAutor.setColumns(10);
		txtAutor.setEditable(false);
		
		txtMateria = new JTextField();
		txtMateria.setHorizontalAlignment(SwingConstants.CENTER);
		txtMateria.setBounds(173, 107, 86, 20);
		getContentPane().add(txtMateria);
		txtMateria.setColumns(10);
		txtMateria.setEditable(false);
		
		txtEditorial = new JTextField();
		txtEditorial.setHorizontalAlignment(SwingConstants.CENTER);
		txtEditorial.setBounds(326, 107, 86, 20);
		getContentPane().add(txtEditorial);
		txtEditorial.setColumns(10);
		txtEditorial.setEditable(false);
	}
	
	private void cargarDatos(){
		String sql="Select isbn, signatura, titulo, autor, materia, editorial from libro";
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
		String sql="Select count(*) from libro";
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
		txtIsbn.setText(""+rset.getInt("isbn"));
		txtSignatura.setText(rset.getString("signatura"));
		txtTitulo.setText(rset.getString("titulo"));
		txtAutor.setText(rset.getString("autor"));
		txtMateria.setText(rset.getString("materia"));
		txtEditorial.setText(rset.getString("editorial"));
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
		txtSignatura.setEditable(activo);
		txtIsbn.setEditable(activo);
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
				txtIsbn.setText("");
				txtSignatura.setText("");
				txtTitulo.setText("");
				txtAutor.setText("");
				txtMateria.setText("");
				txtEditorial.setText("");
			}else{
				String sql="Insert into libro values("+txtIsbn.getText()+", '"+txtSignatura.getText()+"', '"+
				txtTitulo.getText()+"', '"+txtAutor.getText()+"', '"+txtMateria.getText()+"', '"+txtEditorial.getText()+"')";
				try {
					rset.close();
					stmt.executeQuery(sql);
				} catch (SQLException e1) {
					if(e1.getErrorCode()==1){
						JOptionPane.showMessageDialog(null, "Ya existe un libro con ISBN "+txtIsbn.getText());
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
				txtIsbn.setEditable(false);
				txtTotalReg.setText("Modificando");
			}else{
				String sql="Update libro set isbn="+txtIsbn.getText()+", signatura='"+txtSignatura.getText()+
				"', titulo='"+txtTitulo.getText()+"', autor='"+txtAutor.getText()+"', materia='"+txtMateria.getText()+
				"', editorial='"+txtEditorial.getText()+"' where isbn="+txtIsbn.getText();
				try {
					rset.close();
					int ret=stmt.executeUpdate(sql);
					if(ret>0){
						JOptionPane.showMessageDialog(null, "El libro "+txtIsbn.getText()+" ha sido modificado correctamente.");
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
			int ret=JOptionPane.showConfirmDialog(null, "Está seguro de que desea borrar el libro" + txtIsbn.getText(), "Borrar libro", JOptionPane.YES_NO_OPTION);
			if(ret==0){
				String sql="Delete from libro where isbn="+txtIsbn.getText();
				try {
					rset.close();
					ret=stmt.executeUpdate(sql);
					if(ret>0){
						JOptionPane.showMessageDialog(null, "El libro "+txtIsbn.getText()+" se ha borrado correctamente.");
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
