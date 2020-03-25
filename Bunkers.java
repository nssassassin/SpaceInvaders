public class Bunkers extends DefaultCritter {
    int hitPoints;

    public Bunkers(double x, double y){
        super(x,y,0,0, DefaultCritter.bunkerHitPoints);
        hitPoints = DefaultCritter.bunkerHitPoints;
    }

    public int getHitPoints() {
        return hitPoints;
    }
}
