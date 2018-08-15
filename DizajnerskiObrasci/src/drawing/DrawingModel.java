package drawing;

import java.util.ArrayList;

import geometry.Shape;

public class DrawingModel implements Observable {
	private ArrayList<Observer> observers;
	
	ArrayList<Shape> shapes = new ArrayList<Shape>();

	public DrawingModel() {
		observers = new ArrayList<Observer>();
	}
	
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
		
		for(Observer observer:observers) {
			observer.update(numberSelectedObjects);
		}
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
	
	public void change(int i, Shape shape) {
		shapes.remove(i);
		shapes.add(i, shape);
	}

	@Override
	public void addObserver(Observer addObserver) {
		observers.add(addObserver);
	}
}