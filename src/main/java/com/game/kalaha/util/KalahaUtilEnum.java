package com.game.kalaha.util;

public enum KalahaUtilEnum {
	
	NUMBER_OF_SEEDS_PER_PIT(6),
	NUMBER_OF_PITS(14),
	PITS_FOR_PLAYER(7);
	
	private final int value;
	
	private KalahaUtilEnum(int noOfPits) {
		this.value = noOfPits;
	}

	public int getValue() {
		return value;
	}
	
}
