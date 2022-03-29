package Repository;

import Exceptions.MyException;

import Model.PrgState;

import java.io.IOException;
import java.util.List;

public interface IRepo {
    void add(PrgState prg) throws MyException;
    void logPrgStateExec(PrgState prgState) throws MyException, IOException;
    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> list);
    PrgState getPrgWithId(int id);
}
