package geometry;

import java.awt.Color;
import java.awt.Graphics;

import com.sun.org.apache.regexp.internal.recompile;

import geometry.Line;
import geometry.Point;

public class Rectangle extends Square {
	private int height;
	
	public Rectangle() {
		
	}
	
	public Rectangle(Point upLeft, int width, int height) {
		super(upLeft,width);
		this.height=height;
	}
	
	public Rectangle(Point upLeft, int width, int height,Color edgeColor, Color insideColor) {
		this(upLeft,width,height);
		setEdgeColor(edgeColor);
		setInsideColor(insideColor);
	}
	
	public Line diagonal() {
		return new Line(upLeft, new Point(upLeft.getX()+getPageLength(),upLeft.getY()+getHeight()));
	}
	
	public Point center() {
		return diagonal().centerLine();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Rectangle) {
			Rectangle helpRectangle = (Rectangle)obj;
			if(this.upLeft.equals(helpRectangle.upLeft) && this.height==helpRectangle.height) {
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}
	
	public String toString() {
		return "Rectangle: upLeftPoint: ("+getUpLeft().getX()+","+getUpLeft().getY()+"), width="+getPageLength()+", height="+getHeight()+", borderColor="+toHexString(getEdgeColor())+", areaColor="+toHexString(getInsideColor());
	}
	
	public int bulk() {
		return 2*(height+getPageLength());
	}
	
	public int surface() {
		return height*getPageLength();
	}
	
	public boolean contains(int x, int y) {
		if (this.getUpLeft().getX() <= x && x <= (this.getUpLeft().getX() + getPageLength())
				&& this.getUpLeft().getY() <= y && y <= (this.getUpLeft().getY() + height))
			return true;
		else
			return false;
	}
	
	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		new Line(getUpLeft(), new Point(getUpLeft().getX() + getPageLength(), getUpLeft().getY())).selected(g);
		new Line(getUpLeft(), new Point(getUpLeft().getX(), getUpLeft().getY() + height)).selected(g);
		new Line(new Point(getUpLeft().getX() + getPageLength(), getUpLeft().getY()), diagonal().getpEnd())
				.selected(g);
		new Line(new Point(getUpLeft().getX(), getUpLeft().getY() + height), diagonal().getpEnd())
				.selected(g);
	}
	
	public void drawColor(Graphics g) {
		g.setColor(getEdgeColor());
		g.drawRect(upLeft.getX(), upLeft.getY(), getPageLength(), height);
		g.setColor(getInsideColor());
		g.fillRect(upLeft.getX() + 1, upLeft.getY() + 1, getPageLength() - 1, height - 1);
		if(isSelected())
			selected(g);
	}
	

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	
}
