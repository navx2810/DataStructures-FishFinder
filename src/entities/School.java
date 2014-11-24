package entities;

import java.util.ArrayList;

public class School {
	public ArrayList<FishSpot> spots;
	public int totalMass = 0;
	
	public School(ArrayList<FishSpot> container) {
		this.spots = container;
	}
	
}
