package Tests;

import Tests.BoardTests.BoardBadCreationTest;
import Tests.BoardTests.BoardCreationTest;
import Tests.BoardTests.BoardValidateTest;
import Tests.ParserTests.ParserBadTest;
import Tests.ParserTests.ParserGoodTest;
import Tests.ParserTests.ParserOnelineTest;
import Tests.RobotTests.RobotLeftTest;
import Tests.RobotTests.RobotMoveTest;
import Tests.RobotTests.RobotPlaceTest;
import Tests.RobotTests.RobotRightTest;

public class TestMain {

    public static void main(String[] args) {
        /*
            run assorted tests
         */
        new RobotMoveTest().execute(true);
        new RobotLeftTest().execute(true);
        new RobotRightTest().execute(true);
        new RobotPlaceTest().execute(true);
        new ParserGoodTest().execute(true);
        new ParserBadTest().execute(true);
        new ParserOnelineTest().execute(true);
        new BoardCreationTest().execute(true);
        new BoardBadCreationTest().execute(true);
        new BoardValidateTest().execute(true);
    }
}
