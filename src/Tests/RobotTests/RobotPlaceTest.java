package Tests.RobotTests;

import Environment.Board;
import InstructionParser.Instruction;
import MathLib.Vec2;
import Tests.Test;
import ToyRobot.SimpleRobot;
import ToyRobot.ToyRobot;

import static InstructionParser.Instruction.Direction.NORTH;
import static InstructionParser.Instruction.Direction.WEST;

public class RobotPlaceTest extends Test {

    public RobotPlaceTest() {
        super("Robot place test");
    }

    @Override
    public boolean run() {
        Board board = new Board(5, 5);
        ToyRobot robot = new SimpleRobot();
        Instruction.PlaceInstruction instruction = new Instruction.PlaceInstruction(2, 3, WEST);
        instruction.execute(board, robot);
        return robot.getHeading().equals(WEST.getHeading()) && robot.getPosition().equals(new Vec2(2,3));
    }

}
