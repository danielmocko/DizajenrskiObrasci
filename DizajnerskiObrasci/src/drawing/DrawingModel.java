package drawing;

import java.util.ArrayList;

import javax.swing.JOptionPane;


import geometry.Shape;

public class DrawingModel {
	ArrayList<Shape> shapes = new ArrayList<Shape>();
	ArrayList<Shape> selctedObject = new ArrayList<Shape>();

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}
	
	public void add(Shape o) {
		shapes.add(o);
	}
	
	public void remove(Shape o) {
		shapes.remove(o);
	}

	public ArrayList<Shape> getSelctedObject() {
		return selctedObject;
	}

	public void setSelctedObject(ArrayList<Shape> selctedObject) {
		this.selctedObject = selctedObject;
	}
	
	public int numberSelectedObject() {
		int counter=0;
		for(int i=0;i<shapes.size();i++) {
			if(shapes.get(i).isSelected())
				counter++;
		}
		return counter;
	}
	
	
	
	
}
