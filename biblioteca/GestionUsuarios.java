package biblioteca;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class GestionUsuarios extends VentanaOriginalBiblio {
	private JTextField txtCodUsuario, txtNombre, txtPrimApellido, txtSegApellido;
	private JLabel lblCodigoDeUsuario, lblNombre, lblPrimerApellido, lblSegundoApellido; 
	private Statement stmt;
	private ResultSet rset;
	private int totalRegistros;
	
	public GestionUsuarios() {
		super();
		txtTotalReg.setEnabled(true);
		txtTotalReg.setEditable(false);
		setTitle("Gestión de usuarios");
		
		lblCodigoDeUsuario = new JLabel("Código de usuario:");
		lblCodigoDeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodigoDeUsuario.setBounds(40, 27, 120, 14);
		getContentPane().add(lblCodigoDeUsuario);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(281, 27, 92, 14);
		getContentPane().add(lblNombre);
		
		lblPrimerApellido = new JLabel("Primer apellido:");
		lblPrimerApellido.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrimerApellido.setBounds(47, 81, 113, 14);
		getContentPane().add(lblPrimerApellido);
		
		lblSegundoApellido = new JLabel("Segundo apellido:");
		lblSegundoApellido.setHorizontalAlignment(SwingConstants.CENTER);
		lblSegundoApellido.setBounds(269, 81, 113, 14);
		getContentPane().add(lblSegundoApellido);
		
		txtCodUsuario = new JTextField();
		txtCodUsuario.setEditable(false);
		txtCodUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodUsuario.setBounds(54, 52, 92, 20);
		getContentPane().add(txtCodUsuario);
		txtCodUsuario.setColumns(10);
		txtCodUsuario.setEditable(false);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombre.setColumns(10);
		txtNombre.setBounds(281, 52, 92, 20);
		getContentPane().add(txtNombre);
		txtNombre.setEditable(false);
		
		txtPrimApellido = new JTextField();
		txtPrimApellido.setEditable(false);
		txtPrimApellido.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrimApellido.setColumns(10);
		txtPrimApellido.setBounds(54, 106, 92, 20);
		getContentPane().add(txtPrimApellido);
		txtPrimApellido.setEditable(false);
		
		txtSegApellido = new JTextField();
		txtSegApellido.setEditable(false);
		txtSegApellido.setHorizontalAlignment(SwingConstants.CENTER);
		txtSegApellido.setColumns(10);
		txtSegApellido.setBounds(281, 106, 92, 20);
		getContentPane().add(txtSegApellido);
		txtSegApellido.setEditable(false);
	}
	
	public GestionUsuarios(JFrame ventanaPadre, Connection conn) {
		super(ventanaPadre, conn);
		txtTotalReg.setEnabled(true);
		txtTotalReg.setEditable(false);
		setTitle("Gestión de usuarios");
		
		lblCodigoDeUsuario = new JLabel("Código de usuario:");
		lblCodigoDeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodigoDeUsuario.setBounds(40, 27, 120, 14);
		getContentPane().add(lblCodigoDeUsuario);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(281, 27, 92, 14);
		getContentPane().add(lblNombre);
		
		lblPrimerApellido = new JLabel("Primer apellido:");
		lblPrimerApellido.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrimerApellido.setBounds(47, 81, 113, 14);
		getContentPane().add(lblPrimerApellido);
		
		lblSegundoApellido = new JLabel("Segundo apellido:");
		lblSegundoApellido.setHorizontalAlignment(SwingConstants.CENTER);
		lblSegundoApellido.setBounds(269, 81, 113, 14);
		getContentPane().add(lblSegundoApellido);
		
		txtCodUsuario = new JTextField();
		txtCodUsuario.setEditable(false);
		txtCodUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodUsuario.setBounds(54, 52, 92, 20);
		getContentPane().add(txtCodUsuario);
		txtCodUsuario.setColumns(10);
		txtCodUsuario.setEditable(false);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombre.setColumns(10);
		txtNombre.setBounds(281, 52, 92, 20);
		getContentPane().add(txtNombre);
		txtNombre.setEditable(false);
		
		txtPrimApellido = new JTextField();
		txtPrimApellido.setEditable(false);
		txtPrimApellido.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrimApellido.setColumns(10);
		txtPrimApellido.setBounds(54, 106, 92, 20);
		getContentPane().add(txtPrimApellido);
		txtPrimApellido.setEditable(false);
		
		txtSegApellido = new JTextField();
		txtSegApellido.setEditable(false);
		txtSegApellido.setHorizontalAlignment(SwingConstants.CENTER);
		txtSegApellido.setColumns(10);
		txtSegApellido.setBounds(281, 106, 92, 20);
		getContentPane().add(txtSegApellido);
		txtSegApellido.setEditable(false);
	}
	
	private void cargarDatos(){
		String sql="Select codusuario, nombre, apellido1, apellido2 from usuario";
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
		String sql="Select count(*) from usuario";
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
		txtCodUsuario.setText(""+rset.getInt("codusuario"));
		txtNombre.setText(rset.getString("nombre"));
		txtPrimApellido.setText(rset.getString("apellido1"));
		txtSegApellido.setText(rset.getString("apellido2"));
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
		txtCodUsuario.setEditable(activo);
		txtNombre.setEditable(activo);
		txtPrimApellido.setEditable(activo);
		txtSegApellido.setEditable(activo);
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
				txtCodUsuario.setText("");
				txtNombre.setText("");
				txtPrimApellido.setText("");
				txtSegApellido.setText("");
			}else{
				String sql="Insert into usuario values("+txtCodUsuario.getText()+", '"+txtNombre.getText()+"', '"+
				txtPrimApellido.getText()+"', '"+txtSegApellido.getText()+"')";
				try {
					rset.close();
					stmt.executeQuery(sql);
				} catch (SQLException e1) {
					if(e1.getErrorCode()==1){
						JOptionPane.showMessageDialog(null, "Ya existe un usuario con código "+txtCodUsuario.getText());
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
				txtCodUsuario.setEditable(false);
				txtTotalReg.setText("Modificando");
			}else{
				String sql="Update usuario set codusuario="+txtCodUsuario.getText()+", nombre='"+txtNombre.getText()+
				"', apellido1='"+txtPrimApellido.getText()+"', apellido2='"+txtSegApellido.getText()+"' where codusuario="+txtCodUsuario.getText();
				try {
					rset.close();
					int ret=stmt.executeUpdate(sql);
					if(ret>0){
						JOptionPane.showMessageDialog(null, "El usuario "+txtCodUsuario.getText()+" ha sido modificado correctamente.");
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
			int ret=JOptionPane.showConfirmDialog(null, "Está seguro de que desea borrar el usuario" + txtCodUsuario.getText(), "Borrar usuario", JOptionPane.YES_NO_OPTION);
			if(ret==0){
				String sql="Delete from usuario where codusuario="+txtCodUsuario.getText();
				try {
					rset.close();
					ret=stmt.executeUpdate(sql);
					if(ret>0){
						JOptionPane.showMessageDialog(null, "El usuario "+txtCodUsuario.getText()+" se ha borrado correctamente.");
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
