package com.roundbytes.sandbox.defaultmethods;

import com.roundbytes.sandbox.beans.Player;
import com.roundbytes.sandbox.beans.Position;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * We can test interface for its static methods
 */
public class ProcessorTest
{
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldFailOnGettingPositionFromNullCharacter()
    {
        //expected
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("cannot be null");
        //when
        Processor.getPosition(null);
    }

    @Test
    public void shouldGetDefaultPositionIfNotAvailable()
    {
        //given
        final Player player = new Player("player", 1, 2);
        //when
        //there is no need to specify the interface type
        final Position position = Processor.getPosition(player);
        //then
        Assert.assertTrue(position.isDefault());
    }

    @Test
    public void shouldGetPositionFromPlayer()
    {
        //given
        final Position position = new Position(1, 2);
        final Player player = new Player("player", 1, 2);
        player.setPosition(position);
        //when
        final Position retrievedPosition = Processor.getPosition(player);
        //then
        Assert.assertSame(position, retrievedPosition);
    }
}