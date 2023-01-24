package model;

import exceptions.MyException;
import model.Exp;
import model.PrgState;
import model.MyIStack;
import model.BoolValue;
import model.Value;

public class IfStmt implements IStmt
{
    Exp exp;
    IStmt thenS;
    IStmt elseS;

    public IfStmt(Exp e, IStmt t, IStmt el) {
        this.exp = e;
        this.thenS = t;
        this.elseS = el;
    }

    public String toString() {
        return String.format("(IF(" + exp.toString() + ") THEN(" + thenS.toString() + ")ELSE(" + elseS.toString() + "))");
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        //moi
        Value result = this.exp.eval(state.getSymTable(), state.getHeap());
        if (result instanceof BoolValue boolResult) {
            IStmt statement;
            if (boolResult.getValue()) {
                statement = thenS;
            } else {
                statement = elseS;
            }

            MyIStack<IStmt> stack = state.getExeStack();
            stack.push(statement);
            state.setExeStack(stack);
            //fin moi
            return state;
        }
        //moi
        else {
            throw new MyException("Please provide a boolean expression in an if statement.");
        }
        //fin moi
    }

    @Override
    public IStmt deepCopy()
    {
        return new IfStmt(exp.deepCopy(), thenS.deepCopy(), elseS.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeExpr = exp.typeCheck(typeEnv);
        if (typeExpr.equals(new BoolType())) {
            thenS.typeCheck(typeEnv.deepCopy());
            elseS.typeCheck(typeEnv.deepCopy());
            return typeEnv;
        } else
            throw new MyException("The condition of IF does not have the type Bool.");
    }
}

