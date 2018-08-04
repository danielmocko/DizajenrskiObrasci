package drawing;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


import geometry.Shape;

public class DrawingModel implements Observable {
	Observer observer= new Frame();
	ArrayList<Shape> shapes = new ArrayList<Shape>();

	
	
	public void selectObject(int index){
		shapes.get(index).setSelected(true);
		notifyObserver();
	}
	
	public void diselectObject(int index) {
		shapes.get(index).setSelected(false);
		notifyObserver();
	}

	public void notifyObserver() {
		int numberSelectedObjects = numberSelectedObject();
		
		observer.update(numberSelectedObjects);
	}
	
	public void add(Shape o) {
		shapes.add(o);
	}
	
	public void remove(Shape o) {
		shapes.remove(o);
	}
	
	public int numberSelectedObject() {
		int counter=0;
		for(int i=0;i<shapes.size();i++) {
			if(shapes.get(i).isSelected())
				counter++;
		}
		return counter;
	}
	
	public void removeByIndex(int index) {
		shapes.remove(index);
	}
	
	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}
}