package com.roundbytes.sandbox.defaultmethods;

import com.roundbytes.sandbox.beans.Character;
import com.roundbytes.sandbox.beans.Controllable;
import com.roundbytes.sandbox.beans.Position;

import java.util.Optional;

public interface Processor<T extends Character>
{
    void process(T character);

    /**
     * it can't be static, as we rely on interface Type
     * it can't be private/protected
     * public modifier can be omitted
     */
    default boolean isControllable(T t)
    {
        return t != null && t instanceof Controllable;
    }

    /**
     * since non static we can call interface methods
     */
    default void tryProcessControllable(T t)
    {
        if (isControllable(t)) {
            process(t);
        } else {
            throw new IllegalArgumentException("Given character is not controllable.");
        }
    }

    /**
     * static, no references to interface itself
     * it can't be private/protected
     * public modifier can be omitted
     */
    static Position getPosition(Character character)
    {
        if (character == null) {
            throw new IllegalArgumentException("Character cannot be null.");
        }
        final Optional<Position> position = character.getPosition();
        return position.orElse(Position.DEFAULT);
    }

    /**
     * overriding object method is prohibited
     * @see http://mail.openjdk.java.net/pipermail/lambda-dev/2013-March/008435.html
     */
//    default String toString()
//    default int hashCode()
//    default boolean equals(Object other)
}