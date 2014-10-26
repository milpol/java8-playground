package com.roundbytes.sandbox.collections;

import com.roundbytes.sandbox.beans.*;
import com.roundbytes.sandbox.beans.Character;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameHandlerTest
{
    private static final String PLAYER_NAME = "player";

    private GameHandler gameHandler = new GameHandler();

    private ByteArrayOutputStream outputStream;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp()
    {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void shouldFailPrintingAllCharactersNamesWithoutLambdaForNullInput()
    {
        //expect
        expectedException.expect(NullPointerException.class);
        //when
        gameHandler.printAllCharactersWithoutLambda(null);
    }

    @Test
    public void shouldFailPrintingAllCharactersNamesForNullInput()
    {
        //expect
        expectedException.expect(NullPointerException.class);
        //when
        gameHandler.printAllCharacters(null);
    }

    @Test
    public void shouldPrintNoCharacterNamesWithoutLambdaForEmptyInput()
    {
        //when
        gameHandler.printAllCharactersWithoutLambda(Collections.emptyList());
        //then
        Assert.assertEquals("", outputStream.toString());
    }

    @Test
    public void shouldPrintNoCharacterNamesForEmptyInput()
    {
        //when
        gameHandler.printAllCharacters(Collections.emptyList());
        //then
        Assert.assertEquals("", outputStream.toString());
    }

    @Test
    public void shouldPrintCharacterNamesWithoutLambda()
    {
        //given
        final Player player = new Player(PLAYER_NAME, 3, 1);
        //when
        gameHandler.printAllCharactersWithoutLambda(Arrays.asList(player));
        //then
        Assert.assertEquals(PLAYER_NAME, outputStream.toString());
    }

    @Test
    public void shouldPrintCharacterNames()
    {
        //given
        final Player player = new Player(PLAYER_NAME, 3, 1);
        //when
        gameHandler.printAllCharacters(Arrays.asList(player));
        //then
        Assert.assertEquals(PLAYER_NAME, outputStream.toString());
    }

    @Test
    public void shouldFailOnResetPositionWithoutLambdaForNullInput()
    {
        //expect
        expectedException.expect(NullPointerException.class);
        //when
        gameHandler.resetPositionExceptFirstCharacterWithoutLambda(null);
    }

    @Test
    public void shouldFailOnResetPositionForNullInput()
    {
        //expect
        expectedException.expect(NullPointerException.class);
        //when
        gameHandler.resetPositionExceptFirstCharacter(null);
    }

    @Test
    public void shouldResetPositionForAllCharactersExceptFirstWithoutLambda()
    {
        //given
        final Player player = new Player(PLAYER_NAME, 3, 1);
        final Enemy enemy = new Enemy(3, 1);
        //when
        gameHandler.resetPositionExceptFirstCharacterWithoutLambda(Arrays.asList(player, enemy));
        //then
        Assert.assertTrue(!player.getPosition().isPresent());
        Assert.assertTrue(enemy.getPosition().get().isDefault());
    }

    @Test
    public void shouldResetPositionForAllCharactersExceptFirst()
    {
        //given
        final Player player = new Player(PLAYER_NAME, 3, 1);
        final Enemy enemy = new Enemy(3, 1);
        //when
        gameHandler.resetPositionExceptFirstCharacter(Arrays.asList(player, enemy));
        //then
        Assert.assertTrue(!player.getPosition().isPresent());
        Assert.assertTrue(enemy.getPosition().get().isDefault());
    }

    @Test
    public void shouldFailOnRemovingUnsetPositionWithoutLambdaForNullInput()
    {
        //expect
        expectedException.expect(NullPointerException.class);
        //when
        gameHandler.removeCharactersWithUnsetPositionWithoutLambda(null);
    }

    @Test
    public void shouldFailOnRemovingUnsetPositionForNullInput()
    {
        //expect
        expectedException.expect(NullPointerException.class);
        //when
        gameHandler.removeCharactersWithUnsetPosition(null);
    }

    @Test
    public void shouldRemoveUnsetPositionWithoutLambda()
    {
        //given
        final Player player = new Player(PLAYER_NAME, 3, 1);
        final Enemy enemy = new Enemy(3, 1);
        player.setPosition(Position.DEFAULT);
        List<Character> characters = new ArrayList<>(Arrays.asList(player, enemy));
        //when
        gameHandler.removeCharactersWithUnsetPositionWithoutLambda(characters);
        //then
        Assert.assertEquals(1, characters.size());
        Assert.assertTrue(characters.contains(player));
    }

    @Test
    public void shouldRemoveUnsetPosition()
    {
        //given
        final Player player = new Player(PLAYER_NAME, 3, 1);
        final Enemy enemy = new Enemy(3, 1);
        player.setPosition(Position.DEFAULT);
        List<Character> characters = new ArrayList<>(Arrays.asList(player, enemy));
        //when
        gameHandler.removeCharactersWithUnsetPosition(characters);
        //then
        Assert.assertEquals(1, characters.size());
        Assert.assertTrue(characters.contains(player));
    }
}