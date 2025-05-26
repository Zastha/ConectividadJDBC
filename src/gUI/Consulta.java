package gUI;

import DataLayer.SelectDBLayer;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Consulta extends JFrame implements ActionListener, KeyListener, FocusListener, MouseListener, ListSelectionListener {

    private static final long serialVersionUID = 1L;

    ButtonGroup ArtTamaños = new ButtonGroup();
    public static JRadioButton rdC, rdM, rdG;
    public static JTable tblArticulos;

    private JButton btnBuscar, btnRestablecer;
    private JComboBox<String> cbxArtFamID;
    private JTextField txtArtId, txtArtNombre, txtArtDescripcion, txtArtPrecio;

    Connection conexionDB;

    public Consulta(Connection con) {
        super("Consulta");
        conexionDB = con;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        InitComponents();
        setVisible(true);

    }

    private void InitComponents() {
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

        JPanel pnlTablaConMargen = new JPanel(new BorderLayout());
        pnlTablaConMargen.setBackground(fondo);
        pnlTablaConMargen.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlTablaConMargen.add(scrollPane, BorderLayout.CENTER);

        pnlPrincipal.add(pnlTablaConMargen, BorderLayout.CENTER);

  
        JPanel pnlDerecha = new JPanel(new BorderLayout());
        pnlDerecha.setBackground(fondo);

        JPanel pnlCentro = new JPanel();
        pnlCentro.setLayout(new GridBagLayout());
        pnlCentro.setBackground(fondo);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        pnlCentro.add(new JLabel("ID: ", JLabel.RIGHT), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        txtArtId = new JTextField(15);
        pnlCentro.add(txtArtId, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        pnlCentro.add(new JLabel("Nombre: ", JLabel.RIGHT), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        txtArtNombre = new JTextField(15);
        pnlCentro.add(txtArtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        pnlCentro.add(new JLabel("Descripción: ", JLabel.RIGHT), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        txtArtDescripcion = new JTextField(15);
        pnlCentro.add(txtArtDescripcion, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        pnlCentro.add(new JLabel("Precio: ", JLabel.RIGHT), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        txtArtPrecio = new JTextField(15);
        pnlCentro.add(txtArtPrecio, gbc);

        // Fila 5 - ARTICULO TAMAÑO
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        pnlCentro.add(new JLabel("Tamaño: ", JLabel.RIGHT), gbc);

        gbc.gridx = 1;
        JPanel pnlTamaño = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        rdC = new JRadioButton("C");
        rdC.setBackground(fondo);
        setEnabled(true);

        rdM = new JRadioButton("M");
        rdM.setBackground(fondo);
        rdM.setEnabled(true);

        rdG = new JRadioButton("G");
        rdG.setBackground(fondo);
        rdG.setEnabled(true);

        
    rdC.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            if (rdC.isSelected()) {
                ArtTamaños.clearSelection();
            }
        }
    });
    rdM.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            if (rdM.isSelected()) {
                ArtTamaños.clearSelection();
            }
        }
    });
    rdG.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            if (rdG.isSelected()) {
                ArtTamaños.clearSelection();
            }
        }
    });

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


        txtArtNombre.setEnabled(false);
        txtArtDescripcion.setEnabled(false);
        txtArtPrecio.setEnabled(false);

        Color disabledBg = new Color(180, 180, 180);
        txtArtNombre.setDisabledTextColor(Color.BLACK);
        txtArtNombre.setBackground(disabledBg);

        txtArtDescripcion.setDisabledTextColor(Color.BLACK);
        txtArtDescripcion.setBackground(disabledBg);

        txtArtPrecio.setDisabledTextColor(Color.BLACK);
        txtArtPrecio.setBackground(disabledBg);

        // PANEL INFERIOR DERECHO: BOTONES Y RADIOBUTTONS EN COLUMNA
        JPanel pnlBotones = new JPanel(new GridLayout(0, 1, 0, 10));
        pnlBotones.setBackground(fondo);

  
        JPanel pnlRadios = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        pnlRadios.setBackground(fondo);

        btnBuscar = new JButton("BUSCAR");
        btnBuscar.setToolTipText("Buscar campos");
        btnBuscar.addActionListener(this);
        btnBuscar.setBackground(Color.decode("#ECF2F9"));
        pnlBotones.add(btnBuscar);

        btnRestablecer = new JButton("RESTABLECER");
        btnRestablecer.setToolTipText("Restablecer Tabla");
        btnRestablecer.addActionListener(this);
        btnRestablecer.setBackground(Color.decode("#ECF2F9"));
        pnlBotones.add(btnRestablecer);


        JPanel pnlBotonesConMargen = new JPanel(new BorderLayout());
        pnlBotonesConMargen.setBackground(fondo);
        pnlBotonesConMargen.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlBotonesConMargen.add(pnlBotones, BorderLayout.CENTER);

   
        JPanel pnlBuscarConMargen = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlBuscarConMargen.setBackground(fondo);
        pnlBuscarConMargen.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlBuscarConMargen.add(btnBuscar);


        JPanel pnlDerechaContenido = new JPanel(new GridBagLayout());
        pnlDerechaContenido.setBackground(fondo);
        GridBagConstraints gbcDerecha = new GridBagConstraints();
        gbcDerecha.gridx = 0;
        gbcDerecha.gridy = 0;
        gbcDerecha.anchor = GridBagConstraints.CENTER;
        gbcDerecha.weighty = 0;
        gbcDerecha.insets = new Insets(0, 0, 10, 0);
        pnlDerechaContenido.add(pnlCentro, gbcDerecha);

        gbcDerecha.gridy = 1;
        gbcDerecha.insets = new Insets(10, 0, 0, 0);
        pnlDerechaContenido.add(pnlBotonesConMargen, gbcDerecha);

        gbcDerecha.gridy = 2;
        gbcDerecha.insets = new Insets(10, 0, 0, 0);
        pnlDerechaContenido.add(pnlBuscarConMargen, gbcDerecha);

        pnlDerecha.add(pnlDerechaContenido, BorderLayout.CENTER);

        pnlPrincipal.add(pnlDerecha, BorderLayout.EAST);

        add(pnlPrincipal, BorderLayout.CENTER);


        txtArtId.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                tblArticulos.clearSelection();
                tblArticulos.repaint();
            }
        });

        cbxArtFamID.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                tblArticulos.clearSelection();
                tblArticulos.repaint();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBuscar) {
            if (!txtArtId.getText().isEmpty() ) {

                if (cbxArtFamID.getSelectedItem().toString().isEmpty() && checaRadioButtons()) {
                    selectID(Integer.parseInt(txtArtId.getText()));

                } else {
                    JOptionPane.showMessageDialog(this, "Solo se permite utilizar un parametro de busqueda", "Comprobar Borrado", JOptionPane.ERROR_MESSAGE);
                }

            } else if (!cbxArtFamID.getSelectedItem().toString().isEmpty() && checaRadioButtons()) {
                selectFamID(cbxArtFamID.getSelectedItem().toString());

            }else if(!checaRadioButtons()){
                selectSizeID();
                
            }
        }

        if(e.getSource() == btnRestablecer){
            ArticulosModel jtArticulos = new ArticulosModel(conexionDB);
            tblArticulos.setModel(jtArticulos);
            tblArticulos.getSelectionModel().addListSelectionListener(this);

            txtArtId.setText("");
            txtArtNombre.setText("");
            txtArtDescripcion.setText("");
            txtArtPrecio.setText("");
            rdC.setSelected(false);
            rdM.setSelected(false);
            rdG.setSelected(false);
            cbxArtFamID.setSelectedIndex(0);

            repaint();
        }
    }

    public void selectID(int id) {
        ArticulosModel jtArticulos = new ArticulosModel(conexionDB, id);
        if (jtArticulos.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "El artículo con ese ID no existe.", "No encontrado", JOptionPane.ERROR_MESSAGE);
            return;
        }
        tblArticulos.setModel(jtArticulos);
        tblArticulos.getSelectionModel().addListSelectionListener(this);
        actualizarTxtField(0);
        repaint();

    }

    public void selectFamID(String familia) {
        ArticulosModel jtArticulos = new ArticulosModel(conexionDB, familia);
        tblArticulos.setModel(jtArticulos); 
        tblArticulos.getSelectionModel().addListSelectionListener(this);

        repaint();

    }

    public void selectSizeID(){
            String size = null;
    if (rdC.isSelected()) {
        size = "C";
    } else if (rdM.isSelected()) {
        size = "M";
    } else if (rdG.isSelected()) {
        size = "G";
    }

    if (size != null) {
        SelectDBLayer dbLayer = new SelectDBLayer(conexionDB);
        ArticulosModel jtArticulos = new ArticulosModel(conexionDB,size);
        tblArticulos.setModel(jtArticulos);
        tblArticulos.getSelectionModel().addListSelectionListener(this);
        repaint();
    } else {
        JOptionPane.showMessageDialog(this, "Seleccione un tamaño para buscar.", "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    }

    public boolean checaRadioButtons() {
        return !rdC.isSelected() && !rdM.isSelected() && !rdG.isSelected();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
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


}
