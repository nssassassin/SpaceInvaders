public class Enemy extends DefaultCritter{

    private int hitPoints;

    public Enemy(double xInput, double yInput, double dXIn, double speed, double sizeIn){
        super(xInput,yInput, dXIn,speed,sizeIn);
        hitPoints =  DefaultCritter.enemyHitPoints;
    }

    public void removeHitPoints(){
        hitPoints = hitPoints - 100;
    }

    public void moveEnemy(){
        if(getX()<1-getSize()&&getX()>-1+getSize()){
            action();
        }
        else{
            setSpeedX(-getSpeedX());
            action();
        }
    }

    public int getHitPoints() {
        return hitPoints;
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

    public void drawEnemy(){
        StdDraw.picture(getX(),getY(), "ufo-42453_1280.png",getSize()*2,getSize()*2);
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }
}
/*
https://www.needpix.com/photo/33309/ufo-spaceship-science-alien-fantasy-universe-technology-spacecraft-flying
 Creative Commons Zero License for Public Domain

 */