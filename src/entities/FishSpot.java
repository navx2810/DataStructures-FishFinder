package entities;

import java.awt.Point;

public class FishSpot {
	public int mass;
	public Point location;
	
	public FishSpot(int x, int y, int mass)
	{
		location = new Point(x, y);
		this.mass = mass;
	}
}
