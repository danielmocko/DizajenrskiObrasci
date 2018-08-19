package drawing;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import geometry.Shape;

public class DrawingModel implements Observable {
	private ArrayList<Observer> observers;
	private ArrayList<String> logList;
	private ArrayList<Shape> shapes;

	public DrawingModel() {
		observers = new ArrayList<Observer>();
		shapes = new ArrayList<Shape>();
		logList= new ArrayList<String>();
	}

	public void selectObject(Shape shape){
		for(int i=0;i<shapes.size();i++) {
			if(shapes.get(i).equals(shape)) {
				shapes.get(i).setSelected(true);
				notifyMenu();
			}
		}
	}

	public void diselectObject(Shape shape) {
		for(int i=0;i<shapes.size();i++) {
			if(shapes.get(i).equals(shape)) {
				shapes.get(i).setSelected(false);
				notifyMenu();
			}
		}
	} 

	public void notifyMenu() {
		int numberSelectedObjects = numberSelectedObject();

		for(Observer observer:observers) {
			observer.updateView(numberSelectedObjects);
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

	public Shape getSelectedShape() {
		Shape shape; 
		for(int i=0;i<shapes.size();i++) {
			if(shapes.get(i).isSelected())
				return shapes.get(i);
		}
		return null;
	}

	public void removeByIndex(int index) {
		shapes.remove(index);
	}
	
	public int getIndex(Shape shape) {
		
		for(int i=0;i<shapes.size();i++) {
			if(shapes.get(i).equals(shape)) {
				return i;
			}
		}
		
		return -1;
	}


	public void notifyLog() {
		String logList=getLogList().get(getLogList().size()-1);
		for(Observer observer:observers) {
			observer.updateLog(logList);
		}
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

	public void addToLogList(String string) {
		logList.add(string);
		notifyLog();
	}

	@Override
	public void addObserver(Observer addObserver) {
		observers.add(addObserver);
	}

	public ArrayList<Observer> getObservers() {
		return observers;
	}

	public void setObservers(ArrayList<Observer> observers) {
		this.observers = observers;
	}

	public ArrayList<String> getLogList() {
		return logList;
	}
}