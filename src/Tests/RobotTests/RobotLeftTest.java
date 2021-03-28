package Tests.RobotTests;

import Environment.Board;
import InstructionParser.Instruction;
import MathLib.Vec2;
import Tests.Test;
import ToyRobot.SimpleRobot;
import ToyRobot.ToyRobot;

import static InstructionParser.Instruction.Direction.NORTH;

public class RobotLeftTest extends Test {

    public RobotLeftTest() {
        super("Robot left test");
    }

    @Override
    public boolean run() {
        Board board = new Board(5, 5);
        ToyRobot robot = new SimpleRobot();
        Instruction.LeftInstruction instruction = new Instruction.LeftInstruction();
        instruction.execute(board, robot);
        return robot.getHeading().equals(NORTH.getHeading());
    }

}
