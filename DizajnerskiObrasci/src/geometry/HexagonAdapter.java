package geometry;

import java.awt.Color;
import java.awt.Graphics;

import hexagon.Hexagon;

public class HexagonAdapter extends Shape {
	
	private Hexagon hexagon;

	public HexagonAdapter(int x, int y, int r,Color edgeColor, Color innerColor) {
		hexagon = new Hexagon(x,y,r);
		hexagon.setAreaColor(innerColor);
		hexagon.setBorderColor(edgeColor);
	}
	
	public int compareTo(Object o) {
		return 0;
	}
/*
	public void drawSelf(Graphics g) {
		// TODO Auto-generated method stub
		
	}
*/
	public void drawColor(Graphics g) {
		g.setColor(hexagon.getBorderColor());
		g.setColor(hexagon.getAreaColor());
		hexagon.paint(g);
	}

	public void selected(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	public void drawSelf(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
