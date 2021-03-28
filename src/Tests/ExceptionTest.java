package Tests;

import InstructionParser.IInstructionParser;

public abstract class ExceptionTest extends Test {


    private Class<? extends Exception> exceptionClass;
    public ExceptionTest(String name, Class<? extends Exception> exceptionClass){
        super(name);
        this.exceptionClass = exceptionClass;
    }


    @Override
    public boolean execute(boolean individualReport) {
        boolean result = false;
        try {
            this.runExcept();
        } catch (Exception e){
            if(e.getClass() == exceptionClass){
                result = true;
            }
        }

        if(individualReport)System.out.println(this.name + " " + (result ? "PASSED" : "FAILED"));
        return result;
    }

    @Override
    public boolean execute() {
        try {
            this.runExcept();
        } catch (Exception e){
            if(e.getClass() == exceptionClass){
                return true;
            }
        }

        return false;
    }

    public abstract boolean runExcept() throws Exception;
}
