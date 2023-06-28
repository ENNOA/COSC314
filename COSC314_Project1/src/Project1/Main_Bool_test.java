package Project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main_Bool_test {
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

			ArrayList<ArrayList<Boolean>> list = new ArrayList<>();
			list = getData(line);

			System.out.println("\n " + library[run].getName() + " File contents");
			for (int i = 0; i < 10; i++) {
				System.out.println(list.get(i));
			}

			System.out.println("\nThe matrix is Reflexive: " + isReflexive(list));
			System.out.println("The matrix is Antireflexive: " + isAntiReflexive(list));
			System.out.println("The matrix is Symmetric: " + isSymmetric(list));
			System.out.println("The matrix is AntSymmetric: " + isAntiSymmetric(list));
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

	public static ArrayList<ArrayList<Boolean>> getData(String line) throws UnsupportedEncodingException, IOException {
		ArrayList<ArrayList<Boolean>> values = new ArrayList<>();
		File file = new File(line);
		Scanner stdIn = new Scanner(file);

		while (stdIn.hasNextLine()) {
			String x = stdIn.nextLine();
			if (x.isEmpty())
				continue;
			String[] y = x.split(" ");
			ArrayList<Boolean> row = new ArrayList<>();
			for (String z : y) {
				boolean num = toBool(z);
				row.add(num);
			}
			values.add(row);
		}
		stdIn.close();
		return values;
	}

	public static boolean isReflexive(ArrayList<ArrayList<Boolean>> file) {
		int counter = 0;
		for (int i = 0; i < file.size(); i++) {
			if (file.get(i).get(i) == true)
				counter++;
		}
		if (counter != 10)
			return false;
		return true;
	}

	public static boolean isAntiReflexive(ArrayList<ArrayList<Boolean>> file) {
		int counter = 0;
		for (int i = 0; i < file.size(); i++) {
			if (file.get(i).get(i) == false)
				counter++;
		}
		if (counter != 10)
			return false;
		return true;

	}

	public static boolean isSymmetric(ArrayList<ArrayList<Boolean>> file) {
		for (int i = 0; i < 9; i++) {
			for (int j = 1; j < 10; j++) {
				if (i==j) 
					j++;
				else if (j==i+2) {
					j--;
					i++;
				}
				else if (file.get(i).get(j) == file.get(j).get(i)) 
					return true;					
			}
		}
		return false;
	}

	public static boolean isAntiSymmetric(ArrayList<ArrayList<Boolean>> file) {
		for (int i = 0; i < 9; i++) {
			for (int j = 1; j < 10; j++) {
				if (i==j) 
					j++;
				else if (j==i+2) {
					j--;
					i++;
				}
				else if (file.get(i).get(j) != file.get(j).get(i)) 
					return true;					
			}
		}
		return false;
	}

	public static boolean toBool(String x) {
		if (x.equals("1")) {
			return true;
		} else
			return false;
	}

}
