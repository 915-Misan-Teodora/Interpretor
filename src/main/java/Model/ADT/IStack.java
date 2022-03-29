package Model.ADT;

import Exceptions.MyException;

import java.util.List;
import java.util.Stack;

public interface IStack<T> {

    T pop() throws MyException;
    void push(T v);
    boolean isEmpty();
    String toString();
    Stack<T> getStk();
    T top() throws MyException;
    public List getValues();
}
