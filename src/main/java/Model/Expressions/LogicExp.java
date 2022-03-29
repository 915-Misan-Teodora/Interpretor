package Model.Expressions;

import Exceptions.MyException;
import Model.ADT.IDict;
import Model.ADT.IHeap;
import Model.Types.BoolType;
import Model.Types.IType;
import Model.Types.IntType;
import Model.Values.BoolValue;
import Model.Values.IValue;

public class LogicExp implements Exp{
    private Exp e1, e2;
    private int op;

    public LogicExp(Exp a, Exp b, int o){ e1 = a; e2 = b; op =0; }

    @Override
    public IValue eval(IDict<String, IValue> tbl, IHeap<Integer, IValue> heap) throws MyException {
        IValue val1 = e1.eval(tbl, heap);
        IValue val2 = e2.eval(tbl, heap);

        if(!val1.getType().equals(new BoolType()) || !val2.getType().equals(new BoolType())){
            throw new MyException("One operand was not a Bool Type");
        }

        boolean b1 = ((BoolValue) val1).getVal();
        boolean b2 = ((BoolValue) val2).getVal();
        switch (op){
            case 1 :{
                return new BoolValue(b1 && b2);
            }
            case 2: {
                return new BoolValue(b1 || b2);
            }
            default:
                throw new MyException("The operand is not a logic one( && or || )");
        }
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", e1.toString() , op , e2.toString());
    }

    @Override
    public IType typecheck(IDict<String, IType> typeEnv) throws MyException {
        IType type1, type2;
        type1 = e1.typecheck(typeEnv);
        type2 = e2.typecheck(typeEnv);

        if(type1.equals(new BoolType())){
            if(type2.equals(new BoolType())){
                return new BoolType();
            } else
                throw new MyException("second operand is not a bool type");
        } else
            throw new MyException("first operand is not a bool type");
    }
}
