package com.roundbytes.sandbox.beans;

public class Position
{
    public static final Position DEFAULT = new Position(0, 0);

    private final int x;

    private final int y;

    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public boolean isDefault()
    {
        return DEFAULT.equals(this);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        if (y != position.y) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString()
    {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}