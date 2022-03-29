package Model.ADT;

public class Pair<T,T1> {
    T key;
    T1 value;

    public T getKey() {
        return key;
    }

    public T1 getValue() {
        return value;
    }

    public Pair(T key, T1 value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "(" + this.key + ", " + this.value + ")";
    }
}
