public class Missile extends DefaultCritter{


    public Missile(double xInput, double yInput, double angleInput) {
        super(  xInput,
                yInput,
                Math.cos(Math.toRadians( angleInput))*DefaultCritter.missileSpeed,
                Math.sin(Math.toRadians(angleInput))*DefaultCritter.missileSpeed,
                DefaultCritter.missileSize);
    }

    /*The following code makes the private code accessible
    * IntelliJ also automatically inserts this based on the private variables*/



    public double getMissilesYTop() {
        return (getY() + getSize());
    }

    public double getMissilesXMax() { return getX() + getSize();}

    public double getMissilesXMin() { return getX() - getSize();}



    public void drawMissile(){
        StdDraw.setPenColor(StdDraw.MAGENTA);
        StdDraw.filledCircle(getX(),getY(),getSize());
    }




}
/*
https://freesound.org/people/RSilveira_88/sounds/216273/
https://creativecommons.org/licenses/by/3.0/

 */