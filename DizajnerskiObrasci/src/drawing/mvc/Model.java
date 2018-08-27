package drawing.mvc;

import java.io.Serializable;
import java.util.ArrayList;
import drawing.observer.Observable;
import drawing.observer.Observer;
import geometry.Shape;

public class Model implements Observable,Serializable{
	private ArrayList<Observer> observers;
	private ArrayList<String> logList;
	private ArrayList<Shape> shapes;



	public Model() {
		observers = new ArrayList<Observer>();
		shapes = new ArrayList<Shape>();
		logList= new ArrayList<String>();
	}

	/**************************************************
					Remove function
	 **************************************************/
	public void removeLogList() {
		int size =logList.size();
		for(int i=0;i<size;i++) {
			logList.remove(0);
		}
	}

	public void removeShapesList() {
		while(shapes.size()!=0) {
			shapes.remove(0);
		}
	}

	public void remove(Shape o) {
		shapes.remove(o);
	}


	public void removeByIndex(int index) {
		shapes.remove(index);
	}

	/***********************************************
				select functions
	 ***********************************************/


	public int selectedLastShape() { 
		for(int i=shapes.size()-1;i>=0;i--) {
			if(shapes.get(i).isSelected() && i==0) {
				return 1;
			}
		}
		for(int i=0;i<shapes.size();i++) {
			if(shapes.get(i).isSelected() && i==shapes.size()-1) {
				return 2;
			}
		}
		return 0;
	}

	public Shape getSelectedShape() {
		for(int i=0;i<shapes.size();i++) {
			if(shapes.get(i).isSelected())
				return shapes.get(i);
		}
		return null;
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

	/************************************************
	 					Observer functions
	 ************************************************/

	public void notifyLog() {
		String logList=getLogList().get(getLogList().size()-1);
		for(Observer observer:observers) {
			observer.updateLog(logList);
		}
	}

	public void notifyMenu() {
		int numberSelectedObjects = numberSelectedObject();
		int flag = selectedLastShape();
		int size = listSize();
		for(Observer observer:observers) {
			observer.updateView(numberSelectedObjects,flag,size);
		}
	}

	public int numberSelectedObject() {
		int counter=0;
		for(int i=0;i<shapes.size();i++) {
			if(shapes.get(i).isSelected())
				counter++;
		}
		return counter;
	}

	public void addToLogList(String string) {
		logList.add(string);
		notifyLog();
	}

	public void add(Shape o) {
		shapes.add(o);
		notifyMenu();
	}

	public void change(int i, Shape shape) {
		shapes.remove(i);
		shapes.add(i, shape);
		notifyMenu();
	}

	public void addObserver(Observer addObserver) {
		observers.add(addObserver);
	}

	public int listSize() {
		return shapes.size();
	}

	public int getIndex(Shape shape) {
		for(int i=0;i<shapes.size();i++) {
			if(shapes.get(i).equals(shape)) {
				return i;
			}
		}

		return -1;
	}

	public void addOnIndex(Shape shape,int index) {
		if(listSize()==0) {
			shapes.add(shape);
			return;
		}
		else if(index==listSize()) {
			shapes.add(index, shape);
			return;
		}
		for(int i=0;i<listSize();i++) {
			if(i==index) {
				for(int j=listSize()-1;j>=0;j--) {
					Shape current = shapes.get(i);
					if(j==listSize()-1) {
						shapes.add(j, current);
						shapes.add(index,shape);
						shapes.remove(j+1);
						return;
					}else {
						change(j+1, current);
						if(j==index) {
							shapes.add(j, shape);
							shapes.remove(j+1);
						}
					}
				}
			}
		}
	}

	/************************************************
				Getters and Setters 
	 ************************************************/

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
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