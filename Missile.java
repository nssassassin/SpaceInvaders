public class Missile extends DefaultCritter{


    public Missile(double xInput, double yInput, double angleInput) {
        super(xInput,yInput, Math.cos(Math.toRadians( angleInput))*DefaultCritter.missileSpeed, Math.sin(Math.toRadians(angleInput))*DefaultCritter.missileSpeed, DefaultCritter.missileSize);


    }

    /*The following code makes the private code accessible
    * IntelliJ also automatically inserts this based on the private variables*/

    public double getX() {
        return super.getX();
    }

    public double getY() {
        return super.getY();
    }

    public double getSize() {
        return super.getSize();
    }

    public double getMissilesYTop() {
        return (super.getY() + super.getSize());
    }

    public double getMissilesXMax() { return super.getX() + super.getSize();}

    public double getMissilesXMin() { return super.getX() - super.getSize();}


    public void action(){
        super.action();
    }

    public void drawMissile(){
        StdDraw.setPenColor(StdDraw.MAGENTA);
        StdDraw.filledCircle(super.getX(),super.getY(),super.getSize());
    }




}