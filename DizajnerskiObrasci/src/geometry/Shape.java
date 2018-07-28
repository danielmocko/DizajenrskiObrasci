package geometry;

import java.awt.Graphics;

import java.awt.Color;

public abstract class Shape implements Comparable {
	private String color = "black";
	private Color edgeColor;
	private boolean selected;
	
	public Shape() {
		
	}
	
	public Shape(String color) {
		this.color=color;
	}
	
	public Shape(Color edgeColor) {
		this.edgeColor=edgeColor;
	}
	
	public abstract void drawSelf(Graphics g);
	
	public abstract void drawColor(Graphics g);
	
	public abstract void selected(Graphics g);	
	
	public abstract boolean contains(int x, int y);
	
	/*
	public static Color findColor(String color) {
		if(color.equalsIgnoreCase("black")) {
			return Color.BLACK;
		}
		else if(color.equalsIgnoreCase("blue")) {
			return Color.BLUE;
		}
		else if(color.equalsIgnoreCase("red")) {
			return Color.RED;
		}
		else if(color.equalsIgnoreCase("green")) {
			return Color.GREEN;
		}
		else if(color.equalsIgnoreCase("yellow")) {
			return Color.YELLOW;
		}
		else {
			return Color.BLACK;
		}
	}
	*/

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Color getEdgeColor() {
		return edgeColor;
	}

	public void setEdgeColor(Color edgeColor) {
		this.edgeColor = edgeColor;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	
}
