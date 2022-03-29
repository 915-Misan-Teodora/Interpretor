package Model.Statements;

import Exceptions.MyException;
import Model.ADT.IDict;
import Model.PrgState;
import Model.Types.IType;
import Model.Values.IValue;

public class VarDeclStmt implements IStmt{
    String name;
    IType type;

    public VarDeclStmt(String n, IType t){
        name = n;
        type = t;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IDict<String, IValue> tbl = state.getSymTable();
        if(tbl.isDefined(name)){
            throw new MyException("Variable is already declared");
        }
        tbl.add(name, type.defaultValue());
        return null;
    }

    @Override
    public String toString(){
        return String.format("%s %s", type.toString(), name);
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws MyException {
        typeEnv.add(name,type);
        return typeEnv;
    }
}
