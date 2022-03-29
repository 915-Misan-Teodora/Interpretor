package Model.Values;

import Model.Types.BoolType;
import Model.Types.IType;
import Model.Types.IntType;

public class BoolValue implements IValue{
    boolean val;

    public BoolValue(boolean v){ val = v; };
    public BoolValue(){ val = false; };

    public boolean getVal(){
        return this.val;
    }

    @Override
    public String toString(){
        return this.val ? "true" : "false";
    }

    @Override
    public IType getType(){
        return new BoolType();
    }

    @Override
    public IValue deepCopy(){
        return new BoolValue(this.val);
    }
}
