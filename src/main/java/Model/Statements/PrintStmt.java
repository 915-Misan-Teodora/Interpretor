package Model.Statements;

import Exceptions.MyException;
import Model.ADT.IDict;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.IType;

public class PrintStmt implements IStmt{
    Exp expr;

    public PrintStmt(Exp e){
        expr = e;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        state.getOut().add(expr.eval(state.getSymTable(), state.getHeap()));
        return null;
    }

    @Override
    public String toString(){
        return "print(" + expr.toString() + ")";
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws MyException {
        expr.typecheck(typeEnv);
        return typeEnv;
    }
}
