package Model;

import Exceptions.MyException;
import Model.ADT.*;
import Model.Statements.IStmt;
import Model.Values.IValue;
import Model.Values.StringValue;

import java.io.BufferedReader;
import java.util.ArrayList;
//import com.sun.jdi.Value;

public class PrgState {
    IStack<IStmt> exeStack;
    IDict<String, IValue> symTable;
    IDict<StringValue, BufferedReader> fileTable;
    IList<IValue> out;
    IStmt originalProgram;
    IHeap<Integer, IValue> heap;
    IBarrierTable<Integer, Pair<Integer, List<Integer>>> barrierTable;
    private static int nextId = 0;
    private int id;

    public PrgState(IStmt selectedPrg) {
        this.exeStack = new MyStack<IStmt>();
        this.symTable = new Dict<String, IValue>();
        this.fileTable = new Dict<StringValue, BufferedReader>();
        this.out = new List<IValue>();
        this.heap = new Heap<>();
        this.id = 1;
        this.exeStack.push(selectedPrg);
    }
    // modify all - toString()

    public IBarrierTable<Integer, Pair<Integer, List<Integer>>> getBarrierTable() {
        return barrierTable;
    }
    //            - logPrgStateExec()  => such that the id of the prg state to be printed first

    public IHeap<Integer, IValue> getHeap() {
        return heap;
    }

    public int getId(){
        return id;
    }

    public void setExeStack(IStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    public void setSymTable(IDict<String, IValue> symTable) {
        this.symTable = symTable;
    }

    public void setOut(IList<IValue> out) {
        this.out = out;
    }

    public void setOriginalProgram(IStmt originalProgram) {
        this.originalProgram = originalProgram;
    }

    public IStack<IStmt> getExeStack() {
        return exeStack;
    }

    public IDict<String, IValue> getSymTable() {
        return symTable;
    }

    public IDict<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public IList<IValue> getOut() {
        return out;
    }

    public IStmt getOriginalProgram() {
        return originalProgram;
    }

    public PrgState(IStack<IStmt> stk, IDict<String, IValue> sym, IList<IValue> o, IStmt prg,  IDict<StringValue, BufferedReader> fileTable, IHeap<Integer, IValue> heapTable){
        exeStack = stk;
        symTable = sym;
        out = o;
        this.fileTable = fileTable;
        this.heap = heapTable;
        id = ID();
        stk.push(prg);
    }

    public static synchronized int ID() {
        nextId = nextId + 1;
        return nextId;
    }

    @Override
    public String toString(){
        return "- - - - - - - - - - \n" +
                "Id: " + id + "\n" +
                "ExeStack:\n" + exeStack.toString() + "\n" +
                "SymStack:\n" + symTable.toString() + "\n" +
                "Output:\n" + out.toString() + "\n" +
                "FileTable:\n" + fileTable.toString() + "\n" +
                "Heap:\n" + heap.toString() + "\n" +
                "- - - - - - - - - - \n";
    }

    public boolean isNotCompleted(){
        return !exeStack.isEmpty();
    }

    public PrgState oneStep() throws MyException {
        if(exeStack.isEmpty()) {
            throw new MyException("PrgState stack is empty");
        }
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }
}
