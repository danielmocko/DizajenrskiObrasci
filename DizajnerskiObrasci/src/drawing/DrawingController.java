package drawing;


import java.awt.Color;


import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Stack;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import org.omg.CORBA.OMGVMCID;

import drawing.DrawingModel;
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
import geometry.*;


public class DrawingController {
	private DrawingModel model;
	private DrawingView view;
	private Frame frame;

	private Strategy strategy;

	private DialogCircle dlgCircle;
	private DialogSquare dlgSquare;
	private DialogRectangle dlgRectangle;
	private DialogHexagon dlgHexagon;
	private DialogPoint dlgPoint;
	private DialogLine dlgLine;
	private Color edgeColor,insideColor;
	private Stack<Command> executeCommand;
	private Stack<Command> unexecuteCommand;

	private HexagonAdapter hexagon;
	private Rectangle rectangle;
	private Circle circle;
	private Line line;
	private Square square;
	private Point point;
	private Point t1;
	private Point t2;
	private int Point1_x;
	private int Point1_y;
	private int click=0;
	private int x,y;
	private boolean selected=false;

	public DrawingController(DrawingModel model,Frame frame) {
		this.model=model;
		this.frame=frame;
		executeCommand = new Stack<Command>();
		unexecuteCommand = new Stack<Command>();
	}

	public void panelClick(MouseEvent e) {
		if(frame.getTglbtnPoint().isSelected()) {
			x = e.getX();
			y = e.getY();
			Point t = new Point(x,y,Color.BLACK);

			t.setEdgeColor(frame.getBtnEdgeColor().getBackground());
			model.addToLogList("Added --> "+t);
			addInStack(new CommandAdd(model, t));

		}
		else if(frame.getTglbtnLine().isSelected()) {

			if(click==0) {
				Point1_x=e.getX();
				Point1_y=e.getY();
				t1= new Point(Point1_x,Point1_y);
				click++;
			}
			else if(click==1) {
				x=e.getX();
				y=e.getY();
				t2 = new Point(x,y);

				Line l = new Line(t1,t2);
				l.setEdgeColor(frame.getBtnEdgeColor().getBackground());
				addInStack(new CommandAdd(model, l));
				click=0;	
				model.addToLogList("Added --> "+l);
			}
		}
		else if(frame.getTglbtnCircle().isSelected()) {
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
						model.addToLogList("Selected --> "+model.getShapes().get(i));
						addInStack(new CommandSelect(model, i));
						return;
					}else {
						addInStack(new CommandDiselect(model, i));
						model.addToLogList("Diselected --> "+model.getShapes().get(i));
						return;
					}
				}
			}

			if(selected ==false) {
				if(model.numberSelectedObject()>1) {
					for(int i=0;i<model.getShapes().size();i++) {
						if(model.getShapes().get(i).isSelected())
							addInStack(new CommandDiselect(model, i));
					}
					model.addToLogList("Diselected --> All selected objects");
				}
				else if(model.numberSelectedObject()==1) {
					for(int i=0;i<model.getShapes().size();i++) {
						if(model.getShapes().get(i).isSelected()) {
							addInStack(new CommandDiselect(model, i));
							model.addToLogList("Diselected --> "+model.getShapes().get(i));
							return;
						}
					}
				}
			}
		}
	}

	public void modify(ActionEvent e) {
		ListIterator it1 = model.getShapes().listIterator(model.getShapes().size());

		while(it1.hasPrevious()) {
			Shape shape = (Shape) it1.previous();
			if(shape.isSelected()==true) {
				if (shape instanceof Point) {
					Point point = (Point) shape;
					dlgPoint = new DialogPoint();
					dlgPoint.getTxtXCoordinate().setText(String.valueOf(point.getX()));
					dlgPoint.getTxtYCoordinate().setText(String.valueOf(point.getY()));
					dlgPoint.getBtnColor().setBackground(point.getEdgeColor());
					dlgPoint.setVisible(true);
					if (dlgPoint.isAccept()) {

						this.point = dlgPoint.getDlgPoint();
						this.point.setSelected(true);
						frame.getTglbtnSelect().setSelected(true);
						addInStack(new CommandModify(model, point, this.point));
						model.addToLogList("Modifyed --> *OldState: "+point+", *NewState: "+this.point);
					} 
					return;
				}else if(shape instanceof Line) {
					Line line=(Line)shape;
					dlgLine = new DialogLine();
					dlgLine.getTxtXCoordinateStartPoint().setText(String.valueOf(line.getpStart().getX()));
					dlgLine.getTxtYCoordinateStartPoint().setText(String.valueOf(line.getpStart().getY()));
					dlgLine.getTxtXCoordinateEndPoint().setText(String.valueOf(line.getpEnd().getX()));
					dlgLine.getTxtYCoordinateEndPoint().setText(String.valueOf(line.getpEnd().getY()));
					dlgLine.getBtnColorDlg().setBackground(line.getEdgeColor());
					dlgLine.setVisible(true);
					if(dlgLine.isAccept()) {

						this.line = dlgLine.getDlgLine();
						this.line.setSelected(true);
						frame.getTglbtnSelect().setSelected(true);
						addInStack(new CommandModify(model, line, this.line));
						model.addToLogList("Modifyed --> *OldState: "+line+", *NewState: "+this.line);
					}
					return;
				}else if(shape instanceof Circle) {
					Circle circle=(Circle)shape;
					dlgCircle = new DialogCircle();
					dlgCircle.getTxtXCenter().setText(String.valueOf(circle.getCenter().getX()));
					dlgCircle.getTxtYCenter().setText(String.valueOf(circle.getCenter().getY()));
					dlgCircle.getTxtRadius().setText(String.valueOf(circle.getR()));
					dlgCircle.getBtnEdgeColor().setBackground(circle.getEdgeColor());
					dlgCircle.getBtnInsideColor().setBackground(circle.getInsideColor());
					dlgCircle.setVisible(true);
					if(dlgCircle.isAccept()) {

						this.circle = dlgCircle.getCircle();
						this.circle.setSelected(true);
						frame.getTglbtnSelect().setSelected(true);
						addInStack(new CommandModify(model, circle, this.circle));
						model.addToLogList("Modifyed --> *OldState: "+circle+", *NewState: "+this.circle);
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
					dlgRectangle.getBtnEdgeColor().setBackground(rectangle.getEdgeColor());
					dlgRectangle.getBtnInsideColor().setBackground(rectangle.getInsideColor());
					dlgRectangle.setVisible(true);
					if(dlgRectangle.isAccept()) {

						this.rectangle = dlgRectangle.getDlgRectangle();
						this.rectangle.setSelected(true);
						frame.getTglbtnSelect().setSelected(true);
						addInStack(new CommandModify(model, rectangle, this.rectangle));
						model.addToLogList("Modifyed --> *OldState: "+rectangle+", *NewState: "+this.rectangle);
					}
					return;
				}
				else if(shape instanceof Square) {
					Square square = (Square)shape;
					dlgSquare = new DialogSquare();

					dlgSquare.getTxtXCoordinate().setText(String.valueOf(square.getUpLeft().getX()));
					dlgSquare.getTxtYCoordinate().setText(String.valueOf(square.getUpLeft().getY()));
					dlgSquare.getTxtSide().setText(String.valueOf(square.getPageLength()));
					dlgSquare.getBtnEdgeColor().setBackground(square.getEdgeColor());
					dlgSquare.getBtnInsideColor().setBackground(square.getInsideColor());
					dlgSquare.setVisible(true);
					if(dlgSquare.isAccept()) {

						this.square=dlgSquare.getSquareDialog();
						this.square.setSelected(true);
						frame.getTglbtnSelect().setSelected(true);
						addInStack(new CommandModify(model, square, this.square));
						model.addToLogList("Modifyed --> *OldState: "+square+", *NewState: "+this.square);
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
						model.addToLogList("Modifyed --> *OldState: "+hexagonAdapter+", *NewState: "+this.hexagon);
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
						model.addToLogList("Deleted -->"+shape);
						addInStack(new CommandRemove(model, shape));
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
						model.addToLogList("Deleted -->"+shape);
						addInStack(new CommandRemove(model, shape));
					}
				}
			}
		}
	}

	public void toFront() {
		addInStack(new CommandToFront(model));
	}

	public void toBack(ActionEvent e) {
		addInStack(new CommandToBack(model));
	}

	public void bringToBack(ActionEvent e) {
		addInStack(new CommandBringToBack(model));
	}

	public void bringToFront(ActionEvent e) {
		addInStack(new CommandBringToFront(model));
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
		FileNameExtensionFilter fnef = new FileNameExtensionFilter("Text document", ".txt");
		fileChooser.setFileFilter(fnef);

		fileChooser.setDialogTitle("Save a file");

		int returnValue = fileChooser.showSaveDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File output=fileChooser.getSelectedFile();
			if(!model.getLogList().isEmpty()) {

				try {
					File file = new File(output.getPath());
					BufferedWriter buffer= new BufferedWriter(new FileWriter(file));
					String logString="";

					int size=frame.getDlmList().getSize();

					for(int i=0;i<size;i++) {
						logString=frame.getDlmList().get(i);
						buffer.write(logString);
						buffer.newLine();
					}
					buffer.close();
				}
				catch(Exception error) {
					error.getStackTrace();
				}
			}
		}
	}

	public void addInStack(Command command) {
		command.execute();
		executeCommand.push(command);
	}

	public void undo() {
		model.addToLogList("Undo command");
		executeCommand.peek().unexecute();
		unexecuteCommand.push(executeCommand.pop());
	}

	public void redo() {
		model.addToLogList("Redo command");
		unexecuteCommand.peek().execute();
		executeCommand.push(unexecuteCommand.pop());
	}


	public void openFiles(ActionEvent e) {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Open a File");

		int result = fileChooser.showOpenDialog(null);
		if(result== JFileChooser.APPROVE_OPTION) {
			try {
				File fileInput = fileChooser.getSelectedFile();
				BufferedReader bufferRead = new BufferedReader(new FileReader(fileInput.getPath()));

				String s="";

				while((s=bufferRead.readLine())!=null) {
					model.addToLogList(s);
				}
				if(bufferRead!=null)
					bufferRead.close();
			}
			catch(Exception error) {
				error.getStackTrace();
			}
		}	
	}
}
