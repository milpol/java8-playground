package com.roundbytes.sandbox.beans;

import java.util.Optional;

public class Enemy implements Character
{
    private Position position;

    private final int size;

    private final int power;

    public Enemy(int size, int power)
    {
        this.size = size;
        this.power = power;
    }

    @Override
    public void setPosition(Position position)
    {
        this.position = position;
    }

    @Override
    public String getName()
    {
        return "Enemy";
    }

    @Override
    public Optional<Position> getPosition()
    {
        return Optional.ofNullable(position);
    }

    @Override
    public int getSize()
    {
        return size;
    }

    @Override
    public int getPower()
    {
        return power;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Enemy enemy = (Enemy) o;

        if (power != enemy.power) return false;
        if (size != enemy.size) return false;
        if (position != null ? !position.equals(enemy.position) : enemy.position != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = position != null ? position.hashCode() : 0;
        result = 31 * result + size;
        result = 31 * result + power;
        return result;
    }

    @Override
    public String toString()
    {
        return "Enemy{" +
                "position=" + position +
                ", size=" + size +
                ", power=" + power +
                '}';
    }
}
