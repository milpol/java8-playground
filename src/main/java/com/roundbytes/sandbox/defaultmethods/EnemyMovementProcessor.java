package com.roundbytes.sandbox.defaultmethods;

import com.roundbytes.sandbox.beans.Enemy;
import com.roundbytes.sandbox.beans.Position;

import java.util.Optional;

public class EnemyMovementProcessor implements Processor<Enemy>
{
    private final double angle;

    private final double speed;

    public EnemyMovementProcessor(double angle, double speed)
    {
        this.angle = angle;
        this.speed = speed;
    }

    @Override
    public void process(Enemy character)
    {
        Position newPosition;
        final Optional<Position> position = character.getPosition();
        if (position.isPresent()) {
            int newX = (int) (position.get().getX() + Math.cos(angle) * speed);
            int newY = (int) (position.get().getY() + Math.sin(angle) * speed);
            newPosition = new Position(newX, newY);
        } else {
            newPosition = Position.DEFAULT;
        }
        character.setPosition(newPosition);
    }
}