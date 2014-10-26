package com.roundbytes.sandbox.optionals;

import com.roundbytes.sandbox.beans.Player;
import com.roundbytes.sandbox.beans.Position;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class PlayerPositionHandler
{
    public static final Position NEXT_AVAILABLE_POSITION = new Position(1, 1);

    private final Player player;

    public PlayerPositionHandler(Player player)
    {
        this.player = player;
    }

    public Position getPositionOrDefault()
    {
        return player.getPosition().orElse(Position.DEFAULT);
    }

    public Position getPositionOrFailWithoutLambdaNotation()
    {
        return player.getPosition().orElseThrow(new Supplier<IllegalStateException>()
        {
            @Override
            public IllegalStateException get()
            {
                return new IllegalStateException("Expect position but not present.");
            }
        });
    }

    public Position getPositionOrFail()
    {
        return player.getPosition().orElseThrow(() -> new IllegalStateException("Expect position but not present."));
    }

    public Position getPositionOrNextAvailableWithoutLambdaNotation()
    {
        return player.getPosition().orElseGet(new Supplier<Position>()
        {
            @Override
            public Position get()
            {
                return NEXT_AVAILABLE_POSITION;
            }
        });
    }

    public Position getPositionOrNextAvailable()
    {
        return player.getPosition().orElseGet(() -> NEXT_AVAILABLE_POSITION);
    }

    public Optional<Position> getNotDefaultPositionWithoutLambdaNotation()
    {
        return player.getPosition().filter(new Predicate<Position>()
        {
            @Override
            public boolean test(Position position)
            {
                return !position.isDefault();
            }
        });
    }

    public Optional<Position> getNotDefaultPosition()
    {
        return player.getPosition().filter(position -> !position.isDefault());
    }

    public Optional<Position> getSwappedPositionWithoutLambdaNotation()
    {
        return player.getPosition().flatMap(new Function<Position, Optional<Position>>()
        {
            @Override
            public Optional<Position> apply(Position position)
            {
                return Optional.of(new Position(position.getY(), position.getX()));
            }
        });
    }

    /**
     * Thanks to map method there is no need to cover result with Optional after mapping
     */
    public Optional<Position> getSwappedPosition()
    {
        return player.getPosition().map(position -> new Position(position.getY(), position.getX()));
    }

    public void verifyPositionStateWithoutLambdaNotation()
    {
        player.getPosition().ifPresent(new Consumer<Position>()
        {
            @Override
            public void accept(Position position)
            {
                if (position.getX() < 0 || position.getY() < 0) {
                    throw new IllegalStateException("Illegal player position");
                }
            }
        });
    }

    public void verifyPositionState()
    {
        player.getPosition().ifPresent(position -> {
            if (position.getX() < 0 || position.getY() < 0) {
                throw new IllegalStateException("Illegal player position");
            }
        });
    }
}