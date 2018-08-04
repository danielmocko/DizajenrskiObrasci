package drawing;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


import geometry.Shape;

public class DrawingModel implements Observable {
	Observer observer= new Frame();
	ArrayList<Shape> shapes = new ArrayList<Shape>();

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}
	
	
	public void notifyObserver() {
		int numberSelectedObjects = numberSelectedObject();
		
		observer.update(numberSelectedObjects);
	}
	
	public void add(Shape o) {
		shapes.add(o);
		notifyObserver();
	}
	
	public void remove(Shape o) {
		shapes.remove(o);
		notifyObserver();
	}
	
	public int numberSelectedObject() {
		int counter=0;
		for(int i=0;i<shapes.size();i++) {
			if(shapes.get(i).isSelected())
				counter++;
		}
		return counter;
	}
	
	public void removeByUndex(int index) {
		shapes.remove(index);
	}

	
	
	
}
