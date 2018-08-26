package drawing.mvc;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ListIterator;

import javax.swing.JPanel;


import geometry.Shape;

public class View extends JPanel {
	private Model model;
	private Frame frame;
	private ListIterator<Shape> itShapes;

	public View() {
		
	}

	public void paint(Graphics g) {
		super.paint(g);
		if(!model.getShapes().isEmpty()) {
			itShapes = model.getShapes().listIterator();
			while(itShapes.hasNext()) {
				Shape s =(Shape) itShapes.next();
				s.drawColor(g);
			}
		}

		repaint();
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}


}
