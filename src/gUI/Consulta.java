package gUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DataLayer.ConnectDBLayer;

public class Consulta extends JFrame implements ActionListener, KeyListener, FocusListener, MouseListener {

    private static final long serialVersionUID = 1L;

    ButtonGroup ArtTamaños = new ButtonGroup();
    public static JRadioButton rdC, rdM, rdG;
    public static JTable tblArticulos;
    private DefaultTableModel tblModel;

    private JButton btnBuscar;
    private JComboBox<String> cbxArtFamID;
    private JTextField txtArtId, txtArtNombre, txtArtDescripcion, txtArtPrecio;

    Connection conexionDB;

    public Consulta(Connection con) {
        super("Consulta");
          conexionDB =con;
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
        tblArticulos.getTableHeader().setBackground(Color.decode("#133E87"));
        tblArticulos.getTableHeader().setForeground(Color.WHITE);
        tblArticulos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tblArticulos.setFont(new Font("Arial", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(tblArticulos);

        // Panel auxiliar para margen
        JPanel pnlTablaConMargen = new JPanel(new BorderLayout());
        pnlTablaConMargen.setBackground(fondo);
        pnlTablaConMargen.setBorder(new EmptyBorder(10, 10, 10, 10));
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
        gbc.insets = new Insets(5, 5, 5, 5);

        // Ejemplo de campos (puedes adaptar según Captura.java)
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
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        pnlCentro.add(new JLabel("Familia: ", JLabel.RIGHT), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        cbxArtFamID = new JComboBox<>();
        cbxArtFamID.setPreferredSize(new Dimension(150, 20));
        pnlCentro.add(cbxArtFamID, gbc);
        cbxArtFamID.setEditable(false);

        txtArtId.setBorder(null);
        txtArtNombre.setBorder(null);
        txtArtDescripcion.setBorder(null);
        txtArtPrecio.setBorder(null);
        cbxArtFamID.setBorder(null);

        // PANEL INFERIOR DERECHO: BOTONES Y RADIOBUTTONS EN COLUMNA
        JPanel pnlBotones = new JPanel(new GridLayout(0, 1, 0, 10));
        pnlBotones.setBackground(fondo);

        // Panel para los radio buttons en una fila
        JPanel pnlRadios = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        pnlRadios.setBackground(fondo);

        btnBuscar = new JButton("BUSCAR");
        btnBuscar.setToolTipText("Buscar campos");
        btnBuscar.addActionListener(this);
        btnBuscar.setBackground(Color.decode("#ECF2F9"));
        pnlBotones.add(btnBuscar);

        // Panel auxiliar para margen
        JPanel pnlBotonesConMargen = new JPanel(new BorderLayout());
        pnlBotonesConMargen.setBackground(fondo);
        pnlBotonesConMargen.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlBotonesConMargen.add(pnlBotones, BorderLayout.CENTER);

        // Panel auxiliar para margen del botón buscar
        JPanel pnlBuscarConMargen = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlBuscarConMargen.setBackground(fondo);
        pnlBuscarConMargen.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlBuscarConMargen.add(btnBuscar);

        // Panel para centrar verticalmente el contenido derecho
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Implementa la lógica de los botones aquí
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void focusGained(FocusEvent e) {}

    @Override
    public void focusLost(FocusEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
