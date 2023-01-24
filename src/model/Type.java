package model;

import model.Value;

public interface Type
{
    boolean equals(Type anotherType);
    Value defaultValue();
    Type deepCopy();
}

