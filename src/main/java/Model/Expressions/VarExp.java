package Model.Expressions;

import Exceptions.MyException;
import Model.ADT.IDict;
import Model.ADT.IHeap;
import Model.Types.IType;
import Model.Values.IValue;
import Model.Values.IntValue;

public class VarExp implements Exp{
    String id;

    public VarExp(String id){
        this.id = id;
    }

    @Override
    public IValue eval(IDict<String, IValue> tbl, IHeap<Integer, IValue> heap) throws MyException {
        return tbl.lookup(id);
    }

    @Override
    public String toString() {
        return String.format("%s", id);
    }

    @Override
    public IType typecheck(IDict<String, IType> typeEnv) throws MyException {
        return typeEnv.lookup(id);
    }
}
