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
        if(super.getX()<1-super.getSize()&&super.getX()>-1+super.getSize()){
            super.action();
        }
        else{
            super.setSpeedX(-super.getSpeedX());
            super.action();
        }
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public boolean hitMissile(Missile missile1){
        if( missile1.getMissilesYTop()>=super.getY()-super.getSize()&&
                missile1.getMissilesYTop()<=super.getY()+super.getSize()&&
                missile1.getX()>=super.getX()-super.getSize()&&
                missile1.getX()<=super.getX()+super.getSize()){
            return true;
        }
        else return false;
    }

    public void drawEnemy(){
        StdDraw.picture(super.getX(),super.getY(), "ufo-42453_1280.png",super.getSize()*2,super.getSize()*2);
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }
}
/*
https://www.needpix.com/photo/33309/ufo-spaceship-science-alien-fantasy-universe-technology-spacecraft-flying
 Creative Commons Zero License for Public Domain

 */