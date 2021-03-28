package Tests.BoardTests;

import Environment.Board;
import MathLib.Vec2;
import Tests.Test;

import java.util.ArrayList;
import java.util.List;

public class BoardValidateTest extends Test {


    public BoardValidateTest() {
        super("Board bad creation test");
    }

    @Override
    protected boolean run() {
        Board board = new Board(5,5);
        List<Vec2> positions = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                positions.add(new Vec2(i,j));
            }
        }

        boolean allGood = true;

        for(Vec2 vec : positions){
            allGood = allGood && board.validatePosition(vec);
        }
        //should default to width 1 height 1
        return allGood;
    }
}
