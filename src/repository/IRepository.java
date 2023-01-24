package repository;

import exceptions.MyException;
import model.PrgState;

import java.io.IOException;
import java.util.List;

public interface IRepository {
    List<PrgState> getProgramList();
    void setPrgState(List<PrgState> prgState);
    void addProgram(PrgState program);
    void logPrgStateExec(PrgState prgState) throws IOException, MyException;
    void emptyLogFile() throws IOException;
}