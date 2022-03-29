package Model.ADT;

import java.util.Map;

public interface IBarrierTable<T1,T2> {
    void update(T1 id, T2 val);
    T2 lookup(T1 id);
    void add(T1 id, T2 value);
    void setTable(Map<T1, T2> list);
    Integer getAddr();
    Map<T1, T2> getList();
}
