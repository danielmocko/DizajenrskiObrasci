package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape implements Moveable{
	private Point pStart;
	private Point pEnd;

	public Line() {

	}
	public Line(Point pStart,Point pEnd) {
		this.pStart=pStart;
		this.pEnd=pEnd;
	}
	public Line(Point pStart,Point pEnd,Color color) {
		this(pStart,pEnd);
		setEdgeColor(color);
	}

	public Point centerLine() {
		int x = (pStart.getX() + pEnd.getX()) / 2;
		int y = (pStart.getY() + pEnd.getY()) / 2;
		return new Point(x,y);
	}

	public String toString() {
		return "Line: start: ("+pStart.getX()+","+pStart.getY()+"), end: ("+pEnd.getX()+","+pEnd.getY()+"), color= "+toHexString(getEdgeColor());
	}

	public boolean equals(Object obj) {
		if(obj instanceof Line) {
			Line helpLine = (Line)obj;
			if(this.pStart.equals(helpLine.pStart) && this.pEnd.equals(helpLine.pEnd)) {
				return true;
			}
			else 
				return false;
		}else
			return false;
	}

	public double length() {
		return pStart.distance(pEnd);
	}

	public int compareTo(Object o) {
		if(o instanceof Line) {
			Line helpLine = (Line)o;
			return (int)this.length()-(int)helpLine.length();
		}
		else
			return 0;
	}

	public void drawSelf(Graphics g) {
		g.setColor(getEdgeColor());
		g.drawLine(pStart.getX(), pStart.getY(), pEnd.getX(), pEnd.getY());
		if(isSelected())
			selected(g);
	}

	public void drawColor(Graphics g) {
		g.setColor(getEdgeColor());
		g.drawLine(pStart.getX(), pStart.getY(), pEnd.getX(), pEnd.getY());
		if(isSelected())
			selected(g);
	}

	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		pStart.selected(g);
		pEnd.selected(g);
		centerLine().selected(g);

	}

	public boolean contains(int x, int y) {
		Point clickPlace = new Point(x,y);
		if(clickPlace.distance(pStart) + clickPlace.distance(pEnd)- this.length() <= 0.1) {
			return true;
		}
		else
			return false;
	}

	@Override
	public void moveFor(int x, int y) {
		pStart.setX(pStart.getX()+x);
		pStart.setY(pStart.getY()+y);
		pEnd.setX(pEnd.getX()+x);
		pEnd.setY(pEnd.getY()+y);

	}
	//
	@Override
	public void moveTo(int x, int y) {
		//*********************************************
	}

	public Point getpStart() {
		return pStart;
	}
	public void setpStart(Point pStart) {
		this.pStart = pStart;
	}
	public Point getpEnd() {
		return pEnd;
	}
	public void setpEnd(Point pEnd) {
		this.pEnd = pEnd;
	}


}
