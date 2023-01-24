package model;

import exceptions.MyException;
import model.PrgState;
import model.MyIStack;


public class CompStmt implements IStmt
{
    IStmt first;
    IStmt snd;
    //moi
    public CompStmt(IStmt first, IStmt snd)
    {
        this.first = first;
        this.snd = snd;
    }
    //fin moi

    @Override
    public String toString() {
        return String.format("(%s|%s)", first.toString(), snd.toString());
    }

    @Override
    public PrgState execute(PrgState state)
    {
        MyIStack<IStmt> stack = state.getExeStack();
        stack.push(snd);
        stack.push(first);
        state.setExeStack(stack);
        return state;
    }
    @Override
    public IStmt deepCopy()
    {
        return new CompStmt(first.deepCopy(), snd.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return snd.typeCheck(first.typeCheck(typeEnv));
    }
}
