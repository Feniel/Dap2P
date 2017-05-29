class Job {
	int[] bereich;

	public Job(int dauer, int deadline) {
		bereich = new int[2];
	}

	public int getDauer() {
		return this.bereich[0];
	}

	public int getDeadline() {
		return this.bereich[1];
	}

	public String toString() {
		String ausgabe = new String("[" + this.getDauer() + "," + this.getDeadline() + "]");
		return ausgabe;
	}
}