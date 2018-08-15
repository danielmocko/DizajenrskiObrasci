package geometry;

import java.awt.Graphics;
import java.awt.Color;

public class Point extends Shape implements Moveable{
	private int x;
	private int y;
	
	public Point() {
		
	}
	
	public Point(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public Point(int x, int y,Color color) {
		this(x,y);
		setEdgeColor(color);
	}
	
	public String toString() {
		return "Point: (" + this.x + "," + this.y + ")"+", color= "+toHexString(getEdgeColor());
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Point) {
			Point helpPoint =(Point)obj;
			if(this.x ==helpPoint.getX() && this.y==helpPoint.getY()){
				return true;
			}
			else
				return false;
		}
		else 
			return false;
	}
	
	public double distance(Point p) {
		double dx = x-p.getX();
		double dy = y-p.getY();
		double score = Math.sqrt(dx*dx+dy*dy);
		return score;
	}

	public int compareTo(Object o) {
		Point zero = new Point(0,0);
		Point newPoint = (Point)o;
		return (int)(this.distance(zero)-newPoint.distance(zero));
	}
	public void selected(Graphics g) {
		//g.setColor(findColor("blue"));
		g.drawRect(x -3,y-3, 6, 6);
	}
	
	//potencijalno za brisanje
	public void drawSelf(Graphics g) {
		//g.setColor(findColor(getBoja()));
		g.drawLine(x-2, y, x+2, y);
		g.drawLine(x, y-2, x, y+2);
		if(isSelected()) {
			selected(g);
		}
	}

	public void drawColor(Graphics g) {
		g.setColor(getEdgeColor());
		g.drawLine(x - 2, y, x + 2, y);
		g.drawLine(x, y - 2, x, y + 2);
		if (isSelected())
			selected(g);	
	}

	

	public boolean contains(int x, int y) {
		Point click = new Point(x,y);
		if(click.distance(this)<=2)
			return true;
		else
			return false;
	}

	public void moveFor(int x, int y) {
		this.x +=x;
		this.y +=y;
	}

	public void moveTo(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x=x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setY(int y) {
		this.y=y;
	}
}
