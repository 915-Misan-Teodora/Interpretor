package Model.Statements;

import Exceptions.MyException;
import Model.ADT.IBarrierTable;
import Model.ADT.IDict;
import Model.ADT.List;
import Model.ADT.Pair;
import Model.PrgState;
import Model.Types.IType;
import Model.Types.IntType;
import Model.Values.IValue;
import Model.Values.IntValue;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AwaitStmt implements IStmt{
    String var;
    Lock lock = new ReentrantLock();

    public AwaitStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        lock.lock();
        IDict<String, IValue> symTable = state.getSymTable();
        IBarrierTable<Integer, Pair<Integer, Model.ADT.List<Integer>>> barrierTable = state.getBarrierTable();
        IValue value = symTable.lookup(var);
        if (value != null){
            if(value.getType().equals(new IntType())){
                IntValue intValue = (IntValue) value;
                Integer foundIndex = intValue.getVal();
                if(barrierTable.lookup(foundIndex) != null){
                    Pair<Integer, List<Integer>> myPair = barrierTable.lookup(foundIndex);
                    Integer NL = myPair.getValue().size();
                    Integer N1 = myPair.getKey();
                    if (N1 > NL) {
                        state.getExeStack().push(this);
                    }else {
                        return null;
                    }
                }else{
                    throw new MyException("No such barrier!");
                }
            }
        }else {
            throw new MyException("Value not found!");
        }
        lock.unlock();
        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws MyException {
        return null;
    }

    @Override
    public String toString() {
        return "await("+this.var+")";
    }
}
