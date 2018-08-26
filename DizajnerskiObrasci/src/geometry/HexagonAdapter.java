package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import hexagon.Hexagon;

public class HexagonAdapter extends SurfaceShape implements Serializable {
	
	private Hexagon hexagon;

	public HexagonAdapter(int x, int y, int r,Color borderColor, Color areaColor) {
		hexagon = new Hexagon(x,y,r);
		hexagon.setAreaColor(areaColor);
		hexagon.setBorderColor(borderColor);
	}
	
	public int compareTo(Object o) {
		return 0;
	}
	
	public void drawColor(Graphics g) {
		g.setColor(hexagon.getBorderColor());
		fill(g);
		hexagon.paint(g);
		selected(g);
	}

	public void selected(Graphics g) {
		if(isSelected())
			hexagon.setSelected(true);
		else
			hexagon.setSelected(false);
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof HexagonAdapter) {
			HexagonAdapter help = (HexagonAdapter)obj;
			if(this.hexagon.getX()==help.hexagon.getX() && this.hexagon.getY()==help.hexagon.getY() && this.hexagon.getR()==help.hexagon.getR()) {
				return true;
			}else
				return false;
		}
		else
			return false;
	}
	
	public String toString() {
		return "Hexagon: center: ("+hexagon.getX()+","+hexagon.getY()+"), radius="+hexagon.getR()+", borderColor= "+toHexString(hexagon.getBorderColor())+", areaColor= "+toHexString(hexagon.getAreaColor());
	}

	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(hexagon.getAreaColor());
	}
	

	public Hexagon getHexagon() {
		return hexagon;
	}

	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}

}
