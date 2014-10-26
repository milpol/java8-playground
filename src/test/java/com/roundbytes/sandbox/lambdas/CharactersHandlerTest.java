package com.roundbytes.sandbox.lambdas;

import com.roundbytes.sandbox.beans.*;
import com.roundbytes.sandbox.beans.Character;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CharactersHandlerTest
{
    private static final int PLAYER_SIZE = 5;
    private static final Position COLLISION_POSITION_1 = new Position(1, 1);
    private static final Position COLLISION_POSITION_2 = new Position(2, 2);
    private static final Position NOT_COLLISION_POSITION = new Position(10, 10);

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private CharactersHandler charactersHandler = new CharactersHandler();

    private Player player1;

    private Player player2;

    @Before
    public void setUp()
    {
        player1 = new Player("player1", PLAYER_SIZE, 2);
        player2 = new Player("player2", PLAYER_SIZE, 2);
    }

    @Test
    public void shouldFailOnCheckCollisionForNullInputParameters()
    {
        //expect
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("cannot be null");
        //then
        charactersHandler.isCollision(null, null);
    }

    @Test
    public void shouldNotIndicateCollisionForEmptyPosition()
    {
        //when
        final boolean collision = charactersHandler.isCollision(player1, player2);
        //then
        Assert.assertFalse(collision);
    }

    @Test
    public void shouldNotIndicateCollisionOnValidParameters()
    {
        //given
        player1.setPosition(COLLISION_POSITION_1);
        player2.setPosition(NOT_COLLISION_POSITION);
        //when
        final boolean collision = charactersHandler.isCollision(player1, player2);
        //then
        Assert.assertFalse(collision);
    }

    @Test
    public void shouldIndicateCollisionOnValidParameters()
    {
        //given
        player1.setPosition(COLLISION_POSITION_1);
        player2.setPosition(COLLISION_POSITION_2);
        //when
        final boolean collision = charactersHandler.isCollision(player1, player2);
        //then
        Assert.assertTrue(collision);
    }

    @Test
    public void shouldFailOnGettingFightWinnerForNullInputParameters()
    {
        //expect
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("cannot be null");
        //then
        charactersHandler.getFightWinner(null, null);
    }

    @Test
    public void shouldGetFightWinner()
    {
        //when
        final Character fightWinner = charactersHandler.getFightWinner(player1, player2);
        //then
        Assert.assertTrue(fightWinner.equals(player1) || fightWinner.equals(player2));
    }
}