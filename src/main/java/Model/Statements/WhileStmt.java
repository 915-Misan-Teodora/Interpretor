package Model.Statements;

import Exceptions.MyException;
import Model.ADT.IDict;
import Model.ADT.IStack;
import Model.Expressions.Exp;
import Model.Expressions.NotExp;
import Model.PrgState;
import Model.Types.BoolType;
import Model.Types.IType;
import Model.Values.BoolValue;
import Model.Values.IValue;

public class WhileStmt implements IStmt{
    private Exp exp;
    private IStmt stmt;

    public WhileStmt(Exp exp, IStmt stmt) {
        this.exp = exp;
        this.stmt = stmt;
    }


    @Override
    public PrgState execute(PrgState state) throws MyException {
        IDict<String, IValue> symbolTable = state.getSymTable();
        IValue result = exp.eval(symbolTable,state.getHeap());
        if (result.getType().equals(new BoolType())){
            BoolValue downcastedResult = (BoolValue)result;
            if (downcastedResult.getVal()==true){
                state.getExeStack().push(new WhileStmt(exp,stmt));
                state.getExeStack().push(stmt);
            }
        }
        else
            throw new MyException("Condition exp is not a boolean.");
        return null;
    }

    @Override
    public String toString() {
            return "while(" + this.exp.toString() + ") do " + this.stmt.toString() + " end";
        }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws MyException {
        IDict<String,IType> newEnv = typeEnv.clone();
        IType typeExp = exp.typecheck(newEnv);
        if(typeExp.equals(new BoolType())){
            return typeEnv;
        } else
            throw new MyException("The condition of WHILE has not the type bool");
    }

}

