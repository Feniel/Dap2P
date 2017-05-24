public class Muenzen{
	
	public static void main(String[] args){

		if(args.length < 0 || args.length > 2){
			System.out.println("Falsche Anzahl von Argumenten");
			System.exit(1);
		}

		String curr = "";
		int betrag = 0;

		try{
			curr = args[0];
			betrag = Integer.parseInt(args[1]);
		}catch(Exception E){
			System.out.println("falscher Typ");
			System.exit(1);
		}
		
		if(!curr.equals("Euro") && !curr.equals("Alternative")){
			System.out.println(curr);
			System.out.println("Keine vorhandene Waehrung");
			System.exit(1);
		}

		if(betrag < 0){
			System.out.println("Betrag zu klein");
			System.exit(1);
		}
		
		int[] waehrung = switchCurr(curr);
		int[] ausgabe = algorithmusVerteilung(betrag, waehrung);
		
		//Ausgabe der Daten
		print(ausgabe, waehrung);
	}

	private static int[] switchCurr(String eingabe){
		int[] waehrung;

		if(eingabe.equals("Euro")){
			waehrung = new int[] {200, 100, 50, 20, 10, 5, 2, 1};
		}else{
			waehrung = new int[] {200, 100, 50, 20, 10, 5, 4, 2, 1};
		}
		return waehrung;
	}
	
	//Algorithmus
	public static int[] algorithmusVerteilung(int betrag, int[] waehrung){
		int i = 0;
		int[] ausgabe = new int[waehrung.length];

		for(int k=0; k<ausgabe.length; k++){
			ausgabe[k] = 0;
		}
		
		//Geldbetrag noch groesser Null ?
		while(betrag > 0){
			//anzahl beschreibt wie viele Muenzen oder Scheine gebraucht werden
			int anzahl = 0;

			//solange der Geldbetrag groesser dem Schein- oder Muenzbetrag ist wird die anzahl erhöht
			//und der Betrag um den jeweiligen Wert verringert
			while(betrag >= waehrung[i]){
				anzahl = anzahl + 1;
				betrag = betrag - waehrung[i];
			}
			//Speichern der anzahl in dem Array
			ausgabe[i] = anzahl;
			i++;
		}	
		return ausgabe;
	}

	public static void print(int[] ausgabe, int[] waehrung){
		System.out.print("Gewählte Scheine: ");
		for(int i = 0; i < ausgabe.length; i++){
			if(i + 1 == ausgabe.length){
				System.out.print( ausgabe[i] + "x" + waehrung[i]);
			}else{
				System.out.print( ausgabe[i] + "x" + waehrung[i] + ", ");
			}
		}
	}
}