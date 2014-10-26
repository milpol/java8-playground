package com.roundbytes.sandbox.streams;

import com.roundbytes.sandbox.beans.Character;
import com.roundbytes.sandbox.beans.Enemy;
import com.roundbytes.sandbox.beans.Player;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GameUtilsTest
{
    private static final int ENEMY_SIZE = 1;
    private static final int ENEMY_POWER = 1;
    private static final int ENEMY_POWER_THUG = 2;
    private static final Enemy ENEMY_1 = new Enemy(ENEMY_SIZE, ENEMY_POWER);
    private static final Enemy ENEMY_2 = new Enemy(ENEMY_SIZE, ENEMY_POWER_THUG);
    private static final String PLAYER_NAME = "player";
    private static final int PLAYER_POWER = 3;
    private static final int PLAYER_SIZE = 3;
    private static final Player PLAYER = new Player(PLAYER_NAME, PLAYER_SIZE, PLAYER_POWER);

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private final GameUtils gameUtils = new GameUtils();

    @Test
    public void shouldFailOnSumCharacterTypePowerWithoutLambdaWithNullParameter()
    {
        //expected
        expectedException.expect(NullPointerException.class);
        //when
        gameUtils.sumCharacterTypePowerWithoutLambda(null, null);
    }

    @Test
    public void shouldFailOnSumCharacterTypePowerWithNullParameter()
    {
        //expected
        expectedException.expect(NullPointerException.class);
        //when
        gameUtils.sumCharacterTypePower(null, null);
    }

    @Test
    public void shouldSumToNilCharacterPowerWithoutLambdaWhenNoCharactersPassed()
    {
        //given
        final List<Character> noCharacters = Collections.emptyList();
        //when
        final int powerSum = gameUtils.sumCharacterTypePowerWithoutLambda(noCharacters, Player.class);
        //then
        Assert.assertEquals(powerSum, 0);
    }

    @Test
    public void shouldSumToNilCharacterPowerWhenNoCharactersPassed()
    {
        //given
        final List<Character> noCharacters = Collections.emptyList();
        //when
        final int powerSum = gameUtils.sumCharacterTypePower(noCharacters, Player.class);
        //then
        Assert.assertEquals(powerSum, 0);
    }

    @Test
    public void shouldFilterOutAndSumCharacterPowerWithoutLambda()
    {
        //given
        final List<Character> characters = getCharacters();
        //when
        final int powerSum = gameUtils.sumCharacterTypePowerWithoutLambda(characters, Enemy.class);
        //then
        Assert.assertEquals(powerSum, 3);
    }

    @Test
    public void shouldFilterOutAndSumCharacterPower()
    {
        //given
        final List<Character> characters = getCharacters();
        //when
        final int powerSum = gameUtils.sumCharacterTypePower(characters, Enemy.class);
        //then
        Assert.assertEquals(powerSum, 3);
    }

    @Test
    public void shouldFailOnCreatingBossWithoutLambdaWithNullParameter()
    {
        //expected
        expectedException.expect(NullPointerException.class);
        //when
        gameUtils.createEnemyBossWithoutLambda(null);
    }

    @Test
    public void shouldFailOnCreatingBossWithNullParameter()
    {
        //expected
        expectedException.expect(NullPointerException.class);
        //when
        gameUtils.createEnemyBoss(null);
    }

    @Test
    public void shouldCreateDefaultBossWhenWithoutLambdaWithNullParameter()
    {
        //given
        final List<Character> characters = Collections.emptyList();
        //when
        final Enemy enemyBoss = gameUtils.createEnemyBossWithoutLambda(characters);
        //then
        Assert.assertEquals(enemyBoss.getPower(), 1);
        Assert.assertEquals(enemyBoss.getSize(), 1);
    }

    @Test
    public void shouldCreateDefaultBossWhenWithNullParameter()
    {
        //given
        final List<Character> characters = Collections.emptyList();
        //when
        final Enemy enemyBoss = gameUtils.createEnemyBoss(characters);
        //then
        Assert.assertEquals(enemyBoss.getPower(), 1);
        Assert.assertEquals(enemyBoss.getSize(), 1);
    }

    @Test
    public void shouldCreateEnemyBossWithoutLambda()
    {
        //given
        final List<Character> characters = getCharacters();
        //when
        final Enemy enemyBoss = gameUtils.createEnemyBossWithoutLambda(characters);
        //then
        Assert.assertEquals(enemyBoss.getPower(), 4); // a little tricky, check the starting value!
        Assert.assertEquals(enemyBoss.getSize(), 3);
    }

    @Test
    public void shouldCreateEnemyBoss()
    {
        //given
        final List<Character> characters = getCharacters();
        //when
        final Enemy enemyBoss = gameUtils.createEnemyBoss(characters);
        //then
        Assert.assertEquals(enemyBoss.getPower(), 4); // a little tricky, check the starting value!
        Assert.assertEquals(enemyBoss.getSize(), 3);
    }

    @Test
    public void shouldFailGettingNumberOfPlayersWithoutLambdaWhenNullPassed()
    {
        //expected
        expectedException.expect(NullPointerException.class);
        //when
        gameUtils.getNumberOfPlayersWithoutLambda(null);
    }

    @Test
    public void shouldFailGettingNumberOfPlayersWhenNullPassed()
    {
        //expected
        expectedException.expect(NullPointerException.class);
        //when
        gameUtils.getNumberOfPlayers(null);
    }

    @Test
    public void shouldGetNilCountingNumberOfPlayersWithoutLambdaWhenNoCharacters()
    {
        //given
        final List<Character> characters = Collections.emptyList();
        //when
        final long numberOfPlayers = gameUtils.getNumberOfPlayersWithoutLambda(characters);
        //then
        Assert.assertEquals(numberOfPlayers, 0);
    }

    @Test
    public void shouldGetNilCountingNumberOfPlayersWhenNoCharacters()
    {
        //given
        final List<Character> characters = Collections.emptyList();
        //when
        final long numberOfPlayers = gameUtils.getNumberOfPlayers(characters);
        //then
        Assert.assertEquals(numberOfPlayers, 0);
    }

    @Test
    public void shouldGetNumberOfPlayersWithoutLambda()
    {
        //given
        final List<Character> characters = getCharacters();
        //when
        final long numberOfPlayers = gameUtils.getNumberOfPlayersWithoutLambda(characters);
        //then
        Assert.assertEquals(numberOfPlayers, 1);
    }

    @Test
    public void shouldGetNumberOfPlayers()
    {
        //given
        final List<Character> characters = getCharacters();
        //when
        final long numberOfPlayers = gameUtils.getNumberOfPlayers(characters);
        //then
        Assert.assertEquals(numberOfPlayers, 1);
    }

    @Test
    public void shouldFailCheckingIsThereAnyPlayerWithoutLambdaWhenNullPassed()
    {
        //expected
        expectedException.expect(NullPointerException.class);
        //when
        gameUtils.isThereAnyPlayerWithoutLambda(null);
    }

    @Test
    public void shouldFailCheckingIsThereAnyPlayerWhenNullPassed()
    {
        //expected
        expectedException.expect(NullPointerException.class);
        //when
        gameUtils.isThereAnyPlayer(null);
    }

    @Test
    public void shouldNotIndicateAnyPlayersWithoutLambdaWhenNoCharacters()
    {
        //given
        final List<Character> characters = Collections.emptyList();
        //when
        final boolean anyPlayer = gameUtils.isThereAnyPlayerWithoutLambda(characters);
        //then
        Assert.assertFalse(anyPlayer);
    }

    @Test
    public void shouldNotIndicateAnyPlayersWhenNoCharacters()
    {
        //given
        final List<Character> characters = Collections.emptyList();
        //when
        final boolean anyPlayer = gameUtils.isThereAnyPlayer(characters);
        //then
        Assert.assertFalse(anyPlayer);
    }

    @Test
    public void shouldIndicatePlayerWithoutLambda()
    {
        //given
        final List<Character> characters = getCharacters();
        //when
        final boolean anyPlayer = gameUtils.isThereAnyPlayerWithoutLambda(characters);
        //then
        Assert.assertTrue(anyPlayer);
    }

    @Test
    public void shouldIndicatePlayer()
    {
        //given
        final List<Character> characters = getCharacters();
        //when
        final boolean anyPlayer = gameUtils.isThereAnyPlayer(characters);
        //then
        Assert.assertTrue(anyPlayer);
    }

    @Test
    public void shouldFailCheckingIsThereNoEnemiesWithoutLambdaWhenNullPassed()
    {
        //expected
        expectedException.expect(NullPointerException.class);
        //when
        gameUtils.isThereNoEnemiesWithoutLambda(null);
    }

    @Test
    public void shouldFailCheckingIsThereNoEnemiesWhenNullPassed()
    {
        //expected
        expectedException.expect(NullPointerException.class);
        //when
        gameUtils.isThereNoEnemies(null);
    }

    @Test
    public void shouldNotIndicateAnyEnemiesWithoutLambdaWhenNoCharacters()
    {
        //given
        final List<Character> characters = Collections.emptyList();
        //when
        final boolean noEnemies = gameUtils.isThereNoEnemiesWithoutLambda(characters);
        //then
        Assert.assertTrue(noEnemies);
    }

    @Test
    public void shouldNotIndicateAnyEnemiesWhenNoCharacters()
    {
        //given
        final List<Character> characters = Collections.emptyList();
        //when
        final boolean noEnemies = gameUtils.isThereNoEnemies(characters);
        //then
        Assert.assertTrue(noEnemies);
    }

    @Test
    public void shouldIndicateEnemiesWithoutLambda()
    {
        //given
        final List<Character> characters = getCharacters();
        //when
        final boolean noEnemies = gameUtils.isThereNoEnemiesWithoutLambda(characters);
        //then
        Assert.assertFalse(noEnemies);
    }

    @Test
    public void shouldIndicateEnemies()
    {
        //given
        final List<Character> characters = getCharacters();
        //when
        final boolean noEnemies = gameUtils.isThereNoEnemies(characters);
        //then
        Assert.assertFalse(noEnemies);
    }

    @Test
    public void shouldFailTransformToNamedPlayersWithoutLambdaWithNullArgument()
    {
        //expected
        expectedException.expect(NullPointerException.class);
        //when
        gameUtils.transformToNamedPlayersWithoutLambda(null);
    }

    @Test
    public void shouldFailTransformToNamedPlayersWithNullArgument()
    {
        //expected
        expectedException.expect(NullPointerException.class);
        //when
        gameUtils.transformToNamedPlayers(null);
    }

    @Test
    public void shouldNotFailTransformToNamedPlayersWithoutLambdaWithEmptyArgument()
    {
        //given
        final List<Character> characters = Collections.emptyList();
        //when
        final Map<String, Player> namedPlayers = gameUtils.transformToNamedPlayersWithoutLambda(characters);
        //then
        Assert.assertTrue(namedPlayers.isEmpty());
    }

    @Test
    public void shouldNotFailTransformToNamedPlayersWithEmptyArgument()
    {
        //given
        final List<Character> characters = Collections.emptyList();
        //when
        final Map<String, Player> namedPlayers = gameUtils.transformToNamedPlayers(characters);
        //then
        Assert.assertTrue(namedPlayers.isEmpty());
    }

    @Test
    public void shouldTransformToNamedPlayersWithoutLambda()
    {
        //given
        final List<Character> characters = getCharacters();
        //when
        final Map<String, Player> namedPlayers = gameUtils.transformToNamedPlayersWithoutLambda(characters);
        //then
        Assert.assertEquals(namedPlayers.size(), 1);
        Assert.assertEquals(namedPlayers.get(PLAYER_NAME), PLAYER); // same as
    }

    @Test
    public void shouldTransformToNamedPlayers()
    {
        //given
        final List<Character> characters = getCharacters();
        //when
        final Map<String, Player> namedPlayers = gameUtils.transformToNamedPlayers(characters);
        //then
        Assert.assertEquals(namedPlayers.size(), 1);
        Assert.assertEquals(namedPlayers.get(PLAYER_NAME), PLAYER); // same as
    }

    @Test
    public void shouldFailGettingAverageEnemyPowerWithoutLambdaWithNullArgument()
    {
        //expected
        expectedException.expect(NullPointerException.class);
        //when
        gameUtils.getAverageEnemyPowerWithoutLambda(null);
    }

    @Test
    public void shouldFailGettingAverageEnemyPowerWithNullArgument()
    {
        //expected
        expectedException.expect(NullPointerException.class);
        //when
        gameUtils.getAverageEnemyPower(null);
    }

    @Test
    public void shouldNotFailGettingAverageEnemyPowerWithoutLambdaWithEmptyArgument()
    {
        //given
        final List<Character> characters = Collections.emptyList();
        //when
        final double averageEnemyPower = gameUtils.getAverageEnemyPowerWithoutLambda(characters);
        //then
        Assert.assertEquals(averageEnemyPower, 0, 0);
    }

    @Test
    public void shouldNotFailGettingAverageEnemyPowerWithEmptyArgument()
    {
        //given
        final List<Character> characters = Collections.emptyList();
        //when
        final double averageEnemyPower = gameUtils.getAverageEnemyPower(characters);
        //then
        Assert.assertEquals(averageEnemyPower, 0, 0);
    }

    @Test
    public void shouldGetAverageEnemyPowerWithoutLambda()
    {
        //given
        final List<Character> characters = getCharacters();
        //when
        final double averageEnemyPower = gameUtils.getAverageEnemyPowerWithoutLambda(characters);
        //then
        Assert.assertEquals(averageEnemyPower, 1.5, .1); // strict math, just for sake
    }

    @Test
    public void shouldGetAverageEnemyPower()
    {
        //given
        final List<Character> characters = getCharacters();
        //when
        final double averageEnemyPower = gameUtils.getAverageEnemyPower(characters);
        //then
        Assert.assertEquals(averageEnemyPower, 1.5, .1); // strict math, just for sake
    }

    private List<Character> getCharacters()
    {
        return Arrays.asList(ENEMY_1, PLAYER, ENEMY_2);
    }
}