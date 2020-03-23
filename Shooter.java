public class Shooter extends DefaultCritter{
    private boolean moveRight;
    private boolean moveLeft;
    private boolean anglePlus;
    private boolean angleMinus;
    private double angleOfGun;
    private double angleSpeed;
    private int lives;

    public Shooter(double xIN, double yIN){
    super(xIN, yIN, 0,0, DefaultCritter.shooterSize);
    moveLeft = false;
    moveRight = false;
    angleOfGun = 90;
    anglePlus = false;
    angleMinus = false;
    angleSpeed = 0;
    lives = DefaultCritter.lives;
    }


    public double getX() {
        return super.getX();
    }

    public double getY() {
        return super.getY();
    }

    public double getSpeedX() {
        return super.getSpeedX();
    }

    public double getSpeedY() {
        return super.getSpeedY();
    }

    public double getSize() {
        return super.getSize();
    }

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

    public double getAngleOfGun() {
        return angleOfGun;
    }


    public int getLives() {
        return lives;
    }

    public void removeLives(){
        lives--;
    }

    public void setAngleMinus(boolean angleMinus) {
        this.angleMinus = angleMinus;
    }

    public void setAnglePlus(boolean anglePlus) {
        this.anglePlus = anglePlus;
    }



    public void moveShooter() {
        //first the base movement
        if(moveRight){
            super.setSpeedX(DefaultCritter.shooterSpeed);
        }
        else if(moveLeft){
            super.setSpeedX(-DefaultCritter.shooterSpeed);
        }
        else {
            super.setSpeedX(0);
        }
        // Now Turn the turret
        if (anglePlus){
            angleSpeed = DefaultCritter.shooterTurnSpeed;
        }
        else if(angleMinus) {
            angleSpeed = -DefaultCritter.shooterTurnSpeed;
        }
        else{
            angleSpeed = 0;
        }
        if(angleOfGun<180&&angleOfGun>0||(angleOfGun>=180&&angleSpeed<0)||(angleOfGun<=0&&angleSpeed>0)) {
            angleOfGun = angleOfGun + angleSpeed;
        }


        super.action();

    }

    public void drawShooter() {
    StdDraw.picture(getX(),getY(),"valessiobrito_m53_concept_-_above.png",2*getSize(),2*getSize(),getAngleOfGun()-90);
    }

    public boolean hitBomb(Bombs bomb1){
        if( bomb1.getBombYBottom()>=super.getY()- super.getSize()&&
            bomb1.getBombYBottom()<=super.getY()+super.getSize()&&
            bomb1.getX()>=super.getX()-super.getSize()&&
            bomb1.getX()<=super.getX()+super.getSize()){
            return true;
        }
        else return false;
    }
}

/*
https://freesound.org/people/MusicLegends/sounds/344310/
https://creativecommons.org/licenses/by/3.0/


https://publicdomainvectors.org/en/free-clipart/Top-view-of-supersonic-aircraft-vector-clip-art/11866.html
Public domain

 */