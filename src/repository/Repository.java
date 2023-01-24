package repository;

import exceptions.MyException;
import model.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{
    private List<PrgState> prgState;
    private final String logFilePath;

    public Repository(PrgState prgState, String logFilePath){
        this.logFilePath = logFilePath;
        this.prgState = new ArrayList<>();
        this.addProgram(prgState);
    }

    @Override
    public List<PrgState> getProgramList() {
        return this.prgState;
    }

    @Override
    public void setPrgState(List<PrgState> prgState) {
        this.prgState = prgState;
    }

    @Override
    public void addProgram(PrgState program) {
        this.prgState.add(program);
    }

    @Override
    public void logPrgStateExec(PrgState prgState) throws IOException, MyException {
        PrintWriter logFile;
        logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        logFile.println(prgState.prgStateToString());
        logFile.close();
    }

    @Override
    public void emptyLogFile() throws IOException {
        PrintWriter logFile;
        logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, false)));
        logFile.close();
    }

}
