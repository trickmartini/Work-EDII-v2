package gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import graph.GraphAlgo;
import graph.Program;
import graph.Vertice;
import gui.GraphPanel;
import gui.labels.OutputLabel;
import gui.lists.ListOfVertices;
import gui.textfields.TextFieldHandler;


public class ButtonDijkstra extends JButton {

	private static final long serialVersionUID = 102746757120517599L;

	public ButtonDijkstra() {
		super("Calculate");
		
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Vertice start = Program.findVerticeByName(TextFieldHandler.getDistance1().getText());
				Vertice end = Program.findVerticeByName(TextFieldHandler.getDistance2().getText());
				if(start == null) {
					String name = "\"" +TextFieldHandler.getDistance1().getText() + "\"";
					JOptionPane.showMessageDialog(null, name + " is not a exsisting Vertice.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(end == null) {
					String name = "\"" +TextFieldHandler.getDistance2().getText() + "\"";
					JOptionPane.showMessageDialog(null, name + " is not a exsisting Vertice.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				String message = GraphAlgo.startDijkstra(start, end);
				OutputLabel.getLabel().setText(message);
			}
		});
	}
}
