package Model.Expressions;

import Exceptions.MyException;
import Model.ADT.IDict;
import Model.ADT.IHeap;
import Model.Types.IType;
import Model.Values.IValue;



public interface Exp {
    public IValue eval(IDict<String, IValue> tbl, IHeap<Integer, IValue> heap) throws MyException;
    public String toString();
    IType typecheck(IDict<String, IType> typeEnv) throws MyException;
}
