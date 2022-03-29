package Model.Statements;

import Exceptions.MyException;
import Model.ADT.*;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.IType;
import Model.Types.IntType;
import Model.Values.IValue;
import Model.Values.IntValue;
import java.util.concurrent.locks.Lock;

public class NewBarrierStmt implements IStmt{
    String var;
    Exp exp;
    Lock lock;

    public NewBarrierStmt(String var, Exp exp) {
        this.var = var;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        lock.lock();
        IDict<String, IValue> symTable = state.getSymTable();
        IHeap<Integer,IValue> heapTable = state.getHeap();
        IBarrierTable<Integer, Pair<Integer, List<Integer>>> barrieTable = state.getBarrierTable();
        IValue val = this.exp.eval(symTable,heapTable);
        if(val.getType().equals(new IntType())){
            IntValue intValue = (IntValue) val;
            Integer number = intValue.getVal();
            Integer freeLocation = barrieTable.getAddr();
           barrieTable.update(freeLocation, new Pair<>(number, new List<>()));
            symTable.update(var, new IntValue(freeLocation));
        }else{
            throw new MyException("Incorrect variables!");
        }
        lock.unlock();
        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws MyException {
        IDict<String,IType> newEnv = typeEnv.clone();
        IType typeExp = exp.typecheck(newEnv);
        if(typeExp.equals(new IntType())){
            return typeEnv;
        } else
            throw new MyException("The expression of the NewBarrierStmt is not BOOL");
    }

    @Override
    public String toString() {
        return "newBarrier("+this.var+","+this.exp+")";
    }
}
