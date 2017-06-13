package pref;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class EditDistance {

	static int[][] classArray = null;

	public EditDistance() {

	}

	public static void main(String[] args) {
		String string1 = null;
		String string2 = null;
		String pfad = null;

		if (args.length == 1) {
			try {
				pfad = args[0];
				File file = new File(pfad);
				if (!file.canRead() || !file.isFile()) {
					System.out.println("Fehler beim Einlesen der Datei");
					System.exit(0);
				}
			} catch (Exception e) {
				System.out.println("Fehler beim Einlesen des Dateipfades");
				System.exit(0);
			}
			if (pfad != null) {
				LinkedList<String> list = auslesen(pfad);
				for (int x = 0; x < list.size(); x++) {
					for (int y = 0; y < list.size(); y++) {
						String format = "%-7s%-4s%-7s%-3s%s\n";
						System.out.printf(format, list.get(x), "->", list.get(y), ":",
								distance(list.get(x), list.get(y)));
					}
				}
			} else {
				System.out.println("Fehler beim Initialisieren des Programmablaufes");
				System.exit(0);
			}
		} else if (args.length == 2) {
			if(args[1].equals("-o")){
				try {
					pfad = args[0];
					File file = new File(pfad);
					if (!file.canRead() || !file.isFile()) {
						System.out.println("Fehler beim Einlesen der Datei");
						System.exit(0);
					}
				} catch (Exception e) {
					System.out.println("Fehler beim Einlesen des Dateipfades");
					System.exit(0);
				}
				if (pfad != null) {
					LinkedList<String> list = auslesen(pfad);
					for (int x = 0; x < list.size(); x++) {
						for (int y = 0; y < list.size(); y++) {
							String format = "%-7s%-4s%-7s%-3s%s\n";
							System.out.println("Loesung fuer " + list.get(x) + " --> " + list.get(y) + " mit Gesamtkosten " + distance(list.get(x), list.get(y)) + ":");
							System.out.print("----------------------------------------");
							for(int i=2; i< string1.length()+string2.length(); i++){
								System.out.print("-");
							}System.out.println("");
							printEditOperations(classArray,string1,string2);
						}
					}
				} else {
					System.out.println("Fehler beim Initialisieren des Programmablaufes");
					System.exit(0);
				}
			}else{
				try {
					string1 = args[0];
					string2 = args[1];
				} catch (Exception e) {
					System.out.println("Fehler bei der Eingabe");
					System.exit(0);
				}
				System.out.println("Die Distanz betr‰gt: " + distance(string1, string2));
			}
		} else if (args.length == 3){
			String lParameter = null;
			try {
				string1 = args[0];
				string2 = args[1];
				lParameter = args[2];
			} catch (Exception e) {
				System.out.println("Fehler bei der Eingabe");
				System.exit(0);
			}

			if (lParameter.equals("-o")) {
				System.out.println("Loesung fuer " + string1 + " --> " + string2 + " mit Gesamtkosten " + distance(string1, string2) + ":");
				System.out.print("----------------------------------------");
				for(int i=2; i< string1.length()+string2.length(); i++){
					System.out.print("-");
				}System.out.println("");
				printEditOperations(classArray,string1,string2);
			} else {
				System.out.println("Drittes Argument falsch ¸bergeben");
				System.exit(0);
			}
		}else{
			System.out.println("Fehler bei der Eingabe der Argumente");
			System.exit(0);
		}	
	}	

	public static int distance(String eins, String zwei) {
		char[] old = eins.toCharArray();
		char[] wantTo = zwei.toCharArray();
		
		int[][] arr = new int[zwei.length()+1][eins.length()+1];
		
		//Die semple Reiehen werden jeweils mit 1...n initialisiert
		for(int y=0; y<arr.length; y++){
			arr[y][0] = y;
		}
		for(int x=0; x<arr[0].length; x++){
			arr[0][x] = x;
		}
				
		//Die rste Zeile wird als Sonderfall betrachtet
		int count = 0;
		for(int i=1; i<arr[0].length; i++){
			if(old[i-1] == wantTo[0]){
				arr[1][i] = count;
			}else{
				count++;
				arr[1][i] = count;
			}
		}
		for(int y=2; y<arr.length; y++){
			for(int x=1; x<arr[0].length; x++){
				//Hier wird geguckt ob die Buchstaben identisch sind oder ob sie sich unterscheiden und der neue Wert wird gesetzt
				if(old[x-1] == wantTo[y-1]){
					arr[y][x] = arr[y-1][x-1];
				}else{
					arr[y][x] = Math.min(arr[y-1][x-1], Math.min(arr[y-1][x], arr[y][x-1])) +1;
				}
			}
			//assert check(dist[y]) : "";
		}
		
		//Beispielhafte Ausgabe des Array
		for(int y=0; y<arr.length; y++){
			for(int x=0; x<arr[0].length; x++){
				System.out.print(arr[y][x] + " ");
			}
			System.out.println("");
		}
		
		classArray = arr;
		return arr[arr.length-1][arr[0].length-1];
	}

	public static void printEditOperations(int[][] arr, String eins, String zwei) {
		//diagonal -> keine ƒnderung
		//left -> delet old
		//up -> delet new
		
		char[] old = eins.toCharArray();
		char[] wantTo = zwei.toCharArray();
		char[] tmp = old;
		
		int min=0;
		int x = arr[0].length-1;
		int y = arr.length-1;
		
		for(int i=0; i< Math.max(x,y) ;i++){
			min = Math.min(arr[y-1][x-1], Math.min(arr[y-1][x], arr[y][x-1]));
			if(min == arr[y-1][x-1]){
				if(old[i] == wantTo[i]){
					System.out.println(i+1 + ") Kosten " + i+1 + ": Ersetzen" + wantTo[wantTo.length-1] + "durch" + wantTo[wantTo.length-1] + "an Position" + i+1 + "-->" + tmp[i]);
				}
			}else if(min == arr[y-1][x]){
				
			}else if(min == arr[y][x-1]){
				
			}
		}
	}

	public static LinkedList<String> auslesen(String dateiPfad) {
		LinkedList<String> list = new LinkedList<String>();
		BufferedReader input = null;
		try {
			input = new BufferedReader(new FileReader(dateiPfad));
			String zeile = null;
			while ((zeile = input.readLine()) != null) {
				list.add(zeile);
			}
		} catch (IOException e) {
			System.out.println("Fehler beim lesen der Datei");
			System.exit(0);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					System.out.println("Fehler beim schlieﬂen des Readers");
					System.exit(0);
				}
			}
		}
		return list;
	}
}