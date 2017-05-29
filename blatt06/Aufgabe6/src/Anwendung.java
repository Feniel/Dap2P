
//import fuer das Einlesen und Auslesen der Datei
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

//import fuer LinkedList
import java.util.*;

// Die Klasse implementiert den Algorithmus und organisiert das Einlesen und die
// Ausgabe
public class Anwendung {
	static String dateiName;
	static int ZeilenCount;

	public Anwendung() {

	}

	public static void main(String[] args) {

		if (args.length > 2 || args.length < 1) {
			System.out.println("Argumente falsch übergeben");
			System.exit(1);
		}

		// einlesen des Dateipfades und pruefen auf Korrektheit
		String dateiPfad = "";
		try {
			dateiPfad = args[1];
			File file = new File(dateiPfad);
			if (!file.canRead() || !file.isFile()) {
				System.out.println("Fehler beim einlesen der Datei");
				System.exit(1);
			}
		} catch (Exception e) {
			System.out.println("Dateipfad existiert nicht");
			System.exit(1);
		}

		String parameter1 = "";

		try {
			parameter1 = args[0];
		} catch (Exception e) {
			System.out.println("EIngabe 1 entspricht nicht 'Interval' oder 'Lateness'");
			System.exit(1);
		}

		if (parameter1.equals("Interval")) {
			// Speicher des Dateinamens
			int index = dateiPfad.lastIndexOf('/');
			dateiName = dateiPfad.substring(index + 1, dateiPfad.length());
			// Datei auslesen
			Intervall[] auslesenArr = intervallAuslesen(dateiPfad);
			// Array sortieren
			Intervall[] SortArr = sortEnd(auslesenArr);
			// Schedulingalgorithmus anwenden
			Intervall[] ScheduleArr = intervallScheduling(SortArr);
			// Berechnung ausgeben
			ausgabe(auslesenArr, SortArr, ScheduleArr);
		} else if (parameter1.equals("Lateness")) {
			// Speicher des Dateinamens
			int index = dateiPfad.lastIndexOf('/');
			dateiName = dateiPfad.substring(index + 1, dateiPfad.length());
			// Datei auslesen
			Job[] auslesenArr = jobAuslesen(dateiPfad);
			// Array sortieren
			Job[] SortArr = sort(auslesenArr);
			// Schedulingalgorithmus anwenden
			Job[] ScheduleArr = latenessScheduling(SortArr);
			// Berechnung ausgeben
			ausgabe(auslesenArr, SortArr, ScheduleArr);
		}

	}

	// Methode zur Ausgabe eines Array
	public static void ArrAusgabe(Intervall[] arr) {
		System.out.print("[");
		for (int i = 0; i < arr.length; i++) {
			if (i != arr.length - 1) {
				System.out.print(arr[i].toString());
				System.out.print(", ");
			} else {
				System.out.print(arr[i].toString());
			}
		}
		System.out.println("]");

	}

	// Methode zur Ausgabe, die in der Aufgabenstellung vorgegeben war
	public static void ausgabe(Intervall[] auslesenArr, Intervall[] SortArr, Intervall[] ScheduleArr) {
		System.out.println("Bearbeite Datei \"" + dateiName + "\".");
		System.out.println("");
		System.out.println("Es wurden " + ZeilenCount + " mit folgendem Inhalt gelesen:");
		ArrAusgabe(auslesenArr);
		System.out.println("");
		System.out.println("Sortiert:");
		ArrAusgabe(SortArr);
		System.out.println("");
		System.out.println("Berechnetes Intervallscheduling:");
		ArrAusgabe(ScheduleArr);
	}

	// Algorithmus zum Intervallscheduling, der in der Vorlesung vorgegeben
	// wurde
	public static Intervall[] intervallScheduling(Intervall[] intervalls) {
		// erstellen einer Liste, zur Speicherung der gewaehlten Prozesse
		// (einfacher als Array)
		LinkedList<Intervall> ausgabeList = new LinkedList<Intervall>();
		// Abbruch fuer die Vorschleife
		int n = intervalls.length;
		// erstes Element wird in die Liste eingefuegt, da das Array schon
		// sortiert ist
		ausgabeList.add(intervalls[0]);
		// Variable j wird initialisiert
		int j = 0;

		// Die for-Schleife durchlauft das Array
		for (int i = 1; i < n; i++) {
			// Falls der Anfangswert des Vorgaengers groesser gleich dem Endwert
			// des Nachfolgers ist,
			// wird der Prozess mit dem Anfangswert in die Liste aufgenommen
			if (intervalls[i].getStart() >= intervalls[j].getEnd()) {
				ausgabeList.add(intervalls[i]);
				// j wird auf den Vorgaenger gesetzt
				j = i;
			}
		}

		// Liste wird in ein Arrayuebertragen, dass dann zurueckgegeben wird
		int k = 0;
		Intervall[] ausgabe = new Intervall[ausgabeList.size()];

		while (k < ausgabeList.size()) {
			ausgabe[k] = ausgabeList.get(k);
			k++;
		}

		return ausgabe;
	}
	
	public static int[] latenessScheduling(ArrayList<Job> jobs){
		//n um die Schleifenvariable zu erschaffen und um die länge für das erstellen des ausgabe arrays parat zuu haben
		int n = jobs.size();
		int[] ausgabe = new int[n];
		int z = 0;
		//den gesamten array einmal durchlaufen
		for(int i=0;i<n-1;i++){
			ausgabe[i] = z;
			z = z + jobs.get(i).getDauer();
		}
		return ausgabe;
	}

	// Sortieren des Arrays mit Bubblesort
	public static Intervall[] sortEnd(Intervall[] intervalls) {
		int k = intervalls.length;
		while (k > 0) {
			for (int i = 1; i < intervalls.length; i++) {
				if (intervalls[i - 1].getEnd() > intervalls[i].getEnd()) {
					Intervall tmp = intervalls[i - 1];
					intervalls[i - 1] = intervalls[i];
					intervalls[i] = tmp;
				}
			}
			k = k - 1;
		}

		return intervalls;
	}
	
	public static Job[] sort(Job[] jobs) {
		int k = jobs.length;
		while (k > 0) {
			for (int i = 1; i < jobs.length; i++) {
				if (jobs[i - 1].getDeadline() > jobs[i].getDeadline()) {
					Job tmp = jobs[i - 1];
					jobs[i - 1] = jobs[i];
					jobs[i] = tmp;
				}
			}
			k = k - 1;
		}

		return jobs;
	}

	public static Intervall[] intervallAuslesen(String dateipfad) {
		// Zeilensaehler (wichtig fuer die Ausgabe)
		int count = 0;

		// erstellen einer Liste fuer die ausgelesenen Zeilen
		LinkedList<Intervall> liste = new LinkedList<Intervall>();

		// Ausleser initialiseren
		BufferedReader in = null;
		try {
			// Versuch den Ausleser zu erstellen
			in = new BufferedReader(new FileReader(dateipfad));
			// Variable fuer die naechste Zeile initialisieren
			String zeile = null;
			// Auslesen der Zeilen nacheinander, solange bis einen null ist
			while ((zeile = in.readLine()) != null) {
				// Zeilenzaehler wird um eins erhoeht
				count++;
				// Initialisieren von 2 Strings
				String Zeins = "";
				String Zzwei = "";

				int x = zeile.indexOf(",");

				// Initialisieren von 2 String, fuer den Anfangs- und Endwert
				// des Prozess
				Zeins = zeile.substring(0, x);
				Zzwei = zeile.substring(x + 1, zeile.length());

				int Start = 0;
				int End = 0;
				// Versuch die Strings in Ints umzuwandeln
				try {
					Start = Integer.parseInt(Zeins);
					End = Integer.parseInt(Zzwei);
				} catch (Exception e) {
					System.out.println("Hier sollte eine Zahl stehen.");
					return null;
				}

				// neues Intervall mit den 2 Ints erstellen
				Intervall lesen = new Intervall(Start, End);
				// Intervall der Liste hinzufuegen
				liste.add(lesen);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// insofern der Ausleser noch nicht geschlossen wurde, wird er hier
			// geschlossen
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {

				}
			}
		}

		// Umschreiben der Liste in ein Array
		Intervall[] ausgabe = new Intervall[liste.size()];

		int i = 0;
		while (i < liste.size()) {
			ausgabe[i] = liste.get(i);
			i++;
		}

		// globale Variable auf Zeilenzaehler setzen
		ZeilenCount = count;
		// Rueckgabe des Arrays
		return ausgabe;

	}

	public static Job[] jobAuslesen(String dateipfad) {
		// Zeilensaehler (wichtig fuer die Ausgabe)
		int count = 0;

		// erstellen einer Liste fuer die ausgelesenen Zeilen
		LinkedList<Job> liste = new LinkedList<Job>();

		// Ausleser initialiseren
		BufferedReader in = null;
		try {
			// Versuch den Ausleser zu erstellen
			in = new BufferedReader(new FileReader(dateipfad));
			// Variable fuer die naechste Zeile initialisieren
			String zeile = null;
			// Auslesen der Zeilen nacheinander, solange bis einen null ist
			while ((zeile = in.readLine()) != null) {
				// Zeilenzaehler wird um eins erhoeht
				count++;
				// Initialisieren von 2 Strings
				String Zeins = "";
				String Zzwei = "";

				int x = zeile.indexOf(",");

				// Initialisieren von 2 String, fuer den Anfangs- und Endwert
				// des Prozess
				Zeins = zeile.substring(0, x);
				Zzwei = zeile.substring(x + 1, zeile.length());

				int Dauer = 0;
				int Deadline = 0;
				// Versuch die Strings in Ints umzuwandeln
				try {
					Dauer = Integer.parseInt(Zeins);
					Deadline = Integer.parseInt(Zzwei);
				} catch (Exception e) {
					System.out.println("Hier sollte eine Zahl stehen.");
					return null;
				}

				// neues Intervall mit den 2 Ints erstellen
				Job lesen = new Job(Dauer, Deadline);
				// Intervall der Liste hinzufuegen
				liste.add(lesen);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// insofern der Ausleser noch nicht geschlossen wurde, wird er hier
			// geschlossen
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {

				}
			}
		}

		// Umschreiben der Liste in ein Array
		Job[] ausgabe = new Job[liste.size()];

		int i = 0;
		while (i < liste.size()) {
			ausgabe[i] = liste.get(i);
			i++;
		}

		// globale Variable auf Zeilenzaehler setzen
		ZeilenCount = count;
		// Rueckgabe des Arrays
		return ausgabe;

	}
}