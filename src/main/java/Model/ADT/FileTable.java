package Model.ADT;

import Exceptions.MyException;
import Model.Values.StringValue;

import java.io.BufferedReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FileTable<T1, T2> implements IDict<T1, T2> {

    Map<T1, T2> dict;

    public FileTable(){
        dict = new HashMap<T1, T2>();
    }

    @Override
    public void add(T1 v1, T2 v2) throws MyException {
        try{
            this.dict.put(v1, v2);
        }
        catch (RuntimeException e){
            throw new MyException("You can not add null values into a dictionary.");
        }
    }

    @Override
    public void update(T1 v1, T2 v2){
        this.dict.replace(v1, v2);
    }

    @Override
    public T2 lookup(T1 id){ return this.dict.get(id); }

    @Override
    public boolean isDefined(T1 id){
        return dict.containsKey(id);
    }

    @Override
    public void remove(T1 id){
        this.dict.remove(id);
    }

    @Override
    public boolean isEmpty(){
        return this.dict.isEmpty();
    }

    @Override
    public Collection<T2> values(){
        return this.dict.values();
    }

    @Override
    public IDict<T1, T2> deepCopy() throws MyException {
        return null;
    }

    @Override
    public IDict<T1, T2> clone() {
        return null;
    }

    @Override
    public Map getContent() {
        return dict;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Map.Entry<T1, T2> h : this.dict.entrySet()) {
            s.append("File ").append(h.getKey()).append("\n");
        }
        return s.toString();
    }
}
