package def;

import java.util.concurrent.ThreadLocalRandom;

public class Ware {
	double wert;
	int gewicht;
	
	public Ware(double wert,int gewicht){
		this.wert = wert;
		this.gewicht = gewicht;
	}
	public Ware(int p){
		this.wert = ThreadLocalRandom.current().nextDouble(0.8*p, 1.25*p);
		this.gewicht = (int)ThreadLocalRandom.current().nextInt(100, 1000);
	}
	
	public int getGewicht() {
		return gewicht;
	}
	public double getWert() {
		return wert;
	}
	
	public void setGewicht(int gewicht) {
		this.gewicht = gewicht;
	}
	public void setWert(double wert) {
		this.wert = wert;
	}
}
