package com.game.kalaha.service;

import com.game.kalaha.model.Board;
import com.game.kalaha.model.KalahaGame;
import com.game.kalaha.model.Pit;
import com.game.kalaha.model.Player;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by andreivisan on 5/15/14.
 */
public class KalahaServiceTest {

    private static KalahaService testedObject;
    private static Player player1;
    private static Player player2;
    private static Pit pit;
    private static Board board;
    private static KalahaGame game;

    @BeforeClass
    public static void testSetup() {
        testedObject = new KalahaService();
        player1 = Mockito.mock(Player.class);
        player2 = Mockito.mock(Player.class);
        pit = Mockito.mock(Pit.class);
        board = Mockito.mock(Board.class);
        game = Mockito.mock(KalahaGame.class);
    }

    @Test
    public void testIsLastSeedInOwnEmptyPit() {
        when(player1.isActive()).thenReturn(true);
        when(player1.getPlayerNumber()).thenReturn(1);
        when(pit.getId()).thenReturn(0);
        assertEquals(testedObject.isLastSeedInOwnEmptyPit(player1, pit), true);
    }

    @Test
    public void testIsOwnPit() {
        when(player1.isActive()).thenReturn(true);
        when(player1.getPlayerNumber()).thenReturn(1);
        when(pit.getId()).thenReturn(0);
        assertEquals(testedObject.isOwnPit(player1, pit.getId()), true);
    }

    @Test
    public void testIsPlayerKalahaTrue() {
        when(player1.isActive()).thenReturn(true);
        when(player1.getPlayerNumber()).thenReturn(1);
        when(pit.getId()).thenReturn(6);
        assertEquals(testedObject.isPlayerKalaha(player1, pit.getId()), true);
    }

    @Test
    public void testIsPlayerKalahaFalse() {
        when(player1.isActive()).thenReturn(true);
        when(player1.getPlayerNumber()).thenReturn(1);
        when(pit.getId()).thenReturn(5);
        assertEquals(testedObject.isPlayerKalaha(player1, pit.getId()), false);
    }

    @Test
    public void testGetOpositePit() {
        when(pit.getId()).thenReturn(6);
        when(game.getBoard()).thenReturn(board);
        when(board.getPitById(6)).thenReturn(pit);
        assertEquals(testedObject.getOpositePit(game, pit.getId()).getId(), 6);
    }

}
