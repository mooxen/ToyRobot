package Tests;

import InstructionParser.IInstructionParser;

public abstract class Test {

    String name;

    public Test(String name){
        this.name = name;
    }

    public boolean execute(boolean individualReport){
        boolean result = this.run();
        if(individualReport){
            System.out.println(this.name + " " + (result ? "PASSED" : "FAILED"));
        }

        return result;
    }

    public boolean execute(){
        return this.run();
    }

    protected abstract boolean run();
}
