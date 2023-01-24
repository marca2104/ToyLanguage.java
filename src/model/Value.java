package model;

import model.Type;

public interface Value
{
    Type getType();
    Value deepCopy();
}

