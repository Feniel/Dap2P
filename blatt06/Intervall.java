class Intervall implements Comparable<Intervall> {
	int[] bereich;

	public Intervall(int Start, int End) {
		bereich = new int[] { Start, End };
	}

	public int getStart() {
		return this.bereich[0];
	}

	public int getEnd() {
		return this.bereich[1];
	}

	public String toString() {
		String ausgabe = new String("[" + this.getStart() + "," + this.getEnd() + "]");
		return ausgabe;
	}

	// Zweite Ausgabemethode - wird in diesem Programm nicht verwendet
	public String toString2() {
		String ausgabe = new String("Intervallanfang: " + getStart() + " Intevallende: " + getEnd());
		return ausgabe;
	}

	public int compareTo(Intervall other) {
		if (this.getEnd() < other.getEnd()) {
			return -1;
		} else if (this.getEnd() == other.getEnd()) {
			return 0;
		} else {
			return +1;
		}
	}
}