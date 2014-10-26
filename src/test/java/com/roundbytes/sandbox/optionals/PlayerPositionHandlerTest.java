package com.roundbytes.sandbox.optionals;

import com.roundbytes.sandbox.beans.Player;
import com.roundbytes.sandbox.beans.Position;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Optional;

public class PlayerPositionHandlerTest
{
    private static final Position CUSTOM_POSITION = new Position(2, 3);
    private static final Position CUSTOM_SWAP_POSITION = new Position(3, 2);
    private static final Position CUSTOM_ILLEGAL_POSITION = new Position(2, -1);

    private Player player;

    private PlayerPositionHandler playerPositionHandler;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp()
    {
        player = new Player("player name", 1, 1);
        playerPositionHandler = new PlayerPositionHandler(player);
    }

    @Test
    public void positionShouldNotBeAvailableByDefault()
    {
        //when
        final Optional<Position> position = player.getPosition();
        //then
        Assert.assertFalse(position.isPresent());
    }

    @Test
    public void shouldGetDefaultPosition()
    {
        //when
        final Position position = playerPositionHandler.getPositionOrDefault();
        //then
        Assert.assertTrue(position.isDefault());
    }

    @Test
    public void shouldGetPlayerCustomPosition()
    {
        //given
        player.setPosition(CUSTOM_POSITION);
        //when
        final Position position = playerPositionHandler.getPositionOrDefault();
        //then
        Assert.assertEquals(position, CUSTOM_POSITION);
    }

    @Test
    public void shouldFailForEmptyPositionWithoutLambdaNotation()
    {
        //expect
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("not present");
        //when
        playerPositionHandler.getPositionOrFailWithoutLambdaNotation();
    }

    @Test
    public void shouldFailForEmptyPosition()
    {
        //expect
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("not present");
        //when
        playerPositionHandler.getPositionOrFail();
    }

    @Test
    public void shouldNotFailForEmptyPositionWithoutLambdaNotation()
    {
        //given
        player.setPosition(CUSTOM_POSITION);
        //when
        final Position position = playerPositionHandler.getPositionOrFailWithoutLambdaNotation();
        //then
        Assert.assertEquals(position, CUSTOM_POSITION);
    }

    @Test
    public void shouldNotFailForEmptyPosition()
    {
        //given
        player.setPosition(CUSTOM_POSITION);
        //when
        final Position position = playerPositionHandler.getPositionOrFail();
        //then
        Assert.assertEquals(position, CUSTOM_POSITION);
    }

    @Test
    public void shouldGetNextAvailablePosition()
    {
        //when
        final Position position = playerPositionHandler.getPositionOrNextAvailable();
        final Position positionNoLambdaNotation = playerPositionHandler.getPositionOrNextAvailableWithoutLambdaNotation();
        //then
        Assert.assertEquals(position, PlayerPositionHandler.NEXT_AVAILABLE_POSITION);
        Assert.assertEquals(positionNoLambdaNotation, PlayerPositionHandler.NEXT_AVAILABLE_POSITION);
    }

    @Test
    public void shouldNotGetNextAvailablePosition()
    {
        //given
        player.setPosition(CUSTOM_POSITION);
        //when
        final Position position = playerPositionHandler.getPositionOrNextAvailable();
        final Position positionNoLambdaNotation = playerPositionHandler.getPositionOrNextAvailableWithoutLambdaNotation();
        //then
        Assert.assertEquals(position, CUSTOM_POSITION);
        Assert.assertEquals(positionNoLambdaNotation, CUSTOM_POSITION);
    }

    @Test
    public void shouldGetEmptyPosition()
    {
        //when
        final Optional<Position> position = playerPositionHandler.getNotDefaultPosition();
        final Optional<Position> positionNoLambdaNotation = playerPositionHandler.getNotDefaultPositionWithoutLambdaNotation();
        //then
        Assert.assertFalse(position.isPresent());
        Assert.assertFalse(positionNoLambdaNotation.isPresent());
    }

    @Test
    public void shouldNotGetEmptyPosition()
    {
        //given
        player.setPosition(CUSTOM_POSITION);
        //when
        final Optional<Position> position = playerPositionHandler.getNotDefaultPosition();
        final Optional<Position> positionNoLambdaNotation = playerPositionHandler.getNotDefaultPositionWithoutLambdaNotation();
        //then
        Assert.assertEquals(position.get(), CUSTOM_POSITION);
        Assert.assertEquals(positionNoLambdaNotation.get(), CUSTOM_POSITION);
    }

    @Test
    public void shouldNotSwapPositionIfNull()
    {
        //when
        final Optional<Position> position = playerPositionHandler.getSwappedPosition();
        final Optional<Position> positionNoLambdaNotation = playerPositionHandler.getSwappedPositionWithoutLambdaNotation();
        //then
        Assert.assertFalse(position.isPresent());
        Assert.assertFalse(positionNoLambdaNotation.isPresent());
    }

    @Test
    public void shouldSwapPosition()
    {
        //given
        player.setPosition(CUSTOM_POSITION);
        //when
        final Optional<Position> position = playerPositionHandler.getSwappedPosition();
        final Optional<Position> positionNoLambdaNotation = playerPositionHandler.getSwappedPositionWithoutLambdaNotation();
        //then
        Assert.assertEquals(position.get(), CUSTOM_SWAP_POSITION);
        Assert.assertEquals(positionNoLambdaNotation.get(), CUSTOM_SWAP_POSITION);
    }

    @Test
    public void shouldNotFailVerifyingNullPosition()
    {
        //expect
        //when
        playerPositionHandler.verifyPositionState();
        playerPositionHandler.verifyPositionStateWithoutLambdaNotation();
    }

    @Test
    public void shouldNotFailVerifyingValidPosition()
    {
        //given
        player.setPosition(CUSTOM_POSITION);
        //when
        playerPositionHandler.verifyPositionState();
        playerPositionHandler.verifyPositionStateWithoutLambdaNotation();
    }

    @Test
    public void shouldFailOnInvalidPositionWithoutLambdaNotation()
    {
        //expect
        expectedException.expect(IllegalStateException.class);
        //given
        player.setPosition(CUSTOM_ILLEGAL_POSITION);
        //when
        playerPositionHandler.verifyPositionStateWithoutLambdaNotation();
    }

    @Test
    public void shouldFailOnInvalidPosition()
    {
        //expect
        expectedException.expect(IllegalStateException.class);
        //given
        player.setPosition(CUSTOM_ILLEGAL_POSITION);
        //when
        playerPositionHandler.verifyPositionState();
    }
}