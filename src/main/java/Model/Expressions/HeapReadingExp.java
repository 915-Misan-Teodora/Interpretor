package Model.Expressions;

import Exceptions.MyException;
import Model.ADT.IDict;
import Model.ADT.IHeap;
import Model.Types.IType;
import Model.Types.RefType;
import Model.Values.IValue;
import Model.Values.RefValue;

public class HeapReadingExp implements Exp{
    private Exp expression;

    @Override
    public IValue eval(IDict<String, IValue> symTable, IHeap<Integer, IValue> heap) throws MyException {
        IValue val = expression.eval(symTable, heap);
        if(val instanceof RefValue){
            RefValue refValue = (RefValue) val;
            int addr = refValue.getAddress();
            if(heap.contains(addr)){
                return heap.readAddress(addr);
            } throw new MyException("No variable at this address");
        } throw new MyException("The expression must be of type reference");
    }

    public HeapReadingExp(Exp expression) {
        this.expression = expression;
    }

    @Override
    public String toString(){
        return "rH(" + expression + ")";
    }

    @Override
    public IType typecheck(IDict<String, IType> typeEnv) throws MyException {
        IType type = expression.typecheck(typeEnv);
        if(type instanceof RefType){
            RefType reft = (RefType) type;
            return reft.getInner();
        } else
            throw new MyException("the rH argument is not a Ref Type");
    }
}
