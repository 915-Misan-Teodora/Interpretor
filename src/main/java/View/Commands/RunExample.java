package View.Commands;

import Controller.Contr;
import Exceptions.MyException;

import java.io.IOException;

public class RunExample extends Command{
    private Contr ctr;

    public RunExample(String key, String desc,Contr ctr){
        super(key, desc);
        this.ctr=ctr;
    }
    @Override
    public void execute() throws MyException {
        try{
            ctr.allStep();
        }
        catch(MyException | InterruptedException e) {
            //throw new MyException("we could not execute ");
            System.out.println(e.getMessage());
        }
    }
}
