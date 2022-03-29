package Model.Values;

import Model.Types.IType;
import Model.Types.IntType;

public class IntValue implements IValue{
    int val;

    public IntValue(int v){
        val = v;
    };

    public IntValue(){ val = 0; };

    public int getVal(){
        return this.val;
    }

    @Override
    public String toString(){
        return Integer.toString(this.val);
    }

    @Override
    public IType getType(){
        return new IntType();
    }

    @Override
    public IValue deepCopy(){
        return new IntValue(this.val);
    }

}
