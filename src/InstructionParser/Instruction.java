package InstructionParser;

import Environment.Board;
import MathLib.OrthoVec2;
import MathLib.Vec2;
import ToyRobot.ToyRobot;


/*
    Instructions handle the required checks for validity,
    then calls one of the simple commands on the robot.

    Allows for additional instructions to be added simply,
    and allows instructions to be compounds of multiple
    simple robot instructions
 */
public abstract class Instruction {

    public enum Direction {
        EAST(1, 0),
        WEST(-1, 0),
        NORTH(0, 1),
        SOUTH(0, -1);

        /*
            QOL additional enum data, provides simple
            lookup for reporting
         */
        OrthoVec2 heading;

        Direction(int x, int y){
            heading = new OrthoVec2(x,y);
        }

        public OrthoVec2 getHeading() {
            return this.heading;
        }
    }

    public static class PlaceInstruction extends Instruction {

        private int X;
        private int Y;
        private Direction direction;

        public PlaceInstruction(int X, int Y, Direction direction){
            super("PLACE");
            this.X = X;
            this.Y = Y;
            this.direction = direction;
        }

        @Override
        public boolean execute(Board board, ToyRobot robot) {
            if(board.validatePosition(new Vec2(this.X, this.Y))){
                robot.place(new Vec2(this.X, this.Y), this.direction.getHeading());
                return true;
            }

            return false;
        }

        @Override
        public String toString() {
            return this.instructionName + "("+this.X+", "+this.Y+", "+ this.direction.name() +")";
        }
    }

    public static class MoveInstruction extends Instruction {

        public MoveInstruction(){
            super("MOVE");
        }

        @Override
        public boolean execute(Board board, ToyRobot robot) {
            if(board.validatePosition(robot.getPosition().add(robot.getHeading()))){
                robot.move();
                return true;
            }
            return false;
        }
    }

    public static class LeftInstruction extends Instruction {

        public LeftInstruction(){
            super("LEFT");
        }

        @Override
        public boolean execute(Board board, ToyRobot robot) {
            robot.left();
            return true;
        }
    }

    public static class RightInstruction extends Instruction {

        public RightInstruction(){
            super("RIGHT");
        }

        @Override
        public boolean execute(Board board, ToyRobot robot) {
            robot.right();
            return true;
        }
    }

    public static class ReportInstruction extends Instruction {

        public ReportInstruction(){
            super("REPORT");
        }

        @Override
        public boolean execute(Board board, ToyRobot robot) {
            System.out.println(robot.getReport());
            return true;
        }
    }

    protected String instructionName;

    public Instruction(String instructionName){
        this.instructionName = instructionName;
    }

    public abstract boolean execute(Board board, ToyRobot robot);

    @Override
    public String toString() {
        return this.instructionName;
    }
}
