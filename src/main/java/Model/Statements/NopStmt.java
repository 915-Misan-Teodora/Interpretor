package Model.Statements;

import Exceptions.MyException;
import Model.ADT.IDict;
import Model.PrgState;
import Model.Types.IType;

public class NopStmt implements IStmt{
    @Override
    public PrgState execute(PrgState state) throws MyException {
        return null;
    }

    @Override
    public String toString(){
        return "nop";
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws MyException {
        return typeEnv;
    }
}
