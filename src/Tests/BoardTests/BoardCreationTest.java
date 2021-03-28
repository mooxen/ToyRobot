package Tests.BoardTests;

import Environment.Board;
import Tests.Test;

public class BoardCreationTest extends Test {


    public BoardCreationTest() {
        super("Board creation test");
    }

    @Override
    protected boolean run() {
        Board board = new Board(5,5);
        return board.getHeight() == 5 && board.getWidth() == 5;
    }
}
