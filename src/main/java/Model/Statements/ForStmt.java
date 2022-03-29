package Model.Statements;

import Exceptions.MyException;
import Model.ADT.IDict;
import Model.ADT.IHeap;
import Model.Expressions.Exp;
import Model.Expressions.RationalExp;
import Model.Expressions.ValueExp;
import Model.PrgState;
import Model.Types.IType;
import Model.Types.IntType;
import Model.Values.IValue;
import Model.Values.StringValue;

import java.io.BufferedReader;

public class ForStmt implements IStmt{
    Exp v;
    Exp exp1;
    Exp exp2;
    Exp exp3;
    IStmt stmt;

    public ForStmt(Exp v, Exp expression1, Exp expression2, Exp expression3, IStmt statement) {
        this.exp1 = expression1;
        this.exp2 = expression2;
        this.exp3 = expression3;
        this.stmt = statement;
        this.v = v;
    }


    @Override
    public PrgState execute(PrgState state) throws MyException {
        IDict<String, IValue> symTable = state.getSymTable();
        IDict<StringValue, BufferedReader> fileTable = state.getFileTable();
        IHeap<Integer, IValue> heapTable = state.getHeap();
        IValue value1 = this.exp1.eval(symTable, heapTable);


        if(value1.getType().equals(new IntType())){
            IValue value2 = this.exp2.eval(symTable, heapTable);
            if(value2.getType().equals(new IntType())){
                new AssignStmt(v.toString(), exp1).execute(state);
                Exp exp = new RationalExp("<", v, new ValueExp(value2));
                IStmt exeStatement = new CompStmt(this.stmt, new AssignStmt(v.toString(), exp3));
                state.getExeStack().push(new WhileStmt(exp, exeStatement));
                return null;
            }else{
                throw new MyException("The first value is not an integer!");
            }
        }else{
            throw new MyException("The second value is not an integer!");
        }
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws MyException {
        IType t1 = exp1.typecheck(typeEnv);
        IType t2 = exp2.typecheck(typeEnv);
        if(t1 instanceof IntType && t2 instanceof IntType){
            return typeEnv;
        }else {
            throw new MyException("The types are not the same!");
        }
    }

    @Override
    public String toString() {
        return "for(v="+this.exp1 + "; v<"+this.exp2 + "; v="+ this.exp3+")" + "{  " + this.stmt + "  }";
    }
}

