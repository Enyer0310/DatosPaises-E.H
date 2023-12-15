package Controlador;

// Imports
import Modelo.Pais;
import Modelo.Ciudad;
import Modelo.Conexion;
import Modelo.Idioma;

import Vista.Ventana;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;

// Inicio de la clase
public class Controlador implements ActionListener {
    private Connection con;
    private Conexion conectar;
    private PreparedStatement ps;

    PaisOperaciones paisOp = new PaisOperaciones();
    CiudadOperacion ciudadOp = new CiudadOperacion();
    IdiomaOperacion idiomaOp = new IdiomaOperacion();
    
    Pais pais = new Pais();
    Ciudad ciudad = new Ciudad();
    Idioma idioma = new Idioma();
    
    Ventana vista = new Ventana();
    DefaultTableModel modelo = new DefaultTableModel();

    public Controlador(Ventana v) {
        this.vista = v;
        
        // ActionListener Pais
        this.vista.btnConsultarPais.addActionListener(this);
        this.vista.btnAgregarPais.addActionListener(this);
        this.vista.btnModificarPais.addActionListener(this);
        this.vista.btnEliminarPais.addActionListener(this);
        this.vista.btnActualizarPais.addActionListener(this);
        this.vista.btnLimpiarPais.addActionListener(this);
       
        // ActionListener Ciudad
        this.vista.btnConsultarCiudad.addActionListener(this);
        this.vista.btnAgregarCiudad.addActionListener(this);
        this.vista.btnModificarCiudad.addActionListener(this);
        this.vista.btnEliminarCiudad.addActionListener(this);
        this.vista.btnGuardarCiudad.addActionListener(this);
        this.vista.btnLimpiarCiudad.addActionListener(this);
        
        // ActionListener Idioma
        this.vista.btnConsultarIdiom.addActionListener(this);
        this.vista.btnAgregarIdiom.addActionListener(this);
        this.vista.btnOficial.addActionListener(this);
        this.vista.btnModificarIdiom.addActionListener(this);
        this.vista.btnEliminarIdiom.addActionListener(this);
        this.vista.btnGuardarIdiom.addActionListener(this);
        this.vista.btnLimpiarIdiom.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Sección Pais
        if (e.getSource() == vista.btnAgregarPais) {
            agregarPais();
        }
        if (e.getSource() == vista.btnModificarPais) {
          modificarPais();
        }
        if (e.getSource() == vista.btnConsultarPais) {
        }
        if (e.getSource() == vista.btnActualizarPais) {
          
        }
        if (e.getSource() == vista.btnEliminarPais) {
           eliminarPais();
           limpiarPais();
        }
        if (e.getSource() == vista.btnLimpiarPais) {
            limpiarPais();
        }
        
        // Sección Ciudad
        if (e.getSource() == vista.btnAgregarCiudad) {
            agregarCiudad();
            limpiarCiudad();
            
        }
        if (e.getSource() == vista.btnModificarCiudad) {
          modificarCiudad();
        }
        if (e.getSource() == vista.btnGuardarCiudad) {
          
        }
        if (e.getSource() == vista.btnEliminarCiudad) {
           eliminarCiudad();
        }
        if (e.getSource() == vista.btnLimpiarCiudad) {
            limpiarCiudad();
        }
        
        // Sección Idiom
        if (e.getSource() == vista.btnAgregarIdiom) {
            agregarIdioma();
            limpiarIdioma();
        }
        if (e.getSource() == vista.btnOficial) {
            if(vista.btnOficial.isSelected())
            {
                idioma.setIdiomaOficial(true);
            }else{
                idioma.setIdiomaOficial(false);
            }
        }
        if (e.getSource() == vista.btnModificarIdiom) {
          modificarIdioma();
        }
        if (e.getSource() == vista.btnGuardarIdiom) {
          
        }
        if (e.getSource() == vista.btnEliminarIdiom) {
           eliminarIdioma();
        }
        if (e.getSource() == vista.btnLimpiarIdiom) {
            limpiarIdioma();
        }

    }
    
    public void consultarPaises() {
    limpiarTabla(); // Limpiar la tabla antes de actualizarla

    // Obtener la lista de países desde la base de datos
    List<Pais> listaPaises = paisOp.obtenerListaPaises();

    // Llenar la tabla con los datos de la lista
    for (Pais pais : listaPaises) {
        Object[] fila = {pais.getCodigo(), pais.getNombre(), pais.getContinente(), pais.getPoblacion()};
        modelo.addRow(fila);
    }
}
    
    
    // Método para modificar un país
    private void modificarPais() {
    String codigo = vista.txtCodigoPais.getText();
    String nombre = vista.txtNombrePais.getText();
    String continente = vista.txtContinentePais.getText();
    int poblacion = Integer.parseInt(vista.txtPoblacionPais.getText().trim());
    pais.setCodigo(codigo);
    pais.setNombre(nombre);
    pais.setContinente(continente);
    pais.setPoblacion(poblacion);
    int r = paisOp.modificar(pais);
    if (r == 1) {
        JOptionPane.showMessageDialog(vista, "País modificado con éxito.");
        paisOp.actualizarTabla((DefaultTableModel) vista.jTablePais.getModel());
    } else {
        JOptionPane.showMessageDialog(vista, "Error al modificar el país.");
    }
    limpiarTabla();
   }
    
    // Método para eliminar un país
    private void eliminarPais() {
    String codigo = vista.txtCodigoPais.getText();
    int r = paisOp.eliminar(codigo);
    if (r == 1) {
        JOptionPane.showMessageDialog(vista, "País eliminado con éxito.");
    } else {
        JOptionPane.showMessageDialog(vista, "Error al eliminar el país.");
    }
    limpiarTabla();
   }

    void limpiarPais() {
        vista.txtNombrePais.setText("");
        vista.txtCodigoPais.setText("");
        vista.txtPoblacionPais.setText("");
        vista.txtContinentePais.setText("");
        vista.txtCodigoPais.requestFocus();
    }

   
    public void agregarPais() {
        String codigo = vista.txtCodigoPais.getText();
        String nombre = vista.txtNombrePais.getText();
        String continente = vista.txtContinentePais.getText();
        int poblacion = Integer.parseInt(vista.txtPoblacionPais.getText().trim());
        pais.setCodigo(codigo);
        pais.setNombre(nombre);
        pais.setContinente(continente);
        pais.setPoblacion(poblacion);
        int r = paisOp.agregar(pais);
        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Pais agregado con éxito.");
        } else {
            JOptionPane.showMessageDialog(vista, "Error");
        }
        limpiarTabla();
    }
    
    void limpiarCiudad() {
        vista.txtPaisCiudad.setText("");
        vista.txtNombreCiudad.setText("");
        vista.txtDistritoCiudad.setText("");
        vista.txtPoblacionCiudad.setText("");
    }

    //Crud Ciudad
   
    public void agregarCiudad() {
        String paisCiudad = vista.txtPaisCiudad.getText();
        String nombreCiudad = vista.txtNombreCiudad.getText();
        String distritoCiudad = vista.txtDistritoCiudad.getText();
        String poblacionCiudad = vista.txtPoblacionCiudad.getText();
        ciudad.setPaisPert(paisCiudad);
        ciudad.setNombre(nombreCiudad);
        ciudad.setDistrito(distritoCiudad);
        ciudad.setPoblacion(poblacionCiudad);
        int r = ciudadOp.agregar(ciudad);
        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Ciudad agregada con éxito.");
        } else {
            JOptionPane.showMessageDialog(vista, "Error");
        }
        limpiarTabla();
    }
    
    public void eliminarCiudad() {
    String nombreCiudad = vista.txtNombreCiudad.getText();

    if (nombreCiudad.isEmpty()) {
        JOptionPane.showMessageDialog(vista, "Ingrese el nombre de la ciudad a eliminar");
    } else {
        int r = ciudadOp.eliminar(nombreCiudad);

        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Ciudad eliminada con éxito.");
            ciudadOp.actualizarTabla((DefaultTableModel) vista.jTableCiudad.getModel());
        } else {
            JOptionPane.showMessageDialog(vista, "Error al eliminar la ciudad.");
        }
        limpiarCiudad();
       }
    }
    
    
    private void modificarCiudad() {
    String nombreCiudad = vista.txtNombreCiudad.getText();

    if (nombreCiudad.isEmpty()) {
        JOptionPane.showMessageDialog(vista, "Ingrese el nombre de la ciudad a modificar");
    } else {
        String distritoCiudad = vista.txtDistritoCiudad.getText();
        String poblacionCiudad = vista.txtPoblacionCiudad.getText();

        Ciudad ciudad = new Ciudad();
        ciudad.setNombre(nombreCiudad);
        ciudad.setDistrito(distritoCiudad);
        ciudad.setPoblacion(poblacionCiudad);
        ciudad.setPaisPert("Peru");

        int r = ciudadOp.modificar(ciudad);

        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Ciudad modificada con éxito.");
            ciudadOp.actualizarTabla((DefaultTableModel) vista.jTableCiudad.getModel());
        } else {
            JOptionPane.showMessageDialog(vista, "Error al modificar la ciudad.");
        }
        limpiarCiudad();
    }
}
    
    void limpiarIdioma() {
        vista.txtNombreIdioma.setText("");
        vista.txtPorcentajePob.setText("");
        vista.btnOficial.setSelected(false);
    }

   
    public void agregarIdioma() {
        String nombreIdioma = vista.txtNombreIdioma.getText();
        double porcentajePob = Double.parseDouble(vista.txtPorcentajePob.getText());
        boolean idiomaOficial = vista.btnOficial.isSelected();
        idioma.setIdioma(nombreIdioma);
        idioma.setIdiomaOficial(idiomaOficial);
        idioma.setPorcPoblacion(porcentajePob);
        int r = idiomaOp.agregar(idioma);
        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Idioma agregado con éxito.");
        } else {
            JOptionPane.showMessageDialog(vista, "Error");
        }
        limpiarTabla();
    }
    
    public void modificarIdioma() {
    String nombreIdioma = vista.txtNombreIdioma.getText();

    if (nombreIdioma.isEmpty()) {
        JOptionPane.showMessageDialog(vista, "Ingrese el nombre del idioma a modificar");
    } else {
        double porcentajePob = Double.parseDouble(vista.txtPorcentajePob.getText());
        boolean idiomaOficial = vista.btnOficial.isSelected();

        Idioma idioma = new Idioma();
        idioma.setIdioma(nombreIdioma);
        idioma.setPorcPoblacion(porcentajePob);
        idioma.setIdiomaOficial(idiomaOficial);

        int r = idiomaOp.modificar(idioma);

        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Idioma modificado con éxito.");
            idiomaOp.actualizarTabla((DefaultTableModel) vista.jTableIdiomaPais.getModel());
        } else {
            JOptionPane.showMessageDialog(vista, "Error al modificar el idioma.");
        }
        limpiarIdioma();
    }
}
    
    public void eliminarIdioma() {
    String nombreIdioma = vista.txtNombreIdioma.getText();

    if (nombreIdioma.isEmpty()) {
        JOptionPane.showMessageDialog(vista, "Ingrese el nombre del idioma a eliminar");
    } else {
        int r = idiomaOp.eliminar(nombreIdioma);

        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Idioma eliminado con éxito.");
            idiomaOp.actualizarTabla((DefaultTableModel) vista.jTableIdiomaPais.getModel());
        } else {
            JOptionPane.showMessageDialog(vista, "Error al eliminar el idioma.");
        }
        limpiarIdioma();
       }
    }
   

    void centrarCeldas(JTable tabla) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < vista.jTablePais.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    void limpiarTabla() {
        for (int i = 0; i < vista.jTablePais.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
}
