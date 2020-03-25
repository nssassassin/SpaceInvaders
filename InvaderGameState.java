import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class InvaderGameState {


    public static void ThrowTheMenu(boolean menuRunning){
        while(menuRunning) {


            StdDraw.setXscale(-1.0, 1.0);
            StdDraw.setYscale(-1.0, 1.2);
            StdDraw.enableDoubleBuffering();
            StdDraw.clear(StdDraw.BLACK);
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.picture(0, 0.1, "cosmos-background.jpg", 2, 2.2);

            StdDraw.setFont(DefaultCritter.font);
            StdDraw.text(0.0, 0.8, "Space Destroyers");

            StdDraw.setFont(DefaultCritter.font2);
            StdDraw.text(0,0.2,"Press Space to continue");
            StdDraw.text(0,0,"Shoot (w)");
            StdDraw.text(0,-0.2,"Rotate: Left (a), Right (d), Release to stop");
            StdDraw.text(0,-0.4,"Move: Left (z), Right (c), Release to stop");
            StdDraw.text(0,-0.6,"Quit (q), in game q will end game and return to menu");
            StdDraw.text(0,-0.8, "ScreenCapture (p)");

            if(StdDraw.isKeyPressed(KeyEvent.VK_SPACE)){
                Invaders.menuRunning = false;
                menuRunning = false;
                Invaders.isRunning = true;
                Invaders.level = 1;
                Invaders.winOrLose = 0;
                //StdAudio.close();
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_Q)){
                StdOut.println("Exit game and system");
                System.exit(0);
            }
            StdDraw.show();
            StdDraw.pause(20);
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void ThrowEndScreen(){ //boolean endScreen
        //while(endScreen){
        StdDraw.clear();
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setFont(DefaultCritter.font);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.picture(0, 0.1, "cosmos-background.jpg", 2, 2.2);
        switch (Invaders.winOrLose){
            case 0: Invaders.EndCredits = "QUIT";
                break;
            case 1: Invaders.EndCredits = "WON";
                break;
            case 2: Invaders.EndCredits = "LOSS";
                break;
        }
        StdDraw.text(0, 0.5, Invaders.EndCredits);
        StdDraw.text(0,0.2,"Score: " + ((Invaders.myScore3+ Invaders.myScore2+ Invaders.myScore1)));
        StdDraw.show();
        StdDraw.pause(DefaultCritter.timeDelayEnd);
        Invaders.menuRunning = true;





    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void drawTheGame(Enemy enemy,
                                   Shooter shooter,
                                   ArrayList<Missile> missiles,
                                   ArrayList<Bombs> bombs,
                                   Critter[] critters) {


        StdDraw.setXscale(-1.0, 1.0);
        StdDraw.setYscale(-1.0, 1.2);
        StdDraw.enableDoubleBuffering();

        StdDraw.clear(StdDraw.BLACK);
        //Print a background
        StdDraw.picture(0, 0, "cosmos-background.jpg", 2, 2);

        //Draw the enemy
        enemy.drawEnemy();
        //do loops for bombs and missiles
        int count = 0;
        for(int i=0;i<bombs.size();i++){
            for(int j=0;j<missiles.size();j++){
                if(bombs.get(i).hitMissile(missiles.get(j))) {
                    StdOut.println("Detected hit");
                    missiles.remove(j);
                    bombs.remove(i);
                    StdAudio.play("explosion.wav");
                    StdOut.println("Removed Bomb and missile");
                    count = count + 1;
                    System.out.println(count);
                    Invaders.bombsHit++;

                    break;
                }
            }
            if(count>0){

                break;
            }
        }

        //Spawn a PowerUp at a random chance

        if(Math.random()<DefaultCritter.powerUpChance&&Invaders.powerUp1==null&&Invaders.powerUpTime+DefaultCritter.powerUpTimeOut<=System.currentTimeMillis()){
            Invaders.powerUp1 = new PowerUp() ;

        }

        //draw powerUp
        if(Invaders.powerUp1 != null){
            Invaders.powerUp1.drawPowerUp();
            if(Invaders.powerUp1.powerUpUsed(shooter, missiles)!=0){
                switch(Invaders.powerUp1.powerUpUsed(shooter, missiles)){
                    case 1:
                        Invaders.tempSpeed = DefaultCritter.shooterSpeed*3;
                        break;
                    case 2:
                        Invaders.missileTime = 100;
                        break;
                }


                Invaders.powerUpTime = System.currentTimeMillis();
                Invaders.powerUp1 = null;
            }
        }
        if(Invaders.powerUpTime+DefaultCritter.powerUpTimeOut<=System.currentTimeMillis()){
            //resets all changes
            Invaders.tempSpeed = DefaultCritter.shooterSpeed;
            Invaders.missileTime = DefaultCritter.MissileDelay;

        }
        else if(Invaders.powerUpTime!=0){
            StdDraw.setFont(DefaultCritter.font2);
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.text(0,1.04, "PowerUp left: " + (int)((0.001*(5000 - ((System.currentTimeMillis()-Invaders.powerUpTime))))+1) + "s");
        }



        //move the enemy ship
        enemy.moveEnemy();
        //check if enemy was hit
        boolean allDead = false;
        int countDead = 0;
        if (!allDead) {
            for (int i = 0; i < critters.length; i++) {
                if (critters[i].isKilled()) {
                    countDead++;
                }
            }
        }
        if(countDead == critters.length){
            allDead = true;
        }

        for(int o = 0; o<missiles.size(); o++){
            if(enemy.hitMissile(missiles.get(o))&& allDead){

                enemy.removeHitPoints();
                StdOut.println("Enemy hit by Missile, missile removed and enemy life taken");
                missiles.remove(o);
            }
        }

        if(enemy.getHitPoints() == 0){
            Invaders.winOrLose = 1;
            StdOut.println("Game " + Invaders.level  + " won");
            Invaders.isRunning = false;
            if(Invaders.level==3)Invaders.endMenu = true;
        }//    if enemy dies

        if(shooter.getLives()==0){
            Invaders.winOrLose = 2;
            StdOut.println("Game Lost");
            Invaders.isRunning = false;
            Invaders.endMenu = true;
        }//if You die


        //drop random bombs at enemy location
        if(Math.random()<DefaultCritter.chanceOfBomb){
            bombs.add(new Bombs(enemy.getX(),enemy.getY()-enemy.getSize(),DefaultCritter.bombSpeed));
            StdAudio.play("415990__mattix__retro-drop-02.wav");
            // StdOut.println("Bombs: " + bombs.size());

        }

        //Shooter logic
        if(StdDraw.isKeyPressed(65)){
            shooter.setAnglePlus(true);
            //a is 65
        }
        else{
            shooter.setAnglePlus(false);
        }
        if(StdDraw.isKeyPressed(68)){
            shooter.setAngleMinus(true);
            //d is 68
        }
        else{
            shooter.setAngleMinus(false);
        }

        if(StdDraw.isKeyPressed(87)){
            if(System.currentTimeMillis() >= Invaders.previousMissileTime + Invaders.missileTime) {

                missiles.add(new Missile(shooter.getX(), shooter.getY(), shooter.getAngleOfGun()));
                StdAudio.play("344310__musiclegends__laser-shoot.wav");
                Invaders.previousMissileTime = System.currentTimeMillis();

            }

            //w is 87
        }
        if(StdDraw.isKeyPressed(KeyEvent.VK_Z)){
            shooter.setMoveLeft(true);
            //z is 90
        }
        else{
            shooter.setMoveLeft(false);
        }
        if(StdDraw.isKeyPressed(KeyEvent.VK_C)){
            shooter.setMoveRight(true);
            //c is 67
        }
        else{
            shooter.setMoveRight(false);
        }

        if(StdDraw.isKeyPressed(KeyEvent.VK_Q)){
            Invaders.winOrLose = 0;
            Invaders.isRunning = false;
            Invaders.endMenu = true;


        }
        if(StdDraw.isKeyPressed(KeyEvent.VK_P)){
            StdDraw.save("screenshot.png");
        }


        //draw missiles and move and execute logic
        for(int g = 0; g< missiles.size(); g++) {
            missiles.get(g).drawMissile();
            missiles.get(g).action();

            //Remove missiles offscreen
            if(     missiles.get(g).getMissilesYTop()>=1||
                    missiles.get(g).getMissilesXMax()>=1 ||
                    missiles.get(g).getMissilesXMin()<=-1) {

                missiles.remove(g);

            }
        }






        //draw shooter
        shooter.drawShooter();
        shooter.moveShooter();

        //shooter check if hit by bombs
        for(int i = 0; i < bombs.size(); i++){
            if(shooter.hitBomb(bombs.get(i))){
                bombs.remove(i);
                shooter.removeLives();
                StdAudio.play("explosion.wav");

            }
        }
        //shooter check if hit by critters
        for(int i = 0;i<critters.length;i++){
            if(!critters[i].isKilled()&&critters[i].getY()-critters[i].getSize()<=shooter.getY()+shooter.getY()&&
                    ((shooter.getX()-shooter.getSize() <=  critters[i].getX()+critters[i].getSize()&&
                            shooter.getX()-shooter.getSize()>=critters[i].getX()-critters[i].getSize())||
                            ((shooter.getX()+shooter.getSize()>=critters[i].getX()-critters[i].getSize())&&
                                    (shooter.getX()+shooter.getSize()<=critters[i].getX()+critters[i].getSize())) )   ){
                Invaders.winOrLose = 2;
                StdOut.println("Game Lost");
                Invaders.isRunning = false;
                Invaders.endMenu = true;
            }
        }


        //Draw the critters
        for (int i = 0; i < critters.length; i++) {
            if(!(critters[i].isKilled())) {
                critters[i].drawCritter();
            }
        }

        //draw Bombs and move, not wasting a loop
        for(int u = 0; u< bombs.size(); u++) {
            bombs.get(u).drawBomb();
            bombs.get(u).action();

            //Remove bombs offscreen
            if(bombs.get(u).getBombYBottom()<=-1) {
                bombs.remove(u);
            }

        }






        //move the critters

        for (int j = 0; j < critters.length; j++) {
            critters[j].action();

            //also check missile impact


            for(int h= 0 ; h<missiles.size(); h++){
                if(critters[j].hitMissile(missiles.get(h))){
                    if(critters[j].isKilled()){
                    }
                    else{
                        critters[j].setKilled(true);
                        missiles.remove(h);
                    }

                }

            }
        }



        if ((critters[0].getX() <= -1 + critters[0].getSizeCritter()) && (critters[0].getSpeedX() < 0)) {


            for (int k = 0; k < critters.length; k++) {
                critters[k].setSpeedX(-critters[k].getSpeedX());
                critters[k].setY(critters[k].getY()-2*critters[k].getSizeCritter());
            }


        }
        if (((critters[critters.length-1].getX() >= (1 - critters[critters.length-1].getSizeCritter()))) &&
                (critters[critters.length-1].getSpeedX() > 0)) {

            for (int l = 0; l < critters.length; l++) {
                critters[l].setSpeedX(-critters[l].getSpeedX());
                critters[l].setY(critters[l].getY()-2*critters[l].getSizeCritter());
            }
        }
        for(int i=0;i<critters.length;i++){
            if((critters[i].getY()-critters[i].getSize()<=-1+critters[i].getSize())&&!critters[i].isKilled()){
                Invaders.winOrLose = 2;
                StdOut.println("Game Lost");
                Invaders.isRunning = false;
                Invaders.endMenu = true;
            }
        }






        //draw score
        int myCounter = 0;

        for(int a=0;a<critters.length;a++){
            // StdOut.println("Counter = "+myCounter);
            if(critters[a].isKilled()){
                myCounter= myCounter + 1;
            }
        }
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setFont();
        StdDraw.textRight(0.95, 1.15, "Enemy Hitpoints: " + enemy.getHitPoints());
        Invaders.myCurrentScore = (myCounter*1000 +  Invaders.bombsHit *500 + 100*(DefaultCritter.enemyHitPoints*Invaders.level - enemy.getHitPoints()));
        StdDraw.textLeft(-0.95,1.15, "Score: " + Invaders.myCurrentScore);
        StdDraw.text(0, 1.15, "Level " + Invaders.level);
        switch (Invaders.level){
            case 1:
                Invaders.myScore1 = Invaders.myCurrentScore;
                break;
            case 2:
                Invaders.myScore2 = Invaders.myCurrentScore;
                break;
            case 3:
                Invaders.myScore3 = Invaders.myCurrentScore;
                break;
        }


        StdDraw.text(-0.35, 1.15, "Lives: " + shooter.getLives());


        StdDraw.show();
        StdDraw.pause(20);




    } // end of drawthegame


}

/*
        Background used https://www.publicdomainpictures.net/en/view-image.php?image=270151&picture=cosmos-background
        License: CC0 Public Domain


*/



