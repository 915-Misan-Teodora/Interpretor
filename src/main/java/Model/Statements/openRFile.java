package Model.Statements;

import Exceptions.MyException;
import Model.ADT.IDict;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.IType;
import Model.Types.StringType;
import Model.Values.IValue;
import Model.Values.StringValue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class openRFile implements IStmt{
    Exp exp;

    public openRFile(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString(){
        return "open file - " + exp.toString();
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws MyException {
        IType typeExp = exp.typecheck(typeEnv);
        if(typeExp.equals(new StringType())){
            return typeEnv;
        } else
            throw new MyException("Read statement -> exp type is not a string");
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IDict<String, IValue> symTable = state.getSymTable();
        IDict<StringValue, BufferedReader> fileTable = state.getFileTable();
        IValue val = exp.eval(symTable, state.getHeap());
        IType type= new StringType();

        if(val.getType().equals(type)){
            StringValue strVal = (StringValue) val;
            if(!fileTable.isDefined(strVal)){
                try{
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(strVal.getVal()));
                    fileTable.add(strVal, bufferedReader);
                } catch(IOException e){
                    throw new MyException("Error when opening the file");
                }
            } else throw new MyException("The value is already a key in the File table");
        } else throw new MyException("The expression type is not a string");
        return null;
    }
}
