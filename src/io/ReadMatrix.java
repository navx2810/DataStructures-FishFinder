package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public final class ReadMatrix 
{
	public static int[][] readMatrixFromFile(File file)
	{
		try 
		{
		ArrayList<String> list = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		
		while( (line = reader.readLine()) != null )
			list.add(line);
		reader.close();
		
		int[][] matrix = new int[ getSizeOfMatrix(list.get(0))[0] ][ getSizeOfMatrix(list.get(0))[1] ];
		
		for(int x = 1; x < list.size(); x++)
		{
			String[] values = list.get(x).split(" ");
			for(int y = 0; y < values.length; y++)
				matrix[x-1][y] = Integer.parseInt(values[y]); 
		}
			
		return matrix;
		
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}
	
	private static int[] getSizeOfMatrix(String line)
	{
		String[] values = line.split(" ");
		int[] size = new int[2];
		size[0] = Integer.parseInt(values[0]);
		size[1] = Integer.parseInt(values[1]);
		
		return size;
	}
}
