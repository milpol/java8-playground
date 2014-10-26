package com.roundbytes.sandbox.defaultmethods;

import com.roundbytes.sandbox.beans.Enemy;
import com.roundbytes.sandbox.beans.Position;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class EnemyMovementProcessorTest
{
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private final static double ANGLE = 0;
    private final static double SPEED = 1;

    private final EnemyMovementProcessor enemyMovementProcessor = new EnemyMovementProcessor(ANGLE, SPEED);

    private Enemy enemy;

    @Before
    public void setUp()
    {
        enemy = new Enemy(1, 1);
    }

    @Test
    public void shouldNotIndicateNullEnemyAsControllable()
    {
        //given
        //when
        boolean controllable = enemyMovementProcessor.isControllable(null);
        //then
        Assert.assertFalse(controllable);
    }

    @Test
    public void shouldNotIndicateEnemyAsControllable()
    {
        //when
        final boolean controllable = enemyMovementProcessor.isControllable(enemy);
        //then
        Assert.assertFalse(controllable);
    }

    @Test
    public void shouldFailOnTryingToMoveNotControllableCharacter()
    {
        //expect
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("not controllable");
        //when
        enemyMovementProcessor.tryProcessControllable(enemy);
    }

    @Test
    public void shouldInitializeEnemyWithDefaultPosition()
    {
        //when
        enemyMovementProcessor.process(enemy);
        //then
        Assert.assertTrue(enemy.getPosition().isPresent());
        Assert.assertTrue(enemy.getPosition().get().isDefault());
    }

    @Test
    public void shouldInitializeAndMoveEnemyWithDefinedParameters()
    {
        //when
        enemyMovementProcessor.process(enemy);
        enemyMovementProcessor.process(enemy);
        //then
        Assert.assertEquals(enemy.getPosition().get(), new Position(1, 0));
    }
}