package View;

import Controller.Contr;
import Exceptions.MyException;
import Model.ADT.*;
import Model.Expressions.*;
import Model.PrgState;
import Model.Statements.*;
import Model.Types.*;
import Model.Values.BoolValue;
import Model.Values.IValue;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Repository.IRepo;
import Repository.Repo;
import View.Commands.ExitCommand;
import View.Commands.RunExample;

import java.util.HashMap;

public class Interpreter {
    public static void main(String[] args) throws MyException {
        IHeap<Integer, IValue> heap =  new Heap<>();

        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new
                        VarExp("v"))));

        ex1.typecheck(new Dict<>());
        PrgState prg1 = new PrgState(new MyStack<>(), new Dict<>(), new List<>(), ex1, new FileTable<>(), heap);
        IRepo repo1 = new Repo("log1.txt");
        Contr ctr1 = new Contr(repo1);
        ctr1.addProgram(prg1);

        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp('+', new ValueExp(new IntValue(2)), new
                                ArithExp('*', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"), new ValueExp(new
                                        IntValue(1)))), new PrintStmt(new VarExp("b"))))));

        ex2.typecheck(new Dict<>());
        PrgState prg2 = new PrgState(new MyStack<>(), new Dict<>(), new List<>(), ex2, new FileTable<>(), heap);
        IRepo repo2 = new Repo("log2.txt");
        Contr ctr2 = new Contr(repo2);
        ctr2.addProgram(prg2);


        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new
                                        IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                        VarExp("v"))))));

        ex3.typecheck(new Dict<>());
        PrgState prg3 = new PrgState(new MyStack<>(), new Dict<>(), new List<>(), ex3, new FileTable<>(), heap);
        IRepo repo3 = new Repo( "log3.txt");
        Contr ctr3 = new Contr(repo3);
        ctr3.addProgram(prg3);


        IStmt ex4 = new CompStmt(new VarDeclStmt("file", new StringType()),
                new CompStmt(new AssignStmt("file", new ValueExp(new StringValue("C:\\Users\\Misan\\IdeaProjects\\ToyLanguage\\src\\test.in"))),
                        new CompStmt(new openRFile(new VarExp("file")),
                                new CompStmt(new VarDeclStmt("var", new IntType()),
                                        new CompStmt(new readFile(new VarExp("file"), "var"),
                                                new CompStmt(new PrintStmt(new VarExp("var")),
                                                        new CompStmt(new readFile(new VarExp("file"), "var"),
                                                                new CompStmt(new PrintStmt(new VarExp("var")),
                                                                        new closeRFile(new VarExp("file"))))))))));

        ex4.typecheck(new Dict<>());
        PrgState prg4 = new PrgState(new MyStack<>(), new Dict<>(), new List<>(), ex4, new FileTable<>(), heap);
        IRepo repo4 = new Repo( "log4.txt");
        Contr ctr4 = new Contr(repo4);
        ctr4.addProgram(prg4);


        IStmt ex5 =new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(4))),
                        new CompStmt(new WhileStmt(new RationalExp(">", new VarExp("v"), new ValueExp(new IntValue(0))),
                                new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v",
                                        new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1)))))),
                                new PrintStmt(new VarExp("v")))));

        //ex5.typecheck(new Dict<>());
        PrgState prg5 = new PrgState(new MyStack<>(), new Dict<>(), new List<>(), ex5, new FileTable<>(), heap);
        IRepo repo5 = new Repo( "log5.txt");
        Contr ctr5 = new Contr(repo5);
        ctr5.addProgram(prg5);

        IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new HeapAllocation("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new HeapAllocation("a", new VarExp("v")),
                                        new CompStmt(new HeapAllocation("v", new ValueExp(new IntValue(30))),
                                                new CompStmt(new HeapAllocation("v", new ValueExp(new IntValue(40))),
                                                        new PrintStmt(new HeapReadingExp(new HeapReadingExp(new VarExp("a"))))))))));

        ex6.typecheck(new Dict<>());
        PrgState prg6 = new PrgState(new MyStack<>(), new Dict<>(), new List<>(), ex6, new FileTable<>(), heap);
        IRepo repo6 = new Repo( "log6.txt");
        Contr ctr6 = new Contr(repo6);
        ctr6.addProgram(prg6);




        IStmt ex7 = new CompStmt(
                new VarDeclStmt("v", new IntType()),
                new CompStmt(
                        new VarDeclStmt("a", new RefType(new IntType())),
                        new CompStmt(
                                new AssignStmt("v", new ValueExp(new IntValue(10))),
                                new CompStmt(
                                        new HeapAllocation("a", new ValueExp(new IntValue(22))),
                                        new CompStmt(
                                                new forkStmt(new CompStmt(
                                                        new HeapWriting("a", new ValueExp(new IntValue(30))),
                                                        new CompStmt(
                                                                new AssignStmt("v", new ValueExp(new IntValue(32))),
                                                                new CompStmt(
                                                                        new PrintStmt(new VarExp("v")),
                                                                        new PrintStmt(new HeapReadingExp(new VarExp("a")))
                                                                )
                                                        )
                                                )),
                                                new CompStmt(
                                                        new PrintStmt(new VarExp("v")),
                                                        new PrintStmt(new HeapReadingExp(new VarExp("a")))
                                                )
                                        )
                                )
                        )
                )
        );

        ex7.typecheck(new Dict<>());
        PrgState prg7 = new PrgState(new MyStack<>(), new Dict<>(), new List<>(), ex7, new FileTable<>(), heap);
        IRepo repo7 = new Repo( "log7.txt");
        Contr ctr7 = new Contr(repo7);
        ctr7.addProgram(prg7);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
        menu.addCommand(new RunExample("2", ex2.toString(), ctr2));
        menu.addCommand(new RunExample("3", ex3.toString(), ctr3));
        menu.addCommand(new RunExample("4", ex4.toString(), ctr4));
        menu.addCommand(new RunExample("5", ex5.toString(), ctr5));
        menu.addCommand(new RunExample("6", ex6.toString(), ctr6));
        menu.addCommand(new RunExample("7", ex7.toString(), ctr7));
        menu.show();
    }
}
