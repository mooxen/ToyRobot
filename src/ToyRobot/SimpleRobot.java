package ToyRobot;

import InstructionParser.Instruction;
import MathLib.OrthoVec2;
import MathLib.Vec2;

public class SimpleRobot extends ToyRobot {


    @Override
    public String getReport() {
        Instruction.Direction direction = Instruction.Direction.EAST;
        for(Instruction.Direction dir : Instruction.Direction.values()){
            if(dir.getHeading().equals(this.heading)){
                direction = dir;
            }
        }

        return this.position.getX() + "," + this.position.getY() + "," + direction.name();
    }


    public void place(Vec2 position, OrthoVec2 heading){
        this.position = position;
        this.heading = heading;
    }

    public void move(){
        this.position = this.position.add(this.heading);
    }

    public void left(){
        this.heading = this.heading.rotateCCW();
    }

    public void right(){
        this.heading = this.heading.rotateCW();
    }

    @Override
    public String toString() {
        return this.heading + " -- " + this.position;
    }
}
