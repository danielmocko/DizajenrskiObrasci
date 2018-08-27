package drawing.mvc;


import java.awt.Color;


import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Stack;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import drawing.command.Command;
import drawing.command.CommandAdd;
import drawing.command.CommandBringToBack;
import drawing.command.CommandBringToFront;
import drawing.command.CommandDiselect;
import drawing.command.CommandModify;
import drawing.command.CommandRemove;
import drawing.command.CommandSelect;
import drawing.command.CommandToBack;
import drawing.command.CommandToFront;
import drawing.dialogs.DialogCircle;
import drawing.dialogs.DialogHexagon;
import drawing.dialogs.DialogLine;
import drawing.dialogs.DialogPoint;
import drawing.dialogs.DialogRectangle;
import drawing.dialogs.DialogSquare;
import drawing.strategy.FileContext;
import drawing.strategy.LogStrategy;
import drawing.strategy.ImageStrategy;
import geometry.*;

public class Controller {
	private Model model;
	private Frame frame;

	private DialogCircle dlgCircle;
	private DialogSquare dlgSquare;
	private DialogRectangle dlgRectangle;
	private DialogHexagon dlgHexagon;
	private DialogPoint dlgPoint;
	private DialogLine dlgLine;
	private Color edgeColor,insideColor;
	private Stack<Command> executeCommand;
	private Stack<Command> unexecuteCommand;
	private Stack<String> loadStack;

	private HexagonAdapter hexagon;
	private Rectangle rectangle;
	private Circle circle;
	private Line line;
	private Square square;
	private Point point;
	private Point t1;
	private Point t2;
	private int click=0;
	private int x,y;
	private boolean selected=false;

	public Controller(Model model,Frame frame) {
		this.model=model;
		this.frame=frame;
		executeCommand = new Stack<Command>();
		unexecuteCommand = new Stack<Command>();
		loadStack = new Stack<String>();
	}

	public void panelClick(MouseEvent e) {
		if(frame.getTglbtnPoint().isSelected()) {
			diselect();
			x = e.getX();
			y = e.getY();
			Point t = new Point(x,y);

			t.setBorderColor(frame.getBtnEdgeColor().getBackground());
			model.addToLogList("Added --> "+t);
			addInStack(new CommandAdd(model, t));
		}
		else if(frame.getTglbtnLine().isSelected()) {
			diselect();
			if(click==0) {
				int x=e.getX();
				int y=e.getY();
				t1= new Point(x,y);
				click++;
			}
			else if(click==1) {
				int x=e.getX();
				int y=e.getY();
				t2 = new Point(x,y);

				Line l = new Line(t1,t2);
				l.setBorderColor(frame.getBtnEdgeColor().getBackground());
				addInStack(new CommandAdd(model, l));
				click=0;	
				model.addToLogList("Added --> "+l);
			}
		}
		else if(frame.getTglbtnCircle().isSelected()) {
			diselect();
			x=e.getX();
			y=e.getY();
			dlgCircle= new DialogCircle();
			dlgCircle.getTxtXCenter().setText(String.valueOf(x));
			dlgCircle.getTxtYCenter().setText(String.valueOf(y));
			dlgCircle.getBtnInsideColor().setBackground(frame.getBtnInsideColor().getBackground());
			dlgCircle.getBtnEdgeColor().setBackground(frame.getBtnEdgeColor().getBackground());

			dlgCircle.getTxtXCenter().setEditable(false);
			dlgCircle.getTxtYCenter().setEditable(false);
			dlgCircle.getBtnInsideColor().setEnabled(false);
			dlgCircle.getBtnEdgeColor().setEnabled(false);

			dlgCircle.setVisible(true);
			if(dlgCircle.isAccept()) {
				circle = dlgCircle.getCircle();
				addInStack(new CommandAdd(model, circle));
				model.addToLogList("Added --> "+circle);
			}
		}
		else if(frame.getTglbtnSquare().isSelected()) {
			diselect();
			x=e.getX();
			y=e.getY();
			dlgSquare= new DialogSquare();
			dlgSquare.getTxtXCoordinate().setText(String.valueOf(x));
			dlgSquare.getTxtYCoordinate().setText(String.valueOf(y));
			dlgSquare.getBtnEdgeColor().setBackground(frame.getBtnEdgeColor().getBackground());
			dlgSquare.getBtnInsideColor().setBackground(frame.getBtnInsideColor().getBackground());

			dlgSquare.getTxtXCoordinate().setEditable(false);
			dlgSquare.getTxtYCoordinate().setEditable(false);
			dlgSquare.getBtnEdgeColor().setEnabled(false);
			dlgSquare.getBtnInsideColor().setEnabled(false);

			dlgSquare.setVisible(true);
			if(dlgSquare.isAccept()) {
				square = dlgSquare.getSquareDialog();
				model.addToLogList("Added --> "+square);
				addInStack(new CommandAdd(model,square));
			}
		}
		else if(frame.getTglbtnRectangle().isSelected()) {
			diselect();
			x=e.getX();
			y=e.getY();

			dlgRectangle= new DialogRectangle();
			dlgRectangle.getTxtXCoordinate().setText(String.valueOf(x));
			dlgRectangle.getTxtYCoordinate().setText(String.valueOf(y));
			dlgRectangle.getBtnEdgeColor().setBackground(frame.getBtnEdgeColor().getBackground());
			dlgRectangle.getBtnInsideColor().setBackground(frame.getBtnInsideColor().getBackground());

			dlgRectangle.getTxtXCoordinate().setEditable(false);
			dlgRectangle.getTxtYCoordinate().setEditable(false);
			dlgRectangle.getBtnEdgeColor().setEnabled(false);
			dlgRectangle.getBtnInsideColor().setEnabled(false);

			dlgRectangle.setVisible(true);
			if(dlgRectangle.isAccept()) {
				rectangle = dlgRectangle.getDlgRectangle();
				model.addToLogList("Added --> "+rectangle);
				addInStack(new CommandAdd(model, rectangle));
			}
		}else if(frame.getTglbtnHexagon().isSelected()) {
			diselect();
			x=e.getX();
			y=e.getY();
			dlgHexagon= new DialogHexagon();
			dlgHexagon.getTxtXCenter().setText(String.valueOf(x));
			dlgHexagon.getTxtYCenter().setText(String.valueOf(y));
			dlgHexagon.getBtnInsideColor().setBackground(frame.getBtnInsideColor().getBackground());
			dlgHexagon.getBtnEdgeColor().setBackground(frame.getBtnEdgeColor().getBackground());

			dlgHexagon.getTxtXCenter().setEditable(false);
			dlgHexagon.getTxtYCenter().setEditable(false);
			dlgHexagon.getBtnInsideColor().setEnabled(false);
			dlgHexagon.getBtnEdgeColor().setEnabled(false);

			dlgHexagon.setVisible(true);
			if(dlgHexagon.isAccept()) {
				hexagon = dlgHexagon.getHexagon();
				model.addToLogList("Added --> "+hexagon);
				addInStack(new CommandAdd(model, hexagon));
			}
		}
		else if(frame.getTglbtnSelect().isSelected()) {

			x=e.getX();
			y=e.getY();
			selected = false;

			for(int i=model.getShapes().size()-1;i>=0;i--)
			{
				if(model.getShapes().get(i).contains(x, y)) {
					if(!model.getShapes().get(i).isSelected()) {
						selected=true;
						model.addToLogList("Selected --> "+model.getShapes().get(i).toString());
						addInStack(new CommandSelect(model, model.getShapes().get(i)));
						return;
					}else {
						addInStack(new CommandDiselect(model, model.getShapes().get(i)));
						model.addToLogList("Diselected --> "+model.getShapes().get(i));
						return;
					}
				}
			}

			if(selected ==false) {
				diselect();
			}
		}
	}

	public void diselect() {
		if(model.numberSelectedObject()>1) {
			for(int i=0;i<model.getShapes().size();i++) {
				if(model.getShapes().get(i).isSelected())
					addInStack(new CommandDiselect(model, model.getShapes().get(i)));
			}
			model.addToLogList("Diselected --> All selected objects");
		}
		else if(model.numberSelectedObject()==1) {
			for(int i=0;i<model.getShapes().size();i++) {
				if(model.getShapes().get(i).isSelected()) {
					addInStack(new CommandDiselect(model, model.getShapes().get(i)));
					model.addToLogList("Diselected --> "+model.getShapes().get(i));
					return;
				}
			}
		}
	}

	public void modify(ActionEvent e) {
		ListIterator<Shape> it1 = model.getShapes().listIterator(model.getShapes().size());

		while(it1.hasPrevious()) {
			Shape shape = (Shape) it1.previous();
			if(shape.isSelected()==true) {
				if (shape instanceof Point) {
					Point point = (Point) shape;
					dlgPoint = new DialogPoint();
					dlgPoint.getTxtXCoordinate().setText(String.valueOf(point.getX()));
					dlgPoint.getTxtYCoordinate().setText(String.valueOf(point.getY()));
					dlgPoint.getBtnColor().setBackground(point.getBorderColor());
					dlgPoint.setVisible(true);
					if (dlgPoint.isAccept()) {

						this.point = dlgPoint.getDlgPoint();
						this.point.setSelected(true);
						frame.getTglbtnSelect().setSelected(true);
						addInStack(new CommandModify(model, point, this.point));
						model.addToLogList("Modifyed --> *OldState: "+point+" *NewState: "+this.point);
					} 
					return;
				}else if(shape instanceof Line) {
					Line line=(Line)shape;
					dlgLine = new DialogLine();
					dlgLine.getTxtXCoordinateStartPoint().setText(String.valueOf(line.getpStart().getX()));
					dlgLine.getTxtYCoordinateStartPoint().setText(String.valueOf(line.getpStart().getY()));
					dlgLine.getTxtXCoordinateEndPoint().setText(String.valueOf(line.getpEnd().getX()));
					dlgLine.getTxtYCoordinateEndPoint().setText(String.valueOf(line.getpEnd().getY()));
					dlgLine.getBtnColorDlg().setBackground(line.getBorderColor());
					dlgLine.setVisible(true);
					if(dlgLine.isAccept()) {

						this.line = dlgLine.getDlgLine();
						this.line.setSelected(true);
						frame.getTglbtnSelect().setSelected(true);
						addInStack(new CommandModify(model, line, this.line));
						model.addToLogList("Modifyed --> *OldState: "+line+" *NewState: "+this.line);
					}
					return;
				}else if(shape instanceof Circle) {
					Circle circle=(Circle)shape;
					dlgCircle = new DialogCircle();
					dlgCircle.getTxtXCenter().setText(String.valueOf(circle.getCenter().getX()));
					dlgCircle.getTxtYCenter().setText(String.valueOf(circle.getCenter().getY()));
					dlgCircle.getTxtRadius().setText(String.valueOf(circle.getR()));
					dlgCircle.getBtnEdgeColor().setBackground(circle.getBorderColor());
					dlgCircle.getBtnInsideColor().setBackground(circle.getAreaColor());
					dlgCircle.setVisible(true);
					if(dlgCircle.isAccept()) {

						this.circle = dlgCircle.getCircle();
						this.circle.setSelected(true);
						frame.getTglbtnSelect().setSelected(true);
						addInStack(new CommandModify(model, circle, this.circle));
						model.selectObject(this.circle);
						model.addToLogList("Modifyed --> *OldState: "+circle+" *NewState: "+this.circle);
					}
					return;
				}
				else if(shape instanceof Rectangle) {
					Rectangle rectangle = (Rectangle) shape;
					dlgRectangle = new DialogRectangle();
					dlgRectangle.getTxtXCoordinate().setText(String.valueOf(rectangle.getUpLeft().getX()));
					dlgRectangle.getTxtYCoordinate().setText(String.valueOf(rectangle.getUpLeft().getY()));
					dlgRectangle.getTxtWidth().setText(String.valueOf(rectangle.getPageLength()));
					dlgRectangle.getTxtHeight().setText(String.valueOf(rectangle.getHeight()));
					dlgRectangle.getBtnEdgeColor().setBackground(rectangle.getBorderColor());
					dlgRectangle.getBtnInsideColor().setBackground(rectangle.getAreaColor());
					dlgRectangle.setVisible(true);
					if(dlgRectangle.isAccept()) {

						this.rectangle = dlgRectangle.getDlgRectangle();
						this.rectangle.setSelected(true);
						frame.getTglbtnSelect().setSelected(true);
						addInStack(new CommandModify(model, rectangle, this.rectangle));
						model.addToLogList("Modifyed --> *OldState: "+rectangle+" *NewState: "+this.rectangle);
					}
					return;
				}
				else if(shape instanceof Square) {
					Square square = (Square)shape;
					dlgSquare = new DialogSquare();

					dlgSquare.getTxtXCoordinate().setText(String.valueOf(square.getUpLeft().getX()));
					dlgSquare.getTxtYCoordinate().setText(String.valueOf(square.getUpLeft().getY()));
					dlgSquare.getTxtSide().setText(String.valueOf(square.getPageLength()));
					dlgSquare.getBtnEdgeColor().setBackground(square.getBorderColor());
					dlgSquare.getBtnInsideColor().setBackground(square.getAreaColor());
					dlgSquare.setVisible(true);
					if(dlgSquare.isAccept()) {

						this.square=dlgSquare.getSquareDialog();
						this.square.setSelected(true);
						frame.getTglbtnSelect().setSelected(true);
						addInStack(new CommandModify(model, square, this.square));
						model.addToLogList("Modifyed --> *OldState: "+square+" *NewState: "+this.square);
					}
					return;
				}
				else if(shape instanceof HexagonAdapter) {
					HexagonAdapter hexagonAdapter = (HexagonAdapter)shape;

					dlgHexagon=new DialogHexagon();
					dlgHexagon.getTxtXCenter().setText(String.valueOf(hexagonAdapter.getHexagon().getX()));
					dlgHexagon.getTxtYCenter().setText(String.valueOf(hexagonAdapter.getHexagon().getY()));
					dlgHexagon.getTxtRadius().setText(String.valueOf(hexagonAdapter.getHexagon().getR()));
					dlgHexagon.getBtnEdgeColor().setBackground(hexagonAdapter.getHexagon().getBorderColor());
					dlgHexagon.getBtnInsideColor().setBackground(hexagonAdapter.getHexagon().getAreaColor());
					dlgHexagon.setVisible(true);
					if(dlgHexagon.isAccept()) {

						this.hexagon= dlgHexagon.getHexagon();
						this.hexagon.setSelected(true);
						frame.getTglbtnSelect().setSelected(true);
						addInStack(new CommandModify(model, hexagonAdapter, this.hexagon));
						model.addToLogList("Modifyed --> *OldState: "+hexagonAdapter+" *NewState: "+this.hexagon);
					}
					return;
				}
			}
		}
	}

	public void delete(ActionEvent e) {
		int result;
		if(model.numberSelectedObject()==1) {
			result = JOptionPane.showConfirmDialog(null,
					"Do you want to delete selected object ?", "Choose",
					JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				for(int i=model.getShapes().size()-1;i>=0;i--) {
					if(model.getShapes().get(i).isSelected()) {
						Shape shape = (Shape)model.getShapes().get(i);
						model.addToLogList("Deleted --> "+shape);
						addInStack(new CommandRemove(model, shape,i));
					}
				}
			}
		}
		else if(model.numberSelectedObject()>1){
			result = JOptionPane.showConfirmDialog(null,
					"Do you want to delete all selected objects ?", "Choose",
					JOptionPane.YES_NO_OPTION);

			if(result == JOptionPane.YES_OPTION) {
				for(int i=model.getShapes().size()-1;i>=0;i--) {
					if(model.getShapes().get(i).isSelected()) {
						Shape shape = (Shape)model.getShapes().get(i);
						model.addToLogList("Deleted --> "+shape);
						addInStack(new CommandRemove(model, shape,i));
					}
				}
			}
		}
	}

	public void toFront() {
		addInStack(new CommandToFront(model));
		model.addToLogList("Moved one position to front --> "+model.getSelectedShape());
	}

	public void toBack(ActionEvent e) {
		addInStack(new CommandToBack(model));
		model.addToLogList("Moved one position to back --> "+model.getSelectedShape());
	}

	public void bringToBack(ActionEvent e) {
		int length = model.getShapes().size();
		if(length>1) {
			for(int i=length-1;i>=0;i--) {
				if(model.getShapes().get(i).isSelected()) {
					if( i != 0) {
						Shape current = model.getShapes().get(i);
						addInStack(new CommandBringToBack(model,current,i));	
						model.addToLogList("Bringed to back --> "+model.getSelectedShape());
						return;
					}
				}
			}
		}	
	}

	public void bringToFront(ActionEvent e) {
		int length = model.getShapes().size();
		if(length>1) {
			for(int i=0;i<length;i++) {
				if(model.getShapes().get(i).isSelected()) {
					if( i < length) {
						Shape current = model.getShapes().get(i);
						addInStack(new CommandBringToFront(model,current,i));
						model.addToLogList("Bringed to front --> "+model.getSelectedShape());
						return;
					}
				}	
			}
		}
	}

	public void edgeColor(ActionEvent e) {
		edgeColor=JColorChooser.showDialog(null, "Edge color", edgeColor);
		if(edgeColor!=null) {
			frame.getBtnEdgeColor().setBackground(edgeColor);
		}
	}

	public void insideColor(ActionEvent e) {
		insideColor=JColorChooser.showDialog(null, "Inside Color", insideColor);
		if(insideColor!=null) {
			frame.getBtnInsideColor().setBackground(insideColor);
		}
	}
	public void saving(ActionEvent e) {

		JFileChooser fileChooser = new JFileChooser();
		File selectedFile = null;
		FileNameExtensionFilter filter = new FileNameExtensionFilter("log", "log");
		FileNameExtensionFilter filterPnt = new FileNameExtensionFilter("pnt", "pnt");

		fileChooser.setFileFilter(filter);
		fileChooser.addChoosableFileFilter(filterPnt);

		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

		int result = fileChooser.showSaveDialog(frame.getView());

		if (result == JFileChooser.APPROVE_OPTION) {

			FileContext ctx = new FileContext();

			if (fileChooser.getSelectedFile().getAbsolutePath().endsWith(".log")) {

				selectedFile = new File(fileChooser.getSelectedFile().toString());
				ctx.setFileStrategy(new LogStrategy(frame));
				ctx.createFile(selectedFile);
			}
			else if (fileChooser.getSelectedFile().getAbsolutePath().endsWith(".pnt")) {
				selectedFile = new File(fileChooser.getSelectedFile().toString());
				ctx.setFileStrategy(new ImageStrategy(frame));
				ctx.createFile(selectedFile);
			}
			else {
				selectedFile = new File(fileChooser.getSelectedFile() + ".pnt");
				ctx.setFileStrategy(new ImageStrategy(frame));
				ctx.createFile(selectedFile);
			}
		}
	}

	public void addInStack(Command command) {
		command.execute();
		executeCommand.push(command);
		if(!executeCommand.isEmpty()) {
			frame.getBtnUndo().setEnabled(true);
		}

		if(!unexecuteCommand.isEmpty()) {
			for(int i=0;i<=unexecuteCommand.size();i++)
				unexecuteCommand.pop();
			frame.getBtnRedo().setEnabled(false);
		}
	}

	public void undo() {
		try {
			model.addToLogList("Undo command");
			executeCommand.peek().unexecute();
			unexecuteCommand.push(executeCommand.pop());
			if(executeCommand.isEmpty() && !unexecuteCommand.isEmpty()) {
				frame.getBtnUndo().setEnabled(false);
				frame.getBtnRedo().setEnabled(true);
			}
			else {
				frame.getBtnUndo().setEnabled(true);
				frame.getBtnRedo().setEnabled(true);
			}
		}
		catch(Exception error) {
			System.out.println(error.getStackTrace());
		}
	}

	public void redo() {
		try {
			model.addToLogList("Redo command");
			unexecuteCommand.peek().execute();
			executeCommand.push(unexecuteCommand.pop());
			if(unexecuteCommand.isEmpty() && !executeCommand.isEmpty()) {
				frame.getBtnUndo().setEnabled(true);
				frame.getBtnRedo().setEnabled(false);
			}else {
				frame.getBtnUndo().setEnabled(true);
				frame.getBtnRedo().setEnabled(true);
			}
		}
		catch(Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	public void openFiles() throws FileNotFoundException,ClassNotFoundException {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("log", "log");
		FileNameExtensionFilter filterPnt = new FileNameExtensionFilter("pnt", "pnt");
		File selectedFile = null;

		fileChooser.setFileFilter(filter);
		fileChooser.addChoosableFileFilter(filterPnt);
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

		int result = fileChooser.showOpenDialog(frame.getView());

		selectedFile = fileChooser.getSelectedFile();

		BufferedReader bufferRead = null;
		if(result == JFileChooser.APPROVE_OPTION) {
			
			model.removeLogList();
			model.removeShapesList();
			frame.getDlmList().removeAllElements();
			
			while(!executeCommand.isEmpty()){
				executeCommand.pop();
			}
			while(!unexecuteCommand.isEmpty()) {
				unexecuteCommand.pop();
			}
			
			if(!fileChooser.getSelectedFile().getAbsolutePath().isEmpty()) {
				if (selectedFile.getAbsolutePath().endsWith(".log")) {
					try {

						File fileInput = fileChooser.getSelectedFile();
						bufferRead = new BufferedReader(new FileReader(fileInput.getPath()));

						String input="";

						while((input=bufferRead.readLine())!=null) {
							loadStack.push(input);
							input="";
						}
						frame.getBtnLoad().setEnabled(true);
					}
					catch(Exception error) {
						error.getStackTrace();
					}finally{
						try {
							bufferRead.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				else if (selectedFile.getAbsolutePath().endsWith(".pnt")) {
					try (FileInputStream fis = new FileInputStream(selectedFile)) {
						byte[] fileContent = new byte[(int) selectedFile.length()];
						fis.read(fileContent);
						model.setShapes((ArrayList<Shape>) deserialize(fileContent));
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public Object deserialize(byte[] data) throws IOException, ClassNotFoundException{
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		ObjectInputStream ois = new ObjectInputStream(in);
		return ois.readObject();
	}

	public void loadData() {
		if(!loadStack.isEmpty()) {
			String input = loadStack.pop();
			if(loadStack.isEmpty()) {
				frame.getBtnLoad().setEnabled(false);
			}
			model.addToLogList(input);
			String[] splitInput=input.split(" ");

			if(splitInput[0].equals("Added")) {
				if(splitInput[2].equals("Point:")) {

					point = pointFromSring(splitInput);
					addInStack(new CommandAdd(model,point));	
				}
				else if(splitInput[2].equals("Line:")) {

					line= lineFromString(splitInput);
					addInStack(new CommandAdd(model, line));
				}
				else if(splitInput[2].equals("Square:")) {

					square = squareFromString(splitInput);
					addInStack(new CommandAdd(model, square));	
				}
				else if(splitInput[2].equals("Rectangle:")) {

					rectangle= rectangleFromString(splitInput);
					addInStack(new CommandAdd(model, rectangle));

				}else if(splitInput[2].equals("Circle:")) {

					circle = circleFromString(splitInput);
					addInStack(new CommandAdd(model, circle));

				}else if(splitInput[2].equals("Hexagon:")){

					hexagon=hexagonFromString(splitInput);
					addInStack(new CommandAdd(model, hexagon));
				}
			}	
			else if(splitInput[0].equals("Selected")) {
				if(splitInput[2].equals("Point:")) {

					point = pointFromSring(splitInput);
					addInStack(new CommandSelect(model,point));	
				}
				else if(splitInput[2].equals("Line:")) {

					line= lineFromString(splitInput);
					addInStack(new CommandSelect(model, line));
				}
				else if(splitInput[2].equals("Square:")) {

					square = squareFromString(splitInput);
					addInStack(new CommandSelect(model, square));	
				}
				else if(splitInput[2].equals("Rectangle:")) {

					rectangle= rectangleFromString(splitInput);
					addInStack(new CommandSelect(model, rectangle));

				}else if(splitInput[2].equals("Circle:")) {

					circle = circleFromString(splitInput);
					addInStack(new CommandSelect(model, circle));

				}else if(splitInput[2].equals("Hexagon:")){

					hexagon=hexagonFromString(splitInput);
					addInStack(new CommandSelect(model, hexagon));
				}
			}
			else if(input.equals("Diselected --> All selected objects")) {
				for(int i=0;i<model.getShapes().size();i++) {
					if(model.getShapes().get(i).isSelected())
						addInStack(new CommandDiselect(model, model.getShapes().get(i)));
				}
			}
			else if(splitInput[0].equals("Diselected")) {
				if(splitInput[2].equals("Point:")) {

					point = pointFromSring(splitInput);
					addInStack(new CommandDiselect(model,point));	
				}
				else if(splitInput[2].equals("Line:")) {

					line= lineFromString(splitInput);
					addInStack(new CommandDiselect(model, line));
				}
				else if(splitInput[2].equals("Square:")) {

					square = squareFromString(splitInput);
					addInStack(new CommandDiselect(model, square));	
				}
				else if(splitInput[2].equals("Rectangle:")) {

					rectangle= rectangleFromString(splitInput);
					addInStack(new CommandDiselect(model, rectangle));

				}else if(splitInput[2].equals("Circle:")) {

					circle = circleFromString(splitInput);
					addInStack(new CommandDiselect(model, circle));

				}else if(splitInput[2].equals("Hexagon:")){

					hexagon=hexagonFromString(splitInput);
					addInStack(new CommandDiselect(model, hexagon));
				}
			}
			else if(splitInput[0].equals("Modifyed")) {

				String [] stringObject = input.split("OldState: |NewState: ");

				String oldString = "Modifyed --> "+ stringObject[1];
				String newString = "Modifyed --> "+ stringObject[2];

				String[] oldObject =oldString.split(" "); 
				String[] newObject =newString.split(" ");

				if(splitInput[3].equals("Point:")) {
					Point oldPoint = pointFromSring(oldObject);
					Point newPoint = pointFromSring(newObject);

					addInStack(new CommandModify(model, oldPoint, newPoint));

				}
				else if(splitInput[3].equals("Line:")) {
					Line oldLine = lineFromString(oldObject);
					Line newLine = lineFromString(newObject);

					addInStack(new CommandModify(model, oldLine, newLine));
				}
				else if(splitInput[3].equals("Square:")) {
					Square oldSquare = squareFromString(oldObject);
					Square newSquare = squareFromString(newObject);

					addInStack(new CommandModify(model, oldSquare, newSquare));

				}
				else if(splitInput[3].equals("Rectangle:")) {

					Rectangle oldRectangle = rectangleFromString(oldObject);
					Rectangle newRectangle = rectangleFromString(newObject);

					addInStack(new CommandModify(model, oldRectangle, newRectangle));

				}else if(splitInput[3].equals("Circle:")) {

					Circle oldCircle = circleFromString(oldObject);
					Circle newCircle = circleFromString(newObject);
					addInStack(new CommandModify(model,oldCircle, newCircle));

				}else if(splitInput[3].equals("Hexagon:")){
					HexagonAdapter oldHexagon = hexagonFromString(oldObject);
					HexagonAdapter newHexagon = hexagonFromString(newObject);

					addInStack(new CommandModify(model, oldHexagon, newHexagon));
				}
			}else if(splitInput[0].equals("Deleted")) {
				if(splitInput[2].equals("Point:")) {

					point = pointFromSring(splitInput);

					addInStack(new CommandRemove(model,point,model.getIndex(point)));	
				}
				else if(splitInput[2].equals("Line:")) {

					line= lineFromString(splitInput);
					addInStack(new CommandRemove(model,line,model.getIndex(line)));
				}
				else if(splitInput[2].equals("Square:")) {

					square = squareFromString(splitInput);
					addInStack(new CommandRemove(model,square,model.getIndex(square)));
				}
				else if(splitInput[2].equals("Rectangle:")) {

					rectangle= rectangleFromString(splitInput);
					addInStack(new CommandRemove(model,rectangle,model.getIndex(rectangle)));

				}else if(splitInput[2].equals("Circle:")) {

					circle = circleFromString(splitInput);
					addInStack(new CommandRemove(model,circle,model.getIndex(circle)));

				}else if(splitInput[2].equals("Hexagon:")){

					hexagon=hexagonFromString(splitInput);
					addInStack(new CommandRemove(model,hexagon,model.getIndex(hexagon)));
				}
			}
			else if(splitInput[0].equals("Moved") && splitInput[4].equals("front")) {

				addInStack(new CommandToFront(model));
			}
			else if(splitInput[0].equals("Moved") && splitInput[4].equals("back")) {
				addInStack(new CommandToBack(model));
			}
			else if(splitInput[0].equals("Bringed") && splitInput[2].equals("back")) {
				String [] moveInput = new String [splitInput.length-2];
				int j=2;
				for(int i=0;i<moveInput.length;i++) {
					moveInput[i]= splitInput[j];
					j++;
				}
				for(String s:moveInput) {
					System.out.println(s);
				}
				if(moveInput[2].equals("Point:")) {

					point = pointFromSring(moveInput);
					point.setSelected(true);
					addInStack(new CommandBringToBack(model, point, model.getIndex(point)));
				}
				else if(moveInput[2].equals("Line:")) {

					line= lineFromString(moveInput);
					line.setSelected(true);
					addInStack(new CommandBringToBack(model, line, model.getIndex(line)));
				}
				else if(moveInput[2].equals("Square:")) {

					square = squareFromString(moveInput);
					square.setSelected(true);
					addInStack(new CommandBringToBack(model, square, model.getIndex(square)));	
				}
				else if(moveInput[2].equals("Rectangle:")) {

					rectangle= rectangleFromString(moveInput);
					rectangle.setSelected(true);
					addInStack(new CommandBringToBack(model, rectangle, model.getIndex(rectangle)));

				}else if(moveInput[2].equals("Circle:")) {

					circle = circleFromString(moveInput);
					circle.setSelected(true);
					addInStack(new CommandBringToBack(model, circle, model.getIndex(circle)));

				}else if(moveInput[2].equals("Hexagon:")){

					hexagon=hexagonFromString(moveInput);
					hexagon.setSelected(true);
					addInStack(new CommandBringToBack(model, hexagon, model.getIndex(hexagon)));
				}	
			}
			else if(splitInput[0].equals("Bringed") && splitInput[2].equals("front")) {
				String [] moveInput = new String [splitInput.length-2];
				int j=2;
				for(int i=0;i<moveInput.length;i++) {
					moveInput[i]= splitInput[j];
					j++;
				}
				for(String s:moveInput) {
					System.out.println(s);
				}
				if(moveInput[2].equals("Point:")) {
					point = pointFromSring(moveInput);
					point.setSelected(true);
					addInStack(new CommandBringToFront(model, point, model.getIndex(point)));
				}
				else if(moveInput[2].equals("Line:")) {
					line= lineFromString(moveInput);
					line.setSelected(true);
					addInStack(new CommandBringToFront(model, line, model.getIndex(line)));
				}
				else if(moveInput[2].equals("Square:")) {
					square = squareFromString(moveInput);
					square.setSelected(true);
					addInStack(new CommandBringToFront(model, square, model.getIndex(square)));	
				}
				else if(moveInput[2].equals("Rectangle:")) {
					rectangle= rectangleFromString(moveInput);
					rectangle.setSelected(true);
					addInStack(new CommandBringToFront(model, rectangle, model.getIndex(rectangle)));

				}else if(moveInput[2].equals("Circle:")) {
					circle = circleFromString(moveInput);
					circle.setSelected(true);
					addInStack(new CommandBringToFront(model, circle, model.getIndex(circle)));

				}else if(moveInput[2].equals("Hexagon:")){
					hexagon=hexagonFromString(moveInput);
					hexagon.setSelected(true);
					addInStack(new CommandBringToFront(model, hexagon, model.getIndex(hexagon)));
				}	
			}
			else if(input.equals("Undo command")) {
				executeCommand.peek().unexecute();
				unexecuteCommand.push(executeCommand.pop());
			}
			else if(input.equals("Redo command")) {
				unexecuteCommand.peek().execute();
				executeCommand.push(unexecuteCommand.pop());
			}
		}
		else {
			frame.getBtnLoad().setEnabled(false);
		}
	}

	public Point pointFromSring(String[] input) {
		String[] splitCoordinate = input[3].split("[)|\\,|\\(]");

		return new Point(Integer.valueOf(splitCoordinate[1]),Integer.valueOf(splitCoordinate[2]),Color.decode(input[5]));
	}

	public Line lineFromString(String[] input) {
		String[] splitCoordinateStart = input[4].split("[)|\\,|\\(]");
		String[] splitCoordinateEnd = input[6].split("[)|\\,|\\(]");

		return new Line(new Point(Integer.parseInt(splitCoordinateStart[1]),Integer.parseInt(splitCoordinateStart[2])),
				new Point(Integer.valueOf(splitCoordinateEnd[1]),Integer.valueOf(splitCoordinateEnd[2])),
				Color.decode(input[8]));
	}

	public Square squareFromString(String[] input) {
		String[] splitUpLeft = input[4].split("[)|\\,|\\(]");
		String[] splitPageLength = input[5].split("=|,");
		String[] splitBorderColor = input[7].split(",");

		return new Square(new Point(Integer.parseInt(splitUpLeft[1]),Integer.parseInt(splitUpLeft[2])),
				Integer.parseInt(splitPageLength[1]),
				Color.decode(splitBorderColor[0]),
				Color.decode(input[9]));
	}

	public Rectangle rectangleFromString(String[] input) {
		String[] splitUpLeft=input[4].split("[)|\\,|\\(]");
		String[] splitHeight = input[5].split("=|,");
		String[] splitWidth = input[6].split("=|,");
		String[] splitBorderColor = input[8].split(",");

		return new Rectangle(new Point(Integer.parseInt(splitUpLeft[1]),Integer.parseInt(splitUpLeft[2])),
				Integer.parseInt(splitHeight[1]),
				Integer.parseInt(splitWidth[1]),
				Color.decode(splitBorderColor[0]),
				Color.decode(input[10]));
	}

	public Circle circleFromString(String[] input) {
		String[] splitCenter = input[4].split("[)|\\,|\\(]");
		String[] splitRadius = input[5].split("=|,");
		String[] splitBorderColor = input[7].split(",");

		return new Circle(new Point(Integer.parseInt(splitCenter[1]),Integer.parseInt(splitCenter[2])),
				Integer.parseInt(splitRadius[1]),
				Color.decode(splitBorderColor[0]),
				Color.decode(input[9]));
	}

	public HexagonAdapter hexagonFromString(String[] input) {
		String[] splitCenter = input[4].split("[)|\\,|\\(]");
		String[] splitRadius = input[5].split("=|,");
		String[] splitBorderColor = input[7].split(",");

		return new HexagonAdapter(Integer.parseInt(splitCenter[1]),Integer.parseInt(splitCenter[2]), 
				Integer.parseInt(splitRadius[1]), Color.decode(splitBorderColor[0]), Color.decode(input[9]));
	}
}