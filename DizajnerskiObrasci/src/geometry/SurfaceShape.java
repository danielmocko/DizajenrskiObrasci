package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class SurfaceShape extends Shape implements Serializable{
	private Color areaColor;
	
	public abstract void fill(Graphics g);

	public Color getAreaColor() {
		return areaColor;
	}

	public void setAreaColor(Color areaColor) {
		this.areaColor = areaColor;
	}

	
	
	
}
