package Model.Expressions;

import Exceptions.MyException;
import Model.ADT.IDict;
import Model.ADT.IHeap;
import Model.Types.IType;
import Model.Values.BoolValue;
import Model.Values.IValue;
import Model.Values.IntValue;

public class NotExp implements Exp{
    private Exp exp;

    public NotExp(Exp exp) {
        this.exp = exp;
    }

    @Override
    public IValue eval(IDict<String, IValue> tbl, IHeap<Integer, IValue> heap) throws MyException {
        IValue x = this.exp.eval(tbl, heap);
        boolean elem =((BoolValue) x).getVal();
        if(elem)
            return new BoolValue(false);
        return new BoolValue(true);
    }

    @Override
    public IType typecheck(IDict<String, IType> typeEnv) throws MyException {
        return null;
    }
}
