package model;

import exceptions.MyException;
import model.Exp;
import model.PrgState;
import model.StringType;
import model.MyIDictionary;
import model.StringValue;
import model.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenReadFile implements IStmt {
    private final Exp expression;

    public OpenReadFile(Exp expression) {
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        Value value = expression.eval(state.getSymTable(), state.getHeap());
        if (!value.getType().equals(new StringType())) {
            throw new MyException(String.format("%s does not evaluate to StringType", expression.toString()));
        }
        StringValue fileName = (StringValue) value;
        MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
        if (!fileTable.isDefined(fileName.getValue())) {
            BufferedReader br;
            try {
                br = new BufferedReader(new FileReader(fileName.getValue()));
                fileTable.add(fileName.getValue(), br);
                state.setFileTable(fileTable);
            } catch (FileNotFoundException e) {
                throw new MyException(String.format("%s could not be opened", fileName.getValue()));
            }

        } else {
            throw new MyException(String.format("%s is already opened", fileName.getValue()));
        }
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new OpenReadFile(expression.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("OpenReadFile(%s)", expression.toString());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        if (expression.typeCheck(typeEnv).equals(new StringType()))
            return typeEnv;
        else
            throw new MyException("OpenReadFile requires a string expression.");
    }
}
