package Model.Statements;

import Exceptions.MyException;
import Model.ADT.IDict;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.BoolType;
import Model.Types.IType;
import Model.Values.BoolValue;
import Model.Values.IValue;

public class IfStmt implements IStmt{
    Exp exp;
    IStmt thenS, elseS;

    public IfStmt(Exp e, IStmt t, IStmt el){
        exp = e;
        thenS = t;
        elseS = el;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IValue value = exp.eval(state.getSymTable(), state.getHeap());
        if (value.getType().equals(new BoolType())) {
            BoolValue condition = (BoolValue)value;
            if (condition.getVal())
                state.getExeStack().push(thenS);
            else state.getExeStack().push(elseS);
            return null;
        }
        throw new MyException("conditional expr is not a boolean");
    }

    @Override
    public String toString() {
        return "(IF(" + exp.toString() + ")THEN(" + thenS.toString() + ")ELSE(" + elseS.toString() + "))" ;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws MyException {
        IType typeExp = exp.typecheck(typeEnv);
        if(typeExp.equals(new BoolType())){
            thenS.typecheck(typeEnv);
            elseS.typecheck(typeEnv);
            return typeEnv.deepCopy();
        } else
            throw new MyException("The condition of IF has not the type bool");
    }
}
