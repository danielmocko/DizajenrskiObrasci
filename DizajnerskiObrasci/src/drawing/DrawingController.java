package drawing;


import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ListIterator;

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
						model.selectObject(i);
						return;
					}else {
						model.diselectObject(i);
						return;
					}
				}
			}

			if(selected ==false) {
				for(int i=0;i<model.getShapes().size();i++) {
					model.diselectObject(i);
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
						model.remove(shape);
						this.point = dlgPoint.getDlgPoint();
						this.point.setSelected(true);
						frame.getTglbtnSelect().setSelected(true);
						model.add(this.point);
					} else {
						return;
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
						model.remove(shape);
						this.line = dlgLine.getDlgLine();
						this.line.setSelected(true);
						frame.getTglbtnSelect().setSelected(true);
						model.add(this.line);
					}
					else {
						return;
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
						model.remove(shape);
						this.circle = dlgCircle.getCircle();
						this.circle.setSelected(true);
						frame.getTglbtnSelect().setSelected(true);
						model.add(this.circle);
					}else {
						return;	
					}
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
					}
					else {
						rectangle.setSelected(false);
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
						model.remove(square);
						this.square=dlgSquare.getSquareDialog();
						this.square.setSelected(true);
						frame.getTglbtnSelect().setSelected(true);
						model.add(this.square);
					}else {
						return;
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
						model.remove(shape);
						this.hexagon= dlgHexagon.getHexagon();
						this.hexagon.setSelected(true);
						frame.getTglbtnSelect().setSelected(true);
						model.add(this.hexagon);
					}else {

						return;
					}
					return;
				}
			}
		}
	}

	public void delete(ActionEvent e) {
		int result = JOptionPane.showConfirmDialog(null,
				"Da li ste sigurni da želite da obriše objekat ?", "Izaberi",
				JOptionPane.YES_NO_OPTION);

		if(result == JOptionPane.YES_OPTION) {
			for(int i=model.getShapes().size()-1;i>=0;i--) {
				if(model.getShapes().get(i).isSelected()) {
					model.removeByIndex(i);
				}
			}
			disableButton();
		}
		else {
			disableButton();
			//shape.setSelected(false);
		}
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

	public void enableButton() {
		frame.getBtnModify().setEnabled(true);
		frame.getBtnDelete().setEnabled(true);
	}

	public void disableButton() {
		frame.getBtnModify().setSelected(false);
		frame.getBtnModify().setEnabled(false);

		frame.getBtnDelete().setSelected(false);
		frame.getBtnModify().setEnabled(false);

		frame.getTglbtnSelect().setSelected(true);
	}
}
