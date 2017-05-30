///import fuer das Einlesen und Auslesen der Datei
import java.io.File;
import java.io.RandomAccessFile;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

//Die Klasse implementiert den Algorithmus und organisiert das Einlesen und die Ausgabe
public class Anwendung {
	static String dateiName;
	static int ZeilenCount;
	static int deadlineCount;

	public Anwendung() {

	}

	public static void main(String[] args) {
		String dateiPfad = "";
		RandomAccessFile file = null;

		if (args.length > 2 || args.length < 1) {
			System.out.println("Argumente falsch übergeben");
			System.exit(1);
		}

		// einlesen des Dateipfades und pruefen auf Korrektheit
		try {
			dateiPfad = args[1];
			file = new RandomAccessFile(dateiPfad, "r");
		} catch (Exception e) {
			System.out.println("Fehler beim einlesen der Datei");
			System.exit(1);
		}

		String parameter1 = "";

		try {
			parameter1 = args[0];
		} catch (Exception e) {
			System.out.println("Eingabe 1 entspricht nicht 'Interval' oder 'Lateness'");
			System.exit(1);
		}

		if (parameter1.equals("Interval")) {
			// Speicher des Dateinamens
			int index = dateiPfad.lastIndexOf('/');
			dateiName = dateiPfad.substring(index + 1, dateiPfad.length());
			// Datei auslesen
			ArrayList<Intervall> auslesenArr = intervallAuslesen(file);
			// kopiert die ArrayList auslesenArr (wichtig fuer die Ausgabe)
			ArrayList<Intervall> Arr = intervallCopy(auslesenArr);
			// Array sortieren
			ArrayList<Intervall> SortArr = intervallSortStart(Arr);
			// Schedulingalgorithmus anwenden
			ArrayList<Intervall> ScheduleArr = intervallScheduling(SortArr);
			// Berechnung ausgeben
			intervallAusgabe(auslesenArr, SortArr, ScheduleArr);
		} else if (parameter1.equals("Lateness")) {
			// Speicher des Dateinamens
			int index = dateiPfad.lastIndexOf('/');
			dateiName = dateiPfad.substring(index + 1, dateiPfad.length());
			// Datei auslesen
			ArrayList<Job> auslesenArr = jobAuslesen(file);
			// kopiert die ArrayList auslesenArr (wichtig fuer die Ausgabe)
			ArrayList<Job> Arr = jobCopy(auslesenArr);
			// Array sortieren
			ArrayList<Job> SortArr = jobSortStart(Arr);
			// Schedulingalgorithmus anwenden
			int[] ScheduleArr = latenessScheduling(SortArr);
			// Berechnung ausgeben
			jobAusgabe(auslesenArr, SortArr, ScheduleArr);
		}
	}

	// Kopiert die ArrayList (wichtig fuer die Ausgabe)
	public static ArrayList<Intervall> intervallCopy(ArrayList<Intervall> arr) {
		ArrayList<Intervall> ausgabe = new ArrayList<Intervall>();
		for (int i = 0; i < arr.size(); i++) {
			ausgabe.add(arr.get(i));
		}

		return ausgabe;
	}
	
	public static ArrayList<Job> jobCopy(ArrayList<Job> arr) {
		ArrayList<Job> ausgabe = new ArrayList<Job>();
		for (int i = 0; i < arr.size(); i++) {
			ausgabe.add(arr.get(i));
		}

		return ausgabe;
	}

	// Methode zur Ausgabe eines Array
	public static void intervallArrAusgabe(ArrayList<Intervall> arr) {
		System.out.print("[");
		for (int i = 0; i < arr.size(); i++) {
			if (i != arr.size() - 1) {
				System.out.print(arr.get(i).toString());
				System.out.print(", ");
			} else {
				System.out.print(arr.get(i).toString());
			}
		}
		System.out.println("]");

	}
	
	// Methode zur Ausgabe eines Array
	public static void jobIntArrAusgabe(int[] arr) {
		System.out.print("[");
		for (int i = 0; i < arr.length-1; i++) {
			System.out.print(arr[i]);
			System.out.print(", ");
		}
		System.out.print(arr[arr.length-1]);
		System.out.println("]");
	}

	// Methode zur Ausgabe eines Array
	public static void jobArrAusgabe(ArrayList<Job> arr) {
		System.out.print("[");
		for (int i = 0; i < arr.size(); i++) {
			if (i != arr.size() - 1) {
				System.out.print(arr.get(i).toString());
				System.out.print(", ");
			} else {
				System.out.print(arr.get(i).toString());
			}
		}
		System.out.println("]");
	}	
		
	// Methode zur Ausgabe, die in der Aufgabenstellung vorgegeben war
	public static void intervallAusgabe(ArrayList<Intervall> auslesenArr, ArrayList<Intervall> SortArr,ArrayList<Intervall> ScheduleArr) {
		System.out.println("");
		System.out.println("Bearbeite Datei \"" + dateiName + "\".");
		System.out.println("");
		System.out.println("Es wurden " + ZeilenCount + " Zeilen mit folgendem Inhalt gelesen:");
		intervallArrAusgabe(auslesenArr);
		System.out.println("");
		System.out.println("Sortiert:");
		intervallArrAusgabe(SortArr);
		System.out.println("");
		System.out.println("Berechnetes Intervallscheduling:");
		intervallArrAusgabe(ScheduleArr);
	}
	
	// Methode zur Ausgabe, die in der Aufgabenstellung vorgegeben war
	public static void jobAusgabe(ArrayList<Job> auslesenArr, ArrayList<Job> SortArr,int[] ScheduleArr) {
		System.out.println("");
		System.out.println("Bearbeite Datei \"" + dateiName + "\".");
		System.out.println("");
		System.out.println("Es wurden " + ZeilenCount + " Zeilen mit folgendem Inhalt gelesen:");
		jobArrAusgabe(auslesenArr);
		System.out.println("");
		System.out.println("Sortiert:");
		jobArrAusgabe(SortArr);
		System.out.println("");
		System.out.println("Berechnetes Latenessscheduling:");
		jobIntArrAusgabe(ScheduleArr);
		System.out.println("");
		System.out.print("Berechnete maximale Verspätung:");
		System.out.println(deadlineCount);
	}

	// Algorithmus zum Intervallscheduling, der in der Vorlesung vorgegeben
	// wurde
	public static ArrayList<Intervall> intervallScheduling(ArrayList<Intervall> intervalls) {
		// erstellen einer Liste, zur Speicherung der gewaehlten Prozesse
		// (einfacher als Array)
		ArrayList<Intervall> ausgabeList = new ArrayList<Intervall>();
		// Abbruch fuer die Vorschleife
		int n = intervalls.size();
		// erstes Element wird in die Liste eingefuegt, da das Array schon
		// sortiert ist
		ausgabeList.add(intervalls.get(0));
		// Variable j wird initialisiert
		int j = 0;

		// Die for-Schleife durchlauft das Array
		for (int i = 1; i < n; i++) {
			// Falls der Anfangswert des Vorgaengers groesser gleich dem Endwert
			// des Nachfolgers ist,
			// wird der Prozess mit dem Anfangswert in die Liste aufgenommen
			if (intervalls.get(i).getStart() >= intervalls.get(j).getEnd()) {
				ausgabeList.add(intervalls.get(i));
				// j wird auf den Vorgaenger gesetzt
				j = i;
			}
			// assert ausgabeList.get(i).getS
		}

		return ausgabeList;
	}
	
	public static int[] latenessScheduling(ArrayList<Job> jobs){
		//n um die Schleifenvariable zu erschaffen und um die l�nge f�r das erstellen des ausgabe arrays parat zuu haben
		int n = jobs.size();
		int[] ausgabe = new int[n];
		deadlineCount = 0;
		int z = 0;
		//den gesamten array einmal durchlaufen
		for(int i=0;i<n;i++){
			ausgabe[i] = z;
			z = z + jobs.get(i).getDauer();
			//Die Deadline wird hoch gezählt
			if(z - jobs.get(i).getDeadline() > deadlineCount){
				deadlineCount = z - jobs.get(i).getDeadline();
			}
		}
		return ausgabe;
	}

	// Sortiert die eingelesen Liste
	public static ArrayList<Intervall> intervallSortStart(ArrayList<Intervall> array) {
		Collections.sort(array);
		return array;
	}
	
	// Sortiert die eingelesen Liste
		public static ArrayList<Job> jobSortStart(ArrayList<Job> array) {
		Collections.sort(array);
		return array;
	}

	public static ArrayList<Intervall> intervallAuslesen(RandomAccessFile file) {
		// String zeile initialisieren
		String zeile = "";
		// ausgabe ArrayList erstellen
		ArrayList<Intervall> speicher = new ArrayList<Intervall>();
		try {
			// while Schleife - Reader wirft Exception try bricht die while
			// Schleife ab
			while (true) {
				// globale Variable zaehlt die Zeilen
				ZeilenCount++;
				// liest die naechste Zeile
				zeile = file.readLine();
				// erstellt neues StringTokenizer Objekt, die Tokens werden mit
				// Komma getrennt
				StringTokenizer st = new StringTokenizer(zeile, ",");
				// erstes Token der Zeile (vor dem Komma)
				int start = Integer.parseInt(st.nextToken());
				// zweites Token der Zeile (nach dem Komma)
				int ende = Integer.parseInt(st.nextToken());
				// erstellt neues Intervallobjekt
				Intervall ivall = new Intervall(start, ende);
				// fuegt das Intervall der Ausgabe ArrayList hinzu
				speicher.add(ivall);
			}
			// faengt die Exception und macht nichts damit (gewollte Exception
			// -> End of file)
		} catch (Exception e) {

		} finally {
			// scliesst den Reader
			try {
				file.close();
			} catch (Exception e) {
				System.out.println("Reader konnte nicht geschlossen werden");
				System.exit(0);
			}
		}
		ZeilenCount--;
		return speicher;
	}
	
	public static ArrayList<Job> jobAuslesen(RandomAccessFile file) {
		// String zeile initialisieren
		String zeile = "";
		// ausgabe ArrayList erstellen
		ArrayList<Job> speicher = new ArrayList<Job>();
		try {
			// while Schleife - Reader wirft Exception try bricht die while
			// Schleife ab
			while (true) {
				// globale Variable zaehlt die Zeilen
				ZeilenCount++;
				// liest die naechste Zeile
				zeile = file.readLine();
				// erstellt neues StringTokenizer Objekt, die Tokens werden mit
				// Komma getrennt
				StringTokenizer st = new StringTokenizer(zeile, ",");
				// erstes Token der Zeile (vor dem Komma)
				int dauer = Integer.parseInt(st.nextToken());
				// zweites Token der Zeile (nach dem Komma)
				int deadline = Integer.parseInt(st.nextToken());
				// erstellt neues Intervallobjekt
				Job job = new Job(dauer, deadline);
				// fuegt das Intervall der Ausgabe ArrayList hinzu
				speicher.add(job);
			}
			// faengt die Exception und macht nichts damit (gewollte Exception
			// -> End of file)
		} catch (Exception e) {
			
		} finally {
			// scliesst den Reader
			try {
				file.close();
			} catch (Exception e) {
				System.out.println("Reader konnte nicht geschlossen werden");
				System.exit(0);
			}
		}
		ZeilenCount--;
		return speicher;
	}
}
