package drawing;

import java.util.ArrayList;

import javax.swing.JOptionPane;


import geometry.Shape;

public class DrawingModel {
	ArrayList<Shape> shapes = new ArrayList<Shape>();

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}
	
	public void add(Shape o) {
		shapes.add(o);
	}
	
	
}
