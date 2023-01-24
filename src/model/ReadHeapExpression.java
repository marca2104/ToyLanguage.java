package model;

import exceptions.MyException;
import model.MyIDictionary;
import model.MyIHeap;
import model.RefValue;
import model.Value;

public class ReadHeapExpression implements Exp{
    private final Exp expression;

    public ReadHeapExpression(Exp expression) {
        this.expression = expression;
    }
    @Override
    public Value eval(MyIDictionary<String, Value> symTable, MyIHeap heap) throws MyException {
        Value value = expression.eval(symTable, heap);
        if (!(value instanceof RefValue))
            throw new MyException(String.format("%s not of RefType", value));
        RefValue refValue = (RefValue) value;
        return heap.get(refValue.getAddress());
    }

    @Override
    public Exp deepCopy() {
        return new ReadHeapExpression(expression.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("ReadHeap(%s)", expression);
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type = expression.typeCheck(typeEnv);
        if (type instanceof RefType refType) {
            return refType.getInner();
        } else
            throw new MyException("The rH argument is not a RefType.");
    }
}
