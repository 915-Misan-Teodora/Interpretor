package Model.ADT;

import Exceptions.MyException;

import java.util.Stack;

public class List<T> implements IList<T> {
    Stack<T> list;
    int pos = 0;
    public List(){
        list = new Stack<>();
    }
    @Override
    public void add(T v) throws MyException {
        try {
            list.add(v);
            this.pos++;
        }
        catch(RuntimeException e){
            throw new MyException("You can not add null values into a list;");
        }
    }

    public java.util.List getContent(){
        return list;
    }

    @Override
    public boolean isEmpty(){
        return this.list.isEmpty();
    }

    @Override
    public void clear(){
        this.list.clear();
    }

    @Override
    public int size(){
        return this.list.size();
    }

    @Override
    public T get(int position) throws MyException {
        try {
            return list.get(position);
        } catch (RuntimeException e) {
            throw new MyException("blabla");
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(var elem:list){
            sb.append(elem.toString()).append("\n");
        }
        return sb.toString();
    }
}
