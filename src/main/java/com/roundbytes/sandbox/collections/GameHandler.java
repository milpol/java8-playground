package com.roundbytes.sandbox.collections;

import com.roundbytes.sandbox.beans.Character;
import com.roundbytes.sandbox.beans.Position;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class GameHandler
{
    public void printAllCharactersWithoutLambda(Collection<Character> characters)
    {
        characters.forEach(new Consumer<Character>()
        {
            @Override
            public void accept(Character character)
            {
                System.out.print(character.getName());
            }
        });
    }

    public void printAllCharacters(Collection<Character> characters)
    {
        characters.forEach(character -> System.out.print(character.getName()));
    }

    public void resetPositionExceptFirstCharacterWithoutLambda(Collection<Character> characters)
    {
        final Iterator<Character> charactersIterator = characters.iterator();
        charactersIterator.next();
        charactersIterator.forEachRemaining(new Consumer<Character>()
        {
            @Override
            public void accept(Character character)
            {
                character.setPosition(Position.DEFAULT);
            }
        });
    }

    public void resetPositionExceptFirstCharacter(Collection<Character> characters)
    {
        final Iterator<Character> charactersIterator = characters.iterator();
        charactersIterator.next();
        charactersIterator.forEachRemaining(character -> character.setPosition(Position.DEFAULT));
    }

    public boolean removeCharactersWithUnsetPositionWithoutLambda(Collection<Character> characters)
    {
        return characters.removeIf(new Predicate<Character>()
        {
            @Override
            public boolean test(Character character)
            {
                return !character.getPosition().isPresent();
            }
        });
    }

    public boolean removeCharactersWithUnsetPosition(Collection<Character> characters)
    {
        return characters.removeIf(character -> !character.getPosition().isPresent());
    }
}