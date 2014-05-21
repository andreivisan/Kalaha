package com.game.kalaha.model;

import com.game.kalaha.util.KalahaUtilEnum;

public class Pit {
	
	private int noOfseeds;
    private int id;
	private boolean isKalaha;
	private boolean isEmpty;
	
	public Pit() {
        this.noOfseeds = KalahaUtilEnum.NUMBER_OF_SEEDS_PER_PIT.getValue();
	}
	
	public int getNoOfseeds() {
        return noOfseeds;
	}

	public void setNoOfseeds(int noOfseeds) {
        this.noOfseeds = noOfseeds;
	}

	public boolean isKalaha() {
        return isKalaha;
	}
	
	public void setKalaha(boolean isKalaha) {
        this.isKalaha = isKalaha;
	}

	public boolean isEmpty() {
        return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
	
}
