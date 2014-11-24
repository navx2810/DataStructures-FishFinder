import io.ReadMatrix;

import javax.swing.JFileChooser;


public class Driver {

	public static void main(String[] args) {
		JFileChooser chooser = new JFileChooser();
		int matrix[][];
		
		if ( chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION )
			matrix = ReadMatrix.readMatrixFromFile(chooser.getSelectedFile());
		

	}

}
