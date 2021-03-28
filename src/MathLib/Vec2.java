package MathLib;

/*
    A general purpose integer value 2d vector class
 */
public class Vec2 {

    int x;
    int y;

    public Vec2(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Vec2 add(Vec2 other){
        return new Vec2(x + other.x, y + other.y);
    }


    @Override
    public String toString() {
        return "Vec2<"+ this.x +", "+ this.y+">";
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }


    //conventional Java equals override
    @Override
    public boolean equals(Object obj) {

        if(obj instanceof Vec2){
            Vec2 other = (Vec2) obj;
            return other.x == this.x && other.y == this.y;
        }

        return obj == this;

    }
}
