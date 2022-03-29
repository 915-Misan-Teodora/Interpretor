package Model.Statements;

import Exceptions.MyException;
import Model.ADT.IDict;
import Model.ADT.IHeap;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.IType;
import Model.Types.RefType;
import Model.Values.IValue;
import Model.Values.RefValue;

public class HeapWriting implements IStmt{
    private String variableName;
    private Exp exp;

    public HeapWriting(String variableName, Exp exp) {
        this.variableName = variableName;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IDict<String, IValue> symTable = state.getSymTable();
        IHeap<Integer, IValue> heap = state.getHeap();
        if(symTable.isDefined(variableName)){
            IValue value = symTable.lookup(variableName);
            if(value.getType() instanceof RefType){
                RefValue refVal = (RefValue) value;
                int addr = refVal.getAddress();
                if(heap.contains(addr)){
                    IValue exprVal = exp.eval(symTable, heap);
                    if(exprVal.getType().equals(refVal.getLocationType())){
                           heap.writeAddress(addr, exprVal);
                    }
                } else throw new MyException("There is no variable at this address on the heap");
            } else throw new MyException("The value of the variable is not a reference type");
        } else throw new MyException("Undefined variable - missing from symTable");
        return null;
    }

    @Override
    public String toString(){
        return "wH(" + variableName + ", " + exp + ")";
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws MyException {
        IType typeVar = typeEnv.lookup(variableName);
        IType typeExp = exp.typecheck(typeEnv);
        if(typeVar.equals(new RefType(typeExp)))
            return typeEnv;
        else
            throw new MyException("Heap write statement -> different types");
    }
}
