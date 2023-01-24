package model;

import exceptions.MyException;
import model.MyIDictionary;
import model.Value;


public interface Exp
{
    Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException;
    Value eval(MyIDictionary<String, Value> symTable, MyIHeap heap) throws MyException;
    Exp deepCopy();
}
