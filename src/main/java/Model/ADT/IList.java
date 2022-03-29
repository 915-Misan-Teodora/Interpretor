package Model.ADT;

import Exceptions.MyException;

import java.util.List;
import java.util.Stack;

public interface IList<T> {
    void add(T v) throws MyException;
    String toString();
    boolean isEmpty();
    void clear();
    int size();
    List getContent();
    T get(int position) throws MyException;
}
