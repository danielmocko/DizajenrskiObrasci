package drawing;


import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.DefaultListModel;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

import drawing.DrawingModel;
import geometry.*;


public class DrawingController {
	private DrawingModel model;
	private DrawingView view;
	private Frame frame;

	private DialogCircle dlgCircle;
	private DialogSquare dlgSquare;
	private DialogRectangle dlgRectangle;
	private DialogHexagon dlgHexagon;
	private DialogPoint dlgPoint;
	private DialogLine dlgLine;
	private Color edgeColor,insideColor;

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
	}

	public void panelClick(MouseEvent e) {
		if(frame.getTglbtnPoint().isSelected()) {
			x = e.getX();
			y = e.getY();
			Point t = new Point(x,y,Color.BLACK);

			t.setEdgeColor(frame.getBtnEdgeColor().getBackground());
			model.addToLogList("Added --> "+t);
			model.add(t);
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
				model.add(l);
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
				model.add(circle);
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
				model.add(square);
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
				model.add(rectangle);

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
				model.add(hexagon);
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
						model.selectObject(i);
						fillDLM();
						return;
					}else {
						model.diselectObject(i);
						model.addToLogList("Diselected --> "+model.getShapes().get(i));
						fillDLM();
						return;
					}
				}
				
			}

			if(selected ==false) {
				if(model.numberSelectedObject()>1) {
					for(int i=0;i<model.getShapes().size();i++) {
						model.diselectObject(i);
					}
					model.addToLogList("Diselected --> All selected objects");
				}
				else if(model.numberSelectedObject()==1) {
					for(int i=0;i<model.getShapes().size();i++) {
						if(model.getShapes().get(i).isSelected()) {
							model.diselectObject(i);
							model.addToLogList("Diselected --> "+model.getShapes().get(i));
						}
					}
				}

			}
		}
		fillDLM();
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
						model.remove(shape);
						this.point = dlgPoint.getDlgPoint();
						this.point.setSelected(true);
						frame.getTglbtnSelect().setSelected(true);
						model.add(this.point);
						model.addToLogList("Modifyed --> *OldState: "+point+", *NewState: "+this.point);
					} 
					fillDLM();
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
						model.remove(shape);
						this.line = dlgLine.getDlgLine();
						this.line.setSelected(true);
						frame.getTglbtnSelect().setSelected(true);
						model.add(this.line);
						model.addToLogList("Modifyed --> *OldState: "+line+", *NewState: "+this.line);
					}
					fillDLM();
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
						model.remove(shape);
						this.circle = dlgCircle.getCircle();
						this.circle.setSelected(true);
						frame.getTglbtnSelect().setSelected(true);
						model.add(this.circle);
						model.addToLogList("Modifyed --> *OldState: "+circle+", *NewState: "+this.circle);
					}
					fillDLM();
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
						model.remove(shape);
						this.rectangle = dlgRectangle.getDlgRectangle();
						this.rectangle.setSelected(true);
						frame.getTglbtnSelect().setSelected(true);
						model.add(this.rectangle);
						model.addToLogList("Modifyed --> *OldState: "+rectangle+", *NewState: "+this.rectangle);
					}
					fillDLM();
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
						model.remove(square);
						this.square=dlgSquare.getSquareDialog();
						this.square.setSelected(true);
						frame.getTglbtnSelect().setSelected(true);
						model.add(this.square);
						model.addToLogList("Modifyed --> *OldState: "+square+", *NewState: "+this.square);
					}
					fillDLM();
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
						model.remove(shape);
						this.hexagon= dlgHexagon.getHexagon();
						this.hexagon.setSelected(true);
						frame.getTglbtnSelect().setSelected(true);
						model.add(this.hexagon);
						model.addToLogList("Modifyed --> *OldState: "+hexagonAdapter+", *NewState: "+this.hexagon);
					}
					fillDLM();
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
						model.addToLogList("Deleted -->"+model.getShapes().get(i));
						model.removeByIndex(i);
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
						model.addToLogList("Deleted -->"+model.getShapes().get(i));
						model.removeByIndex(i);
					}
				}
			}
		}
		fillDLM();
	}

	public void toFront(ActionEvent e) {
		int length = model.getShapes().size();
		if(length>1) {
			for(int i=0;i<length;i++) {
				if(model.getShapes().get(i).isSelected()) {
					if( i+1 < length) {
						Shape current = model.getShapes().get(i);
						Shape next = model.getShapes().get(i+1);
						model.change(i,next);
						model.change(i+1, current);
						model.addToLogList("Moved one position to front --->"+current);
						fillDLM();
						return;
					}
				}
			}
		}
		
	}

	public void toBack(ActionEvent e) {
		int length = model.getShapes().size();
		if(length>1) {
			for(int i=length-1;i>=0;i--) {
				if(model.getShapes().get(i).isSelected()) {
					if( i-1 >= 0) {
						Shape current = model.getShapes().get(i);
						Shape next = model.getShapes().get(i-1);
						model.change(i,next);
						model.change(i-1, current);
						model.addToLogList("Moved one position to back --->"+current);
						fillDLM();
						return;
					}
				}
			}
		}
		
	}

	public void bringToBack(ActionEvent e) {
		int length = model.getShapes().size();
		if(length>1) {
			for(int i=length-1;i>=0;i--) {
				if(model.getShapes().get(i).isSelected()) {
					if( i != 0) {
						Shape current = model.getShapes().get(i);

						for(int j=i-1;j>=0;j--) {
							Shape start = model.getShapes().get(j);
							model.change(j+1,start);
						}
						model.change(0, current);
						model.addToLogList("Bringed to back --->"+current);
						fillDLM();
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
						for(int j=i+1;j<length;j++) {
							Shape start = model.getShapes().get(j);
							model.change(j-1,start);
						}
						model.change(length-1, current);
						model.addToLogList("Bringed to front --->"+current);
						fillDLM();
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
	
	public void fillDLM() {
		frame.getDlmList().clear();
		for(int i=model.getLogList().size()-1;i>=0;i--) {
			frame.getDlmList().addElement(model.getLogList().get(i));
		}
	}

}
