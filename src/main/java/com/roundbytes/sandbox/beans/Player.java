package com.roundbytes.sandbox.beans;

import java.util.Optional;

public class Player implements Character, Controllable
{
    private Position position;

    private final String name;

    private final int size;

    private final int power;

    public Player(String name, int size, int power)
    {
        this.name = name;
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
        return name;
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

        Player player = (Player) o;

        if (power != player.power) return false;
        if (size != player.size) return false;
        if (name != null ? !name.equals(player.name) : player.name != null) return false;
        if (position != null ? !position.equals(player.position) : player.position != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = position != null ? position.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + size;
        result = 31 * result + power;
        return result;
    }

    @Override
    public String toString()
    {
        return "Player{" +
                "position=" + position +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", power=" + power +
                '}';
    }
}