package ToyRobot;

import InstructionParser.Instruction;
import MathLib.OrthoVec2;
import MathLib.Vec2;

public abstract class ToyRobot {

     //the direction the robot is facing
     protected OrthoVec2 heading;

     //this current position in the grid
     protected Vec2 position;

     public ToyRobot(){
          this.heading = new OrthoVec2(1,0);
          this.position = new Vec2(0,0);
     }

     //actual effects of commands is up to specific robot implementation
     public abstract void place(Vec2 position, OrthoVec2 heading);
     public abstract void move();
     public abstract void left();
     public abstract void right();
     public abstract String getReport();

     public OrthoVec2 getHeading() {
          return heading;
     }

     public Vec2 getPosition() {
          return position;
     }
}
