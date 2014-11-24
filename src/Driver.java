import java.io.File;

import io.ReadMatrix;

import javax.swing.JFileChooser;

public class Driver {

	public static void main(String[] args) {
		JFileChooser chooser = new JFileChooser();
		FishFinder finder = null;
		
//		if ( chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION )
//			finder = new FishFinder( ReadMatrix.readMatrixFromFile(chooser.getSelectedFile()) );
		finder = new FishFinder( ReadMatrix.readMatrixFromFile(new File("fishschools2.txt")) );
		
		finder.findFishingSpots();
		finder.determineSchools();
		finder.solveForCenterOfMass();

	}

}
