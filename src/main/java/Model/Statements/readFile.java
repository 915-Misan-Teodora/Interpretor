package Model.Statements;

import Exceptions.MyException;
import Model.ADT.IDict;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.IType;
import Model.Types.IntType;
import Model.Types.StringType;
import Model.Values.IValue;
import Model.Values.IntValue;
import Model.Values.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class readFile implements IStmt{

    Exp exp;
    String varName;

    public readFile(Exp exp, String varName) {
        this.exp = exp;
        this.varName = varName;
    }

    @Override
    public String toString(){
        return "read file - " + exp.toString();
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws MyException {
        IType typeVar = typeEnv.lookup(varName);
        IType typeExp = exp.typecheck(typeEnv);
        if(typeVar.equals(new IntType())){
            if(typeExp.equals(new StringType())){
                return typeEnv;
            } else
                throw new MyException("Read file expression is not a string");
        } else
            throw new MyException("Read file variable is not an int");
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IDict<String, IValue> symTable = state.getSymTable();
        IDict<StringValue, BufferedReader> fileTable = state.getFileTable();
        IType type= new IntType();

        if(symTable.isDefined(varName)){
            if(symTable.lookup(varName).getType() != type){
                IValue value = exp.eval(symTable, state.getHeap());
                IType typeId = new StringType();
                if(value.getType().equals(typeId)){
                    StringValue strVal = (StringValue) value;
                    if(fileTable.isDefined(strVal)){
                        BufferedReader bufferReader = fileTable.lookup(strVal);
                        try{
                            String line = bufferReader.readLine();
                            int nmb;
                            if(line.length() == 0){
                                nmb = 0;
                            } else {
                                nmb = Integer.parseInt(line);
                            }
                            IntValue intVal = new IntValue(nmb);
                            symTable.update(varName, intVal);
                        } catch(IOException e){
                            throw new MyException("Exception at the file");
                        }
                    } else throw new MyException("There is no such file!");
                } else throw new MyException("The expression is not of string type");
            } else throw new MyException("The variable for reading the file is not an integer");
        } else throw new MyException(" The variable is not defined in the symTable");
        return null;
    }
}
