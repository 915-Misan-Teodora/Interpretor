package com.example.demo1;

import Controller.Contr;
import Exceptions.MyException;
import Model.ADT.IStack;
import Model.PrgState;
import Model.Statements.IStmt;
import Model.Values.IValue;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;


public class MainWindowController implements Initializable {
    @FXML
    private ListView<String> exeStackView;
    @FXML
    private TableView<Map.Entry<String, IValue>> symTable;
    @FXML
    private TableColumn<Map.Entry<String, IValue>,String> symTableNames;
    @FXML
    private TableColumn<Map.Entry<String, IValue>,String> symTableValues;
    @FXML
    private TextField progStatesCount;
    @FXML
    private Button execButton;
    @FXML
    private TableView<Map.Entry<Integer,IValue>> heapTableView;
    @FXML
    private TableColumn<Map.Entry<Integer,IValue>, Integer> heapTableAddr;
    @FXML
    private TableColumn<Map.Entry<Integer,IValue>, String> heapTableValues;
    @FXML
    private ListView<String> outView;
    @FXML
    private ListView<String> fileTableView;
    private Contr controller;
    @FXML
    private ListView<Integer> progIdentifiers;


    private List<Integer> getProgramStateIds(List<PrgState> programStateList) {
        return programStateList.stream().map(PrgState::getId).collect(Collectors.toList());
    }

    private void populateProgramStateIdentifiers() {
        List<PrgState> programStates = controller.getRepo().getPrgList();

        progIdentifiers.setItems(FXCollections.observableArrayList(controller.getRepo().getPrgList().stream().map(PrgState::getId).collect(Collectors.toList())));
        progIdentifiers.refresh();

        progStatesCount.setText("" + programStates.size());
    }


    public void executeOneStep() throws MyException {
        if(controller==null){
            Alert error = new Alert(Alert.AlertType.ERROR,"No program selected!");
            error.show();
            execButton.setDisable(true);
            return;
        }
        PrgState programState = getSelectedProgramState();
        if(programState!=null && !programState.isNotCompleted()){
            Alert error = new Alert(Alert.AlertType.ERROR,"Nothing left to execute!");
            error.show();
            return;
        }
        try {
            controller.executeOneStep();
            changePrgState(programState);
            if(controller.getRepo().getPrgList().size()==0)
                execButton.setDisable(true);
        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.ERROR,e.getMessage());
            error.show();
            execButton.setDisable(true);
        }
    }

    private void changePrgState(PrgState currentPrg) {
        if (currentPrg == null) {
            return;
        }
        populateProgramStateIdentifiers();
        populateExeStackView(currentPrg);
        populateSymTableView(currentPrg);
        populateOutView(currentPrg);
        populateFileTableView(currentPrg);
        populateHeapTableView(currentPrg);
    }
    private void populateHeapTableView(PrgState prg){
        heapTableView.setItems(FXCollections.observableList(new ArrayList<>(prg.getHeap().toHasMap().entrySet())));
        heapTableView.refresh();
    }

    private void populateOutView(PrgState prg){
        outView.setItems(FXCollections.observableArrayList(prg.getOut().getContent()));
    }

    private void populateFileTableView(PrgState prg){
        fileTableView.setItems(FXCollections.observableArrayList(prg.getFileTable().getContent().keySet()));
    }

    private void populateExeStackView(PrgState prg) {
        IStack<IStmt> stack = prg.getExeStack();
        List<String> stackOutput = new ArrayList<>();
        for (IStmt stm : stack.getStk()){
            stackOutput.add(stm.toString());
        }
        Collections.reverse(stackOutput);
        exeStackView.setItems(FXCollections.observableArrayList(stackOutput));

    }

    private void populateSymTableView(PrgState givenProgramState){
        symTable.setItems(FXCollections.observableList(new ArrayList<>(givenProgramState.getSymTable().getContent().entrySet())));
        symTable.refresh();
    }

    public void setController(Contr contr){
        this.controller = contr;
        populateProgramStateIdentifiers();
        execButton.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        heapTableAddr.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        symTableNames.setCellValueFactory(p->new SimpleStringProperty(p.getValue().getKey() + " "));
        symTableValues.setCellValueFactory(p-> new SimpleStringProperty(p.getValue().getValue() + " "));

        progIdentifiers.setOnMouseClicked(mouseEvent -> changePrgState(getSelectedProgramState()));
        execButton.setDisable(true);
    }

    private PrgState getSelectedProgramState(){
        if(progIdentifiers.getSelectionModel().getSelectedIndex() == -1)
            return null;
        int id = progIdentifiers.getSelectionModel().getSelectedItem();
        return controller.getRepo().getPrgWithId(id);
    }
}