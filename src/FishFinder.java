import java.util.LinkedList;
import java.util.Stack;

import entities.FishSpot;
import entities.School;


public class FishFinder {
	private LinkedList<FishSpot> allSpots;
	private LinkedList<School> schools;
	private int[][] matrix;
	private boolean[][] visited;
	private FishSpot[][] fishingSpotMatrix;
	
	public FishFinder(int[][] matrix)
	{
		this.matrix = matrix;
		allSpots = new LinkedList<FishSpot>();
		visited = new boolean[ matrix.length ][ matrix[0].length ];
		fishingSpotMatrix = new FishSpot[ matrix.length ][ matrix[0].length ];
		schools = new LinkedList<School>();
	}
	
	public void findFishingSpots()
	{
		for(int x = 0; x < matrix.length; x++)
			for(int y = 0; y < matrix[0].length; y++)
				if ( matrix[x][y] > 0 )
					allSpots.add(new FishSpot(x, y, matrix[x][y]));
		fillFishingSpotMatrix();
	}

	public void determineSchools()
	{
		while( allSpots.size() > 0 )
		{
		Stack<FishSpot> spotStack = new Stack<FishSpot>();
		LinkedList<FishSpot> spots = new LinkedList<FishSpot>();
		FishSpot spot = allSpots.pop();
		spotStack.push(spot);
		spots.add(spot);
		boolean done = false;
		
		while( !done )
			if ( !isNorthEmpty(spot) && !haveIVisisted(spotAtNorth(spot)) )
				addSpot( spotAtNorth(spot), spots, spotStack );
			else if ( !isNorthEastEmpty(spot) && !haveIVisisted(spotAtNorthEast(spot)) )
				addSpot(spotAtNorthEast(spot), spots, spotStack );
			else if ( !isEastEmpty(spot) && !haveIVisisted(spotAtEast(spot)) )
				addSpot(spotAtEast(spot), spots, spotStack );
			else if ( !isSouthEastEmpty(spot) && !haveIVisisted(spotAtSouthEast(spot)) )
				addSpot(spotAtSouthEast(spot), spots, spotStack );
			else if ( !isSouthEmpty(spot) && !haveIVisisted(spotAtSouth(spot)) )
				addSpot(spotAtSouth(spot), spots, spotStack );
			else if ( !isSouthWestEmpty(spot) && !haveIVisisted(spotAtSouthWest(spot)) )
				addSpot(spotAtSouthWest(spot), spots, spotStack );
			else if ( !isWestEmpty(spot) && !haveIVisisted(spotAtWest(spot)) )
				addSpot(spotAtWest(spot), spots, spotStack );
			else if ( !isNorthWestEmpty(spot) && !haveIVisisted(spotAtNorthWest(spot)) )
				addSpot(spotAtNorthWest(spot), spots, spotStack );
			else if ( spotStack.size() == 0 )
			{
				schools.add(new School(spots));
				done = true;
			}
			else
				spot = spotStack.pop();	
		}
		
	}
	
	public void solveForCenterOfMass()
	{
		
		for(School school : schools)
		{
			int totalx = 0;
			int totaly = 0;
			int totalMass = 0;
			for(FishSpot spot : school.spots)
			{
				totalMass += spot.mass;
				totalx += spot.x * spot.mass;
				totaly += spot.y * spot.mass;
			}
			
			printSchoolMatrix(school);
			System.out.println(String.format("\t\tCenter of mass [%s, %s]\t\t%s\n", (totalx/totalMass), (totaly/totalMass), totalMass-1));
		}
	}
	
	// ========================================= Helper Functions =========================================
	
	private boolean isNorthEmpty(FishSpot spot)
	{
		if ( pointIsInRange(spot.x-1, spot.y) && fishingSpotMatrix[spot.x-1][spot.y] != null  )
			return false;
		return true;
	}
	
	private boolean isSouthEmpty(FishSpot spot)
	{
		if ( pointIsInRange(spot.x+1, spot.y) && fishingSpotMatrix[spot.x+1][spot.y] != null )
			return false;
		return true;
	}
	
	private boolean isEastEmpty(FishSpot spot)
	{
		if ( pointIsInRange(spot.x, spot.y+1) && fishingSpotMatrix[spot.x][spot.y+1] != null )
			return false;
		return true;
	}
	
	private boolean isWestEmpty(FishSpot spot)
	{
		if ( pointIsInRange(spot.x, spot.y-1) && fishingSpotMatrix[spot.x][spot.y-1] != null )
			return false;
		return true;
	}
	
	private boolean isNorthWestEmpty(FishSpot spot)
	{
		if ( pointIsInRange(spot.x-1, spot.y-1) && fishingSpotMatrix[spot.x-1][spot.y-1] != null )
			return false;
		return true;
	}
	
	private boolean isNorthEastEmpty(FishSpot spot)
	{
		if ( pointIsInRange(spot.x-1, spot.y+1) && fishingSpotMatrix[spot.x-1][spot.y+1] != null )
			return false;
		return true;
	}
	
	private boolean isSouthWestEmpty(FishSpot spot)
	{
		if ( pointIsInRange(spot.x+1, spot.y-1) && fishingSpotMatrix[spot.x+1][spot.y-1] != null )
			return false;
		return true;
	}
	
	private boolean isSouthEastEmpty(FishSpot spot)
	{
		if ( pointIsInRange(spot.x+1, spot.y+1) && fishingSpotMatrix[spot.x+1][spot.y+1] != null )
			return false;
		return true;
	}

	private boolean pointIsInRange(int x, int y) {
		if( x >= 0 && y >= 0 && x < matrix.length && y < matrix[0].length)
			return true;
		return false;
	}
	
	private FishSpot spotAtNorth(FishSpot spot)
	{
		return fishingSpotMatrix[spot.x-1][spot.y];
	}
	
	private FishSpot spotAtEast(FishSpot spot)
	{
		return fishingSpotMatrix[spot.x][spot.y+1];
	}
	
	private FishSpot spotAtSouth(FishSpot spot)
	{
		return fishingSpotMatrix[spot.x+1][spot.y];
	}
	
	private FishSpot spotAtWest(FishSpot spot)
	{
		return fishingSpotMatrix[spot.x][spot.y-1];
	}
	
	private FishSpot spotAtNorthEast(FishSpot spot)
	{
		return fishingSpotMatrix[spot.x-1][spot.y+1];
	}
	
	private FishSpot spotAtNorthWest(FishSpot spot)
	{
		return fishingSpotMatrix[spot.x-1][spot.y-1];
	}
	
	private FishSpot spotAtSouthWest(FishSpot spot)
	{
		return fishingSpotMatrix[spot.x+1][spot.y-1];
	}
	
	private FishSpot spotAtSouthEast(FishSpot spot)
	{
		return fishingSpotMatrix[spot.x+1][spot.y+1];
	}
	
	private boolean haveIVisisted(FishSpot spot)
	{
		if ( visited[spot.x][spot.y] )
			return true;
		return false;
	}
	
	private void addSpot(FishSpot spot, LinkedList<FishSpot> spots, Stack<FishSpot> spotStack)
	{
		spots.add(spot);
		spotStack.push(spot);
		visited[spot.x][spot.y] = true;
		
		allSpots.remove(spot);
	}
	
	private void fillFishingSpotMatrix() {
		for (FishSpot spot : allSpots)
			fishingSpotMatrix[spot.x][spot.y] = spot;
		
	}
	
	private void printSchoolMatrix(School school)
	{
		boolean[][] tempMatrix = new boolean[matrix.length][matrix[0].length];
		for(FishSpot spot : school.spots)
			tempMatrix[spot.x][spot.y] = true;
		
		for(int x = 0; x < tempMatrix.length; x++)
		{
			System.out.print("| ");
			for(int y = 0; y < tempMatrix[0].length; y++)
				if ( tempMatrix[x][y] )
					System.out.print("D ");
				else
					System.out.print("- ");
			System.out.print("|\n");
		}
		System.out.println("\n");
			
				
		
	}
	
	
}
