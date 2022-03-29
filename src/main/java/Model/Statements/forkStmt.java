package Model.Statements;

import Exceptions.MyException;
import Model.ADT.IDict;
import Model.ADT.IStack;
import Model.ADT.MyStack;
import Model.PrgState;
import Model.Types.IType;

import java.util.Stack;

public class forkStmt implements IStmt{
    private IStmt stmt;

    public forkStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IStack<IStmt> exeStack = new MyStack<>();
        return new PrgState(exeStack, state.getSymTable().deepCopy(), state.getOut(), stmt,state.getFileTable(), state.getHeap());
    }

    @Override
    public String toString() {
        return "Fork{"+ stmt.toString() + "}";
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws MyException {
        stmt.typecheck(typeEnv.deepCopy());
        return typeEnv;
    }
}
