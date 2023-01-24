package model;

import exceptions.MyException;
import model.PrgState;
import model.Type;
import model.MyIDictionary;
import model.Value;

public class VarDecIStmt implements IStmt
{
    String name;
    Type typ;
    //moi
    public VarDecIStmt(String name, Type typ)
    {
        this.name = name;
        this.typ = typ;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException
    {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        if (symTable.isDefined(name)) {
            throw new MyException("Variable " + name + " already exists in the symTable.");
        }
        symTable.add(name, typ.defaultValue());
        state.setSymTable(symTable);
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new VarDecIStmt(name, typ);
    }

    @Override
    public String toString() {
        return String.format("%s %s", typ.toString(), name);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        typeEnv.add(name, typ);
        return typeEnv;
    }
    //fin moi


}
