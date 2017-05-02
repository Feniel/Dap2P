import java.util.Random;

public class Sortierung {
	
	public static void main(String[] args){
		int[] array;
		int parameter1=0; 
		
		//Wenn nur die länge gegeben ist bzw. ein Parameter
		if(args.length == 2){
			//prüfen ob eine Zahl eingegeben wurde 
			try{
				  parameter1 = Integer.parseInt(args[0]);
			}catch(NumberFormatException e){
				  System.out.println("Parameter ist keine Zahl");
			}
			//prüfen ob die Zahl im zulässigen Bereich liegt so wie Unterscheidung merge und insert
			if(parameter1 > 0 && parameter1 < 2147483647 && (args[1].equals("insert") || args[1].equals("merge"))){
				array = new int[parameter1];
				//da kein Parameter übergeben wird rand = ""
				fill("",array);
				long tStart=0;
				long tEnd=0;
				if(args[1].equals("insert")){
					//Zeitmessung starten
					tStart = System.currentTimeMillis();
					insertionSort(array);
					//Zeitmessung beenden
					tEnd = System.currentTimeMillis();
				}else if(args[1].equals("merge")){
					//Zeitmessung starten
					tStart = System.currentTimeMillis();
					mergeSort(array);
					//Zeitmessung beenden
					tEnd = System.currentTimeMillis();
				}
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
		else if(args.length == 3){
			//prüfen ob eine Zahl eingegeben wurde
			try{
				  parameter1 = Integer.parseInt(args[0]);
			}catch(NumberFormatException e){
				  System.out.println("Parameter ist keine Zahl");
			}
			//prüfen ob die Zahl im zulässigen Bereich liegt und ob die Eingegebenen Abkürzungen stimmen so wie Unterscheidung merge und insert
			if(parameter1 > 0 && parameter1 < 2147483647 && (args[1].equals("insert") || args[1].equals("merge")) && (args[2].equals("auf") || args[2].equals("ab") || args[2].equals("rand"))){
				array = new int[parameter1]; 
				fill(args[2],array);
				long tStart=0;
				long tEnd=0;
				if(args[1].equals("insert")){
					//Zeitmessung starten
					tStart = System.currentTimeMillis();
					insertionSort(array);
					//Zeitmessung beenden
					tEnd = System.currentTimeMillis();
				}else if(args[1].equals("merge")){
					//Zeitmessung starten
					tStart = System.currentTimeMillis();
					mergeSort(array);
					//Zeitmessung beenden
					tEnd = System.currentTimeMillis();
				}
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
	
	public static int[] mergeSort(int[] array){
		int[] tmpArray = new int[array.length];
		mergeSort(array,tmpArray,0,array.length-1);
		assert isSorted(array);
		return tmpArray;
	}
	
	static public void mergeTogether(int[] array, int[]tmpArray, int leftStart, int rightEnd){
		int leftEnd, rightStart;
		leftEnd = (rightEnd + leftStart) / 2;
		rightStart = leftEnd + 1;
		int leftIndex = leftStart;
		int rightIndex = rightStart;
		int tmpIndex = leftStart;
		int size = rightEnd - leftStart +1;
		
		while(leftIndex <= leftEnd && rightIndex <= rightEnd){
			if(array[leftIndex] <= array[rightIndex]){
				tmpArray[tmpIndex] = array[leftIndex];
				leftIndex++;
			}else{
				tmpArray[tmpIndex] = array[rightIndex];
				rightIndex++;
			}
			tmpIndex++;
		}
		//kopieren des linken Teils in den tmp array
		while(leftIndex <= leftEnd){
			tmpArray[tmpIndex] = array[leftIndex];
			tmpIndex++;
			leftIndex++;
		}
		//kopieren des rechten Teils in den Array
		while(rightIndex <= rightEnd){
			tmpArray[tmpIndex] = array[rightIndex];
			tmpIndex++;
			rightIndex++;
		}
		//zusammenfügen der Arrays
		for(int i=0; i<size; i++){
			array[leftStart] = tmpArray[leftStart];
			leftStart++;
		}
	}
	
	static public void mergeSort(int [] array, int[] tmpArray, int left, int right){
		int middle;
		if (right > left){
			middle = (right + left) / 2;
			//rekursive aufteilung des Mergesorts
			mergeSort(array, tmpArray, left, middle);
			mergeSort(array, tmpArray,(middle + 1), right);
			mergeTogether(array, tmpArray, left, right);
		}
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
