package com.game.kalaha.model;

import com.game.kalaha.util.KalahaUtilEnum;


public class Board {
	
	private Pit[] pits;
	
	public Board() {
		this.pits = new Pit[KalahaUtilEnum.NUMBER_OF_PITS.getValue()];
		for(int i=0; i<KalahaUtilEnum.NUMBER_OF_PITS.getValue(); i++) {
			Pit pit = new Pit();
			pits[i] = pit;
            pits[i].setId(i);
			if(((i+1) % KalahaUtilEnum.PITS_FOR_PLAYER.getValue()) == 0) {
				pits[i].setKalaha(true);
			} else {
				pits[i].setKalaha(false);
			}
			if(!pits[i].isKalaha()) {
				pits[i].setNoOfseeds(KalahaUtilEnum.NUMBER_OF_SEEDS_PER_PIT.getValue());
			} else {
				pits[i].setNoOfseeds(0);
			}
		}
	}

	public Pit[] getPits() {
        return pits;
	}

	public void setPits(Pit[] pits) {
        this.pits = pits;
	}

    public Pit getPitById(int pitId) {
        return this.getPits()[pitId];
    }
}
