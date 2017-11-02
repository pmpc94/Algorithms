import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nums = br.readLine().split(" ");
		
		AdministrativeReform ar = new AdministrativeReform(Integer.parseInt(nums[0]),Integer.parseInt(nums[1]));
		for(int i=0; i<Integer.parseInt(nums[1]); i++){
			String[] line= br.readLine().split(" ");
			ar.addConnection(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]));
		}
		String[] line = br.readLine().split(" ");
		ar.setCapitals(Integer.parseInt(line[0]),Integer.parseInt(line[1]));
		
		ar.computeSolution();
		System.out.println(ar.getFirst() + " " + ar.getSecond() + " " + ar.getAny() );
	}
}
