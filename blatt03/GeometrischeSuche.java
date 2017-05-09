package dap2;

public class GeometrischeSuche {
	
	
	public static void main(String[] args){
		float parameter1 = 0;
		int parameter2 = 0;
		if(args.length == 2){
			try{
				  parameter1 = Float.parseFloat(args[0]);
				  parameter2 = Integer.parseInt(args[1]);
			}catch(NumberFormatException e){
				  System.out.println("Parameter ist keine Zahl");
				  System.exit(1);
			}
			if(parameter1 > 0 && parameter1 < 2139095039 && parameter2 > 0 && parameter2 < 100){
				int[] array = new int[1000];
				int status = 1;
				long tStart=0;
				long tEnd=0;
				long msecs = 0;
				fill("ab",array);
				System.out.println("");
				System.out.print("Durchläufe: ");
				while(status >= 1){				
					msecs = 0;
					for(int x=0; x<parameter2; x++){
						tStart = System.currentTimeMillis();
						bubbleSort(array);
						//Zeitmessung beenden
						tEnd = System.currentTimeMillis();
						msecs = msecs + (tEnd - tStart);
					}
					msecs = msecs / parameter2;				
					System.out.print(status + ": " + msecs + "ms |");
					status++;
					if (msecs > parameter1*1000){
						status = 0;
					}else{
						array = new int[array.length*2];
						fill("ab",array);
					}					
				}			
				System.out.println("");
				int feldgrößeRechts = array.length;
				int feldgrößeLinks = array.length/2;
				//Man errechnet die Differenz teilt diese durch zwei um so an den Mittelwert zu kommen und um diesen zwischen 
				//den Werten zu positionieren wir der kleinere addiert
				int tempFeldgröße = ((feldgrößeRechts - feldgrößeLinks)/2) + feldgrößeLinks;
				int startwert = 0;
				int status2 = 1; //zähler bennen
				System.out.println("BinäreSuche gestartet");
				System.out.println("Durchläufe: ");
				while(status2 >= 1){ 
					array = new int[tempFeldgröße];
					fill("ab", array);
					msecs = 0;
					//for schleife zum genaueren messen der Werte
					for(int x=0; x<parameter2; x++){
						tStart = System.currentTimeMillis();
						bubbleSort(array);
						tEnd = System.currentTimeMillis();
						msecs = msecs + (tEnd - tStart);
					}
					msecs = msecs / parameter2;
					//Jeweils +-50 da man ja eine Toleranz von 100ms haben darf
					if(msecs < (parameter1*1000)+50 && msecs > (parameter1*1000)-50){						
						System.out.println(status2 + ": " + msecs + "ms ," + array.length + "Felder");
						System.out.println("Ergebnis: " + array.length + "Felder und diese werden in " + msecs/1000 + "," + (msecs/100)%10 + "s durchlaufen.");
						status2 = 0;
					//Wenn die milli sec kleiner sind und man in den rechten teil geht
					}else if(msecs < parameter1*1000){
						System.out.println(status2 + ": " + msecs + "ms ," + array.length + "Felder");
						feldgrößeLinks = tempFeldgröße;
						tempFeldgröße = ((feldgrößeRechts - feldgrößeLinks)/2) + feldgrößeLinks;
						status2++;
					//Wenn die milli sec kleiner sind und man in den linken teil geht
					}else if(msecs > parameter1*1000){
						System.out.println(status2 + ": " + msecs + "ms ," + array.length + "Felder");
						feldgrößeRechts = tempFeldgröße;
						tempFeldgröße = ((feldgrößeRechts - feldgrößeLinks)/2) + feldgrößeLinks;
						status2++;
					}else{
						System.out.println("Logisch gibts es keine Möglichkeit diesen Fall zu erreichen");
					}
					
				}
				
			}
		}else{
			System.out.println("Fehler bei der Parameterübergabe");
		}
		 
		
	}
	
	public static int[] bubbleSort(int[] array){
		int n = array.length;
		//Schleife für die gesamte Länge des arrays
		for(int i=1; i<n; i++){
			//für die einzelnen Durchläufe
			for(int j=n-1; j>i+1; j--){
				//Wenn die nachfolgende Zahl kleiner ist wird getauscht
				if(array[j-1] > array[j]){
					int temp = array[j];
					array[j] = array[j-1];
					array[j-1] = temp;
				}
			}
		}
		return array;
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
