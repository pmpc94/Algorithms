import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RomanWarfare {
	private List<People> armies;
	private List<People> populations;
	private int nArmies;
	private int nPops;
	private int[][] distances;
	private int[][] wealth;
	private int[][] maint;
	private int[][] neoTheChosenOne;
	private int wealthToRet;
	private int distToRet;
	private int maintToRet;

	public RomanWarfare(int nArmies, int nPops) {
		this.nArmies = nArmies;
		this.nPops = nPops;
		armies = new ArrayList<People>(nArmies);
		populations = new ArrayList<People>(nPops);
	}

	public void addArmy(int x, int y, int cost) {
		armies.add(new People(x, y, cost));
	}

	public void addPop(int x, int y, int wealth) {
		populations.add(new People(x, y, wealth));
	}

	public int getWealth() {
		return wealthToRet;
	}

	public int getTotalDistance() {
		return distToRet;
	}

	public int getMaintenance() {
		return maintToRet;
	}

	private int getDistance(People p1, People p2) {
		return Math.abs(p1.getX() - p2.getX()) + Math.abs(p1.getY() - p2.getY());
	}

	private void preProcess() {
		distances = new int[nArmies + 1][nPops + 1];
		wealth = new int[nArmies + 1][nPops + 1];
		maint = new int[nArmies + 1][nPops + 1];
		neoTheChosenOne = new int[nArmies][nPops];
		
		int k = 0;
		do {
			for (int i = 0, j = 0; j < nPops; i++, j++) {
				neoTheChosenOne[i + k][j] = -1;
			}
			k++;
		} while (neoTheChosenOne[nArmies - 1][nPops - 1] == 0);
	}
	
	private void sort(){
		Collections.sort(populations);
		Collections.sort(armies);
	}

	public void computeSolution() {
		sort();
	
		if(nArmies>nPops){
		preProcess();
		for (int currentArmy = 1; currentArmy < nArmies + 1; currentArmy++) {
			for (int currentPop = 1; currentPop < nPops + 1; currentPop++) {
				if (neoTheChosenOne[currentArmy - 1][currentPop - 1] == -1) {
					int w = wealth[currentArmy - 1][currentPop - 1] + populations.get(currentPop - 1).getPrice();
					int d = distances[currentArmy - 1][currentPop - 1] + getDistance(populations.get(currentPop - 1), armies.get(currentArmy - 1));
					int m = maint[currentArmy - 1][currentPop - 1] + armies.get(currentArmy - 1).getPrice();

					if (w > wealth[currentArmy - 1][currentPop]) {
						setProperties(w, d, m, currentArmy, currentPop);
					} else if (d < distances[currentArmy - 1][currentPop] && w == wealth[currentArmy - 1][currentPop]) {
						setProperties(w, d, m, currentArmy, currentPop);
					} else if (m < maint[currentArmy - 1][currentPop] && d == distances[currentArmy - 1][currentPop] && w == wealth[currentArmy - 1][currentPop]) {
						setProperties(w, d, m, currentArmy, currentPop);
					} else {
						wealth[currentArmy][currentPop] = wealth[currentArmy - 1][currentPop];
						distances[currentArmy][currentPop] = distances[currentArmy - 1][currentPop];
						maint[currentArmy][currentPop] = maint[currentArmy - 1][currentPop];
					}
				} else {
					continue;
				}
			}
		}
		wealthToRet=wealth[nArmies][nPops];
		distToRet = distances[nArmies][nPops];
		maintToRet = maint[nArmies][nPops];
		} else  if (nPops==nArmies){
			for (int i = 0; i < nArmies; i++) {
				People currArmy = armies.get(i);
				People currPop = populations.get(i);
				wealthToRet += currPop.getPrice();
				maintToRet += currArmy.getPrice();
				distToRet += getDistance(currArmy, currPop);
			}
		}else{
			for(int i = nArmies-1, j = nPops-1; i>=0; i--, j--){
				People currArmy = armies.get(i);
				People currPop = populations.get(j);
				wealthToRet += currPop.getPrice();
				maintToRet += currArmy.getPrice();
				distToRet += getDistance(currArmy, currPop);
			}
		}
	}

	private void setProperties(int w, int d, int m, int currentArmy, int currentPop) {
		wealth[currentArmy][currentPop] = w;
		distances[currentArmy][currentPop] = d;
		maint[currentArmy][currentPop] = m;
	}

	/*
	 * public void printMatrix() { computeSolution(); for (int i = 0; i <
	 * neoTheChosenOne.length; i++) { for (int j = 0; j <
	 * neoTheChosenOne[i].length; j++) { System.out.print(neoTheChosenOne[i][j]
	 * + " "); } System.out.println(); } }
	 */

}
