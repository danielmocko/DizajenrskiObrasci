package geometry;

import java.awt.Color;
import java.awt.Graphics;

import geometry.Line;
import geometry.Point;

public class Circle extends SurfaceShape implements Moveable {
	private Point center;
	private int r;

	public Circle() {

	}

	public Circle(Point center,int r) {
		this.center=center;
		this.r=r;
	}

	public Circle(Point center,int r,Color edgeColor,Color insideColor) {
		this(center,r);
		setEdgeColor(edgeColor);
		setInsideColor(insideColor);
	}

	public String toString() {
		return "Center="+this.center+", r="+r;
	}

	public int compareTo(Object o) {
		if(o instanceof Circle) {
			Circle help = (Circle)o;
			return this.r-help.r;
		}
		else
			return 0;
	}

	public boolean equals(Object obj) {
		if( obj instanceof Circle) {
			Circle help =(Circle)obj;
			if(this.center.equals(help.center) && this.r==help.r)
				return true;
			else
				return false;
		}
		else
			return false;
	}

	public void moveFor(int x, int y) {
		// TODO Auto-generated method stub

	}

	public void moveTo(int x, int y) {
		center.setX(x);
		center.setY(y);
	}
	@Override
	public void fill(Graphics g) {
		// TODO Auto-generated method stub

	}
	@Override
	public void drawSelf(Graphics g) {
		// TODO Auto-generated method stub

	}
	@Override
	public void drawColor(Graphics g) {
		g.setColor(getEdgeColor());
		g.drawOval(center.getX() - r, center.getY() - r, 2 * r, r * 2);
		g.setColor(getInsideColor());
		g.fillOval(center.getX() - r + 1, center.getY() - r + 1, 2 * r - 2, r * 2 - 2);
		if(isSelected())
			selected(g);
	}
	@Override
	public void selected(Graphics g) {
		new Line(new Point(center.getX(),center.getY()-r),new Point(center.getX(), center.getY() + r)).selected(g);;
		new Line(new Point(center.getX() - r, center.getY()), new Point(center.getX() + r, center.getY()))
		.selected(g);
	}
	@Override
	public boolean contains(int x, int y) {
		Point clickPlace = new Point(x,y);
		if(clickPlace.distance(center) <=r)
			return true;
		else
			return false;
	}


	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}





}
