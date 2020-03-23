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
            StdDraw.text(0,0.2,"Press Enter to continue");
            StdDraw.text(0,0,"Shoot (w)");
            StdDraw.text(0,-0.2,"Rotate: Left (a), Right (d), Release to stop");
            StdDraw.text(0,-0.4,"Move: Left (z), Right (c), Release to stop");
            StdDraw.text(0,-0.6,"Quit (q), in game q will end game and return to menu");
            StdDraw.text(0,-0.8, "ScreenCapture (q)");

            if(StdDraw.isKeyPressed(KeyEvent.VK_ENTER)){
                Invaders.menuRunning = false;
                menuRunning = false;
                Invaders.isRunning = true;
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_Q)){
                StdOut.println("Exit game and system");
                System.exit(0);
            }
            StdDraw.show();
            StdDraw.pause(20);
        }
    }
    public static void ThrowEndScreen(boolean endScreen){
        while(endScreen){
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
            StdDraw.text(0,0.2,"Score: " + Invaders.myScore);
            StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
            StdDraw.text(0,-0.2,"Press Space");
            StdDraw.text(0,-0.4,"to continue");



            if(StdDraw.isKeyPressed(KeyEvent.VK_SPACE)){
                Invaders.menuRunning = true;
                endScreen = false;
            }
            StdDraw.show();
            StdDraw.pause(20);



        }

    }

    public static void drawTheGame(Enemy enemy, Shooter shooter, ArrayList<Missile> missiles, ArrayList<Bombs> bombs,  Critter[] critters) {


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
                    StdOut.println("Removed Bomb and missile");
                    count = count + 1;
                    System.out.println(count);

                    break;
                }
            }
            if(count>0){
                //System.out.println("Did break and hopefully stop loop");
                break;
            }
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
            StdOut.println("Game won");
            Invaders.isRunning = false;
            Invaders.endMenu = true;

        }//    if enemy dies
        if(shooter.getLives()==0){
            Invaders.winOrLose = 2;
            StdOut.println("Game Lost");
            Invaders.isRunning = false;
            Invaders.endMenu = true;
        }


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
        /*
        if(StdDraw.isKeyPressed(83)){
            shooter.setAngleMinus(false);
            shooter.setAnglePlus(false);
            //d is 68
        }
         */
        if(StdDraw.isKeyPressed(87)){
            if(System.currentTimeMillis() >= Invaders.previousMissileTime + DefaultCritter.MissileDelay) {

                missiles.add(new Missile(shooter.getX(), shooter.getY(), shooter.getAngleOfGun()));
                StdAudio.play("344310__musiclegends__laser-shoot.wav");
                Invaders.previousMissileTime = System.currentTimeMillis();

            }

            //w is 87
        }
        if(StdDraw.isKeyPressed(90)){
            shooter.setMoveLeft(true);
            //z is 90
        }
        else{
            shooter.setMoveLeft(false);
        }
        if(StdDraw.isKeyPressed(67)){
            shooter.setMoveRight(true);
            //c is 67
        }
        else{
            shooter.setMoveRight(false);
        }

        if(StdDraw.isKeyPressed(81)){
            Invaders.winOrLose = 0;
            Invaders.isRunning = false;
            Invaders.endMenu = true;


        }
        if(StdDraw.isKeyPressed(80)){
            StdDraw.save("screenshot.png");
        }


        //s is 83
        //x is 88
        //q is 81
        //p is 80





        //draw missiles and move and execute logic
        for(int g = 0; g< missiles.size(); g++) {
            missiles.get(g).drawMissile();
            missiles.get(g).action();

            //Remove missiles offscreen
            if(missiles.get(g).getMissilesYTop()>=1|| missiles.get(g).getMissilesXMax()>=1 || missiles.get(g).getMissilesXMin()<=-1) {
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
        if (((critters[critters.length-1].getX() >= (1 - critters[critters.length-1].getSizeCritter()))) && (critters[critters.length-1].getSpeedX() > 0)) {

            for (int l = 0; l < critters.length; l++) {
                critters[l].setSpeedX(-critters[l].getSpeedX());
                critters[l].setY(critters[l].getY()-2*critters[l].getSizeCritter());
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
        StdDraw.textRight(0.95, 1.1, "Enemy Hitpoints: " + enemy.getHitPoints());
        Invaders.myScore = (myCounter*1000 + 100*(1000 - enemy.getHitPoints()));
        StdDraw.textLeft(-0.95,1.1, "Score: " + Invaders.myScore);
        StdDraw.text(0, 1.1, "Lives: " + shooter.getLives());


        StdDraw.show();
        StdDraw.pause(20);




    } // end of drawthegame


}

/*
        Background used https://www.publicdomainpictures.net/en/view-image.php?image=270151&picture=cosmos-background
        License: CC0 Public Domain
*/

