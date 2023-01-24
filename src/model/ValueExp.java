package model;

import exceptions.MyException;
import model.MyIDictionary;
import model.Value;
public class ValueExp implements Exp
{
    Value e;
    //moi
    public ValueExp(Value e)
    {
        this.e = e;
    }
    //fin moi

    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap heap) throws MyException
    {
        return this.e;
    }

    //moi
    @Override
    public Exp deepCopy()
    {
        return new ValueExp(e);
    }
    @Override
    public String toString()
    {
        return this.e.toString();
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return e.getType();
    }
    //fin moi

}
