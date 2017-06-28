
public class Hanoi {
	public static void main(String[] args){
		if(args.length == 1){
			int n = 0;
			try {
				n = Integer.parseInt(args[0]);
			} catch (Exception e) {
				System.out.println("Fehler bei dem Verarbeiten der Eingaben");
				System.exit(0);
			}
			char A='A',B='B',C='C';	//EIngabe der Bezeichnungen für die Ausgabe
			move(n,A,B,C);
		}else{
			System.out.println("Fehler bei der Eingabe der Parameter");
			System.exit(0);
		}
	}
	public static void move(int quantity, char start, char help,char target){
		if (quantity == 1){
		    System.out.println("Verschiebe oberste Scheibe von Turm " + start + " nach "+ target +".");	//Verschiebung von einer Scheibe unmittelbar möglich
		}
		else {
	            move(quantity-1,start, target, help); //Zunächst werden quantity-1 Scheiben von Stab start nach Stab help verschoben. Stab target nimmt die Rolle als Hilfsstapel ein.
	            move(1,start, help, target);		  //Anschließend wird, wie in Fall(1) beschrieben, die größte (und einzige) Scheibevon start nach target bewegt.
	            move(quantity-1,help, start, target); //Abschließend werden quantity-1 Scheiben von Stab help nach Stab target verschoben. Stab start nimmt die Rolle als Hilfsstapel ein.
		}
	}
}