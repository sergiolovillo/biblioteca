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

public class GestionArticulos extends VentanaOriginalBiblio{
	private JTextField txtCodigo, txtTitulo, txtAutor, txtNumPaginas;
	private JLabel lblCodigo, lblTitulo, lblAutor, lblNumPaginas;
	private Statement stmt;
	private ResultSet rset;
	private int totalRegistros;
	
	public GestionArticulos() {
		super();
		txtTotalReg.setEnabled(true);
		txtTotalReg.setEditable(false);
		setTitle("Gestión de artículos");
		
		lblCodigo = new JLabel("Código:");
		lblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodigo.setBounds(58, 11, 89, 14);
		getContentPane().add(lblCodigo);
		
		lblTitulo = new JLabel("Título:");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(284, 11, 89, 14);
		getContentPane().add(lblTitulo);
		
		lblAutor = new JLabel("Autor:");
		lblAutor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutor.setBounds(58, 79, 89, 14);
		getContentPane().add(lblAutor);
		
		lblNumPaginas = new JLabel("Nº de páginas:");
		lblNumPaginas.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumPaginas.setBounds(284, 79, 89, 14);
		getContentPane().add(lblNumPaginas);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodigo.setBounds(58, 36, 86, 20);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);
		txtCodigo.setEditable(false);
		
		txtTitulo = new JTextField();
		txtTitulo.setEditable(false);
		txtTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		txtTitulo.setColumns(10);
		txtTitulo.setBounds(284, 36, 86, 20);
		getContentPane().add(txtTitulo);
		txtTitulo.setEditable(false);
		
		txtAutor = new JTextField();
		txtAutor.setEditable(false);
		txtAutor.setHorizontalAlignment(SwingConstants.CENTER);
		txtAutor.setColumns(10);
		txtAutor.setBounds(58, 104, 86, 20);
		getContentPane().add(txtAutor);
		txtAutor.setEditable(false);
		
		txtNumPaginas = new JTextField();
		txtNumPaginas.setEditable(false);
		txtNumPaginas.setHorizontalAlignment(SwingConstants.CENTER);
		txtNumPaginas.setColumns(10);
		txtNumPaginas.setBounds(287, 104, 86, 20);
		getContentPane().add(txtNumPaginas);
		txtNumPaginas.setEditable(false);
	}
	
	public GestionArticulos(JFrame ventanaPadre, Connection conn) {
		super(ventanaPadre, conn);
		txtTotalReg.setEnabled(true);
		txtTotalReg.setEditable(false);
		setTitle("Gestión de artículos");
		
		lblCodigo = new JLabel("Código:");
		lblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodigo.setBounds(58, 11, 89, 14);
		getContentPane().add(lblCodigo);
		
		lblTitulo = new JLabel("Título:");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(284, 11, 89, 14);
		getContentPane().add(lblTitulo);
		
		lblAutor = new JLabel("Autor:");
		lblAutor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutor.setBounds(58, 79, 89, 14);
		getContentPane().add(lblAutor);
		
		lblNumPaginas = new JLabel("Nº de páginas:");
		lblNumPaginas.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumPaginas.setBounds(284, 79, 89, 14);
		getContentPane().add(lblNumPaginas);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodigo.setBounds(58, 36, 86, 20);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);
		txtCodigo.setEditable(false);
		
		txtTitulo = new JTextField();
		txtTitulo.setEditable(false);
		txtTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		txtTitulo.setColumns(10);
		txtTitulo.setBounds(284, 36, 86, 20);
		getContentPane().add(txtTitulo);
		txtTitulo.setEditable(false);
		
		txtAutor = new JTextField();
		txtAutor.setEditable(false);
		txtAutor.setHorizontalAlignment(SwingConstants.CENTER);
		txtAutor.setColumns(10);
		txtAutor.setBounds(58, 104, 86, 20);
		getContentPane().add(txtAutor);
		txtAutor.setEditable(false);
		
		txtNumPaginas = new JTextField();
		txtNumPaginas.setEditable(false);
		txtNumPaginas.setHorizontalAlignment(SwingConstants.CENTER);
		txtNumPaginas.setColumns(10);
		txtNumPaginas.setBounds(287, 104, 86, 20);
		getContentPane().add(txtNumPaginas);
		txtNumPaginas.setEditable(false);
	}
	
	private void cargarDatos(){
		String sql="Select codarticulo, titulo, autor, numpaginas from articulo";
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
		String sql="Select count(*) from articulo";
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
		txtCodigo.setText(""+rset.getInt("codarticulo"));
		txtTitulo.setText(rset.getString("titulo"));
		txtAutor.setText(rset.getString("autor"));
		txtNumPaginas.setText(rset.getString("numpaginas"));
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
		txtTitulo.setEditable(activo);
		txtAutor.setEditable(activo);
		txtNumPaginas.setEditable(activo);
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
				txtTitulo.setText("");
				txtAutor.setText("");
				txtNumPaginas.setText("");
			}else{
				String sql="Insert into articulo values("+txtCodigo.getText()+", '"+txtTitulo.getText()+"', '"+
				txtAutor.getText()+"', '"+txtNumPaginas.getText()+"')";
				try {
					rset.close();
					stmt.executeQuery(sql);
				} catch (SQLException e1) {
					if(e1.getErrorCode()==1){
						JOptionPane.showMessageDialog(null, "Ya existe un artículo con código "+txtCodigo.getText());
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
				String sql="Update articulo set codarticulo="+txtCodigo.getText()+", titulo='"+txtTitulo.getText()+
				"', autor='"+txtAutor.getText()+"', numpaginas='"+txtNumPaginas.getText()+"' where codarticulo="+txtCodigo.getText();
				try {
					rset.close();
					int ret=stmt.executeUpdate(sql);
					if(ret>0){
						JOptionPane.showMessageDialog(null, "El artículo "+txtCodigo.getText()+" ha sido modificado correctamente.");
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
			int ret=JOptionPane.showConfirmDialog(null, "Está seguro de que desea borrar el artículo" + txtCodigo.getText(), "Borrar artículo", JOptionPane.YES_NO_OPTION);
			if(ret==0){
				String sql="Delete from articulo where codarticulo="+txtCodigo.getText();
				try {
					rset.close();
					ret=stmt.executeUpdate(sql);
					if(ret>0){
						JOptionPane.showMessageDialog(null, "El artículo "+txtCodigo.getText()+" se ha borrado correctamente.");
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
