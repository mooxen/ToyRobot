import Environment.Board;
import InstructionParser.IInstructionParser;
import InstructionParser.Instruction;
import ToyRobot.ToyRobot;

import java.util.List;

public class Simulator {

    private IInstructionParser instructionParser;
    private ToyRobot robot;
    private Board board;


    public Simulator(IInstructionParser instructionParser, ToyRobot robot, Board board){
        this.instructionParser = instructionParser;
        this.robot = robot;
        this.board = board;
    }

    public void execute(String instructions){

        List<Instruction> instructionList = null;
        try {
            instructionList = this.instructionParser.parse(instructions);
        } catch (IInstructionParser.ParserException e) {
            //abort execution if the instructions cant be parsed.

            System.out.println(e.getMessage());
            return;
        }
        boolean foundPlace = false;
        for(Instruction instruction : instructionList){

            //do not begin execution until the robot has been placed
            if(instruction instanceof Instruction.PlaceInstruction){
                foundPlace = true;
            }


            if(foundPlace) instruction.execute(board, robot);
        }
    }
}
