package model;

import exceptions.MyException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public interface MyIDictionary<T1,T2> {
    boolean isDefined(T1 key);
    void add(T1 key, T2 value);
    T2 lookUp(T1 key) throws MyException;
    void update(T1 key, T2 value) throws MyException;
    Collection<T2> value();
    void remove(T1 key) throws MyException;
    Set<T1> keySet();
    HashMap<T1, T2> getContent();
    MyIDictionary<T1, T2> deepCopy() throws MyException;
}