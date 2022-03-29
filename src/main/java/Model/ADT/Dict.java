package Model.ADT;

import Exceptions.MyException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Dict<T1, T2> implements IDict<T1, T2> {

    Map<T1, T2> dict;

    public Dict(){
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
    public T2 lookup(T1 id){
        return this.dict.get(id);
    }

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
    public String toString(){
        return this.dict.toString();
    }

    @Override
    public IDict<T1, T2> deepCopy() throws MyException {
        IDict<T1,T2> myDict = new Dict<>();
        for(T1 key: this.dict.keySet()){
            myDict.add(key, this.dict.get(key));
        }
        return myDict;
    }

    @Override
    public IDict<T1, T2> clone() {
        IDict<T1,T2> myDict = new Dict<>();
        for(T1 key: this.dict.keySet()){
            try {
                myDict.add(key, this.dict.get(key));
            } catch (MyException e) {
                e.printStackTrace();
            }
        }
        return myDict;
    }

    @Override
    public Map getContent() {
        return dict;
    }
}
