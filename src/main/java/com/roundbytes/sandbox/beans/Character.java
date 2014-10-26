package com.roundbytes.sandbox.beans;

import java.util.Optional;

public interface Character
{
    public String getName();

    public Optional<Position> getPosition();

    public void setPosition(Position position);

    public int getSize();

    public int getPower();
}