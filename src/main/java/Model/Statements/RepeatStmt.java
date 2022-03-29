package Model.Statements;

import Exceptions.MyException;
import Model.ADT.IDict;
import Model.Expressions.Exp;
import Model.Expressions.NotExp;
import Model.PrgState;
import Model.Types.BoolType;
import Model.Types.IType;

public class RepeatStmt implements IStmt{
    private IStmt stmt1;
    private Exp exp2;


    public RepeatStmt(IStmt stmt1, Exp exp2) {
        this.stmt1 = stmt1;
        this.exp2 = exp2;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IStmt newStmt = new CompStmt(stmt1, new WhileStmt(new NotExp(exp2), stmt1));
        state.getExeStack().push(newStmt);
        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws MyException {
        IDict<String,IType> newEnv = typeEnv.clone();
        IType typeExp = exp2.typecheck(newEnv);
        if(typeExp.equals(new BoolType())){
            return typeEnv;
        } else
            throw new MyException("The expression of the REPEAT is not BOOL");
    }

    @Override
    public String toString() {
        return "repeat{ " + this.stmt1.toString() + " }until( " + this.exp2.toString() +" )";
    }
}
