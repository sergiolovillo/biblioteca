package biblioteca;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PanelJtableRealizarPrestamo extends JScrollPane implements MouseListener {

	private JTable tabla;
	private ModeloDatos modeloDatos=new ModeloDatos();
	private Connection conn;
	private String primaryKey;

	/**
	 * Create the panel.
	 */
	public PanelJtableRealizarPrestamo() {
		tabla = new JTable();
		tabla.setBounds(0, 0, 300, 300);
		this.setViewportView(tabla);
	}
	public PanelJtableRealizarPrestamo(Connection conn,String sql) {
		this.conn=conn;
		modeloDatos=modeloDatos(sql);
		tabla = new JTable(modeloDatos);
		tabla.setBounds(0, 0, 300, 300);
		tabla.addMouseListener(this);
		this.setViewportView(tabla);
	}
	
	public void setConnection(Connection conn){
		this.conn=conn;
	}
	
	public void setSQL(String sql){
		modeloDatos=modeloDatos(sql);
		tabla.setModel(modeloDatos);
	}
	
	private ModeloDatos modeloDatos(String sql){
		ModeloDatos modelo=new ModeloDatos();
		try{
			//Creamos las filas
			Statement stmt=conn.createStatement();
			ResultSet rset=stmt.executeQuery(sql);
			
			//Nos traemos los metadatos
			ResultSetMetaData metaDatos = rset.getMetaData();
			// Se obtiene el número de columnas.
			int numeroColumnas = metaDatos.getColumnCount();

			// Se crea un array de etiquetas para rellenar
			Object[] etiquetas = new Object[numeroColumnas];

			// Se obtiene cada una de las etiquetas para cada columna
			for (int i = 0; i < numeroColumnas; i++)
			{
			   // Nuevamente, para ResultSetMetaData la primera columna es la 1. 
			   etiquetas[i] = metaDatos.getColumnLabel(i + 1); 
			}
			
			// Creamos las columnas.
			modelo.setColumnIdentifiers(etiquetas);
			
			// Bucle para cada resultado en la consulta
			while (rset.next())
			{
			   // Se crea un array que será una de las filas de la tabla. 
			   Object [] fila = new Object[numeroColumnas]; // Hay tres columnas en la tabla
	
			   // Se rellena cada posición del array con una de las columnas de la tabla en base de datos.
			   for (int i=0;i<numeroColumnas;i++){
			      fila[i] = rset.getObject(i+1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.
			   }
			   // Se añade al modelo la fila completa.
			   modelo.addRow(fila); 
			}
			
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		return modelo;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tabla.rowAtPoint(e.getPoint());
		primaryKey=tabla.getValueAt(row, 0).toString();	
	
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Apéndice de método generado automáticamente
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Apéndice de método generado automáticamente
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Apéndice de método generado automáticamente
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Apéndice de método generado automáticamente
		
	}

}
