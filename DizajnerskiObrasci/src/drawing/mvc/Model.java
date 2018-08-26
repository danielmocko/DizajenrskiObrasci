package drawing.mvc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.DefaultListModel;

import drawing.command.Command;
import drawing.observer.Observable;
import drawing.observer.Observer;
import geometry.Shape;

public class Model implements Observable,Serializable{
	private ArrayList<Observer> observers;
	private ArrayList<String> logList;
	private ArrayList<Shape> shapes;
	private Stack<Command> executeCommand;
	private Stack<Command> unexecuteCommand;

	public Model() {
		observers = new ArrayList<Observer>();
		shapes = new ArrayList<Shape>();
		logList= new ArrayList<String>();
		executeCommand = new Stack<Command>();
		unexecuteCommand = new Stack<Command>();
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

	public int listSize() {
		return shapes.size();
	}

	public void notifyMenu() {
		int numberSelectedObjects = numberSelectedObject();
		int flag = selectedLastShape();
		int size = listSize();
		for(Observer observer:observers) {
			observer.updateView(numberSelectedObjects,flag,size);
		}
	}

	public void addObserver(Observer addObserver) {
		observers.add(addObserver);
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
		notifyMenu();
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
			
			/*if(i==index) {
				if(index==shapes.size()) {
					//menjan poslednji
					shapes.add(shape);
					return;
				}else {
					//pomeri u levo
					Shape current=shapes.get(i);
					if(i==listSize()-1) {
						shapes.add(i+1, current);
						shapes.remove(i);
						shapes.add(i, shape);
					}else {
						change(i+1, current);
						shapes.add(i,shape);

					}
					return;
				}
			}else {
				if(index==shapes.size()) {
					//poslednji
					shapes.add(shape);
					return;
				}else {
					Shape current=shapes.get(i);
					if(i==listSize()-1) {
						shapes.add(i+1, current);
					}
					else
						change(i+1, current);
				}
			}*/
		}
	}

	public void addToLogList(String string) {
		logList.add(string);
		notifyLog();
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

	public Stack<Command> getExecuteCommand() {
		return executeCommand;
	}

	public void setExecuteCommand(Stack<Command> executeCommand) {
		this.executeCommand = executeCommand;
	}

	public Stack<Command> getUnexecuteCommand() {
		return unexecuteCommand;
	}

	public void setUnexecuteCommand(Stack<Command> unexecuteCommand) {
		this.unexecuteCommand = unexecuteCommand;
	}
	}