class Job implements Comparable<Job> {
	int[] bereich;

	public Job(int dauer, int deadline) {
		bereich = new int[2];
		bereich[0] = dauer;
		bereich[1] = deadline;
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

	public String toString2() {
		String ausgabe = new String("Dauer: " + getDauer() + " Deadline: " + getDeadline());
		return ausgabe;
	}

	public int compareTo(Job other) {
		if (this.getDeadline() < other.getDeadline()) {
			return -1;
		} else if (this.getDeadline() == other.getDeadline()) {
			return 0;
		} else {
			return +1;
		}
	}
}