package Model.Statements;

import Exceptions.MyException;
import Model.ADT.IDict;
import Model.Expressions.Exp;
import Model.Expressions.RationalExp;
import Model.PrgState;
import Model.Types.IType;

public class SwitchStatement implements IStmt{
    private Exp exp, exp1, exp2;
    private IStmt stmt1, stmt2, stmt3;

    public SwitchStatement(Exp exp, Exp exp1, Exp exp2, IStmt stmt1, IStmt stmt2, IStmt stmt3) {
        this.exp = exp;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.stmt1 = stmt1;
        this.stmt2 = stmt2;
        this.stmt3 = stmt3;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IStmt newStmt = new IfStmt(new RationalExp("==",exp,exp1),stmt1, new IfStmt(new RationalExp("==",exp,exp2), stmt2,stmt3));
        state.getExeStack().push(newStmt);
        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws MyException {
        IType typeExp = exp.typecheck(typeEnv);
        IType typeExp1 = exp1.typecheck(typeEnv);
        IType typeExp2 = exp2.typecheck(typeEnv);
        if (typeExp.equals(typeExp1) && typeExp.equals(typeExp2))       // it checks for the same types
            // if the expressions have the same type => typecheck the statements
            return stmt1.typecheck(stmt2.typecheck(stmt3.typecheck(typeEnv)));
        else
            throw new MyException("Incompatible types");
    }

    @Override
    public String toString() {
        return "switch("+ exp.toString() + ")(case "+
                exp1.toString()  + ": " + stmt1.toString() +
                ")(case " + exp2.toString() + ": " + stmt2.toString() +
                ")(default: " + stmt3.toString() + ")";
    }
}
