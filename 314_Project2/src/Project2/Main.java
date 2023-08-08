package Project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class Main {
	//static String LOCATION = System.getProperty("user.dir") + "\\src\\inputs\\";
	static Scanner input = new Scanner(System.in);
	private static final int INF = Integer.MAX_VALUE;
	private static ArrayList<ArrayList<Integer>> distanceMatrix;
	private static ArrayList<String> codes;
	private static HashMap<String, Integer> airportCodes = new HashMap<>();

	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
		//variables
		String start = null;
		String end = null;
		//populate 2d arraylist with data
		//distanceMatrix = getDistances(LOCATION + "distance_matrix.txt");
		distanceMatrix = getDistances("src"+File.separator+"inputFiles"+File.separator+"distance_matrix.txt");
		//populate arraylist with data
		//getCodes(LOCATION + "airport_codes.txt");
		getCodes("src"+File.separator+"inputFiles"+File.separator+"airport_codes.txt");


		//print list of airport codes
		System.out.println("Airport Codes");
		System.out.println("-------------");
		for (String x : airportCodes.keySet())
			System.out.println(" "+x);
		System.out.print("\nSelect a start location: ");
		start = input.next();
		start=start.toUpperCase();
		System.out.print("Select an end destination: ");
		end = input.next();
		end=end.toUpperCase();

		int src = airportCodes.getOrDefault(start, -1);
		int dest = airportCodes.getOrDefault(end, -1);

		if (src == -1 || dest == -1) {
			System.out.println("Invalid airport code.");
			return;
		}

		int[] distances = dijkstra(src);
		int shortestDistance = distances[dest];
		List<String> shortestPath = shortestPath(distances, src, dest);

		System.out.println("Shortest distance from " + start + " to " + end + ": " + shortestDistance + " miles");
		System.out.println("Shortest route: " + String.join(" -> ", shortestPath));
	}
	//Method to get data from text file and place into 2d arraylist
	private static ArrayList<ArrayList<Integer>> getDistances(String line)
			throws UnsupportedEncodingException, IOException {
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
	
	//Method to get data from text file and place into 1d arraylist
	private static ArrayList<String> getCodes(String line) throws FileNotFoundException {
		File file = new File(line);
		Scanner stdIn = new Scanner(file);
		int index=0;
		while (stdIn.hasNext()) {
			String x = stdIn.next();
			if (!x.isEmpty()) {
				airportCodes.put(x, index);
				index++;
			}
		}
		stdIn.close();
		return codes;
	}

	private static int[] dijkstra(int src) {
		int[] distances = new int[distanceMatrix.size()];
		Arrays.fill(distances, INF);
		distances[src] = 0;
		boolean[] visited = new boolean[distanceMatrix.size()];

		for (int i = 0; i < distanceMatrix.size(); i++) {
			int minDist = INF;
			int nearV = -1;

			for (int j = 0; j < distanceMatrix.size(); j++) {
				if (!visited[j] && distances[j] < minDist) {
					minDist = distances[j];
					nearV = j;
				}
			}

			if (nearV == -1) 
				break;
			
			visited[nearV] = true;

			for (int k = 0; k < distanceMatrix.size(); k++) {
				int weight = distanceMatrix.get(nearV).get(k);
				if (weight != INF && distances[nearV] + weight < distances[k]) 
					distances[k] = distances[nearV] + weight;
			}
		}
		return distances;
	}

	private static List<String> shortestPath(int[] distances, int src, int dest) {
		List<String> path = new ArrayList<>();
		int currV = dest;
		while (currV != src) {
			path.add(getAirportCode(currV));
			int minDist = INF;
			int nextV = currV;
			for (int i = 0; i < distanceMatrix.size(); i++) {
				if (distanceMatrix.get(i).get(currV) != INF
						&& distances[i] + distanceMatrix.get(i).get(currV) < minDist) {
					minDist = distances[i] + distanceMatrix.get(i).get(currV);
					nextV = i;
				}
			}
			currV = nextV;
		}
		path.add(getAirportCode(src));
		Collections.reverse(path);
		return path;
	}
	
	private static String getAirportCode(int index) {
	    for (String airportCode : airportCodes.keySet()) {
	        if (airportCodes.get(airportCode) == index) {
	            return airportCode;
	        }
	    }
	    return null; 
	}

}