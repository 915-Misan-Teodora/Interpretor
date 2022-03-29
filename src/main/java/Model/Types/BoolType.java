package Model.Types;

import Model.Values.BoolValue;
import Model.Values.IValue;
import Model.Values.IntValue;

public class BoolType implements IType{
    @Override
    public IValue defaultValue() {
        return new BoolValue(false);
    }

    @Override
    public boolean equals(Object another){
        if(another instanceof BoolType)
            return true;
        else
            return false;
    }

    @Override
    public String toString(){
        return "bool";
    }

    @Override
    public IType deepCopy(){
        return new BoolType();
    }
}
