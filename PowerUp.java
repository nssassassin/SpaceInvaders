import java.util.ArrayList;

public class PowerUp extends DefaultCritter {
    private int typePower;


    public PowerUp(){

        super(Math.random()*2-1,-0.95,0,0,DefaultCritter.shooterSize);
        typePower = (int)(Math.random()*2)+1;                                                                                       // change to 3 later

    }



    public int powerUpUsed(Shooter shooter, ArrayList<Missile> missile1){
        if((shooter.getX()-shooter.getSize() <=  getX()+getSize()&&
                shooter.getX()-shooter.getSize()>=getX()-getSize())||
                ((shooter.getX()+shooter.getSize()>=getX()-getSize())&&
                        (shooter.getX()+shooter.getSize()<=getX()+getSize())))
        {
            switch(typePower) {
                case 1: // speed
                    return 1;
                case 2://missile rate faster
                    return 2;
                //case 3:
                //
                //    return 3;
                //
            }
        }
        return 0;
    }
    public void drawPowerUp(){
        switch(typePower) {
            case 1:
                StdDraw.picture(getX(),getY(), "fast-forward-27916_640.png", 2*getSize(),2*getSize());
                break;
            case 2:
                StdDraw.picture(getX(),getY(), "target-39708_1280.png", 2*getSize(),2*getSize());
                break;
            // case 3:
            //      StdDraw.filledSquare(super.getX(),super.getY(),super.getSize());
            //      break;
        }
    }

}

/*
Fast forward
Image by Clker-Free-Vector-Images from Pixabay
https://pixabay.com/vectors/fast-forward-action-arrows-arrow-27916/

Bullseye
https://www.needpix.com/photo/download/31037/target-circle-bullseye-achievement-competition-shot-sport-goal-center
Clker-Free-Vector-Images (pixabay.com)

 */

