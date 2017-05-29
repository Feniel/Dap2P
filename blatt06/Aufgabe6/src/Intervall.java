class Intervall {
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
}