public class Euclid{

      public static void main(String[] args){
    	  	  int var1= 0;
    	  	  int var2= 0;
		var1 = Integer.parseInt(args[0]);
                var2 = Integer.parseInt(args[1]);
              System.out.println("Der ggT ist:");
              if(var1 > 0 && var2 > 0 && args.length == 2){
                      //die als String übergebenen Variablen werden für die weiteren Rechnungen in ints umgewandelt
                      int erg = rechnung(var1,var2);
                      System.out.println(erg);
              }else{
		System.out.println("Fehlerhafte Parameter eingegeben");
	      }
      }

      public static int rechnung(int var1,int var2){
              //0 kennzeichnet das Ende des Algorithmus, da der Divisor bei dem sich 0 ergibt der größte gemeinsame
              // Teiler der Ausgangszahl ist
              // deshalb hier die exit abfrage der rekursion
              if(var2 == 0){
                      return var1; 
              }
              else{
                      //Der Divisor wird der neue Divident und der Rest wird der neue Divisor
                      return rechnung(var2,var1%var2);
              }
      }
}
