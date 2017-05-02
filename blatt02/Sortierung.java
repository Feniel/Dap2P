import java.util.Random;

public class Sortierung {
	
	public static void main(String[] args){
		int[] array;
		int parameter1=0; 
		
		if(args.length == 1){
			try{
				  parameter1 = Integer.parseInt(args[0]);
			}catch(NumberFormatException e){
				  System.out.println("Parameter ist keine Zahl");
			}
			if(parameter1 > 0 && parameter1 < 2147483647){
				array = new int[parameter1];
				fill("",array);
				long tStart = System.currentTimeMillis();
				insertionSort(array);
				long tEnd = System.currentTimeMillis();
				long msecs = tEnd - tStart;
				boolean check = isSorted(array);
				if(check){
					System.out.println("Feld sortiert!");
					System.out.println("benötigte Zeit = " + msecs + "ms");
					if(array.length < 101){
						for(int i=0; i<(array.length-1); i++){
							System.out.print(array[i] + ".");
						}
					}else{
						System.out.println("Eingabe zu lang für Grafische Ausgabe");
					}
				}else{
					System.out.println("Feld NICHT sortiert");
					System.out.println("benötigte Zeit = " + msecs + "ms");
					if(array.length < 101){
						for(int i=0; i<(array.length-1); i++){
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
			try{
				  parameter1 = Integer.parseInt(args[0]);
			}catch(NumberFormatException e){
				  System.out.println("Parameter ist keine Zahl");
			}
			if(parameter1 > 0 && parameter1 < 2147483647 && (args[1].equals("auf") || args[1].equals("ab") || args[1].equals("rand"))){
				array = new int[parameter1];
				fill("",array);
				long tStart = System.currentTimeMillis();
				insertionSort(array);
				long tEnd = System.currentTimeMillis();
				long msecs = tEnd - tStart;
				boolean check = isSorted(array);
				if(check){
					System.out.println("Feld sortiert!");
					System.out.println("benötigte Zeit = " + msecs + "ms");
					if(array.length < 101){
						for(int i=0; i<(array.length-1); i++){
							System.out.print(array[i] + ".");
						}
					}else{
						System.out.println("Eingabe zu lang für Grafische Ausgabe");
					}
				}else{
					System.out.println("Feld NICHT sortiert");
					System.out.println("benötigte Zeit = " + msecs + "ms");
					if(array.length < 101){
						for(int i=0; i<(array.length-1); i++){
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
		int temp;
		for (int i=1; i<array.length; i++){
			temp = array[i];
			int index = i;
			while (index > 0 && array[index - 1] > temp){
				array[index] = array[index - 1];
				index--;
			}
			array[index] = temp;
		}
		return array;
	}
	
	public static boolean isSorted(int[] array){
		for(int i=0; i<(array.length-1); i++){
			if(array[i] > array[i+1]){
				return false;
			}
		}
		return true;
	}
	
	public static int[] fill(String eingabe2,int[] array){
		if(eingabe2 == "auf"){
			for(int i=0; i<array.length; i++){
				array[i] = i;
			}
		}
		else if(eingabe2 == "ab"){
			int zaehler=0;
			for(int i=(array.length-1); i!=0; i--){
				array[i] = zaehler;
				zaehler++;
			}
		}
		else{
			java.util.Random numberGenerator = new java.util.Random();
			for(int i=0; i<array.length; i++){
				array[i] = numberGenerator.nextInt();
			}
		}
		
		return array;
	}
}
