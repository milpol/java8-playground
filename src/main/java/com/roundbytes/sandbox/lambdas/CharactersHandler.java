package com.roundbytes.sandbox.lambdas;

import com.roundbytes.sandbox.beans.Character;
import com.roundbytes.sandbox.beans.Position;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.Random;

import static com.roundbytes.sandbox.lambdas.CharactersActionsExecutor.executeChallenge;
import static com.roundbytes.sandbox.lambdas.CharactersActionsExecutor.executeCheck;

public class CharactersHandler
{
    public boolean isCollision(Character character, Character otherCharacter)
    {
        checkInputParameters(character, otherCharacter);

        return executeCheck(character, otherCharacter, (ch1, ch2) -> {
            final Optional<Position> position = character.getPosition();
            final Optional<Position> otherPosition = otherCharacter.getPosition();
            boolean collision = false;
            if (position.isPresent() && otherPosition.isPresent()) {
                final int size = Collections.min(Arrays.asList(ch1.getSize(), ch2.getSize()));
                collision = Math.abs(position.get().getX() - otherPosition.get().getX()) < size &&
                        Math.abs(position.get().getY() - otherPosition.get().getY()) < size;
            }
            return collision;
        });
    }

    public Character getFightWinner(Character character, Character otherCharacter)
    {
        checkInputParameters(character, otherCharacter);

        return executeChallenge(character, otherCharacter, (ch1, ch2) -> {
            final Random random = new Random();
            int ch1Power = random.nextInt(10) + ch1.getPower();
            int ch2Power = random.nextInt(10) + ch2.getPower();
            if (ch1Power > ch2Power) {
                return ch1;
            } else {
                return ch2;
            }
        });
    }

    private void checkInputParameters(Character character, Character otherCharacter)
    {
        if (character == null || otherCharacter == null) {
            throw new IllegalArgumentException("Character cannot be null.");
        }
    }
}