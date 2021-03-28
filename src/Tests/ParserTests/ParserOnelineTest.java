package Tests.ParserTests;

import InstructionParser.BasicInstructionParser;
import InstructionParser.IInstructionParser;
import InstructionParser.Instruction;
import Tests.Test;

import java.util.List;

public class ParserOnelineTest extends Test {

    public ParserOnelineTest() {
        super("Parser oneline test");
    }

    @Override
    public boolean run() {
        BasicInstructionParser basicInstructionParser = new BasicInstructionParser();
        List<Instruction> instructions;
        try {
            instructions = basicInstructionParser.parse("PLACE 1,2,EAST " +
                    "MOVE " +
                    "MOVE " +
                    "REPORT");
        } catch (IInstructionParser.ParserException e) {
            return false;
        }
        return instructions.size() == 4;
    }

}
