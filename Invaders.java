import java.util.ArrayList;

//needed to have random amount of things

public class Invaders {

    public static boolean isRunning = false;
    public static long  previousMissileTime;
    public static int winOrLose;
    public static double myCurrentScore;
    public static double myScore1 =0;
    public static double myScore2 =0;
    public static double myScore3 =0;
    public static boolean menuRunning = true;
    public static boolean endMenu = false;
    public static String EndCredits;
    public static int level= 1;

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
            myScore1 = 0;
            myScore2 = 0;
            myScore3 = 0;
            ////////////////////////////////////////////
            while(level<DefaultCritter.levels+1&&winOrLose!=2) {

                Enemy newEnemy = new Enemy(0, 1 - DefaultCritter.enemySize, DefaultCritter.enemySpeed, 0, DefaultCritter.enemySize);
                newEnemy.setHitPoints(DefaultCritter.enemyHitPoints*level);
                Shooter newShooter = new Shooter(-0, -1 + DefaultCritter.shooterSize);
                Critter[] newCritter = new Critter[DefaultCritter.critterCount * level];

                ArrayList<Missile> newMissile = new ArrayList<Missile>();

                ArrayList<Bombs> newBomb = new ArrayList<Bombs>();

                winOrLose = 0;
                previousMissileTime = 0;
                myCurrentScore = 0;




                //create critter array
                for (int k = 0; k < DefaultCritter.critterCount * level / 7; k++) {
                    for (int j = 0; j < 7; j++) {
                        newCritter[(j + k * 7)] = new Critter((-1+2*DefaultCritter.critterSize + 2 * DefaultCritter.critterSize * j), (1 - 2 * DefaultCritter.enemySize - DefaultCritter.critterSize - 2 * DefaultCritter.critterSize * k));
                    }
                }

                /////////////////////////////////////////////////////////////////////////////
                isRunning = true;

                while (isRunning) {
                    InvaderGameState.drawTheGame(newEnemy, newShooter, newMissile, newBomb, newCritter);

                }
                StdOut.println(level + " "+ winOrLose + myScore1 + " "+ myScore2 + " "+ myScore3 );
                level++;


            }
            ///////////////////////////////////////
            InvaderGameState.ThrowEndScreen(endMenu);

        }






    }



}

