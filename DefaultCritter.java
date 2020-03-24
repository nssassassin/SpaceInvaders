import java.awt.*;

public class DefaultCritter{
    private double x;
    private double y;
    private double speedY;
    private double speedX;
    private double size;

    public static final double enemySize = 0.16;
    public static final double critterSize = 0.04;
    public static final double critterSpeed = 0.01;
    public static final double enemySpeed = 0.02;
    public static final double bombSize = 0.05;
    public static final double chanceOfBomb = 0.015;
    public static final double missileSpeed = 0.03;
    public static final double missileSize = 0.01;
    public static final double bombSpeed = -0.01;
    public static final double shooterSize = 0.05;
    public static final double shooterSpeed = 0.01;
    public static final double MissileDelay = 25;
    public static final double shooterTurnSpeed = 2.5; //degrees per round
    public static final int enemyHitPoints = 1000;
    public static final int lives = 3;
    public static final int levels = 3;
    public static final int critterCount = 14; //multiples of 7, will be divided into rows

    public static final Font font2 = new Font("Slightly larger Arial", Font.PLAIN, 19);
    public static final Font font = new Font("Arial", Font.BOLD, 50);






        public DefaultCritter(double xIn, double yIn, double xSpeed, double ySpeed, double sizeIn){
        x = xIn;
        y = yIn;
        speedY = ySpeed;
        speedX = xSpeed;
        size = sizeIn;

    }
    public double getX() { return x; }

    public double getY() { return y; }

    //the following was added automatically by IntelliJ.
    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public double getSpeedX() {
        return speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getSize() {
        return size;
    }
    public void action(){
        x = x + speedX;
        y = y + speedY;
    }
}