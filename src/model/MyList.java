package model;

import exceptions.MyException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MyList<T> implements MyIList<T>{
    List<T> list;

    public MyList() {
        this.list = new ArrayList<>();
    }

    @Override
    public void add(T elem) {
        this.list.add(elem);
    }

    @Override
    public T pop() throws MyException{
        if (list.isEmpty())
            throw new MyException("The list is empty!");
        return this.list.remove(0);
    }

    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override
    public List<T> getList() {
        return list;
    }

    @Override
    public String toString() {
        return this.list.toString();
    }
}
