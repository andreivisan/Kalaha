package com.game.kalaha.model;

public class KalahaGame {
	
	private Player player1;
	private Player player2;
	private Board board;
    private String message;
	
	
	public KalahaGame() {
		this.board = new Board();
		this.player1 = new Player();
		this.player2 = new Player();
		player1.setPlayerNumber(1);
		player2.setPlayerNumber(2);
		player1.setActive(true);
		player2.setActive(false);
	}

	public Player getPlayer1() {
        return player1;
	}

	public void setPlayer1(Player player1) {
        this.player1 = player1;
	}

	public Player getPlayer2() {
        return player2;
	}

	public void setPlayer2(Player player2) {
        this.player2 = player2;
	}

	public Board getBoard() {
        return board;
	}

	public void setBoard(Board board) {
        this.board = board;
	}

    public Player getActivePlayer() {
        return player1.isActive() ? player1 : player2;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
