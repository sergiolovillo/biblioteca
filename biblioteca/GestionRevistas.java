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

public class GestionRevistas extends VentanaOriginalBiblio{
	private JTextField txtCodigo, txtSignatura, txtNombre, txtMateria;
	private JLabel lblCodigo, lblSignatura, lblNombre, lblMateria;
	private Statement stmt;
	private ResultSet rset;
	private int totalRegistros;
	
	public GestionRevistas() {
		super();
		txtTotalReg.setEditable(false);
		txtTotalReg.setEnabled(true);
		setTitle("Gestión de revistas");
		
		lblCodigo = new JLabel("Código:");
		lblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodigo.setBounds(47, 11, 89, 14);
		getContentPane().add(lblCodigo);
		
		lblSignatura = new JLabel("Signatura:");
		lblSignatura.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignatura.setBounds(287, 11, 89, 14);
		getContentPane().add(lblSignatura);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(47, 72, 89, 14);
		getContentPane().add(lblNombre);
		
		lblMateria = new JLabel("Materia:");
		lblMateria.setHorizontalAlignment(SwingConstants.CENTER);
		lblMateria.setBounds(287, 72, 89, 14);
		getContentPane().add(lblMateria);
		
		txtCodigo = new JTextField();
		txtCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodigo.setBounds(50, 36, 86, 20);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);
		txtCodigo.setEditable(false);
		
		txtSignatura = new JTextField();
		txtSignatura.setHorizontalAlignment(SwingConstants.CENTER);
		txtSignatura.setColumns(10);
		txtSignatura.setBounds(287, 36, 86, 20);
		getContentPane().add(txtSignatura);
		txtSignatura.setEditable(false);
		
		txtNombre = new JTextField();
		txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombre.setBounds(50, 97, 86, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		txtNombre.setEditable(false);
		
		txtMateria = new JTextField();
		txtMateria.setHorizontalAlignment(SwingConstants.CENTER);
		txtMateria.setBounds(287, 97, 86, 20);
		getContentPane().add(txtMateria);
		txtMateria.setColumns(10);
		txtMateria.setEditable(false);
	}

	public GestionRevistas(JFrame ventanaPadre, Connection conn) {
		super(ventanaPadre, conn);
		txtTotalReg.setEditable(false);
		txtTotalReg.setEnabled(true);
		setTitle("Gestión de revistas");
		
		lblCodigo = new JLabel("Código:");
		lblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodigo.setBounds(47, 11, 89, 14);
		getContentPane().add(lblCodigo);
		
		lblSignatura = new JLabel("Signatura:");
		lblSignatura.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignatura.setBounds(287, 11, 89, 14);
		getContentPane().add(lblSignatura);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(47, 72, 89, 14);
		getContentPane().add(lblNombre);
		
		lblMateria = new JLabel("Materia:");
		lblMateria.setHorizontalAlignment(SwingConstants.CENTER);
		lblMateria.setBounds(287, 72, 89, 14);
		getContentPane().add(lblMateria);
		
		txtCodigo = new JTextField();
		txtCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodigo.setBounds(50, 36, 86, 20);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);
		txtCodigo.setEditable(false);
		
		txtSignatura = new JTextField();
		txtSignatura.setHorizontalAlignment(SwingConstants.CENTER);
		txtSignatura.setColumns(10);
		txtSignatura.setBounds(287, 36, 86, 20);
		getContentPane().add(txtSignatura);
		txtSignatura.setEditable(false);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(50, 97, 86, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		txtNombre.setEditable(false);
		
		txtMateria = new JTextField();
		txtMateria.setBounds(287, 97, 86, 20);
		getContentPane().add(txtMateria);
		txtMateria.setColumns(10);
		txtMateria.setEditable(false);
	
	}
	
	private void cargarDatos(){
		String sql="Select codrevista, signatura, nombre, materia from revista";
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
		String sql="Select count(*) from revista";
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
		txtCodigo.setText(""+rset.getInt("codrevista"));
		txtSignatura.setText(rset.getString("signatura"));
		txtNombre.setText(rset.getString("nombre"));
		txtMateria.setText(rset.getString("materia"));
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
		txtNombre.setEditable(activo);
		txtMateria.setEditable(activo);
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
	public void actionPerformed(ActionEvent a) {
		if(a.getSource().equals(btnPrim)){
			try {
				rset.first();
				mostrarDatos();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			return;
		}
		
		if(a.getSource().equals(btnAnt)){
			try {
				rset.previous();
				mostrarDatos();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			return;		
		}
		
		if(a.getSource().equals(btnSig)){
			try {
				rset.next();
				mostrarDatos();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			return;
		}
		
		if(a.getSource().equals(btnUlt)){
			try {
				rset.last();
				mostrarDatos();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			return;
		}
		
		if(a.getSource().equals(btnInsertar)){
			if(btnInsertar.getText().equals("Insertar")){
				setEditableText(true);
				setEnableBotones(false);
				btnInsertar.setEnabled(true);
				btnInsertar.setText("Guardar");
				txtCodigo.setText("");
				txtSignatura.setText("");
				txtNombre.setText("");
				txtMateria.setText("");
			}else{
				String sql="Insert into revista values("+txtCodigo.getText()+", '"+txtSignatura.getText()+"', '"+
				txtNombre.getText()+"', '"+txtMateria.getText()+"')";
				try {
					rset.close();
					stmt.executeQuery(sql);
				} catch (SQLException e) {
					if(e.getErrorCode()==1){
						JOptionPane.showMessageDialog(null, "Ya existe una revista con código "+txtCodigo.getText()+".");
					}else{
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
				setEditableText(false);
				setEnableBotones(true);
				btnInsertar.setText("Insertar");
				cargarDatos();
			}
			return;
		}
		
		if(a.getSource().equals(btnModificar)){
			if(btnModificar.getText().equals("Modificar")){
				setEditableText(true);
				txtCodigo.setEditable(false);
				setEnableBotones(false);
				btnModificar.setEnabled(true);
				btnModificar.setText("Guardar");
				txtTotalReg.setText("Modificando");
			}else{
				String sql="Update revista set codrevista="+txtCodigo.getText()+", signatura='"+txtSignatura.getText()+"', nombre='"+txtNombre.getText()+
				"', materia='"+txtMateria.getText()+"' where codrevista="+txtCodigo.getText();	
				try {
					rset.close();
					int ret=stmt.executeUpdate(sql);
					if(ret>0){
						JOptionPane.showMessageDialog(null, "La revista con código "+txtCodigo.getText()+" se ha modificado correctamente.");
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				setEditableText(false);
				setEnableBotones(true);
				btnModificar.setText("Modificar");
				cargarDatos();
			}
			return;
		}
		
		if(a.getSource().equals(btnBorrar)){
			int ret=JOptionPane.showConfirmDialog(null, "Está seguro de que desea borrar la revista "+txtCodigo.getText(), "Borrar revista", JOptionPane.YES_NO_OPTION);
			if(ret==0){
				String sql="Delete from revista where codrevista="+txtCodigo.getText();
				try {
					rset.close();
					ret=stmt.executeUpdate(sql);
					if(ret>0){
						JOptionPane.showMessageDialog(null, "La revista con código "+txtCodigo.getText()+" se ha borrado correctamente.");
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				cargarDatos();
			}
			return;
		}
		super.actionPerformed(a);
	}
	
	@Override
	public void windowOpened(WindowEvent arg0) {
		cargarDatos();
		super.windowOpened(arg0);
	}
}
