import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try{
			String[] numArmiesPop = br.readLine().split(" ");
			int nArmies = Integer.parseInt(numArmiesPop[0]);
			int nPops = Integer.parseInt(numArmiesPop[1]);
			RomanWarfare rw = new RomanWarfare(nArmies, nPops);
			
			for(int i=0;i< nArmies;i++){
				String[] armyInfo = br.readLine().split(" ");
				rw.addArmy(Integer.parseInt(armyInfo[0]), Integer.parseInt(armyInfo[1]), Integer.parseInt(armyInfo[2]));
			}
			for(int i=0;i< nPops;i++){
				String[] popInfo = br.readLine().split(" ");
				rw.addPop(Integer.parseInt(popInfo[0]), Integer.parseInt(popInfo[1]), Integer.parseInt(popInfo[2]));
			}
			
			rw.computeSolution();
			
			String output = rw.getWealth() + " " + rw.getTotalDistance() + " " + rw.getMaintenance();
			
			System.out.println(output);
			//rw.printMatrix();
		}catch(IOException e){
			System.out.println("Error reading from stdin");
		}
	}

}
