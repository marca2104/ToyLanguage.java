package model;

import exceptions.MyException;
import model.BoolType;
import model.MyIDictionary;
import model.BoolValue;
import model.Value;

import java.util.Objects;

public class LogicExp implements Exp
{
    Exp e1;
    Exp e2;
    int op;
    //moi
    public LogicExp(int op , Exp e1, Exp e2)
    {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }
    //fin moi
    public Value eval(MyIDictionary<String,Value> symTable, MyIHeap heap) throws MyException
    {
        //moi
        Value value1, value2;
        value1 = this.e1.eval(symTable, heap);
        if (value1.getType().equals(new BoolType())) {
            value2 = this.e2.eval(symTable, heap);
            if (value2.getType().equals(new BoolType())) {
                BoolValue bool1 = (BoolValue) value1;
                BoolValue bool2 = (BoolValue) value2;
                boolean b1, b2;
                b1 = bool1.getValue();
                b2 = bool2.getValue();
                if (Objects.equals(this.op, "and")) {
                    return new BoolValue(b1 && b2);
                } else if (Objects.equals(this.op, "or")) {
                    return new BoolValue(b1 || b2);
                }
            } else
            {
                throw new MyException("Second operand is not a boolean.");
            }
        }
        else
            throw new MyException("First operand is not a boolean.");
        return null;
    }


    //fin moi
    //moi
    public Exp deepCopy() {
        return new LogicExp(op, e1.deepCopy(), e2.deepCopy());
    }
    @Override
    public String toString() {
        return e1.toString() + " " + op + " " + e2.toString();
    }
    //fin moi

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException{
        Type type1, type2;
        type1 = e1.typeCheck(typeEnv);
        type2 = e2.typeCheck(typeEnv);
        if (type1.equals(new BoolType())) {
            if (type2.equals(new BoolType())) {
                return new BoolType();
            } else
                throw new MyException("Second operand is not a boolean.");
        } else
            throw new MyException("First operand is not a boolean.");

    }
}
