
public class Critter extends DefaultCritter{
    private boolean isKilled;

    public Critter(double xInput, double yInput) {
        super(xInput, yInput, DefaultCritter.critterSpeed,0,  DefaultCritter.critterSize);
        isKilled = false;
    }


    public double getSizeCritter() {
        return super.getSize();
    }



    public boolean isKilled() {
        return isKilled;
    }


    public void setKilled(boolean yesOrNo){
        isKilled = yesOrNo;
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