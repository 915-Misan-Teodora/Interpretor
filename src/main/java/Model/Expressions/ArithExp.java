package Model.Expressions;

import Exceptions.MyException;
import Model.ADT.IDict;
import Model.ADT.IHeap;
import Model.Types.IType;
import Model.Types.IntType;
import Model.Values.IValue;
import Model.Values.IntValue;

public class ArithExp implements Exp{
    private char op;
    private Exp e1, e2;

    public ArithExp(char o, Exp elem1, Exp elem2){ op = o; e1 = elem1; e2 = elem2;}

    @Override
    public IValue eval(IDict<String, IValue> tbl, IHeap<Integer, IValue> heap) throws MyException{
        IValue val1 = e1.eval(tbl, heap);
        IValue val2 = e2.eval(tbl, heap);

        if(!val1.getType().equals(new IntType()) || !val2.getType().equals(new IntType())){
            throw new MyException("One of the operand is not of type int");
        }
        int n1 = ((IntValue) val1).getVal();
        int n2 = ((IntValue) val2).getVal();

        switch(op){
            case '+':
                return new IntValue(n1+n2);
            case '-':
                return new IntValue(n1-n2);
            case '*':
                return new IntValue(n1*n2);
            case '/':
                if(n2 != 0)
                    return new IntValue(n1/n2);
                else
                    throw new MyException("Division by 0");
            default:
                throw new MyException("There is no arithmetic expression(+,-,* or /)");
        }
    }

    @Override
    public IType typecheck(IDict<String, IType> typeEnv) throws MyException {
        IType type1, type2;
        type1 = e1.typecheck(typeEnv);
        type2 = e2.typecheck(typeEnv);

        if(type1.equals(new IntType())){
            if(type2.equals(new IntType())){
                return new IntType();
            } else
                throw new MyException("second operand is not an integer");
        } else
            throw new MyException("first operand is not an integer");
    }
    @Override
    public String toString() {
        return this.e1.toString()  + this.op  + this.e2.toString();
    }
}
