package Model.ADT;

import Model.Values.IValue;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Heap<T> implements IHeap<Integer, T>{
    int index;
    HashMap<Integer, T> value;

    @Override
    public int allocate(T val) {
        this.index++;
        this.value.put(this.index, val);
        return index;
    }


    @Override
    public T readAddress(int address) {
        return this.value.get(address);
    }

    @Override
    public void writeAddress(int address, T value) {
        this.value.put(address, value);
    }

    @Override
    public T deallocate(int addr) {
        return this.value.remove(addr);
    }

    @Override
    public void setHashMap(HashMap<Integer, T> hashMap) {
        this.value = hashMap;
    }

    @Override
    public Collection<T> getValue() {
        return this.value.values();
    }

    @Override
    public HashMap<Integer, T> toHasMap() {
        return this.value;
    }

    @Override
    public boolean contains(Integer address) {
        return this.value.containsKey(address);
    }

    public Heap() {
        this.value = new HashMap<Integer, T>();
    }

    @Override
    public String toString() {
        String s="{";
        for(HashMap.Entry<Integer,T> entry : this.value.entrySet()){
            s+=entry.getKey().toString() + "->" + entry.getValue().toString() + "\n";
        }
        s+="}";
        return s;
    }
}
