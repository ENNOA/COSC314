package Project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class main {
	static String LOCATION = System.getProperty("user.dir") + "\\src\\inputFiles\\";
	static Scanner input = new Scanner(System.in);
	private static final int INF = Integer.MAX_VALUE;
	private static ArrayList<ArrayList<Integer>> distanceMatrix;
	private static ArrayList<String> codes; 
	private static Map<String, Integer> airportCodes;
	
	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
		distanceMatrix=getDistances(LOCATION+"distance_matrix.txt");
		codes=getCodes(LOCATION+"airport_codes.txt");
		
	}

	 private static ArrayList<ArrayList<Integer>> getDistances(String line) throws UnsupportedEncodingException, IOException {
		ArrayList<ArrayList<Integer>> distances = new ArrayList<>();
		File file = new File(line);
		Scanner stdIn = new Scanner(file);

		while (stdIn.hasNextLine()) {
			String x = stdIn.nextLine();
			if (x.isEmpty())
				continue;
			String[] y = x.split(" ");
			ArrayList<Integer> row = new ArrayList<>();
			for (String z : y) {
				int num = Integer.parseInt(z);
				row.add(num);
			}
			distances.add(row);
		}
		stdIn.close();
		return distances;
	}
	
	private static ArrayList<String> getCodes(String line) throws FileNotFoundException {
		ArrayList<String> codes = new ArrayList<>(); 
		File file = new File(line);
		Scanner stdIn = new Scanner(file);

		while (stdIn.hasNext()) {
			String x = stdIn.next();
			if (!x.isEmpty())
				codes.add(x);
		}
		stdIn.close();
		
		return codes;
	}
	
	private static int dijkstra (int source) {
		int distance=0;
		
		
		return distance;
		
	}

}
