package Model.ADT;

import java.util.HashMap;
import java.util.Map;

public class BarrierTable<T1,T2> implements IBarrierTable<T1,T2>{
    Map<T1,T2> listOfIntegers;
    int integerNmb;

    public BarrierTable(){
        this.integerNmb = 1;
        this.listOfIntegers = new HashMap<>();
    }

    @Override
    public void update(T1 id, T2 val) {
        this.listOfIntegers.put(id, val);
    }

    @Override
    public T2 lookup(T1 id) {
        return listOfIntegers.get(id);
    }

    @Override
    public void add(T1 id, T2 value) {
        this.listOfIntegers.put(id,value);
    }

    @Override
    public void setTable(Map<T1, T2> list) {
        this.listOfIntegers = list;
    }

    @Override
    public Integer getAddr() {
        this.integerNmb++;
        return this.integerNmb;
    }

    @Override
    public Map<T1, T2> getList() {
        return this.listOfIntegers;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(T1 address : this.listOfIntegers.keySet()){
            stringBuilder.append(address).append(" -> ").append(this.listOfIntegers.get(address)).append(" ");
        }
        return stringBuilder.toString();
    }


}
