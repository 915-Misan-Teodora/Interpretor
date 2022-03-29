package Model.Expressions;

import Exceptions.MyException;
import Model.ADT.IDict;
import Model.ADT.IHeap;
import Model.Types.IType;
import Model.Values.IValue;


public class ValueExp implements Exp{
    IValue nmb;

    public ValueExp(IValue n) { nmb = n; }

    @Override
    public IValue eval(IDict<String, IValue> tbl, IHeap<Integer, IValue> heap) throws MyException {
        return this.nmb;
    }

    @Override
    public String toString(){
        return String.format("%s", nmb.toString());
    }

    @Override
    public IType typecheck(IDict<String, IType> typeEnv) throws MyException {
        return nmb.getType();
    }

}
