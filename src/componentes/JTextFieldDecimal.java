package componentes;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;

public class JTextFieldDecimal extends JTextField implements KeyListener{
	
	private static final long serialVersionUID = 1L;
	private int CantEnteros, CantDecimales;
	
	public JTextFieldDecimal(int Enteros, int Decimales) {
		super();
		CantEnteros = Enteros;
		CantDecimales = Decimales;
		addKeyListener(this);
	}
	
	
	public void keyPressed(KeyEvent e) {
		if(e.isControlDown());
		if(e.getKeyCode() == KeyEvent.VK_X || e.getKeyCode() == KeyEvent.VK_C || e.getKeyCode() == KeyEvent.VK_V)
			e.consume();

		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && (getCaretPosition() == (getText().indexOf(".") + 1)) && ((getText().indexOf(".") + 1) != getText().length()) ) 
			e.consume();
		
	}
	
	public void keyTyped(KeyEvent e) {

		// VERIFICAR TAMAÑO 
		if(getText().length() == (CantEnteros + CantDecimales + 1))
			e.consume();

		// VERIFICAR QUE EL CARACTER INTRODUCIDO SE UN NUMERO O UN PUNTO
		else if(!((e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || e.getKeyChar() == '.'))
			e.consume();

		// VERIFICAR QUE NO HAYA MAS DE UN PUNTO
		else if(e.getKeyChar() == '.' && (getText().contains(".") || (getText().length() - getCaretPosition()) > CantDecimales)){
			e.consume();
		}
            
		//EVITA LOS CEROS AL INICIO
		else if ((getCaretPosition() == 0) && (getText().contains(".")) && (getText().indexOf(".") < CantEnteros) && (e.getKeyChar() == '0')) {
			e.consume();
		}

		else if(getText().indexOf("0") == 0 && getCaretPosition() == 1 && e.getKeyChar() != '.'){
			e.consume();
		}

		//SOLO PODER PONER UN PUNTO SI LOS ENTEROS YA ESTAN LLENOS
		else if (getText().length() == CantEnteros && e.getKeyChar() != '.' && !(getText().contains("."))) {
			e.consume();
		}

		//VERIFICAR QUE SE RESPETEN LA CANTIDAD DE ENTEROS
		else if (getText().indexOf(".") == CantEnteros && getCaretPosition() <= CantEnteros) {
			e.consume();
		}

		//VERIFICAR QUE SE RESPETEN LA CANTIDAD DE DECIMALES
		else if (getText().contains(".") && (getText().length() - ((getText().indexOf(".") + 1)) == CantDecimales) && getCaretPosition() > getText().indexOf(".")) {
			e.consume();
		}
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			while (getText().indexOf("0") == 0 && getText().indexOf(".") != 1) {
				setText(getText().substring(1, getText().length()));
			}
		}
	}

}
