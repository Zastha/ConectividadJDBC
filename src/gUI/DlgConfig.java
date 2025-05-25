package gUI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DataLayer.ConnectDBLayer;

public class DlgConfig extends JDialog implements ActionListener{

    public static JLabel lblServidor, lblUsuario, lblContra, lblBD, lblMsj;
	public static JTextField txtServidor, txtUsuario, txtContra, txtBD;
	public static JButton btnConectar, btnConsulta, btnCaptura,btnLimpiar;
	private JPanel PanelDB;
	//Este booleano hara que si esta activado aparezcan los botones, esto para que se asegure que sea el usuario que realizo la conexion
	//Es es necesario por que se usaran los datos que estan en los campos de texto para abrir los consulta y modificar
	private boolean exitoso=false;

    Consulta VentConsulta;
    Captura VentCaptura;
	Connection con;

    public DlgConfig(JFrame marco, boolean nuevo) {
		super(marco,"Login");
		getContentPane().setLayout(new BorderLayout());
		setSize(320,400);
		setLocationRelativeTo(null);
		
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		InitComponents();
	}

    private void InitComponents(){
        InitPanelLogin();
    }

        private void InitPanelLogin(){
        PanelDB = new JPanel();
        PanelDB.setLayout(null); 
		PanelDB.setBackground(Color.decode("#d7e7eb"));

        lblServidor = new JLabel("Servidor:");
        lblServidor.setBounds(10, 20, 100, 25);
        PanelDB.add(lblServidor);

        txtServidor = new JTextField();
        txtServidor.setBounds(140, 20, 160, 25);
		txtServidor.setBackground(Color.WHITE);
		txtServidor.setBorder(null);
		txtServidor.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        PanelDB.add(txtServidor);

        lblBD = new JLabel("Base de Datos:");
        lblBD.setBounds(10, 60, 100, 25);
        PanelDB.add(lblBD);

        txtBD = new JTextField();
        txtBD.setBounds(140, 60, 160, 25);
		txtBD.setBackground(Color.WHITE);
		txtBD.setBorder(null);
		txtBD.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        PanelDB.add(txtBD);

        lblUsuario = new JLabel("Inicio de Sesión:");
        lblUsuario.setBounds(10, 100, 120, 25);
        PanelDB.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(140, 100, 160, 25);
		txtUsuario.setBackground(Color.WHITE);
		txtUsuario.setBorder(null);
		txtUsuario.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        PanelDB.add(txtUsuario);

        lblContra = new JLabel("Contraseña:");
        lblContra.setBounds(10, 140, 100, 25);
        PanelDB.add(lblContra);

        txtContra = new JPasswordField();
        txtContra.setBounds(140, 140, 160, 25);
		txtContra.setBackground(Color.WHITE);
		txtContra.setBorder(null);
		txtContra.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        PanelDB.add(txtContra);

		btnConectar = new JButton("CONECTAR");
		btnConectar.setBounds(60, 190, 90, 30);
		btnConectar.addActionListener(this);
		btnConectar.setBackground(Color.decode("#ECF2F9"));
		PanelDB.add(btnConectar);

		btnLimpiar = new JButton("LIMPIAR");
		btnLimpiar.setBounds(170, 190, 90, 30); 
		btnLimpiar.addActionListener(this);
		btnLimpiar.setBackground(Color.decode("#ECF2F9"));
		PanelDB.add(btnLimpiar);

		btnCaptura = new JButton("CAPTURA");
		btnCaptura.setBounds(55, 250, 90, 30);
		btnCaptura.addActionListener(this);
		btnCaptura.setVisible(true);
		btnCaptura.setVisible(false);
		btnCaptura.setBackground(Color.decode("#ECF2F9"));
		PanelDB.add(btnCaptura);

		btnConsulta = new JButton("CONSULTA");
		btnConsulta.setBounds(165, 250, 90, 30);
		btnConsulta.addActionListener(this);
		btnConsulta.setVisible(true);
		btnConsulta.setBackground(Color.decode("#ECF2F9"));
		btnConsulta.setVisible(false); // <-- Cambia a invisible
		PanelDB.add(btnConsulta);

		lblMsj = new JLabel("");
        lblMsj.setBounds(10, 320, 350, 25);
        PanelDB.add(lblMsj);

        add(PanelDB, BorderLayout.CENTER);

		txtServidor.setFont(new Font("Arial", Font.BOLD, 13));
		txtBD.setFont(new Font("Arial", Font.BOLD, 13));
		txtUsuario.setFont(new Font("Arial", Font.BOLD, 13));
		txtContra.setFont(new Font("Arial", Font.BOLD, 13));

		lblServidor.setFont(new Font("Arial", Font.BOLD, 13));
		lblBD.setFont(new Font("Arial", Font.BOLD, 13));
		lblUsuario.setFont(new Font("Arial", Font.BOLD, 13));
		lblContra.setFont(new Font("Arial", Font.BOLD, 13));

		btnConectar.setMargin(new Insets(10, 5, 10, 5));
		btnCaptura.setMargin(new Insets(10, 5, 10, 5));
		btnConsulta.setMargin(new Insets(10, 5, 10, 5));

		//Perfil con permisos para facilitar pruebas
		txtServidor.setText("MSI");
		txtBD.setText("Ventas");
		txtUsuario.setText("Mark");
		txtContra.setText("123");

				txtServidor.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(javax.swing.event.DocumentEvent e) { visibilidadBotones(false); }
			public void removeUpdate(javax.swing.event.DocumentEvent e) { visibilidadBotones(false); }
			public void changedUpdate(javax.swing.event.DocumentEvent e) { visibilidadBotones(false); }
		});
		txtBD.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(javax.swing.event.DocumentEvent e) { visibilidadBotones(false); }
			public void removeUpdate(javax.swing.event.DocumentEvent e) { visibilidadBotones(false); }
			public void changedUpdate(javax.swing.event.DocumentEvent e) { visibilidadBotones(false); }
		});
		txtUsuario.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(javax.swing.event.DocumentEvent e) { visibilidadBotones(false); }
			public void removeUpdate(javax.swing.event.DocumentEvent e) { visibilidadBotones(false); }
			public void changedUpdate(javax.swing.event.DocumentEvent e) { visibilidadBotones(false); }
		});
		txtContra.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(javax.swing.event.DocumentEvent e) { visibilidadBotones(false); }
			public void removeUpdate(javax.swing.event.DocumentEvent e) { visibilidadBotones(false); }
			public void changedUpdate(javax.swing.event.DocumentEvent e) { visibilidadBotones(false); }
		});
    }

		public void visibilidadBotones(boolean estado){
		btnCaptura.setVisible(estado);
		btnConsulta.setVisible(estado);
	}
    
    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == btnConectar) {
			
			
			visibilidadBotones(conexionHandler());
		}

		if(e.getSource()==btnLimpiar){
            txtBD.setText("");
            txtServidor.setText("");
            txtUsuario.setText("");
            txtContra.setText("");

        }
	
		if (e.getSource() == btnConsulta) {
			if(exitoso){
				
				VentConsulta = new Consulta(con);
				VentConsulta.setVisible(true);
			}
		
			
		}
	
		if (e.getSource() == btnCaptura) {
			if(exitoso){
				VentCaptura = new Captura(con);
			VentCaptura.setVisible(true);
			}
			
		}
    }

	public boolean conexionHandler(){
		String servidor = txtServidor.getText();
			String DB = txtBD.getText();
			String usuario = txtUsuario.getText();
			String contra = txtContra.getText();

			if(!servidor.isEmpty() && !DB.isEmpty() && !usuario.isEmpty() ){
            ConnectDBLayer conexion = new ConnectDBLayer(servidor, DB, usuario,contra);
            int estado = conexion.getEstado();
            mostrarRespuestaConexion(estado);
			if (estado==999){
				con = conexion.getConexion();
				exitoso =true;
				return true;
			}
        }else{

					JOptionPane.showMessageDialog( 
                null, "Por favor ingrese correctamente los datos", 
                "Error", JOptionPane.ERROR_MESSAGE);
				return false;

        }
		return false;


	}

	public void mostrarRespuestaConexion(int estado){
		 if(estado == 999){
            JOptionPane.showMessageDialog( 
                null, "Conexion Exitosa!", 
                "Conexion Establecida", JOptionPane.INFORMATION_MESSAGE);

				//En caso de ser exitosa se puede hacer la funcionalidad para que aparezcan los botones consulta y modificacion
				exitoso=true;

        }else if(estado == 18456){

			JOptionPane.showMessageDialog( 
                null, "Los Datos de Login Son incorrectos.Verifique el Usuario y Contraseña", 
                "Error", JOptionPane.ERROR_MESSAGE);

        }else if(estado==4060){
           
            JOptionPane.showMessageDialog( 
                null, "Este usuario no tiene acceso a esta base de datos", 
                "Error", JOptionPane.ERROR_MESSAGE);

        }

	}

}
