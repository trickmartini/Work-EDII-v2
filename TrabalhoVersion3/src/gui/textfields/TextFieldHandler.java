package gui.textfields;

import java.awt.Dimension;

import javax.swing.JTextField;


public class TextFieldHandler {

	public static final int TEXTFIELD_HEIGTH = 20;
	public static final int TEXTFIELD_WIDTH = 150;
	
	private static JTextField vertice1, vertice2, weight, distance1, distance2;
	
	private TextFieldHandler() {
		
	}
	
	public static JTextField getVertice1() {
		if(null == vertice1) {
			vertice1 = new JTextField();
			vertice1.setPreferredSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGTH));
		}
		return vertice1;
	}
	
	public static JTextField getVertice2() {
		if(null == vertice2) {
			vertice2 = new JTextField();
			vertice2.setPreferredSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGTH));
		}
		return vertice2;
	}
	
	public static JTextField getWeight() {
		if(null == weight) {
			weight = new JTextField();
			weight.setPreferredSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGTH));
		}
		return weight;
	}
	
	public static JTextField getDistance1() {
		if(null == distance1) {
			distance1 = new JTextField();
			distance1.setPreferredSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGTH));
		}
		return distance1;
	}
	
	public static JTextField getDistance2() {
		if(null == distance2) {
			distance2 = new JTextField();
			distance2.setPreferredSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGTH));
		}
		return distance2;
	}
}
