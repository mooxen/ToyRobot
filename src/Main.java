import Environment.Board;
import InstructionParser.BasicInstructionParser;
import InstructionParser.IInstructionParser;
import MathLib.OrthoVec2;
import Tests.ParserTests.ParserBadTest;
import Tests.ParserTests.ParserGoodTest;
import Tests.ParserTests.ParserOnelineTest;
import Tests.RobotTests.RobotLeftTest;
import Tests.RobotTests.RobotMoveTest;
import Tests.RobotTests.RobotPlaceTest;
import Tests.RobotTests.RobotRightTest;
import Tests.Test;
import ToyRobot.SimpleRobot;
import ToyRobot.ToyRobot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {


        StringBuilder inputData = new StringBuilder();
        if (args.length > 0){
            String filename = args[0];
            try {
                File file = new File(filename);
                Scanner myReader = new Scanner(file);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    inputData.append(data).append("\n");
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("file "+filename+" could not be found. Aborting");
                return;
            }

        }else{
            System.out.println("Please provide file name of file containing robot commands");
            return;
        }
        IInstructionParser basicInstructionParser = new BasicInstructionParser();
        ToyRobot robot = new SimpleRobot();

        Simulator simulator = new Simulator(basicInstructionParser, robot, new Board(5,5));

        simulator.execute(inputData.toString());
    }
}
