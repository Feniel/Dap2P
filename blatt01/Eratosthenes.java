import java.util.Arrays;

public class Eratosthenes{

        static boolean[] isPrime;

        public static void main(String[] args){
                int n = Integer.parseInt(args[0]);
                if(n < 2){
                        n = 2;
			System.out.println("Werte unter 2 sind nicht zulässig daher wurde n auf 2 gesetzt");
                }
                int arrayL = n+1;
                //Die länge wird in einen int umgewandelt damit wir einen boolean array mit der länge erstellen können
                isPrime = new boolean[arrayL];
                //Da arrays mit false initialisiert werden füllen wir den array hier mit true
                Arrays.fill(isPrime, Boolean.TRUE);

                filtern(arrayL);

		int ausgabeZaehler = 0;

                for(int i=2; i < isPrime.length;i++){
                        if(isPrime[i]){
                                ausgabeZaehler ++;
                        }
                }
		System.out.println("Es sind " + ausgabeZaehler + " Primzahlen in 0 bis n");
		if(args.length == 2 && args[1].equals("-o")){
			for(int i=2; i < isPrime.length;i++){
                 	     	if(isPrime[i]){
                        	System.out.println(i);
                        	}
                	}
		}
        }

        public static void filtern(int n){
                // 2 da man ja  0 und 1 ausschließen kann
                int zaehler1 = 2;
                int notPrim = 0;
                //wir wollen ja jede zahl bis n durchgehen
                while(zaehler1 < isPrime.length){
                        //Wenn die Zahl im array als true markiert ist gehen wir davon aus es ist eine Primzahl
			if(isPrime[zaehler1]){
                                // Die Markierungen beginnen jeweils mit dem Quadrat der Primzahl
                                notPrim = zaehler1 * zaehler1;
				//kleiner als n da wir ja den Zähler um einen erhöht haben
                                while(notPrim < n){
                                        isPrime[notPrim] = false;
                                        // die vielfachen mit notPrim hochzählen und auf false setzen
                                        notPrim = notPrim + zaehler1;
                                }
                        }//Wenn nicht machen wir weiter
                        zaehler1++;
                }
        }

}
