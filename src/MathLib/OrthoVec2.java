package MathLib;

/*
    A class for 2d vectors that are part of a orthonormal
    basis. Maintains unit length and orthognal angles
 */
public class OrthoVec2 extends Vec2{

    public OrthoVec2(int x, int y){

        super((int) Math.signum(x), (int) Math.signum(y));

    }

    //rotation matrix formula
    //read more: https://en.wikipedia.org/wiki/Rotation_matrix
    private OrthoVec2 rotate(double angle){
        int newx = Math.toIntExact(Math.round(this.x * Math.cos(angle) - this.y * Math.sin(angle)));
        int newy = Math.toIntExact(Math.round(this.x * Math.sin(angle) + this.y * Math.cos(angle)));

        return new OrthoVec2(newx, newy);
    }

    public OrthoVec2 rotateCW(){
        return this.rotate(-Math.PI/2f);
    }

    public OrthoVec2 rotateCCW(){
        return this.rotate(Math.PI/2f);
    }

    @Override
    public String toString() {
        return "OrthoVec2<"+ this.x +", "+ this.y+">";
    }

    //conventional Java equals override
    @Override
    public boolean equals(Object obj) {

        if(obj instanceof OrthoVec2){
            OrthoVec2 other = (OrthoVec2) obj;
            return other.x == this.x && other.y == this.y;
        }

        return obj == this;

    }
}
