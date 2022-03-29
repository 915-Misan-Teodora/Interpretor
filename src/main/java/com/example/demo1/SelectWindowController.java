package com.example.demo1;

import Controller.Contr;
import Exceptions.MyException;
import Model.ADT.Dict;
import Model.Expressions.*;
import Model.PrgState;
import Model.Statements.*;
import Model.Types.*;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Repository.IRepo;
import Repository.Repo;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SelectWindowController implements Initializable {

    @FXML
    private Button selectButton;
    @FXML
    private ListView<IStmt> selectItemListView;

    private MainWindowController mainWindowContr;

    public MainWindowController getMainWindow(){
        return mainWindowContr;
    }

    public void setMainWindowContr(MainWindowController main){
        this.mainWindowContr = main;
    }

    @FXML
    private IStmt selectExample(ActionEvent actionEv){
        return selectItemListView.getItems().get(selectItemListView.getSelectionModel().getSelectedIndex());
    }

    private List<IStmt> exemples(){
        List<IStmt> list = new ArrayList<>();
        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new
                        VarExp("v"))));
        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp('+', new ValueExp(new IntValue(2)), new
                                ArithExp('*', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"), new ValueExp(new
                                        IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new
                                        IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                        VarExp("v"))))));
        IStmt ex4 = new CompStmt(new VarDeclStmt("file", new StringType()),
                new CompStmt(new AssignStmt("file", new ValueExp(new StringValue("C:\\Users\\Misan\\IdeaProjects\\ToyLanguage\\src\\test.in"))),
                        new CompStmt(new openRFile(new VarExp("file")),
                                new CompStmt(new VarDeclStmt("var", new IntType()),
                                        new CompStmt(new readFile(new VarExp("file"), "var"),
                                                new CompStmt(new PrintStmt(new VarExp("var")),
                                                        new CompStmt(new readFile(new VarExp("file"), "var"),
                                                                new CompStmt(new PrintStmt(new VarExp("var")),
                                                                        new closeRFile(new VarExp("file"))))))))));
        IStmt ex5 =new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(4))),
                        new CompStmt(new WhileStmt(new RationalExp(">", new VarExp("v"), new ValueExp(new IntValue(0))),
                                new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v",
                                        new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1)))))),
                                new PrintStmt(new VarExp("v")))));


        IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new HeapAllocation("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new HeapAllocation("a", new VarExp("v")),
                                        new CompStmt(new HeapAllocation("v", new ValueExp(new IntValue(30))),
                                                new CompStmt(new HeapAllocation("v", new ValueExp(new IntValue(40))),
                                                        new PrintStmt(new HeapReadingExp(new HeapReadingExp(new VarExp("a"))))))))));
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
                                                        new PrintStmt(new HeapReadingExp(new VarExp("a")))))))));


        IStmt ex8 = new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b",new IntType()),
                new CompStmt(new VarDeclStmt("c",new IntType()),
                        new CompStmt(new AssignStmt("a",new ValueExp(new StringValue("hello"))),
                        new CompStmt(new AssignStmt("b",new ValueExp(new IntValue(2))),
                                new CompStmt(new AssignStmt("c",new ValueExp(new IntValue(5))),
                                new CompStmt(new SwitchStatement(new ArithExp('*',new VarExp("a"),new ValueExp(new IntValue(10))),new ArithExp('*', new VarExp("b"), new VarExp("c")),
                                        new ValueExp(new IntValue(10)), new CompStmt(new PrintStmt(new VarExp("a")),new PrintStmt(new VarExp("b"))),
                                        new CompStmt(new PrintStmt(new ValueExp(new IntValue(100))),new PrintStmt(new ValueExp(new IntValue(200)))),new PrintStmt(new ValueExp(new IntValue(300)))),new PrintStmt(new ValueExp(new IntValue(300))))))))));


        IStmt ex9 = new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b",new IntType()),
                        new CompStmt(new VarDeclStmt("c",new IntType()),
                                new CompStmt(new AssignStmt("a",new ValueExp(new IntValue(1))),
                                        new CompStmt(new AssignStmt("b",new ValueExp(new IntValue(2))),
                                                new CompStmt(new AssignStmt("c",new ValueExp(new IntValue(5))),
                                                        new CompStmt(new SwitchStatement(new ArithExp('*',new VarExp("a"),new ValueExp(new IntValue(10))),new ArithExp('*', new VarExp("b"), new VarExp("c")),
                                                                new ValueExp(new IntValue(10)), new CompStmt(new PrintStmt(new VarExp("a")),new PrintStmt(new VarExp("b"))),
                                                                new CompStmt(new PrintStmt(new ValueExp(new IntValue(100))),new PrintStmt(new ValueExp(new IntValue(200)))),new PrintStmt(new ValueExp(new IntValue(300)))),new PrintStmt(new ValueExp(new IntValue(300))))))))));



        IStmt ex10 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(0))),
                new CompStmt(new RepeatStmt(new CompStmt(new forkStmt(new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1)))))), new AssignStmt("v", new ArithExp('+',new VarExp("v"), new ValueExp(new IntValue(1))))), new RationalExp("==",new VarExp("v"), new ValueExp(new IntValue(3)))),
                        new CompStmt(new VarDeclStmt("x", new IntType()),
                        new CompStmt(new AssignStmt("x", new ValueExp(new IntValue(1))),
                                new CompStmt(new VarDeclStmt("y", new IntType()),
                                new CompStmt(new AssignStmt("y", new ValueExp(new IntValue(2))),
                                        new CompStmt(new VarDeclStmt("z", new IntType()),
                                        new CompStmt(new AssignStmt("z", new ValueExp(new IntValue(3))),
                                                new CompStmt(new VarDeclStmt("w", new IntType()),
                                                new CompStmt(new AssignStmt("w", new ValueExp(new IntValue(4))),
                                                        new PrintStmt(new ArithExp('*',new VarExp("v"), new ValueExp(new IntValue(10)))))))))))))));


        IStmt ex12 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(0))),
                        new CompStmt(new RepeatStmt(new CompStmt(new forkStmt(new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1)))))), new AssignStmt("v", new ArithExp('+',new VarExp("v"), new ValueExp(new IntValue(1))))), new RationalExp("==",new VarExp("v"), new ValueExp(new IntValue(3)))),
                                new CompStmt(new VarDeclStmt("x", new IntType()),
                                        new CompStmt(new AssignStmt("x", new ValueExp(new IntValue(1))),
                                                new CompStmt(new NopStmt(),
                                                new CompStmt(new VarDeclStmt("y", new IntType()),
                                                        new CompStmt(new AssignStmt("y", new ValueExp(new IntValue(3))),
                                                                new CompStmt(new NopStmt(),
                                                                        new PrintStmt(new ArithExp('*',new VarExp("v"), new ValueExp(new IntValue(10)))))))))))));


        IStmt ex11 = new CompStmt(new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(20))),
                        new ForStmt(new VarExp("v"),
                                new ValueExp(new IntValue(0)),
                                new ValueExp(new IntValue(3)),
                                new ArithExp('+', new VarExp("v"), new ValueExp(new IntValue(1))),
                                new forkStmt(new CompStmt(new PrintStmt(new VarExp("v")),
                                        new AssignStmt("v", new ArithExp('+', new VarExp("v"), new ValueExp(new IntValue(1))))))))),
                new PrintStmt(new ArithExp('*',new VarExp("v"), new ValueExp(new IntValue(10)))));

        list.add(ex1);
        list.add(ex2);
        list.add(ex3);
        list.add(ex4);
        list.add(ex5);
        list.add(ex6);
        list.add(ex7);
        list.add(ex8);
        list.add(ex9);
        list.add(ex10);
        list.add(ex11);
        list.add(ex12);
        return list;
    }

    private void display(){

        List<IStmt> list = exemples();
        selectItemListView.setItems(FXCollections.observableArrayList(list));
        selectItemListView.getSelectionModel().select(0);
        selectButton.setOnAction(actionEvent -> {
            int index = selectItemListView.getSelectionModel().getSelectedIndex();
            IStmt selectedPrg = selectItemListView.getItems().get(index);
            index++;
            PrgState prgState = new PrgState(selectedPrg);
            IRepo repo = new Repo("log" + index + ".txt");
            Contr controller = new Contr(repo);
            try {
                controller.addProgram(prgState);
            } catch (MyException e) {
                e.printStackTrace();
            }
            try{
                System.out.println(selectedPrg);
                selectedPrg.typecheck(new Dict<String, IType>());
                mainWindowContr.setController(controller);
            } catch(MyException e){
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
            mainWindowContr.setController(controller);
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        display();
    }
}
