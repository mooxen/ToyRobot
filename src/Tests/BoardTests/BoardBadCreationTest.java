package Tests.BoardTests;

import Environment.Board;
import Tests.Test;

public class BoardBadCreationTest extends Test {


    public BoardBadCreationTest() {
        super("Board bad creation test");
    }

    @Override
    protected boolean run() {
        Board board = new Board(-1,-1);
        //should default to width 1 height 1
        return board.getHeight() == 1 && board.getWidth() == 1;
    }
}
