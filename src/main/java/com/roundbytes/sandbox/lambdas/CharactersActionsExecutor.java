package com.roundbytes.sandbox.lambdas;

import com.roundbytes.sandbox.beans.Character;

import java.util.function.BiFunction;

/**
 * I am aware of the peculiarities of this example
 * It is used to show execution of lambda expression
 */
public interface CharactersActionsExecutor
{
    static boolean executeCheck(
            Character character,
            Character otherCharacter,
            BiFunction<Character, Character, Boolean> function)
    {
        return function.apply(character, otherCharacter);
    }

    static Character executeChallenge(
            Character character,
            Character otherCharacter,
            BiFunction<Character, Character, Character> function)
    {
        return function.apply(character, otherCharacter);
    }
}