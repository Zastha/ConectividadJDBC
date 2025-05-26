package gUI;

import DataLayer.ChangeDBLayer;
import DataLayer.SelectDBLayer;
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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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



public class Captura extends JFrame implements ActionListener, KeyListener, FocusListener, MouseListener, ListSelectionListener{

     private static final long serialVersionUID = 1L;

	private JButton btnLimpiar, btnGrabar, btnDelete;
    public static JRadioButton RBModificar, RBNuevo, RBM, RBF;


 
    private JTextField txtArtId,txtArtNombre,txtArtDescripcion;

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


    JPanel pnlPrincipal = new JPanel(new BorderLayout());
    pnlPrincipal.setBackground(fondo);


        ArticulosModel jtAriculos = new ArticulosModel(conexionDB);
        tblArticulos = new JTable(jtAriculos);

        tblArticulos.getSelectionModel().addListSelectionListener(this);

        tblArticulos.getTableHeader().setBackground(Color.decode("#133E87"));
        tblArticulos.getTableHeader().setForeground(Color.WHITE);
        tblArticulos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tblArticulos.setFont(new Font("Arial", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(tblArticulos);


    JPanel pnlTablaConMargen = new JPanel(new BorderLayout());
    pnlTablaConMargen.setBackground(fondo);
    pnlTablaConMargen.setBorder(new EmptyBorder(5, 5, 5, 5));
    pnlTablaConMargen.add(scrollPane, BorderLayout.CENTER);

    pnlPrincipal.add(pnlTablaConMargen, BorderLayout.CENTER);


    JPanel pnlDerecha = new JPanel(new BorderLayout());
    pnlDerecha.setBackground(fondo);


    JPanel pnlCentro = new JPanel();
    pnlCentro.setLayout(new GridBagLayout());
    pnlCentro.setBackground(fondo);
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new java.awt.Insets(5, 5, 5, 5);


    ButtonGroup group = new ButtonGroup();
    RBNuevo = new JRadioButton("Nuevo");
    RBNuevo.setBackground(fondo);
    RBNuevo.addActionListener(this);
    group.add(RBNuevo);

    RBModificar = new JRadioButton("Modificar");
    RBModificar.setBackground(fondo);
    RBModificar.addActionListener(this);
    group.add(RBModificar);

    JPanel pnlRadiosArriba = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
    pnlRadiosArriba.setBackground(fondo);
    pnlRadiosArriba.add(RBNuevo);
    pnlRadiosArriba.add(RBModificar);


    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.WEST;
    pnlCentro.add(pnlRadiosArriba, gbc);


    gbc.gridwidth = 1;

    // Fila 1 - ID ARTICULO
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.anchor = GridBagConstraints.EAST; 
    pnlCentro.add(new JLabel("ArtID: ", JLabel.RIGHT), gbc);

    gbc.gridx = 1;
    gbc.anchor = GridBagConstraints.WEST;
    txtArtId = new JTextField();
    txtArtId.setPreferredSize(new Dimension(50, 20));
    txtArtId.setHorizontalAlignment(JTextField.CENTER);
    txtArtId.addFocusListener(this);
    pnlCentro.add(txtArtId, gbc);

    // Fila 2 - ARTICULO NOMBRE
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.anchor = GridBagConstraints.EAST;
    pnlCentro.add(new JLabel("Nombre: ", JLabel.RIGHT), gbc);

    gbc.gridx = 1;
    gbc.anchor = GridBagConstraints.WEST;
    txtArtNombre = new JTextField();
    txtArtNombre.setPreferredSize(new Dimension(200, 20));
    pnlCentro.add(txtArtNombre, gbc);

    // Fila 3 - ARTICULO DESCRIPCION
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.anchor = GridBagConstraints.EAST;
    pnlCentro.add(new JLabel("Descripcion: ", JLabel.RIGHT), gbc);

    gbc.gridx = 1;
    gbc.anchor = GridBagConstraints.WEST;
    txtArtDescripcion = new JTextField();
    txtArtDescripcion.setPreferredSize(new Dimension(200, 20));
    pnlCentro.add(txtArtDescripcion, gbc);

    // Fila 4 - ARTICULO PRECIO
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.anchor = GridBagConstraints.EAST;
    pnlCentro.add(new JLabel("Precio: ", JLabel.RIGHT), gbc);

    gbc.gridx = 1;
    gbc.anchor = GridBagConstraints.WEST;
    txtArtPrecio = new JTextField();
    txtArtPrecio.setPreferredSize(new Dimension(200, 20));
    pnlCentro.add(txtArtPrecio, gbc);


    // Fila 5 - ARTICULO TAMAÑO
    gbc.gridx = 0;
    gbc.gridy = 5;
    gbc.anchor = GridBagConstraints.EAST;
    pnlCentro.add(new JLabel("Tamaño: ", JLabel.RIGHT), gbc);

   // Crear un panel para los radio buttons
    gbc.gridx = 1;
    JPanel pnlTamaño = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0)); 
    rdC = new JRadioButton("C");
    rdC.setBackground(fondo);
    rdC.setOpaque(false); 

    rdM = new JRadioButton("M");
    rdM.setBackground(fondo);
    rdM.setOpaque(false); 

    rdG = new JRadioButton("G");
    rdG.setBackground(fondo);
    rdG.setOpaque(false);

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
    gbc.gridy = 6;
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

    habilitacionInfo(false);

    txtArtId.setBorder(null);
    txtArtNombre.setBorder(null);
    txtArtDescripcion.setBorder(null);
    txtArtPrecio.setBorder(null);
    cbxArtFamID.setBorder(null);

    txtArtId.setBorder(null);
    txtArtNombre.setBorder(null);
    txtArtDescripcion.setBorder(null);
    txtArtPrecio.setBorder(null);
    cbxArtFamID.setBorder(null);

    // PANEL INFERIOR DERECHO: BOTONES Y RADIOBUTTONS EN COLUMNA
    JPanel pnlBotones = new JPanel(new GridLayout(0, 1, 0, 10)); 
    pnlBotones.setBackground(fondo);

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
    btnDelete.addMouseListener(this);
    btnDelete.setBackground(Color.decode("#ECF2F9"));
    btnDelete.setEnabled(false);
    pnlBotones.add(btnDelete);

    // Panel auxiliar para margen
    JPanel pnlBotonesConMargen = new JPanel(new BorderLayout());
    pnlBotonesConMargen.setBackground(fondo);
    pnlBotonesConMargen.setBorder(new EmptyBorder(10, 10, 10, 10)); 
    pnlBotonesConMargen.add(pnlBotones, BorderLayout.CENTER);

 
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

    pnlDerecha.add(pnlDerechaContenido, BorderLayout.CENTER);

    pnlPrincipal.add(pnlDerecha, BorderLayout.EAST); 


    add(pnlPrincipal, BorderLayout.CENTER);
}

    @Override
    public void mouseClicked(MouseEvent e) {

        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == btnLimpiar) {

            txtArtId.setText("");
            txtArtNombre.setText("");
            txtArtDescripcion.setText("");
            txtArtPrecio.setText("");
            cbxArtFamID.setSelectedIndex(-1);
            ArtTamaños.clearSelection();
            tblArticulos.clearSelection(); 
            btnDelete.setEnabled(false);  

   
            habilitacionInfo(false);

  
            ButtonGroup group = new ButtonGroup();
            group.add(RBNuevo);
            group.add(RBModificar);
            group.clearSelection();

            repaint();
            return;
        }

        if (source == RBNuevo) {
            habilitacionInfo(true);
            txtArtId.setEditable(false);
            // Limpiar campos
            txtArtId.setText("*");
            txtArtId.setFont(new Font(txtArtId.getFont().getName(), Font.BOLD, 24)); 
            txtArtNombre.setText("");
            
            txtArtDescripcion.setText("");
            txtArtPrecio.setText("");
            cbxArtFamID.setSelectedIndex(-1);
            ArtTamaños.clearSelection();
            repaint();
        }
        else if (source == RBModificar) {
             txtArtId.setFont(new Font(txtArtId.getFont().getName(), Font.PLAIN, 12));
            txtArtId.setEditable(true);
            int filaSeleccionada = tblArticulos.getSelectedRow();
            if (filaSeleccionada != -1) {
                habilitacionInfo(true);
                txtArtId.setEnabled(true);
               
             
                txtArtId.setText(tblArticulos.getValueAt(filaSeleccionada, 0).toString());
                txtArtNombre.setText(tblArticulos.getValueAt(filaSeleccionada, 1).toString());
                txtArtDescripcion.setText(tblArticulos.getValueAt(filaSeleccionada, 2).toString());
                txtArtPrecio.setText(tblArticulos.getValueAt(filaSeleccionada, 3).toString());
                cbxArtFamID.setSelectedIndex(-1);
                String tamaño = tblArticulos.getValueAt(filaSeleccionada, 4).toString();
                ArtTamaños.clearSelection();
                if (tamaño.equals("C")) rdC.setSelected(true);
                else if (tamaño.equals("M")) rdM.setSelected(true);
                else if (tamaño.equals("G")) rdG.setSelected(true);
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una fila para modificar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }

        if(source == btnGrabar){
         if(comprobarCamposLlenos()){

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


               ChangeDBLayer modify = new ChangeDBLayer(conexionDB, cambios);
               dibujarTabla();

         }else{
            	JOptionPane.showMessageDialog( 
                null, "Por favor llene correctamente todos los campos", 
                "Error", JOptionPane.ERROR_MESSAGE);
				
         }

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

    public void habilitacionInfo(boolean estado){
        txtArtId.setEnabled(estado);
        txtArtNombre.setEnabled(estado);
        txtArtDescripcion.setEnabled(estado);
        txtArtPrecio.setEnabled(estado);
        cbxArtFamID.setEnabled(estado);
        rdC.setEnabled(estado);
        rdM.setEnabled(estado);
        rdG.setEnabled(estado);
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
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedRowCount = tblArticulos.getSelectedRow();
        if (selectedRowCount >= 0) {
            actualizarTxtField(selectedRowCount);
            RBModificar.setSelected(true);
            btnDelete.setEnabled(true); 
            habilitacionInfo(true);     
        } else {
            btnDelete.setEnabled(false); 
            habilitacionInfo(false);     
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
