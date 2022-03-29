package Model.Statements;

import Exceptions.MyException;
import Model.ADT.IDict;
import Model.PrgState;
import Model.Types.IType;

public interface IStmt {
    public PrgState execute(PrgState state) throws MyException;
    public String toString();
    IDict<String, IType> typecheck(IDict<String,IType> typeEnv) throws MyException;
}
