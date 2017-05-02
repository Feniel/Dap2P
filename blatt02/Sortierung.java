import java.util.Random;

public class Sortierung {
	
	public static void main(String[] args){
		int[] array;
		int parameter1=0; 
		
		//Wenn nur die länge gegeben ist bzw. ein Parameter
		if(args.length == 1){
			//prüfen ob eine Zahl eingegeben wurde 
			try{
				  parameter1 = Integer.parseInt(args[0]);
			}catch(NumberFormatException e){
				  System.out.println("Parameter ist keine Zahl");
			}
			//prüfen ob die Zahl im zulässigen Bereich liegt
			if(parameter1 > 0 && parameter1 < 2147483647){
				array = new int[parameter1];
				//da kein Parameter übergeben wird rand = ""
				fill("",array);
				//Zeitmessung starten
				long tStart = System.currentTimeMillis();
				insertionSort(array);
				//Zeitmessung beenden
				long tEnd = System.currentTimeMillis();
				//Zeitmessung wird verrechnet
				long msecs = tEnd - tStart;
				boolean check = isSorted(array);
				//Hier wird mit den Assertions geprüft ob der Algorithmus nach Plan abgelaufen ist
				assert check : "Fehler bei der Implementierung des Algorithmus";
				if(check){
					System.out.println("Feld sortiert!");
					System.out.println("benötigte Zeit = " + msecs + "ms");
					// bis 100 soll eine grafische Ausgabe erfolgen also <101
					if(array.length < 101){
						for(int i=0; i<(array.length); i++){
							System.out.print(array[i] + ".");
						}
					}else{
						System.out.println("Eingabe zu lang für Grafische Ausgabe");
					}
				}else{
					System.out.println("Feld NICHT sortiert");
					System.out.println("benötigte Zeit = " + msecs + "ms");
					if(array.length < 101){
						for(int i=0; i<(array.length); i++){
							System.out.print(array[i] + ".");
						}
					}else{
						System.out.println("Eingabe zu lang für Grafische Ausgabe");
					}
				}
			}
			else{
				System.out.println("Parameter sind falsch gewählt");
			}
		}
		else if(args.length == 2){
			//prüfen ob eine Zahl eingegeben wurde
			try{
				  parameter1 = Integer.parseInt(args[0]);
			}catch(NumberFormatException e){
				  System.out.println("Parameter ist keine Zahl");
			}
			//prüfen ob die Zahl im zulässigen Bereich liegt und ob die Eingegebenen Abkürzungen stimmen
			if(parameter1 > 0 && parameter1 < 2147483647 && (args[1].equals("auf") || args[1].equals("ab") || args[1].equals("rand"))){
				array = new int[parameter1]; 
				fill(args[1],array);
				//Zeitmessung starten
				long tStart = System.currentTimeMillis();
				insertionSort(array);
				//Zeitmessung beenden
				long tEnd = System.currentTimeMillis();
				//Zeitmessung wird verrechnet
				long msecs = tEnd - tStart;
				boolean check = isSorted(array);
				//Hier wird mit den Assertions geprüft ob der Algorithmus nach Plan abgelaufen ist
				assert check : "Fehler bei der Implementierung des Algorithmus";
				if(check){
					System.out.println("Feld sortiert!");
					System.out.println("benötigte Zeit = " + msecs + "ms");
					// bis 100 soll eine grafische Ausgabe erfolgen also <101
					if(array.length < 101){
						for(int i=0; i<(array.length); i++){
							System.out.print(array[i] + ".");
						}
					}else{
						System.out.println("Eingabe zu lang für Grafische Ausgabe");
					}
				}else{
					System.out.println("Feld NICHT sortiert");
					System.out.println("benötigte Zeit = " + msecs + "ms");
					if(array.length < 101){
						for(int i=0; i<(array.length); i++){
							System.out.print(array[i] + ".");
						}
					}else{
						System.out.println("Eingabe zu lang für Grafische Ausgabe");
					}
				}
			}
			else{
				System.out.println("Parameter sind falsch gewählt");
			}
		}else{
			System.out.println("Falsche Eingabe der Parameter");
		}
		
	}
	
	public static int[] insertionSort(int[] array){
		//var für den Dreieckstausch
		int temp;
		for (int i=1; i<array.length; i++){
			temp = array[i];
			int index = i;
			//solange der Anfang des Index nicht erreicht ist und die zahl vor temp größer ist wird getauscht
			while (index > 0 && array[index - 1] > temp){
				array[index] = array[index - 1];
				index--;
			}
			array[index] = temp;
		}
		return array;
	}
	
	public static boolean isSorted(int[] array){
		//der array wird einmal komplett durchgezählt und immmer mit der vorherigen Zahl verglichen
		for(int i=0; i<(array.length-1); i++){
			if(array[i] > array[i+1]){
				return false;
			}
		}
		return true;
	}
	
	public static int[] fill(String eingabe2,int[] array){
		if(eingabe2.equals("auf")){
			for(int i=1; i<=array.length; i++){
				array[i-1] = i;
			}
		}
		else if(eingabe2.equals("ab")){
			int temp=array.length;
			for(int i=0; i<array.length; i++){
				array[i] = temp;
				temp--;
			}
		}
		else{
			//zufallsgenerator aus dem zusatz wird genutzt
			java.util.Random numberGenerator = new java.util.Random();
			for(int i=0; i<array.length; i++){
				array[i] = numberGenerator.nextInt();
			}
		}
		
		return array;
	}
}