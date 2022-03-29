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
import java.io.IOException;

public class closeRFile implements IStmt{
    Exp exp;

    public closeRFile(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString(){
        return "close file - " + exp.toString();
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws MyException {
        IType typeExp = exp.typecheck(typeEnv);
        if(typeExp.equals(new StringType())){
            return typeEnv;
        } else
            throw new MyException("Close statement -> exp type is not a string");
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IDict<String, IValue> symTable = state.getSymTable();
        IDict<StringValue, BufferedReader> fileTable = state.getFileTable();
        IValue val = exp.eval(symTable, state.getHeap());
        IType type= new StringType();

        if(val.getType().equals(type)){
            // if the expression is a String
            StringValue strVal = (StringValue) val;
            if(fileTable.isDefined(strVal)){
                BufferedReader bufferReader = fileTable.lookup(strVal);
                try{
                    bufferReader.close();
                }catch(IOException e){
                    throw new MyException("Error at closing file");
                }
                fileTable.remove(strVal);
            } else throw new MyException("This file does NOT exist");
        }else throw new MyException("The expression type is not a string");
        return null;
    }

}
