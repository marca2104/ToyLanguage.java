package model;

import exceptions.MyException;
import model.Exp;
import model.PrgState;
import model.MyIList;
import model.Value;

public class PrintStmt implements IStmt
{
    Exp exp;

    //moi
    public PrintStmt(Exp exp)
    {
        this.exp = exp;
    }
    //fin moi

    @Override
    public String toString()
    {
      return String.format("print("+exp.toString()+")");
    }

    @Override
    public PrgState execute(PrgState state) throws MyException
    {
        MyIList<Value> out = state.getOut();
        out.add(exp.eval(state.getSymTable(), state.getHeap()));
        state.setOut(out);
        return state;
    }

    @Override
    public IStmt deepCopy()
    {
        return new PrintStmt(exp.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        exp.typeCheck(typeEnv);
        return typeEnv;
    }
}
