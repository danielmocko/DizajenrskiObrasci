package geometry;

import java.awt.Graphics;

import java.awt.Color;

public abstract class Shape implements Comparable {
	private Color borderColor;
	private boolean selected;
	
	public Shape() {
		
	}
	
	public Shape(Color borderColor) {
		this.borderColor=borderColor;
	}
	
	public abstract void drawColor(Graphics g);
	
	public abstract void selected(Graphics g);	
	
	public abstract boolean contains(int x, int y);
	
	public static String toHexString(Color color) {
		String hexColour = Integer.toHexString(color.getRGB() & 0xffffff);
		if (hexColour.length() < 6) {
		    hexColour = "000000".substring(0, 6 - hexColour.length()) + hexColour;
		}
		return "#" + hexColour;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	
}
