package Model.Values;

import Model.Types.IType;
import Model.Types.RefType;

public class RefValue implements IValue{
    int address;
    IType locationType;

    public RefValue(int address, IType locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddress() {
        return address;
    }

    public IType getType() {
        return new RefType(locationType);
    }

    public IType getLocationType() {
        return locationType;
    }

    @Override
    public IValue deepCopy() {
        return null;
    }

    @Override
    public String toString(){
        return "(" + address + " --> " + locationType + ")";
    }
}
