package model;

import exceptions.MyException;

import java.util.List;
import java.util.function.Consumer;

public interface MyIList<T>
{
    void add(T elem);
    T pop() throws MyException;
    boolean isEmpty();
    List<T> getList();
}