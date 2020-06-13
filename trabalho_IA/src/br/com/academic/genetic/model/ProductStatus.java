package br.com.academic.genetic.model;

public enum ProductStatus {

	NON_OCCUPIED((byte) 0), OCCUPIED((byte) 1);

	private final byte state;

	ProductStatus(byte state) {
		this.state = state;
	}

	public byte getState() {
		return state;
	}

	public static ProductStatus fromInt(int value) {
		if(value > 127) {
			value = -1;
		}
		
		return fromByte((byte) value);
	}

	public static ProductStatus fromByte(byte value) {
		switch (value) {
		case 0:
			return NON_OCCUPIED;
		case 1:
			return OCCUPIED;
		default:
			return null;
		}
	}
}
