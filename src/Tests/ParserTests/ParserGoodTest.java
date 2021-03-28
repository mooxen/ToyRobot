package Tests.ParserTests;

import Environment.Board;
import InstructionParser.BasicInstructionParser;
import InstructionParser.IInstructionParser;
import InstructionParser.Instruction;
import Tests.Test;
import ToyRobot.SimpleRobot;
import ToyRobot.ToyRobot;

import java.util.ArrayList;
import java.util.List;

import static InstructionParser.Instruction.Direction.NORTH;

public class ParserGoodTest extends Test {

    public ParserGoodTest() {
        super("Parser good test");
    }

    @Override
    public boolean run() {
        BasicInstructionParser basicInstructionParser = new BasicInstructionParser();
        List<Instruction> instructions;
        try {
            instructions = basicInstructionParser.parse("PLACE 1,2,EAST\n" +
                    "MOVE\n" +
                    "MOVE\n" +
                    "REPORT");
        } catch (IInstructionParser.ParserException e) {
            return false;
        }
        return instructions.size() == 4;
    }

}
