package gUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import DataLayer.ChangeDBLayer;
import DataLayer.DeleteDBLayer;
import DataLayer.SelectDBLayer;



public class Captura extends JFrame implements ActionListener, KeyListener, FocusListener, ListSelectionListener{

     private static final long serialVersionUID = 1L;

	private JButton btnLimpiar, btnGrabar, btnDelete;
    public static JRadioButton RBModificar, RBNuevo, RBM, RBF;


    //ARTICULO ATRIBUTOS
    private JTextField txtArtId,txtArtNombre,txtArtDescripcion;
	//private JTextFieldDecimal txtArtPrecio;
    private JTextField txtArtPrecio;
    private JComboBox<String> cbxArtFamID; 
    ButtonGroup ArtTamaños = new ButtonGroup();
    public static JRadioButton rdC,rdM,rdG;
      Connection conexionDB;

    public static JTable tblArticulos;
    private DefaultTableModel tblModel; 

    
    public Captura(Connection con) {
		super("Captura");
           conexionDB = con;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(800,500);
        setResizable(false);
		setLocationRelativeTo(null);
		InitComponents();
       
	}

    private void InitComponents(){
        InitCaptura();
    }
    
    private void InitCaptura() {
    Color fondo = Color.decode("#d7e7eb");

    // PANEL PRINCIPAL CENTRAL
    JPanel pnlPrincipal = new JPanel(new BorderLayout());
    pnlPrincipal.setBackground(fondo);

    // TABLA DIRECTAMENTE EN EL PANEL PRINCIPAL
        ArticulosModel jtAriculos = new ArticulosModel(conexionDB);
        tblArticulos = new JTable(jtAriculos);

        tblArticulos.getSelectionModel().addListSelectionListener(this);

        tblArticulos.getTableHeader().setBackground(Color.decode("#133E87"));
        tblArticulos.getTableHeader().setForeground(Color.WHITE);
        tblArticulos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tblArticulos.setFont(new Font("Arial", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(tblArticulos);

    // Panel auxiliar para margen
    JPanel pnlTablaConMargen = new JPanel(new BorderLayout());
    pnlTablaConMargen.setBackground(fondo);
    pnlTablaConMargen.setBorder(new EmptyBorder(5, 5, 5, 5));
    pnlTablaConMargen.add(scrollPane, BorderLayout.CENTER);

    pnlPrincipal.add(pnlTablaConMargen, BorderLayout.CENTER);

    // PANEL DERECHO: FORMULARIO Y BOTONES
    JPanel pnlDerecha = new JPanel(new BorderLayout());
    pnlDerecha.setBackground(fondo);

    // Formulario arriba
    JPanel pnlCentro = new JPanel();
    pnlCentro.setLayout(new GridBagLayout());
    pnlCentro.setBackground(fondo);
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new java.awt.Insets(5, 5, 5, 5);

    // Configuración general
    gbc.fill = GridBagConstraints.HORIZONTAL; // Expandir componentes horizontalmente
    gbc.insets = new java.awt.Insets(5, 5, 5, 5);      // Márgenes (arriba, izquierda, abajo, derecha)

        
        // Fila 1 - ID ARTICULO
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST; // Etiquetas alineadas a la derecha
        pnlCentro.add(new JLabel("ArtID: ", JLabel.RIGHT), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST; // Campo alineado a la izquierda
        txtArtId = new JTextField();
        txtArtId.setPreferredSize(new Dimension(50, 20));
        txtArtId.setHorizontalAlignment(JTextField.CENTER);


        txtArtId.addFocusListener(this);
        pnlCentro.add(txtArtId, gbc);

        // Fila 2 - ARTICULO NOMBRE
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        pnlCentro.add(new JLabel("Nombre: ", JLabel.RIGHT), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        txtArtNombre = new JTextField();
        txtArtNombre.setPreferredSize(new Dimension(200, 20));
        pnlCentro.add(txtArtNombre, gbc);

        // Fila 3 - ARTICULO DESCRIPCION
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        pnlCentro.add(new JLabel("Descripcion: ", JLabel.RIGHT), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        txtArtDescripcion = new JTextField();
        txtArtDescripcion.setPreferredSize(new Dimension(200, 20));
        pnlCentro.add(txtArtDescripcion, gbc);

        // Fila 4 - ARTICULO PRECIO
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        pnlCentro.add(new JLabel("Precio: ", JLabel.RIGHT), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        txtArtPrecio = new JTextField();
        //txtArtPrecio = new JTextFieldDecimal(10,2); // Asegurar que está instanciado
        txtArtPrecio.setPreferredSize(new Dimension(200, 20));
        pnlCentro.add(txtArtPrecio, gbc);


        // Fila 5 - ARTICULO TAMAÑO
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        pnlCentro.add(new JLabel("Tamaño: ", JLabel.RIGHT), gbc);

       // Crear un panel para los radio buttons
        gbc.gridx = 1;
        JPanel pnlTamaño = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0)); // Espaciado entre botones
        rdC = new JRadioButton("C");
        rdC.setBackground(fondo);
        rdC.setOpaque(false); // Elimina el recuadro gris

        rdM = new JRadioButton("M");
        rdM.setBackground(fondo);
        rdM.setOpaque(false); // Elimina el recuadro gris

        rdG = new JRadioButton("G");
        rdG.setBackground(fondo);
        rdG.setOpaque(false); // Elimina el recuadro gris

        pnlTamaño.setBackground(fondo);
        ArtTamaños.add(rdC);
        ArtTamaños.add(rdM); 
        ArtTamaños.add(rdG);
        pnlTamaño.add(rdC);
        pnlTamaño.add(rdM);
        pnlTamaño.add(rdG);

        gbc.anchor = GridBagConstraints.WEST;
        pnlCentro.add(pnlTamaño, gbc);

        // Fila 6 - ARTICULO FAMILIA
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        pnlCentro.add(new JLabel("Familia: ", JLabel.RIGHT), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        cbxArtFamID = new JComboBox<>();

        cbxArtFamID.setPreferredSize(new Dimension(150, 20));
        pnlCentro.add(cbxArtFamID, gbc);
        cbxArtFamID.setEditable(false);
        SelectDBLayer dbLayer = new SelectDBLayer(conexionDB);
        ArrayList<String> familias = dbLayer.getListaFamilias();
        cbxArtFamID.addItem("");
        for (String fam : familias) {
            cbxArtFamID.addItem(fam);
        }

        txtArtId.setBorder(null);
        txtArtNombre.setBorder(null);
        txtArtDescripcion.setBorder(null);
        txtArtPrecio.setBorder(null);
        cbxArtFamID.setBorder(null);

        /* No se como agregaremos los tamaños de esto, si habra una tabla o algo
        try {
            cbxArtFamID.setModel(DBClientes.ListarTipos());
        } 
        catch (Exception e) {
            e.getMessage();
        }   
        */

        txtArtId.setBorder(null);
        txtArtNombre.setBorder(null);
        txtArtDescripcion.setBorder(null);
        txtArtPrecio.setBorder(null);
        cbxArtFamID.setBorder(null);

        // PANEL INFERIOR DERECHO: BOTONES Y RADIOBUTTONS EN COLUMNA
    JPanel pnlBotones = new JPanel(new GridLayout(0, 1, 0, 10)); // 1 columna, separación vertical de 10px
    pnlBotones.setBackground(fondo);

    // Panel para los radio buttons en una fila
    JPanel pnlRadios = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
    pnlRadios.setBackground(fondo);
    ButtonGroup group = new ButtonGroup();
    RBNuevo = new JRadioButton("Nuevo");
    RBNuevo.setBackground(fondo);
    RBNuevo.addActionListener(this);
    group.add(RBNuevo);
    pnlRadios.add(RBNuevo);

    RBModificar = new JRadioButton("Modificar");
    RBModificar.setBackground(fondo);
    RBModificar.addActionListener(this);
    group.add(RBModificar);
    pnlRadios.add(RBModificar);

    // Agrega el panel de radio buttons como la primera fila
    pnlBotones.add(pnlRadios);

    // Botón Limpiar
    btnLimpiar = new JButton("LIMPIAR");
    btnLimpiar.setToolTipText("Limpiar campos");
    btnLimpiar.addActionListener(this);
    btnLimpiar.setBackground(Color.decode("#ECF2F9"));
    pnlBotones.add(btnLimpiar);

    // Botón Grabar
    btnGrabar = new JButton("GRABAR");
    btnGrabar.setToolTipText("Realiza el cambio en la tabla");
    btnGrabar.addActionListener(this);
    btnGrabar.setBackground(Color.decode("#ECF2F9"));
    pnlBotones.add(btnGrabar);

    // Botón Eliminar
    btnDelete = new JButton("ELIMINAR ");
    btnDelete.setToolTipText("Elimina el cliente seleccionado");
    btnDelete.addActionListener(this);
    btnDelete.setBackground(Color.decode("#ECF2F9"));
    pnlBotones.add(btnDelete);

    // Panel auxiliar para margen
    JPanel pnlBotonesConMargen = new JPanel(new BorderLayout());
    pnlBotonesConMargen.setBackground(fondo);
    pnlBotonesConMargen.setBorder(new EmptyBorder(10, 10, 10, 10)); // top, left, bottom, right
    pnlBotonesConMargen.add(pnlBotones, BorderLayout.CENTER);

    // Panel para centrar verticalmente el contenido derecho
    JPanel pnlDerechaContenido = new JPanel(new GridBagLayout());
    pnlDerechaContenido.setBackground(fondo);
    GridBagConstraints gbcDerecha = new GridBagConstraints();
    gbcDerecha.gridx = 0;
    gbcDerecha.gridy = 0;
    gbcDerecha.anchor = GridBagConstraints.CENTER;
    gbcDerecha.weighty = 1;
    gbcDerecha.insets = new java.awt.Insets(0, 0, 10, 0);
    pnlDerechaContenido.add(pnlCentro, gbcDerecha);

    gbcDerecha.gridy = 1;
    gbcDerecha.insets = new java.awt.Insets(10, 0, 0, 0);
    pnlDerechaContenido.add(pnlBotonesConMargen, gbcDerecha);

    // Agrega el panel centrado al centro del panel derecho
    pnlDerecha.add(pnlDerechaContenido, BorderLayout.CENTER);

    // Añadir paneles al principal
    pnlPrincipal.add(pnlDerecha, BorderLayout.EAST); // Panel derecho a la derecha

    // Añadir al frame
    add(pnlPrincipal, BorderLayout.CENTER);
}



    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        Object source = e.getSource();
        
        if (source == btnLimpiar) {
            // Limpiar campos
            txtArtId.setText("");
            txtArtNombre.setText("");
            txtArtDescripcion.setText("");
            txtArtPrecio.setText("");
            cbxArtFamID.setSelectedIndex(-1); // Desmarca cualquier selección
            ArtTamaños.clearSelection(); // Desmarca los radio buttons
            return;
        }
        
        if (source == RBNuevo) {
            modoNuevo();
        } else if (source == RBModificar) {
            modoModificar();
        }

        if(source == btnGrabar){
         if(comprobarCamposLlenos()){

               ChangeDBLayer modify = new ChangeDBLayer(conexionDB, llenarMatrizTexto());
               dibujarTabla();

         }else{
            	JOptionPane.showMessageDialog( 
                null, "Por favor llene correctamente todos los campos", 
                "Error", JOptionPane.ERROR_MESSAGE);
				
         }

        }

        
         if(source == btnDelete){
            if(txtArtId.getText().isEmpty()){
                JOptionPane.showMessageDialog( 
                null, "Indique el ID del Articulo que desea borrar", 
                "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                int id;
                try{
                    id = Integer.parseInt(txtArtId.getText());
                    DeleteDBLayer deleteLayer = new DeleteDBLayer(conexionDB, id);
                    dibujarTabla();
                }catch(NumberFormatException exc){
                    JOptionPane.showMessageDialog( 
                null, "Ingrese un ID Valido", 
                "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            }
         }
           
    }

    private void modoNuevo() {
        txtArtId.setEnabled(false);
        txtArtNombre.setEnabled(true);
        txtArtDescripcion.setEnabled(true);
        txtArtPrecio.setEnabled(true);
        cbxArtFamID.setEnabled(true);
        rdC.setEnabled(true);
        rdM.setEnabled(true);
        rdG.setEnabled(true);

        // Limpiar campos
        txtArtId.setText("*");
        txtArtNombre.setText("");
        txtArtDescripcion.setText("");
        txtArtPrecio.setText("");
        cbxArtFamID.setSelectedIndex(-1); // Desmarca cualquier selección
        ArtTamaños.clearSelection(); // Desmarca los radio buttons
        repaint();
    }

    private void modoModificar() {
        int filaSeleccionada = tblArticulos.getSelectedRow();
        if (filaSeleccionada != -1) {
            txtArtId.setEnabled(true);
            txtArtNombre.setEnabled(true);
            txtArtDescripcion.setEnabled(true);
            txtArtPrecio.setEnabled(true);
            cbxArtFamID.setEnabled(true);
            rdC.setEnabled(true);
            rdM.setEnabled(true);
            rdG.setEnabled(true);

            // Cargar datos en los campos
            txtArtId.setText(tblArticulos.getValueAt(filaSeleccionada, 0).toString());
            txtArtNombre.setText(tblArticulos.getValueAt(filaSeleccionada, 1).toString());
            txtArtDescripcion.setText(tblArticulos.getValueAt(filaSeleccionada, 2).toString());
            txtArtPrecio.setText(tblArticulos.getValueAt(filaSeleccionada, 3).toString());

            // Seleccionar la opción correspondiente en el ComboBox de familia
            String familia = tblArticulos.getValueAt(filaSeleccionada, 5).toString();
            cbxArtFamID.setSelectedItem(familia);
            
            // Seleccionar tamaño
            String tamaño = tblArticulos.getValueAt(filaSeleccionada, 4).toString();
            ArtTamaños.clearSelection(); // Desmarca los radio buttons
            if (tamaño.equals("C")) {
                rdC.setSelected(true);
            } else if (tamaño.equals("M")) {
                rdM.setSelected(true);
            } else if (tamaño.equals("G")) {
                rdG.setSelected(true);
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Seleccione una fila para modificar.", "Advertencia", javax.swing.JOptionPane.WARNING_MESSAGE);
        }
    }

    public boolean comprobarCamposLlenos() {
        

        try {
            double precio = Double.parseDouble(txtArtPrecio.getText().trim());
            if (precio <= 0) {
            JOptionPane.showMessageDialog(
                null,
                "El precio debe ser un número positivo.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return false;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
            null,
            "El precio debe ser un número válido.",
            "Error",
            JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
        boolean camposTextoLlenos = 
            !txtArtId.getText().trim().isEmpty() &&
            !txtArtNombre.getText().trim().isEmpty() &&
            !txtArtDescripcion.getText().trim().isEmpty() &&
            !txtArtPrecio.getText().trim().isEmpty();

        boolean familiaSeleccionada = cbxArtFamID.getSelectedIndex() > 0; 

        boolean tamañoSeleccionado = rdG.isSelected() || rdC.isSelected() || rdM.isSelected();

        return camposTextoLlenos && familiaSeleccionada && tamañoSeleccionado;
    }

    public String[] llenarMatrizTexto(){
           String cambios[]=new String[6]; 

            if(txtArtId.getText().equals("*")){
                 cambios[0] = "0";
            }else{
                 cambios[0] = txtArtId.getText();
            }

            cambios[1]=txtArtNombre.getText();

            cambios[2]=txtArtDescripcion.getText();

            cambios[3]=txtArtPrecio.getText();


            if (rdC.isSelected()) {
                cambios[4]="C";
            } else if (rdM.isSelected() ) {
                cambios[4]="M";
             } else if (rdG.isSelected()) {
                  cambios[4]="G";
               }
            
               SelectDBLayer fam = new SelectDBLayer(conexionDB);
               cambios[5]= fam.getFamiliaID(cbxArtFamID.getSelectedItem().toString()) ;

               return cambios;
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
    
        Object source = e.getSource();
        
        if (source == txtArtId) {
         
            if (!Character.isDigit(e.getKeyChar())) {
                e.consume(); 
            }
        }
        else if (source == txtArtPrecio) {
        
            char c = e.getKeyChar();
            if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE || c == '.')) {
                e.consume();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void focusGained(FocusEvent e) {
        
        Object source = e.getSource();
        
        if (source == txtArtId) {
            txtArtId.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLUE));
        }
        else if (source == txtArtNombre) {
            txtArtNombre.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLUE));
        }
        else if (source == txtArtDescripcion) {
            txtArtDescripcion.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLUE));
        }
        else if (source == txtArtPrecio) {
            txtArtPrecio.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLUE));
        }
        else if (source == cbxArtFamID) {
            cbxArtFamID.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLUE));
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        
        Object source = e.getSource();
        
        if (source == txtArtId) {
            txtArtId.setBorder(null);
        }
        else if (source == txtArtNombre) {
            txtArtNombre.setBorder(null);
        }
        else if (source == txtArtDescripcion) {
            txtArtDescripcion.setBorder(null);
        }
        else if (source == txtArtPrecio) {
            txtArtPrecio.setBorder(null);
        }
        else if (source == cbxArtFamID) {
            cbxArtFamID.setBorder(null);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedRowCount = tblArticulos.getSelectedRow();
        if (selectedRowCount >= 0) {
            actualizarTxtField(selectedRowCount);
            RBModificar.setSelected(true);
        }
    }

    public void actualizarTxtField(int filaSelect) {
        int id = (int) tblArticulos.getValueAt(filaSelect, 0);
        txtArtId.setText(String.valueOf(id));

        String nombre = (String) tblArticulos.getValueAt(filaSelect, 1);
        txtArtNombre.setText(String.valueOf(nombre));

        String descripcion = (String) tblArticulos.getValueAt(filaSelect, 2);
        txtArtDescripcion.setText(String.valueOf(descripcion));

        double precio = (double) tblArticulos.getValueAt(filaSelect, 3);
        txtArtPrecio.setText(String.valueOf(precio));

        String size = (String) tblArticulos.getValueAt(filaSelect, 4);
        if (size.charAt(0) == 'C') {
            rdC.setSelected(true);
        } else if (size.charAt(0) == 'M') {
            rdM.setSelected(true);
        } else if (size.charAt(0) == 'G') {
            rdG.setSelected(true);
        }

        String familia = (String) tblArticulos.getValueAt(filaSelect, 5);
        cbxArtFamID.setSelectedItem(familia);

        repaint();

    }


    public void dibujarTabla(){
         ArticulosModel jtArticulos = new ArticulosModel(conexionDB);
            tblArticulos.setModel(jtArticulos);
            tblArticulos.getSelectionModel().addListSelectionListener(this);
            repaint();

    }
}
