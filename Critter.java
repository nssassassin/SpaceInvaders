
public class Critter extends DefaultCritter{
    private boolean isKilled;

    public Critter(double xInput, double yInput) {
        super(xInput, yInput, DefaultCritter.critterSpeed,0,  DefaultCritter.critterSize);
        isKilled = false;
    }
    public double getX() {
        return super.getX();
    }

    public double getSizeCritter() {
        return super.getSize();
    }

    public double getY() {
        return super.getY();
    }

    public boolean isKilled() {
        return isKilled;
    }

    public void setSpeedX(double speedX) {
        super.setSpeedX(speedX);
    }

    public void setSpeedY(double speedY) {
        super.setSpeedY(speedY);
    }

    public void setKilled(boolean yesOrNo){
        isKilled = yesOrNo;
    }

    public void setY(double y) {
        super.setY(y);
    }

    public void action(){
        super.action();
    }

    public boolean hitMissile(Missile missile1){
        if( missile1.getMissilesYTop()>=getY()-getSize()&&
            missile1.getMissilesYTop()<=getY()+getSize()&&
            missile1.getX()>=getX()-getSize()&&
            missile1.getX()<=getX()+getSize()){
            return true;
        }
        else return false;
    }


    public void drawCritter(){
        StdDraw.picture(super.getX(),super.getY(), "gth.png",super.getSize()*2,super.getSize()*2);
    }
}
/*https://freesvg.org/funny-green-monster
OpenClipart
SVG ID: 67049
License: Public Domain

 */