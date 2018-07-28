package drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ListIterator;

import javax.swing.JPanel;


import geometry.Shape;

public class DrawingView extends JPanel {
	private DrawingModel model;
	private Frame frame;
	
	public DrawingView() {
		//setBackground(Color.WHITE);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		ListIterator<Shape>itShapes = model.shapes.listIterator();
		while(itShapes.hasNext()) {
			Shape s =(Shape) itShapes.next();
			s.drawColor(g);
		}
		repaint();
	}

	public DrawingModel getModel() {
		return model;
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}

	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}
	
	
}
