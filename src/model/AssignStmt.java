package model;

import exceptions.MyException;

public class AssignStmt implements IStmt
{
    String key;
    Exp exp;
    //moi
    public AssignStmt(String key, Exp exp)
    {
        this.key = key;
        this.exp = exp;
    }
    //fin moi

    @Override
    public String toString() {
        return String.format("%s = %s", key, exp.toString());
    }
    public PrgState execute(PrgState state) throws MyException
    {
        MyIDictionary<String, Value> symbolTable = state.getSymTable();

        if (symbolTable.isDefined(key)) {
            Value value = exp.eval(symbolTable, state.getHeap());
            Type typeId = (symbolTable.lookUp(key)).getType();
            if (value.getType().equals(typeId))
            {
                symbolTable.update(key, value);
            } else {
                throw new MyException("Declared type of variable " + key + " and type of the assigned expression do not match.");
            }
        } else {
            throw new MyException("The used variable " + key + " was not declared before.");
        }
        state.setSymTable(symbolTable);
        return state;

    }
    //moi
    @Override
    public IStmt deepCopy()
    {
        return new AssignStmt(key, exp.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.lookUp(key);
        Type typeExpr = exp.typeCheck(typeEnv);
        if (typeVar.equals(typeExpr))
            return typeEnv;
        else
            throw new MyException("Assignment: right hand side and left hand side have different types.");
    }

    //fin moi

}

