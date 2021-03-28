package Environment;

import MathLib.Vec2;

public class Board {

    private int width;
    private int height;

    public Board(int width, int height){
        this.width = Math.max(1,Math.min(width, Integer.MAX_VALUE));
        this.height = Math.max(1,Math.min(height, Integer.MAX_VALUE));
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean validatePosition(Vec2 position){
        return 0 <= position.getX() && position.getX() < this.width &&
               0 <= position.getY() && position.getY() < this.height;
    }
}
