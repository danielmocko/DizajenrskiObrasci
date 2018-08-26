package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import geometry.Point;
import geometry.Line;

public class Square extends SurfaceShape implements Moveable,Serializable{
	protected Point upLeft;
	protected int pageLength;
	
	public Square() {
		
	}
	
	public Square(Point upLeft, int pageLength) {
		this.upLeft=upLeft;
		this.pageLength=pageLength;
	}
	
	public Square(Point upLeft, int pageLength,Color borderColor, Color areaColor) {
		this(upLeft,pageLength);
		setBorderColor(borderColor);
		setAreaColor(areaColor);
	}
	
	public Line diagonal() {
		return new Line(upLeft,new Point(upLeft.getX()+pageLength,upLeft.getY()+pageLength));
	}
	
	public String toString() {
		return "Square: upLeftPoint: ("+upLeft.getX()+","+upLeft.getY()+"), pageLength="+pageLength+", borderColor= "+toHexString(getBorderColor())+", areaColor= "+toHexString(getAreaColor());
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Square) {
			Square helpSquare = (Square) obj;
			if(this.upLeft.equals(helpSquare.upLeft) && (this.pageLength == helpSquare.pageLength)) {
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}
	
	public int bulk() {
		return 4* pageLength;
	}
	
	public int surface() {
		return pageLength*pageLength;
	}
	@Override
	public int compareTo(Object o) {
		if(o instanceof Square) {
			Square helpSquare = (Square)o;
			return this.surface()-helpSquare.surface();
		}
		else
			return 0;
	}

	@Override
	public void moveFor(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveTo(int x, int y) {
		upLeft.setX(x);
		upLeft.setY(y);
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(getAreaColor());
		g.fillRect(upLeft.getX()+1, upLeft.getY()+1, pageLength-1, pageLength-1);;
		
	}

	@Override
	public void drawColor(Graphics g) {
		g.setColor(getBorderColor());
		g.drawRect(upLeft.getX(), upLeft.getY(), pageLength, pageLength);
		fill(g);
		if(isSelected())
			selected(g);
	}

	@Override
	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		new Line(getUpLeft(), new Point(getUpLeft().getX() + pageLength, getUpLeft().getY())).selected(g);
		new Line(getUpLeft(), new Point(getUpLeft().getX(), getUpLeft().getY() + pageLength)).selected(g);
		new Line(new Point(getUpLeft().getX() + pageLength, getUpLeft().getY()), diagonal().getpEnd())
				.selected(g);
		new Line(new Point(getUpLeft().getX(), getUpLeft().getY() + pageLength), diagonal().getpEnd())
				.selected(g);
	}

	@Override
	public boolean contains(int x, int y) {
		if (upLeft.getX() <= x && x <= (upLeft.getX() + pageLength)
				&& upLeft.getY() <= y && y <= (upLeft.getY() + pageLength))
			return true;
		else
			return false;
	}

	public Point getUpLeft() {
		return upLeft;
	}

	public void setUpLeft(Point upLeft) {
		this.upLeft = upLeft;
	}

	public int getPageLength() {
		return pageLength;
	}

	public void setPageLength(int pageLength) {
		this.pageLength = pageLength;
	}
	
	
	
}
