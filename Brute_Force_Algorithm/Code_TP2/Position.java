public class Position {

	private int NalaX, NalaY, SimbaX, SimbaY, currentCost;

	public Position(int NalaX, int NalaY, int SimbaX, int SimbaY, int currentCost) {
		this.NalaX = NalaX;
		this.NalaY = NalaY;
		this.SimbaX = SimbaX;
		this.SimbaY = SimbaY;
		this.currentCost = currentCost;
	}

	public int getNalaX() {
		return NalaX;
	}

	public int getNalaY() {
		return NalaY;
	}

	public int getSimbaX() {
		return SimbaX;
	}

	public int getSimbaY() {
		return SimbaY;
	}

	public int getCurrentCost() {
		return currentCost;
	}

	public void setCost(int cost) {
		this.currentCost = cost;
	}

}