package Controller;

import Exceptions.MyException;
import Model.ADT.Dict;
import Model.ADT.IDict;
import Model.ADT.IHeap;
import Model.ADT.IStack;
import Model.PrgState;
import Model.Statements.IStmt;
import Model.Types.IType;
import Model.Values.IValue;
import Model.Values.RefValue;
import Repository.IRepo;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Contr {
    IRepo repo;
    // add a new field "executor" of type ExecutorService
    ExecutorService executor;
    public Contr(IRepo repo){
        this.repo = repo;
    }

    public void addProgram(PrgState prg) throws MyException {
        try{
            repo.add(prg);
        }
        catch(RuntimeException e){
            throw new MyException("The program could not be add");
        }
    }

    public IRepo getRepo(){
        return repo;
    }




    public void executeOneStep() throws MyException {
        executor = Executors.newFixedThreadPool(2);
        repo.setPrgList(removeCompletedPrg(repo.getPrgList()));
        List<PrgState> programStates = repo.getPrgList();
        if(programStates.size() > 0)
        {
            try {
                oneStepForAllPrg(repo.getPrgList());
            } catch (InterruptedException e) {
                System.out.println();
            }
            repo.setPrgList(removeCompletedPrg(repo.getPrgList()));
            executor.shutdownNow();
            garbCollectorForAll(programStates);
        }
    }

    public void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException {
        prgList.forEach(prg-> {
            try {
                repo.logPrgStateExec(prg);
            } catch (MyException | IOException e) {
                e.printStackTrace();
            }
        });

        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>)(p::oneStep))
                .collect(Collectors.toList());

        List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        prgList.addAll(newPrgList);
        prgList.forEach(prg ->
        {
            try {
                repo.logPrgStateExec(prg);
            } catch (MyException | IOException e) {
                e.printStackTrace();
            }
        });

        // save the current programs in the repository
        repo.setPrgList(prgList);
    }

    public void allStep() throws MyException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        //remove the completed programs
        List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());

        while(prgList.size() > 0){
            //garbageCollector()
            garbCollectorForAll(prgList);
            oneStepForAllPrg(prgList);
            //remove the completed programs
            prgList = removeCompletedPrg(repo.getPrgList());
        }
        executor.shutdownNow();
        //HERE the repository still contains at least one Completed Prg
        // and its List<PrgState> is not empty. Note that oneStepForAllPrg calls the method
        //setPrgList of repository in order to change the repository

        // update the repository state
        repo.setPrgList(prgList);
      
    }

    public void garbCollectorForAll(List<PrgState> list){
        list.forEach(prg->prg.getHeap().setHashMap((HashMap<Integer, IValue>) garbageCollector(getSymbolTableAddresses(prg.getSymTable()),getHeapTableAddresses(prg.getHeap()), prg.getHeap().toHasMap())));
    }


    private Map<Integer, IValue> garbageCollector(Collection<Integer> symbolTableAddresses,
                                                    Collection<Integer> heapTableAddresses,
                                                    HashMap<Integer, IValue> heapTable) {
    return heapTable.entrySet()
            .stream()
            .filter(e -> symbolTableAddresses.contains(e.getKey()) || heapTableAddresses.contains(e.getKey()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
}

    private Collection<Integer> getSymbolTableAddresses(final IDict<String, IValue> symbolTable) {
        return symbolTable.values()
                .stream()
                .filter(ref -> ref instanceof RefValue)
                .map(value -> {
                    RefValue refValue = (RefValue) value;
                    return refValue.getAddress();
                })
                .collect(Collectors.toList());
    }

    private Collection<Integer> getHeapTableAddresses(final IHeap<Integer, IValue> heapTable) {
        return heapTable.getValue()
                .stream()
                .filter(ref -> ref instanceof RefValue)
                .map(value -> {
                    RefValue refValue = (RefValue) value;
                    return refValue.getAddress();
                })
                .collect(Collectors.toList());
    }

    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
        return inPrgList.stream()
                .filter(PrgState::isNotCompleted)
                .collect(Collectors.toList());
    }


    void typeCheck() throws MyException {
        IDict<String, IType> typeEnv = new Dict<>();
        List<PrgState> prgStates = repo.getPrgList();

        if(prgStates.size() != 1)
            throw new MyException("no typecheck");

        PrgState prgState = prgStates.get(0);
        IStack<IStmt> exeStack = prgState.getExeStack();
        while(!exeStack.isEmpty()){
            IStmt topStmt = exeStack.top();
            exeStack = (IStack<IStmt>) exeStack.pop();
            typeEnv = topStmt.typecheck(typeEnv);
        }
    }


}
