package entities;

import java.util.LinkedList;

public class School {
	public LinkedList<FishSpot> spots;
	public int totalMass = 0;
	
	public School() {}
	
	public void calculateTotalMass()
	{
		for(FishSpot spot : spots)
			totalMass += spot.mass;
	}
}
