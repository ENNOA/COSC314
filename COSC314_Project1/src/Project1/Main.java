package Project1;
/*
 * Jaison Eccleston 27JUN2023
 * program to determine whether a 10x10 boolean matrix is  Reflexive, AntiReflexive, Symmetric, and or AntiSymmetric
 * Reflexive is defined as  [i][i] == true
 * AntiReflexive is defined as  [i][i] == false
 * Symmetric is defined as  [i][j] == [j][i]
 * AntiSymmetric is defined as  [i][j] != [j][i]
 * 
 */
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static String LOCATION = System.getProperty("user.dir") + "\\src\\inputFiles\\";
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
		// Variables
		int run = 0;
		String Sentinel = null;

		do {
			System.out.println("Files available for selection:");
			File[] library = Files();
			System.out.print("\nSelect a file to examine: ");
			run = (input.nextInt() - 1);
			String line = LOCATION + library[run].getName();

			ArrayList<ArrayList<Integer>> list = new ArrayList<>();
			list = getData(line);

			System.out.println("\n " + library[run].getName() + " File contents");
			for (int i = 0; i < 10; i++) {
				System.out.println(list.get(i));
			}

			System.out.println("\nThe matrix is Reflexive: " + isReflexive(list));
			System.out.println("The matrix is Antireflexive: " + isAntiReflexive(list));
			System.out.println("The matrix is Symmetric: " + isSymmetric(list));
			System.out.println("The matrix is AntiSymmetric: " + isAntiSymmetric(list));

			System.out.println("\n\nDo it again? Y/N");
			input.nextLine();
			Sentinel = input.next();
			System.out.println("\n\n");
		} while (Sentinel.equalsIgnoreCase("Y"));
		input.close();
	}

	public static File[] Files() {
		File files = new File(LOCATION);

		File[] listOfFiles = files.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			System.out.println(i + 1 + ") " + listOfFiles[i].getName());
		}

		return listOfFiles;

	}

	public static ArrayList<ArrayList<Integer>> getData(String line) throws UnsupportedEncodingException, IOException {
		ArrayList<ArrayList<Integer>> values = new ArrayList<>();
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
			values.add(row);
		}
		stdIn.close();
		return values;
	}

	public static boolean isReflexive(ArrayList<ArrayList<Integer>> file) {
		int counter = 0;
		for (int i = 0; i < file.size(); i++) {
			if (file.get(i).get(i) == 1)
				counter++;
		}
		if (counter != 10)
			return false;
		return true;
	}

	public static boolean isAntiReflexive(ArrayList<ArrayList<Integer>> file) {
		int counter = 0;
		for (int i = 0; i < file.size(); i++) {
			if (file.get(i).get(i) == 0)
				counter++;
		}
		if (counter != 10)
			return false;
		return true;

	}

	public static boolean isSymmetric(ArrayList<ArrayList<Integer>> file) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (file.get(i).get(j) == file.get(j).get(i))
					return true;
			}
		}
		return false;
	}

	public static boolean isAntiSymmetric(ArrayList<ArrayList<Integer>> file) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (file.get(i).get(j) != file.get(j).get(i))
					return true;
			}
		}
		return false;
	}

	public static boolean toBool(int x) {
		if (x == 1) 
			return true;
		 else
			return false;
	}

}
