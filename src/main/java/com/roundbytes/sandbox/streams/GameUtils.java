package com.roundbytes.sandbox.streams;

import com.roundbytes.sandbox.beans.Character;
import com.roundbytes.sandbox.beans.Enemy;
import com.roundbytes.sandbox.beans.Player;

import java.util.Collection;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class GameUtils
{
    public int sumCharacterTypePowerWithoutLambda(Collection<Character> characters, Class<? extends Character> characterType)
    {
        return characters.stream()
                .filter(new Predicate<Character>()
                {
                    @Override
                    public boolean test(Character character)
                    {
                        return character.getClass().isAssignableFrom(characterType);
                    }
                })
                .mapToInt(new ToIntFunction<Character>()
                {
                    @Override
                    public int applyAsInt(Character character)
                    {
                        return character.getPower();
                    }
                })
                .sum();

    }

    public int sumCharacterTypePower(Collection<Character> characters, Class<? extends Character> characterType)
    {
        return characters.stream()
                .filter(character -> character.getClass().isAssignableFrom(characterType))
                .mapToInt(character -> character.getPower())
                .sum();
    }

    public Enemy createEnemyBossWithoutLambda(Collection<Character> characters)
    {
        return characters.stream()
                .filter(new Predicate<Character>()
                {
                    @Override
                    public boolean test(Character character)
                    {
                        return character instanceof Enemy;
                    }
                })
                .map(new Function<Character, Enemy>()
                {
                    @Override
                    public Enemy apply(Character character)
                    {
                        return (Enemy) character;
                    }
                })
                .reduce(new Enemy(1, 1), new BinaryOperator<Enemy>()
                {
                    @Override
                    public Enemy apply(Enemy baseCharacter, Enemy nextCharacter)
                    {
                        return new Enemy(baseCharacter.getSize() + nextCharacter.getSize(),
                                baseCharacter.getPower() + nextCharacter.getPower());
                    }
                });
    }

    public Enemy createEnemyBoss(Collection<Character> characters)
    {
        return characters.stream()
                .filter(character -> character instanceof Enemy)
                .map(character -> (Enemy) character)
                .reduce(new Enemy(1, 1), (baseCharacter, nextCharacter) -> new Enemy(
                        baseCharacter.getSize() + nextCharacter.getSize(),
                        baseCharacter.getPower() + nextCharacter.getPower()));
    }

    public long getNumberOfPlayersWithoutLambda(Collection<Character> characters)
    {
        return characters.stream()
                .filter(new Predicate<Character>()
                {
                    @Override
                    public boolean test(Character character)
                    {
                        return character instanceof Player;
                    }
                }).count();

    }

    public long getNumberOfPlayers(Collection<Character> characters)
    {
        return characters.stream()
                .filter(character -> character instanceof Player)
                .count();

    }

    public boolean isThereAnyPlayerWithoutLambda(Collection<Character> characters)
    {
        return characters.stream()
                .anyMatch(new Predicate<Character>()
                {
                    @Override
                    public boolean test(Character character)
                    {
                        return character instanceof Player;
                    }
                });
    }

    public boolean isThereAnyPlayer(Collection<Character> characters)
    {
        return characters.stream()
                .anyMatch(character -> character instanceof Player);
    }

    public boolean isThereNoEnemiesWithoutLambda(Collection<Character> characters)
    {
        return characters.stream()
                .noneMatch(new Predicate<Character>()
                {
                    @Override
                    public boolean test(Character character)
                    {
                        return character instanceof Enemy;
                    }
                });
    }

    public boolean isThereNoEnemies(Collection<Character> characters)
    {
        return characters.stream()
                .noneMatch(character -> character instanceof Enemy);
    }

    public Map<String, Player> transformToNamedPlayersWithoutLambda(Collection<Character> characters)
    {
        return characters.stream()
                .filter(new Predicate<Character>()
                {
                    @Override
                    public boolean test(Character character)
                    {
                        return character instanceof Player;
                    }
                }).collect(Collectors.toMap(new Function<Character, String>()
                {
                    @Override
                    public String apply(Character character)
                    {
                        return character.getName();
                    }
                }, new Function<Character, Player>()
                {
                    @Override
                    public Player apply(Character character)
                    {
                        return (Player) character;
                    }
                }));
    }

    public Map<String, Player> transformToNamedPlayers(Collection<Character> characters)
    {
        return characters.stream()
                .filter(character -> character instanceof Player)
                .collect(Collectors.toMap(Character::getName, character -> (Player) character));
    }

    public double getAverageEnemyPowerWithoutLambda(Collection<Character> characters)
    {
        return characters.stream().filter(new Predicate<Character>()
        {
            @Override
            public boolean test(Character character)
            {
                return character instanceof Enemy;
            }
        }).collect(Collectors.averagingInt(new ToIntFunction<Character>()
        {
            @Override
            public int applyAsInt(Character character)
            {
                return character.getPower();
            }
        }));
    }

    public double getAverageEnemyPower(Collection<Character> characters)
    {
        return characters.stream()
                .filter(character -> character instanceof Enemy)
                .collect(Collectors.averagingInt(character -> character.getPower()));
    }
}