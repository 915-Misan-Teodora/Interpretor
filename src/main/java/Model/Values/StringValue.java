package Model.Values;

import Model.Types.IType;
import Model.Types.IntType;
import Model.Types.StringType;

public class StringValue implements IValue{
    String val;

    public StringValue(String val) {
        this.val = val;
    }


    public String getVal() {
        return val;
    }

    public String toString(){
        return String.valueOf(val);
    }

    @Override
    public IValue deepCopy(){
        return new StringValue(this.val);
    }

    @Override
    public IType getType(){
        return new StringType();
    }
}
