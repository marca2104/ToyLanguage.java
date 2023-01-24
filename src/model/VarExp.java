package model;

import exceptions.MyException;
import model.MyIDictionary;
import model.Value;

public class VarExp implements Exp
{
    String key;

    public VarExp(String key) {
        this.key = key;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> symTable, MyIHeap heap) throws MyException
    {
        return symTable.lookUp(key);
    }

    @Override
    public Exp deepCopy() {
        return new VarExp(key);
    }

    @Override
    public String toString() {
        return key;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv.lookUp(key);
    }

}