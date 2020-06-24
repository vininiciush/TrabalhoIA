package br.com.academic.genetic.model;

public final class Truck {

	public static final Double MAXCAPACITY = 3.0;

	public static boolean checkIfValid(Double capacity) {
		return capacity <= MAXCAPACITY;
	}
}
