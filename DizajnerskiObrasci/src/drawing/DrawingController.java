package drawing;


import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

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
	private DialogRectangle dlgRectangle = new DialogRectangle();
	private Color edgeColor,insideColor;

	private Rectangle rectangle;
	private Circle circle;
	private Square square;
	private Point t1;
	private Point t2;
	private int Point1_x;
	private int Point1_y;
	private int click=0;
	private int x,y;

	public DrawingController(DrawingModel model,Frame frame) {
		this.model=model;
		this.frame=frame;
	}

	/*
	public DrawingController(DrawingModel model,DrawingView view,Frame frame) {
		this.model=model;
		this.view=view;
		this.frame=frame;
	}
	 */
	public void panelClick(MouseEvent e) {
		if(frame.getTglbtnPoint().isSelected()) {

			x = e.getX();//8
			y = e.getY();//79
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
			dlgSquare.setVisible(true);
			if(dlgSquare.isAccept()) {
				square = dlgSquare.getSquareDialog();
				
				model.add(square);
			}
		}
		else if(frame.getTglbtnRectangle().isSelected()) {
			x=e.getX();
			y=e.getY();
			
			dlgRectangle.getTxtXCoordinate().setText(String.valueOf(x));
			dlgRectangle.getTxtYCoordinate().setText(String.valueOf(y));
			dlgRectangle.getBtnEdgeColor().setBackground(frame.getBtnEdgeColor().getBackground());
			dlgRectangle.getBtnInsideColor().setBackground(frame.getBtnInsideColor().getBackground());
			dlgRectangle.setVisible(true);
			if(dlgRectangle.isAccept()) {
				rectangle = dlgRectangle.getDlgRectangle();
				model.add(rectangle);
				
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
	
	public void select(ActionEvent e) {
		
	}


}
