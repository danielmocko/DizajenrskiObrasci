package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import geometry.Line;
import geometry.Point;

public class Circle extends SurfaceShape implements Moveable,Serializable {
	private Point center;
	private int r;

	public Circle() {

	}

	public Circle(Point center,int r) {
		this.center=center;
		this.r=r;
	}

	public Circle(Point center,int r,Color borderColor,Color areaColor) {
		this(center,r);
		setBorderColor(borderColor);
		setAreaColor(areaColor);
	}

	public String toString() {
		return "Circle: center: ("+center.getX()+","+center.getY()+"), radius="+r+", borderColor= "+toHexString(getBorderColor())+", areaColor= "+toHexString(getAreaColor());
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
		g.setColor(getAreaColor());
		g.fillOval(center.getX() - r + 1, center.getY() - r + 1, 2 * r - 2, r * 2 - 2);
	}
	
	@Override
	public void drawColor(Graphics g) {
		g.setColor(getBorderColor());
		g.drawOval(center.getX() - r, center.getY() - r, 2 * r, r * 2);
		fill(g);
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
