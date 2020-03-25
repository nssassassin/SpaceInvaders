public class Bombs extends DefaultCritter{


    public Bombs(double xInput, double yInput, double speed){
        super(xInput, yInput, 0, speed, DefaultCritter.bombSize);
    }



    public double getBombYBottom() {
        return super.getY() - super.getSize();
    }







    public void drawBomb(){
        StdDraw.picture(getX(),getY(), "nuclear-36817_1280.png",getSize()*2,getSize()*2);
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

}

/*
https://pixabay.com/vectors/nuclear-warhead-bomb-atomic-weapon-36817/
Picture of bomb, slightly modified
 Pixabay License
Free for commercial use
No attribution required


https://freesound.org/people/MATTIX/sounds/415990/
https://creativecommons.org/licenses/by/3.0/

 */