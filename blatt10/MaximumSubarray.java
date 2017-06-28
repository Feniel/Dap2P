import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MaximumSubarray {
	public static void main(String[] args) {
		if ((args.length == 2 || args.length == 4) && args[0].equals("naiv")) {
			if (args.length == 2) {
				int n = 0;
				try {
					n = Integer.parseInt(args[1]);
				} catch (Exception e) {
					System.out.println("Fehler bei dem Verarbeiten der Eingaben");
					System.exit(0);
				}
				int[] arr = new int[n];
				fillRand(arr);
				arrAusgabe(arr);
				long tStart = System.currentTimeMillis();
				System.out.println(naiv(arr));
				long tEnd = System.currentTimeMillis();
				long msecs = tEnd - tStart;
				System.out.println(msecs);

			} else if (args.length == 4) {
				int n = 0, anf = 0, end = 0;
				try {
					n = Integer.parseInt(args[1]);
					anf = Integer.parseInt(args[2]);
					end = Integer.parseInt(args[3]);
				} catch (Exception e) {
					System.out.println("Fehler bei dem Verarbeiten der Eingaben");
					System.exit(0);
				}
				if (anf < end) {
					int[] arr = new int[n];
					fillRand(arr, anf, end);
					arrAusgabe(arr);
					long tStart = System.currentTimeMillis();
					System.out.println(naiv(arr));
					long tEnd = System.currentTimeMillis();
					long msecs = tEnd - tStart;
					System.out.println(msecs);

				} else {
					System.out.println("Fehler beim wählen des Wertebereichs");
					System.exit(0);
				}
			} else {
				System.out.println("Fehler bei der Eingabe der Parameter");
				System.exit(0);
			}
		} else if ((args.length == 2 || args.length == 4) && args[0].equals("vorgegeben")) {
			if (args.length == 2) {
				int n = 0;
				try {
					n = Integer.parseInt(args[1]);
				} catch (Exception e) {
					System.out.println("Fehler bei dem Verarbeiten der Eingaben");
					System.exit(0);
				}
				int[] arr = new int[n];
				fillRand(arr);
				arrAusgabe(arr);
				long tStart = System.currentTimeMillis();
				System.out.println(vorgegeben(arr));
				long tEnd = System.currentTimeMillis();
				long msecs = tEnd - tStart;
				System.out.println(msecs);
				
			} else if (args.length == 4) {
				int n = 0, anf = 0, end = 0;
				try {
					n = Integer.parseInt(args[1]);
					anf = Integer.parseInt(args[2]);
					end = Integer.parseInt(args[3]);
				} catch (Exception e) {
					System.out.println("Fehler bei dem Verarbeiten der Eingaben");
					System.exit(0);
				}
				if (anf < end) {
					int[] arr = new int[n];
					fillRand(arr, anf, end);
					arrAusgabe(arr);
					long tStart = System.currentTimeMillis();
					System.out.println(vorgegeben(arr));
					long tEnd = System.currentTimeMillis();
					long msecs = tEnd - tStart;
					System.out.println(msecs);
				} else {
					System.out.println("Fehler beim wählen des Wertebereichs");
					System.exit(0);
				}
			} else {
				System.out.println("Fehler bei der Eingabe der Parameter");
				System.exit(0);
			}
		} else {
			System.out.println("Erster Parameter falsch gewählt");
			System.exit(0);
		}
	}

	public static void fillRand(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ThreadLocalRandom.current().nextInt(-10, 10);
		}
	}

	public static void fillRand(int[] arr, int anf, int end) {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ThreadLocalRandom.current().nextInt(anf, end);
		}
	}

	public static void arrAusgabe(int[] arr) {
		System.out.println("");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "|");
		}
		System.out.println("");
	}

	public static int naiv(int[] values) {
		int maxSum = 0;
		int sum = 0;
		int q = 0;
		if (onlyPos(values)) { // Wenn das Array nur positive Einträge hat, dann
								// summiere alle
			System.out.println(sum(values));
		} else {
			for (int i = 0; i <= values.length; i++) { // ermittle Startposition
				if (sum > maxSum) {
					maxSum = sum;
					sum = 0;
				}
				for (int j = i; j < values.length; j++) { // zähler bis zum Ende
															// von i an
					if (values[j] >= 0) {
						sum = sum + values[j]; // summe bilde wenn Wert positiv
					} else {
						i = j;
						j = values.length;

					}
					if (j == values.length - 1) { // bei vorzeitiger Abbruch bei
													// kompletten durchlaufs

						i = values.length - 1;
					}
				}
			}
		}
		return maxSum;
	}

	public static int sum(int[] values) {// bilde die Summe über das gesamte
											// Array
		int sum = 0;
		for (int i = 0; i < values.length; i++) {
			sum = sum + values[i];
		}
		return sum;
	}

	public static boolean onlyPos(int[] values) { // prüfe ob das Aray nur
													// Positive Einträge
													// enthälht
		for (int i = 0; i < values.length; i++) {
			if (values[i] < 0) {
				return false;
			}
		}
		return true;
	}

	public static boolean onlyNeg(int[] values) {
		for (int i = 0; i < values.length; i++) {
			if (values[i] >= 0) {
				return false;
			}
		}
		return true;
	}

	public static int maxNeg(int[] values) {
		int max = values[0];
		for (int i = 0; i < values.length; i++) {
			if (values[i] > max) {
				max = values[i];
			}
		}
		return max;

	}
	
	public static int vorgegeben(int[] arr) {
		int sum = 0;
		int maxSum = 0;

		// der array wird einmal durchlaufen
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < 0) {// Wenn ein minus genommen wird unterbricht das die
								// Folge
				if (maxSum < sum) {
					maxSum = sum;
					sum = 0;
				}
			} else {
				sum = sum + arr[i]; // sonst wird die Folge addiert
			}
		}
		if (sum > maxSum) {
			maxSum = sum;
		}
		return maxSum;
	}
}
