package model;
import exceptions.MyException;
import model.IntType;
import model.MyIDictionary;
import model.IntValue;
import model.Value;
import model.MyIHeap;

public class ArithExp implements Exp
{
    Exp e1;
    Exp e2;
    int op; //1-plus, 2-minus, 3-star, 4-divide

    //ma parties
    public ArithExp(int op, Exp e1, Exp e2)
    {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }
    //fin de parties

    @Override
    public Value eval(MyIDictionary<String, Value> symTable, MyIHeap heap) throws MyException {
        Value value1, value2;
        value1 = this.e1.eval(symTable, heap);
        if (value1.getType().equals(new IntType())) {
            value2 = this.e2.eval(symTable, heap);
            if (value2.getType().equals(new IntType())) {
                IntValue int1 = (IntValue) value1;
                IntValue int2 = (IntValue) value2;
                int n1, n2;
                n1 = int1.getValue();
                n2 = int2.getValue();
                if (this.op == '+')
                    return new IntValue(n1 + n2);
                else if (this.op == '-')
                    return new IntValue(n1 - n2);
                else if (this.op == '*')
                    return new IntValue(n1 * n2);
                else if (this.op == '/')
                    if (n2 == 0)
                        throw new MyException("Division by zero.");
                    else
                        return new IntValue(n1 / n2);
            } else
                throw new MyException("Second operand is not an integer.");
        } else
            throw new MyException("First operand is not an integer.");
        return null;
    }


    @Override
    public Exp deepCopy() {
        return new ArithExp(op, e1.deepCopy(), e2.deepCopy());
    }

    @Override
    public String toString() {
        return e1.toString() + " " + op + " " + e2.toString();
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type1, type2;
        type1 = e1.typeCheck(typeEnv);
        type2 = e2.typeCheck(typeEnv);
        if (type1.equals(new IntType())) {
            if (type2.equals(new IntType())) {
                return new IntType();
            } else
                throw new MyException("Second operand is not an integer.");
        } else
            throw new MyException("First operand is not an integer.");
    }
}
