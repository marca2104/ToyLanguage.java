package model;

import exceptions.MyException;
import model.MyIDictionary;
import model.PrgState;

public interface IStmt
{
    //moi
    PrgState execute(PrgState state) throws MyException;
    MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException;
    IStmt deepCopy();

}
