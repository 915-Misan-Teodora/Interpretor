package Repository;

import Exceptions.MyException;
//import Model.ADT.List;
import Model.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repo implements IRepo{

    List<PrgState> listOfPrgStates;
    String logFilePath; // the path read from the console with Scanner
    private boolean firstTime;
    public String getLogFilePath() {
        return logFilePath;
    }

    public Repo(String fileName){
        listOfPrgStates = new ArrayList<>();
        logFilePath = fileName;
    }

    @Override
    public void add(PrgState prg) throws MyException{
        listOfPrgStates.add(prg);
    }

    // it should be changed with a parameter inside the function (PrgState)
    @Override
    public void logPrgStateExec(PrgState prgState) throws MyException, IOException {
        PrintWriter logFile;
        try {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        } catch (IOException e) {
            throw new MyException("Can not write in the logFile!");
        }
        logFile.write(prgState.toString());
        logFile.close();
    }

    @Override
    public List<PrgState> getPrgList() {
        return listOfPrgStates;
    }

    @Override
    public void setPrgList(List<PrgState> list) {
        listOfPrgStates = list;
    }

    @Override
    public PrgState getPrgWithId(int id) {
        for(PrgState prg : listOfPrgStates){
            if(prg.getId() == id)
                return prg;
        }
        return null;
    }
}

