package Model.ADT;

import Exceptions.MyException;

import java.util.List;
import java.util.Stack;


public class MyStack<T> implements IStack<T>{
    Stack<T> myStack;

    public MyStack(){
        this.myStack = new Stack<T>();
    }

    @Override
    public T pop() throws MyException{
        if(myStack.isEmpty())
            throw new MyException("The stack is empty!");
        return myStack.pop();

    }

    @Override
    public void push(T v){
        this.myStack.push(v);
    }

    @Override
    public boolean isEmpty(){
        return this.myStack.isEmpty();
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        for(T elem : myStack){
            str.append("[").append(elem.toString()).append(";]\n");
        }
        return str.toString();
    }

    @Override
    public Stack<T> getStk(){
        return this.myStack;
    }

    @Override
    public T top() throws MyException {
        return myStack.get(0);
    }

    @Override
    public List getValues() {
        return myStack.subList(0,myStack.size());
    }
}
