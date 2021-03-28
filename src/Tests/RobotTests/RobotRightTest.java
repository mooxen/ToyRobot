package Tests.RobotTests;

import Environment.Board;
import InstructionParser.Instruction;
import Tests.Test;
import ToyRobot.SimpleRobot;
import ToyRobot.ToyRobot;

import static InstructionParser.Instruction.Direction.SOUTH;

public class RobotRightTest extends Test {

    public RobotRightTest() {
        super("Robot right test");
    }

    @Override
    public boolean run() {
        Board board = new Board(5, 5);
        ToyRobot robot = new SimpleRobot();
        Instruction.RightInstruction instruction = new Instruction.RightInstruction();
        instruction.execute(board, robot);
        return robot.getHeading().equals(SOUTH.getHeading());
    }

}
