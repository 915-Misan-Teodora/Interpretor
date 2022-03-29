package Model.Statements;

import Exceptions.MyException;
import Model.ADT.IDict;
import Model.ADT.IStack;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.IType;
import Model.Values.IValue;

public class AssignStmt implements IStmt{
    String id;
    Exp expr;

    public AssignStmt(String s, Exp e){
        id = s;
        expr = e;
    }

    @Override
    public String toString(){
        return this.id + "=" + this.expr;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws MyException {
        IType typevar = typeEnv.lookup(id);
        IType typexp = expr.typecheck(typeEnv);
        if(typevar.equals(typexp))
            return typeEnv;
        else
            throw new MyException("Assignment: right hand side and left hand side have different types");
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IDict<String, IValue> symTbl = state.getSymTable();

        if(symTbl.isDefined(id)){
            IValue val = expr.eval(symTbl, state.getHeap());
            if(val.getType().equals(symTbl.lookup(this.id).getType())){
                symTbl.update(this.id, val);
            }
            else throw new MyException("declared type of variable" + id + " and type of the assigned expression do not match");
        }
        else throw new MyException("The used variable " + id + "was nor declared before");
        return null;

    }
}
