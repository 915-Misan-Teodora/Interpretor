package Model.ADT;

import Exceptions.MyException;

import java.util.Collection;
import java.util.Map;

public interface IDict<T1, T2> {
    void add(T1 v1, T2 v2) throws MyException;
    void update(T1 v1, T2 v2);
    T2 lookup(T1 id);
    boolean isDefined(T1 id);
    String toString();
    void remove(T1 id);
    boolean isEmpty();
    Collection<T2> values();
    IDict<T1, T2> deepCopy() throws MyException;
    IDict<T1, T2> clone() throws MyException;
    public Map getContent();
}
