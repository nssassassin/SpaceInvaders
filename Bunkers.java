import java.awt.*;

public class Bunkers extends DefaultCritter {
    int hitPoints;

    public Bunkers(double x, double y){
        super(x,y,0,0, DefaultCritter.bunkerSize);
        hitPoints = DefaultCritter.bunkerHitPoints;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public void drawBunkers() {
        StdDraw.setPenColor(Color.GRAY);
        StdDraw.filledSquare(getX(),getY(),getSize());
        StdDraw.setFont(DefaultCritter.font2);
        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
        StdDraw.text(getX(), getY(), "" +hitPoints);
    }

    public boolean hitBomb(Bombs bomb1){
        if(     bomb1.getBombYBottom()>=getY()- getSize()&&
                bomb1.getBombYBottom()<=getY()+getSize()&&
                (((bomb1.getX()-bomb1.getSize() <=  getX()+getSize()&&
                        bomb1.getX()-bomb1.getSize()>=getX()-getSize())||
                        ((bomb1.getX()+bomb1.getSize()>=getX()-getSize())&&
                                (bomb1.getX()+bomb1.getSize()<=getX()+getSize())) ))
        ){
            return true;
        }
        else return false;
    }
    public boolean checkCritter(Critter critter){
        if(
        critter.getY()-critter.getSize()>=getY()- getSize()&&
                critter.getY()-critter.getSize()<=getY()+getSize()&&
                (((critter.getX()-critter.getSize() <=  getX()+getSize()&&
                        critter.getX()-critter.getSize()>=getX()-getSize())||
                        ((critter.getX()+critter.getSize()>=getX()-getSize())&&
                                (critter.getX()+critter.getSize()<=getX()+getSize())) ))){
            return true;
        }
        return false;
    }
}

