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


public class HeapAllocation implements IStmt{
    private String variableName;
    private Exp expression;


    public HeapAllocation(String variableName, Exp expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public String toString(){
        return "new(" + variableName + ", " + expression + ")";
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws MyException {
        IType typeVar = typeEnv.lookup(variableName);
        IType typeExp = expression.typecheck(typeEnv);
        if(typeVar.equals(new RefType(typeExp)))
            return typeEnv;
        else
            throw new MyException("Heap allocation statement -> different types");
    }

    @Override
    public PrgState execute(PrgState state) throws MyException{
        IDict<String, IValue> symTable = state.getSymTable();
        IHeap<Integer, IValue> heap = state.getHeap();
        if(symTable.isDefined(variableName)){
            if(symTable.lookup(variableName) instanceof RefValue){
                IValue value = expression.eval(symTable,heap);
                RefValue refValue = (RefValue) symTable.lookup(variableName);
                if(refValue.getLocationType().equals(value.getType())){
                    Integer addr = heap.allocate(value);
                    RefValue ref = new RefValue(addr, refValue.getLocationType());
                    symTable.update(variableName, ref);
                } else throw new MyException("The exp does not reerence the correct type");
            } else throw new MyException("The variable is not of type Ref");
        } else throw new MyException("Undefined variable");
        return null;
    }


}
