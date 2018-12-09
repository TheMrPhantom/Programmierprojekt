package de.propro.backend;

public class MutableInt implements Comparable<MutableInt> {

	public MutableInt(int i) {
		this.i = i;
	}

	public int i;

	@Override
	public int compareTo(MutableInt j) {
		if (i < j.i) {
			return -1;
		} else {
			return 1;
		}

	}

	@Override
	public String toString() {
		return i+"";
	}

}
