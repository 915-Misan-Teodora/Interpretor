package Model.ADT;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public interface IHeap <TLoc,Tval>{
    public int allocate(Tval value);
    Tval readAddress(int address);
    void writeAddress(int address, Tval value);
    Tval deallocate(int addr);
     void setHashMap(HashMap<Integer, Tval> hashMap);
     HashMap<Integer, Tval> toHasMap();
     boolean contains(TLoc address);
     Collection<Tval> getValue();
}
