package com.game.kalaha.service;

import com.game.kalaha.model.Board;
import com.game.kalaha.model.KalahaGame;
import com.game.kalaha.model.Pit;
import com.game.kalaha.model.Player;
import com.game.kalaha.util.KalahaUtilEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andreivisan on 5/12/14.
 */
@Service("kalahaService")
@Transactional
public class KalahaService {

    String message = "";

    public KalahaGame startTheGame(int clickedPitId, KalahaGame game) {
        Board newBoard = game.getBoard();
        int i = clickedPitId + 1;
        Pit clickedPit = game.getBoard().getPitById(clickedPitId);
        int seedsInClickedPit = clickedPit.getNoOfseeds();
        Pit[] pits = game.getBoard().getPits();
        pits[clickedPitId].setNoOfseeds(0);
        Player currentActivePlayer = game.getActivePlayer();
        while(i< KalahaUtilEnum.NUMBER_OF_PITS.getValue() && seedsInClickedPit > 0) {
            if(seedsInClickedPit == 1) {
                message = "Last seed in active player's own Klaha. You are still the active player!";
                if(isLastSeedInOwnEmptyPit(currentActivePlayer, pits[i])) {
                    Pit oppositePit = getOpositePit(game, pits[i].getId());
                    int numberOfSeedsToAdd = oppositePit.getNoOfseeds() + 1;
                    if(currentActivePlayer.getPlayerNumber() == 1) {
                        pits[KalahaUtilEnum.PITS_FOR_PLAYER.getValue() - 1].setNoOfseeds(pits[KalahaUtilEnum.PITS_FOR_PLAYER.getValue() - 1].getNoOfseeds() + numberOfSeedsToAdd);
                    } else {
                        pits[KalahaUtilEnum.NUMBER_OF_PITS.getValue() - 1].setNoOfseeds(pits[KalahaUtilEnum.NUMBER_OF_PITS.getValue() - 1].getNoOfseeds() + numberOfSeedsToAdd);
                    }
                    pits[oppositePit.getId()].setNoOfseeds(0);
                    pits[pits[i].getId()].setNoOfseeds(0);
                    game = changeActivePlayer(game, currentActivePlayer);
                    break;
                } else if(!isPlayerKalaha(currentActivePlayer, pits[i].getId())) {
                    //if last seed doesn't end up in active player's Kalaha change turns
                    game = changeActivePlayer(game, currentActivePlayer);
                }
            }
            //check that the next pit is not opponent's Kalaha
            if(!pits[i].isKalaha() || isPlayerKalaha(currentActivePlayer, pits[i].getId())) {
                pits[i].setNoOfseeds(pits[i].getNoOfseeds()+1);
                seedsInClickedPit--;
            }
            //if we reached the end of the board and still have seeds re-start from first pit
            if(i == KalahaUtilEnum.NUMBER_OF_PITS.getValue()-1 && seedsInClickedPit > 0) {
                i = 0;
            } else {
                i++;
            }
        }
        newBoard.setPits(pits);
        int player1NoOfSeeds = getNumberOfSeedsForPlayer(newBoard, game.getPlayer1());
        int player2NoOfSeeds = getNumberOfSeedsForPlayer(newBoard, game.getPlayer2());
        if(player1NoOfSeeds == 0 || player2NoOfSeeds == 0) {
            game = gameOver(game, pits, player1NoOfSeeds, player2NoOfSeeds);
        }
        game.setMessage(message);
        game.setBoard(newBoard);
        return game;
    }

    private KalahaGame gameOver(KalahaGame game, Pit[] pits, int player1NoOfSeeds, int player2NoOfSeeds) {
        Pit player1Kalaha = pits[KalahaUtilEnum.PITS_FOR_PLAYER.getValue() - 1];
        Pit player2Kalaha = pits[KalahaUtilEnum.NUMBER_OF_PITS.getValue() - 1];
        int player1TotalNoOfSeeds = player1Kalaha.getNoOfseeds() + player1NoOfSeeds;
        int player2TotalNoOfSeeds = player2Kalaha.getNoOfseeds() + player2NoOfSeeds;
        if(player1TotalNoOfSeeds > player2TotalNoOfSeeds) {
            message = "GAME FINISHED!!! PLAYER 1 HAS " + player1TotalNoOfSeeds + " SEEDS AND PLAYER 2 HAS " + player2TotalNoOfSeeds + " SEEDS. PLAYER 1 WINS!!!";
        } else if(player1TotalNoOfSeeds < player2TotalNoOfSeeds) {
            message = "GAME FINISHED!!! PLAYER 1 HAS " + player1TotalNoOfSeeds + " SEEDS AND PLAYER 2 HAS " + player2TotalNoOfSeeds + " SEEDS. PLAYER 2 WINS!!!";
        } else {
            message = "GAME FINISHED!!! IT'S A DRAW!!!";
        }
        game.getPlayer1().setActive(false);
        game.getPlayer2().setActive(false);
        return game;
    }

    public boolean isLastSeedInOwnEmptyPit(Player currentActivePlayer, Pit pit) {
        return !isPlayerKalaha(currentActivePlayer, pit.getId()) && pit.getNoOfseeds() == 0 && isOwnPit(currentActivePlayer, pit.getId());
    }

    public KalahaGame changeActivePlayer(KalahaGame game, Player activePlayer) {
        if(activePlayer.getPlayerNumber() == 1) {
            game.getPlayer1().setActive(false);
            game.getPlayer2().setActive(true);
        } else {
            game.getPlayer1().setActive(true);
            game.getPlayer2().setActive(false);
        }
        message = "Player has changed.";
        game.setMessage(message);
        return game;
    }

    public boolean isOwnPit(Player player, int pitId) {
        return ((player.getPlayerNumber() == 1 && pitId < KalahaUtilEnum.PITS_FOR_PLAYER.getValue()) ||
                (player.getPlayerNumber() == 2 && pitId > KalahaUtilEnum.PITS_FOR_PLAYER.getValue()));
    }

    public Pit getOpositePit(KalahaGame game, int pitId) {
        return game.getBoard().getPitById(KalahaUtilEnum.NUMBER_OF_PITS.getValue() - 2 - pitId);
    }

    public boolean isPlayerKalaha(Player player, int pitId) {
        return pitId == player.getPlayerNumber()*7-1;
    }

    public int getNumberOfSeedsForPlayer(Board board, Player player) {
        int numberOfSeedsForPlayer = 0;
        List<Pit> pitsForPlayer = getPlayerPits(board, player);
        for(Pit pit : pitsForPlayer) {
            numberOfSeedsForPlayer += pit.getNoOfseeds();
        }
        return numberOfSeedsForPlayer;
    }

    public List<Pit> getPlayerPits(Board board, Player player) {
        List<Pit> pits = new ArrayList<Pit>();
        Pit[] allPits = board.getPits();
        if(player.getPlayerNumber() == 1) {
            for(int i=0; i<KalahaUtilEnum.PITS_FOR_PLAYER.getValue()-2; i++) {
                pits.add(allPits[i]);
            }
        } else {
            for(int i=KalahaUtilEnum.PITS_FOR_PLAYER.getValue(); i<KalahaUtilEnum.NUMBER_OF_PITS.getValue()-1; i++) {
                pits.add(allPits[i]);
            }
        }
        return pits;
    }

}