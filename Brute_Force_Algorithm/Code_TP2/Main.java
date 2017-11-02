import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";

		while ((line = br.readLine()) != null) {
			String[] matrixDimensions = line.split(" ");
			if (matrixDimensions.length != 2)
				System.exit(0);

			int nRows = Integer.parseInt(matrixDimensions[0]);
			int nCols = Integer.parseInt(matrixDimensions[1]);

			String[] objectPositions = br.readLine().split(" ");

			Land l = new Land(nRows, nCols, Integer.parseInt(objectPositions[0]), Integer.parseInt(objectPositions[1]),
					Integer.parseInt(objectPositions[2]), Integer.parseInt(objectPositions[3]),
					Integer.parseInt(objectPositions[4]), Integer.parseInt(objectPositions[5]));

			for (int i = 0; i < nRows; i++) {
				String fillingMatrix = br.readLine();
				l.fillWall(fillingMatrix, i);
			}

			int result = l.computeSolution();
			if (result != -1)
				System.out.println(result);
			else
				System.out.println(Land.NOLOVE);
		}
	}
}
