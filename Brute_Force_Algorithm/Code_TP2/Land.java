import java.util.LinkedList;
import java.util.Queue;

public class Land {

	public static final char WALL = '#';
	public static final String NOLOVE = "NO LOVE";

	private boolean[][] land;
	private int treasureX, treasureY, initialNalaX, initialNalaY, initialSimbaX, initialSimbaY;
	private int nRows, nCols;
	private int cost;
	private Queue<Position> toExpand;
	private boolean[][][][] visitedStates;

	public Land(int nRows, int nCols, int treasureX, int treasureY, int NalaX, int NalaY, int SimbaX, int SimbaY) {
		land = new boolean[nRows][nCols];
		this.treasureX = treasureX - 1;
		this.treasureY = treasureY - 1;
		this.initialNalaX = NalaX - 1;
		this.initialNalaY = NalaY - 1;
		this.initialSimbaX = SimbaX - 1;
		this.initialSimbaY = SimbaY - 1;
		this.nRows = nRows - 1;
		this.nCols = nCols - 1;
		visitedStates = new boolean[nRows][nCols][nRows][nCols];
	}

	public int computeSolution() {

		Position NalaSimbaFrom = new Position(initialNalaX, initialNalaY, initialSimbaX, initialSimbaY, cost);
		toExpand = new LinkedList<>();
		toExpand.add(NalaSimbaFrom);

		while (toExpand.size() != 0) {
			Position expanded = toExpand.poll();

			int xNala = expanded.getNalaX();
			int yNala = expanded.getNalaY();

			int xSimba = expanded.getSimbaX();
			int ySimba = expanded.getSimbaY();

			int currentCost = expanded.getCurrentCost();

			if (xNala == treasureX && xSimba == treasureX && yNala == treasureY && ySimba == treasureY)
				return currentCost;

			if (canMove(xNala - 1, yNala) && canMove(xSimba - 1, ySimba)
					&& !visitedStates[xNala - 1][yNala][xSimba - 1][ySimba]) {
				Position NalaSimbaTo = new Position(xNala - 1, yNala, xSimba - 1, ySimba, currentCost + 1);
				toExpand.add(NalaSimbaTo);
				visitedStates[xNala - 1][yNala][xSimba - 1][ySimba] = true;
			} // both moved north
			else if (canMove(xNala - 1, yNala) && !canMove(xSimba - 1, ySimba)
					&& !visitedStates[xNala - 1][yNala][xSimba][ySimba]) {
				Position NalaTo = new Position(xNala - 1, yNala, xSimba, ySimba, currentCost + 1);
				toExpand.add(NalaTo);
				visitedStates[xNala - 1][yNala][xSimba][ySimba] = true;
			} // only nala moved north
			else if (!canMove(xNala - 1, yNala) && canMove(xSimba - 1, ySimba)
					&& !visitedStates[xNala][yNala][xSimba - 1][ySimba]) {
				Position SimbaTo = new Position(xNala, yNala, xSimba - 1, ySimba, currentCost + 1);
				toExpand.add(SimbaTo);
				visitedStates[xNala][yNala][xSimba - 1][ySimba] = true;
			} // only simba moved north

			if (canMove(xNala + 1, yNala) && canMove(xSimba + 1, ySimba)
					&& !visitedStates[xNala + 1][yNala][xSimba + 1][ySimba]) {
				Position NalaSimbaTo = new Position(xNala + 1, yNala, xSimba + 1, ySimba, currentCost + 1);
				toExpand.add(NalaSimbaTo);
				visitedStates[xNala + 1][yNala][xSimba + 1][ySimba] = true;
				// System.out.println("Nala and Simba moved south");
			}
			else if (canMove(xNala + 1, yNala) && !canMove(xSimba + 1, ySimba)
					&& !visitedStates[xNala + 1][yNala][xSimba][ySimba]) {
				Position NalaTo = new Position(xNala + 1, yNala, xSimba, ySimba, currentCost + 1);
				toExpand.add(NalaTo);
				visitedStates[xNala + 1][yNala][xSimba][ySimba] = true;
				// System.out.println("Only Nala moved south");
			} else if (!canMove(xNala + 1, yNala) && canMove(xSimba + 1, ySimba)
					&& !visitedStates[xNala][yNala][xSimba + 1][ySimba]) {
				Position SimbaTo = new Position(xNala, yNala, xSimba + 1, ySimba, currentCost + 1);
				toExpand.add(SimbaTo);
				visitedStates[xNala][yNala][xSimba + 1][ySimba] = true;
				// System.out.println("Only Simba moved south");
			}

			if (canMove(xNala, yNala - 1) && canMove(xSimba, ySimba + 1)
					&& !visitedStates[xNala][yNala - 1][xSimba][ySimba + 1]) {
				Position NalaSimbaTo = new Position(xNala, yNala - 1, xSimba, ySimba + 1, currentCost + 1);
				toExpand.add(NalaSimbaTo);
				visitedStates[xNala][yNala - 1][xSimba][ySimba + 1] = true;
				// System.out.println("Nala and Simba moved westEast");
			}

			else if (canMove(xNala, yNala - 1) && !canMove(xSimba, ySimba + 1)
					&& !visitedStates[xNala][yNala - 1][xSimba][ySimba]) {
				Position NalaTo = new Position(xNala, yNala - 1, xSimba, ySimba, currentCost + 1);
				toExpand.add(NalaTo);
				visitedStates[xNala][yNala - 1][xSimba][ySimba] = true;
				// System.out.println("Only Nala moved west");
			} else if (!canMove(xNala, yNala - 1) && canMove(xSimba, ySimba + 1)
					&& !visitedStates[xNala][yNala][xSimba][ySimba + 1]) {
				Position SimbaTo = new Position(xNala, yNala, xSimba, ySimba + 1, currentCost + 1);
				toExpand.add(SimbaTo);
				visitedStates[xNala][yNala][xSimba][ySimba + 1] = true;
				// System.out.println("Only Simba moved east");
			}
			
			if (canMove(xNala, yNala + 1) && canMove(xSimba, ySimba - 1)
					&& !visitedStates[xNala][yNala + 1][xSimba][ySimba - 1]) {
				Position NalaSimbaTo = new Position(xNala, yNala + 1, xSimba, ySimba - 1, currentCost + 1);
				toExpand.add(NalaSimbaTo);
				visitedStates[xNala][yNala + 1][xSimba][ySimba - 1] = true;
				// System.out.println("Nala and Simba moved eastWest");
			}
			else if (canMove(xNala, yNala + 1) && !canMove(xSimba, ySimba - 1)
					&& !visitedStates[xNala][yNala + 1][xSimba][ySimba]) {
				Position NalaTo = new Position(xNala, yNala + 1, xSimba, ySimba, currentCost + 1);
				toExpand.add(NalaTo);
				visitedStates[xNala][yNala + 1][xSimba][ySimba] = true;
				// System.out.println("Only Nala moved east");
			} else if (!canMove(xNala, yNala + 1) && canMove(xSimba, ySimba - 1)
					&& !visitedStates[xNala][yNala][xSimba][ySimba - 1]) {
				Position SimbaTo = new Position(xNala, yNala, xSimba, ySimba - 1, currentCost + 1);
				toExpand.add(SimbaTo);
				visitedStates[xNala][yNala][xSimba][ySimba - 1] = true;
				// System.out.println("Only Simba moved west");
			}
		}
		return -1;
	}

	// AUXILIARY FUNCTIONS

	public void fillWall(String row, int pos) {
		for (int i = 0; i < row.length(); i++) {
			if (row.charAt(i) == WALL)
				land[pos][i] = true;
		}
	}

	public boolean canMove(int nRow, int nCol) {
		return !(nRow > nRows || nCol > nCols || nRow < 0 || nCol < 0 || land[nRow][nCol]);
	}

}
