
public class Quicksort {
	
	public static void main(String[] args){
				
		if(args.length == 0 || args.length > 1){	
				System.out.println("Falsches Argument");
				System.exit(1);
		}
		int laenge = 0;
		try{
			laenge = Integer.parseInt(args[0]);
		}catch(Exception e){	
			System.out.println("Bitte ine Zahl übergeben");
			System.exit(1);
		}
		if(laenge < 0){
			System.out.println("Keine negativen Zahlen");
			System.exit(1);
		}
		int[] arr = new int[laenge];
		fill("",arr);
		System.out.println(isSorted(arr));
			long tStart=0;
			long tEnd=0;
			long msecs = 0;
			for(int x=0; x<3; x++){
				tStart = System.currentTimeMillis();
				quickSort(arr);
				//Zeitmessung beenden
				tEnd = System.currentTimeMillis();
				boolean check = isSorted(arr);
				//Hier wird mit den Assertions geprüft ob der Algorithmus nach Plan abgelaufen ist
				assert check : "Fehler bei der finalen Übergabe des Algorithmus";
				msecs = msecs + (tEnd - tStart);
			}
		msecs = msecs / 3;	
		System.out.println(msecs);	
		//Pruefung ob das Array aufsteigen sortiert wurde
		System.out.println(isSorted(arr));
		
	}
	
	public static void quickSort(int[] arr){
		  int untereGrenze = 0;
		  int obereGrenze = arr.length-1;
		  quickSort(arr, untereGrenze,obereGrenze);
	}

	private static void quickSort(int[] arr,int untereGrenze,int obereGrenze){
		  int links = untereGrenze;
		  int rechts = obereGrenze;
		  int pivot = arr[((untereGrenze + obereGrenze) / 2)];
		  
		while(links <= rechts){
			while(arr[links] < pivot){
				links++;
			}
			while(pivot < arr[rechts]){
				rechts--;
			}
			if(links <= rechts){
			    int tmp = arr[links];
			    arr[links] = arr[rechts];
			    arr[rechts] = tmp;
			    links++;
			    rechts--;
			}
		}
		if(untereGrenze < rechts){
			boolean check = partSorted(arr,untereGrenze,(arr.length/2)-1);
			//Hier wird mit den Assertions geprüft ob der Algorithmus nach Plan abgelaufen ist
			assert check : "Fehler im Verlauf des Algorithmus";
		    quickSort(arr, untereGrenze, rechts);
		}
		if (links < obereGrenze) {
			boolean check = partSorted(arr,(arr.length/2)+1,obereGrenze);
			//Hier wird mit den Assertions geprüft ob der Algorithmus nach Plan abgelaufen ist
			assert check : "Fehler im Verlauf des Algorithmus";
		    quickSort(arr, links, obereGrenze);
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
	
	public static boolean partSorted(int[] array, int stelle){
		for(int i=array.length-1; i>stelle; i--){
			if(array[i] < array[i-1]){
				return false;
			}
		}
		return true;
	}
	
	public static boolean partSorted(int[] array,int anfang, int ende){
		for(int i=anfang; i>ende; i--){
			if(array[i] < array[i-1]){
				return false;
			}
		}
		return true;
	}
}
