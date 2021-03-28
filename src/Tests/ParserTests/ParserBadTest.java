package Tests.ParserTests;

import InstructionParser.BasicInstructionParser;
import InstructionParser.IInstructionParser;
import InstructionParser.Instruction;
import Tests.ExceptionTest;
import Tests.Test;

import java.util.List;

public class ParserBadTest extends ExceptionTest {

    public ParserBadTest() {
        super("Parser bad test", IInstructionParser.ParserException.class);
    }

    @Override
    public boolean runExcept() throws IInstructionParser.ParserException {
        BasicInstructionParser basicInstructionParser = new BasicInstructionParser();
        List<Instruction> instructions;
        instructions = basicInstructionParser.parse("PLAsCE 1,2,EAST" +
                "MOVE" +
                "MOVE" +
                "REPORT");

        return instructions.size() == 4;
    }

    @Override
    protected boolean run() {
        return false;
    }
}
