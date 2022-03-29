package Model.Types;

import Model.Values.IValue;
import Model.Values.IntValue;
import Model.Values.StringValue;

public class StringType implements IType{

    @Override
    public IValue defaultValue() {
        return new StringValue("");
    }

    @Override
    public boolean equals(Object another){
        return another instanceof StringType;
    }

    @Override
    public String toString(){
        return "String";
    }

    @Override
    public IType deepCopy(){
        return new StringType();
    }
}
