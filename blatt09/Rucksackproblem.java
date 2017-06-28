package def;

import java.util.concurrent.ThreadLocalRandom;

public class Rucksackproblem {
	
	
	public static void main(String[] args){
		
		if(args.length == 3){
			int n = 0,g = 0,p = 0;
			try {
				n = Integer.parseInt(args[0]);
				g = Integer.parseInt(args[1]);
				p = Integer.parseInt(args[2]);
			} catch (Exception e) {
				System.out.println("Fehler bei dem Verarbeiten der Eingaben");
				System.exit(0);
			}
			if(n > 0 && g > 0 && p > 0){
				double tmp;
				Ware[] waren = new Ware[n];
				for(int i=0; i<n; i++){
					waren[i] = new Ware(p);
				}
				tmp = algorithmusVorlesung(n, g, p, waren);
				System.out.println(tmp);
				tmp = algorithmusAufgabe(n, g, p, waren);
				System.out.println(tmp);
			}else{
				System.out.println("Eingegebene Parameter ungültig");
				System.exit(0);
			}
		}else{
			System.out.println("Fehler bei der Eingabe der Parameter");
			System.exit(0);
		}
	}
	
	public static double algorithmusVorlesung(int anzahl, int gewichtsschranke, int p, Ware[] waren){
		double[][] arr = new double[anzahl][gewichtsschranke];
		for(int i=0; i<gewichtsschranke; i++){
			arr[0][i] = 0;
		}
		for(int i=1; i<anzahl; i++){
			for(int j=0; j<gewichtsschranke; j++){
				if(waren[i].getGewicht() > j){
					arr[i][j] = arr[i-1][j];
				}else{
					if(arr[i-1][j] > waren[i].getWert() + arr[i-1][j- waren[i].getGewicht()]){
						arr[i][j] = arr[i-1][j];
					}else{
						arr[i][j] = waren[i].getWert() + arr[i-1][j- waren[i].getGewicht()];
					}
				}
			}
		}
		return arr[anzahl-1][gewichtsschranke-1];
	}
	public static double algorithmusAufgabe(int anzahl, int gewichtsschranke, int p, Ware[] waren){
		double tmp=0;
		int tmp2=0;
		double ausgabe=0;
		while(ausgabe < gewichtsschranke){
			for(int i=0; i<waren.length-1;i++){
				if(Math.abs(waren[i].getGewicht()-waren[i].getWert()) < tmp){
					tmp = waren[i].getGewicht();
					tmp2 = i;
				}
			}
			ausgabe += tmp;
			waren[tmp2].setGewicht(0);
			waren[tmp2].setWert(10000);
		}
		return ausgabe;
	}
}
