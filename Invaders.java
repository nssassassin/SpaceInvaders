import java.util.ArrayList;

//needed to have random amount of things

public class Invaders {

    public static boolean isRunning = false;
    public static long  previousMissileTime;
    public static int winOrLose;
    public static double myScore;
    public static boolean menuRunning = true;
    public static boolean endMenu = false;
    public static String EndCredits;

    public static void main(String[] args) {

        //print default values
        StdOut.println("Enemy Size; " + DefaultCritter.enemySize);
        StdOut.println("Critter Size; " + DefaultCritter.critterSize);
        StdOut.println("Critter Speed; " + DefaultCritter.critterSpeed);
        StdOut.println("Enemy Speed; " + DefaultCritter.enemySpeed);
        StdOut.println("Bomb Size; " + DefaultCritter.bombSize);
        StdOut.println("Bomb Chance; " + DefaultCritter.chanceOfBomb);
        StdOut.println("Missile Speed; " + DefaultCritter.missileSpeed);
        StdOut.println("Missile Size; " + DefaultCritter.missileSize);
        StdOut.println("Bomb Speed; " + DefaultCritter.bombSpeed);
        StdOut.println("Shooter Size; " + DefaultCritter.shooterSize);
        StdOut.println("Shooter Speed; " + DefaultCritter.shooterSpeed);
        StdOut.println("Missile Delay; " + DefaultCritter.MissileDelay);
        StdOut.println("Enemy HitPoints; " + DefaultCritter.enemyHitPoints);
        StdOut.println("Shooter Turn Speed; " + DefaultCritter.shooterTurnSpeed);
        StdOut.println("Lives; " + DefaultCritter.lives);





        while(true) {

            InvaderGameState.ThrowTheMenu(menuRunning);
            //init
            ////////////////////////////////////////////////////////////////////////////
            Enemy newEnemy = new Enemy(0,1-DefaultCritter.enemySize, DefaultCritter.enemySpeed,0,DefaultCritter.enemySize);
            Shooter newShooter = new Shooter(-0, -1+DefaultCritter.shooterSize );
            Critter[] newCritter = new Critter[21];

            ArrayList<Missile> newMissile = new ArrayList<Missile>();

            ArrayList<Bombs> newBomb = new ArrayList<Bombs>();
            isRunning = true;
            previousMissileTime = 0;
            winOrLose = 0;

            //create critter array
            for(int k = 0;k<3;k++) {
                for (int j = 0; j < 7; j++) {
                    newCritter[(j+k*7)] = new Critter((-3 * DefaultCritter.critterSize*2 + 2*DefaultCritter.critterSize * j), (1 - 2 * DefaultCritter.enemySize- DefaultCritter.critterSize - 2*DefaultCritter.critterSize * k));
                }
            }

            /////////////////////////////////////////////////////////////////////////////


            while (isRunning) {
                InvaderGameState.drawTheGame(newEnemy, newShooter, newMissile, newBomb, newCritter);

            }
            InvaderGameState.ThrowEndScreen(endMenu);
        }






    }



}

