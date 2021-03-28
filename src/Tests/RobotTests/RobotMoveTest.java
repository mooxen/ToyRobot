package Tests.RobotTests;

import Environment.Board;
import InstructionParser.Instruction;
import MathLib.Vec2;
import Tests.Test;
import ToyRobot.SimpleRobot;
import ToyRobot.ToyRobot;

public class RobotMoveTest extends Test {

    public RobotMoveTest() {
        super("Robot move test");
    }

    @Override
    public boolean run() {
        Board board = new Board(5, 5);
        ToyRobot robot = new SimpleRobot();
        Instruction.MoveInstruction instruction = new Instruction.MoveInstruction();
        instruction.execute(board, robot);
        return robot.getPosition().equals(new Vec2(1, 0));
    }

}
