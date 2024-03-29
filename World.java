//change made by Advait


/******************************************************************************
  *  Compilation:  javac World.java
  *  Execution:    java World
  *  Dependencies: Barrel.java, DonkeyKong.java, Floor.java, Ladder.java,
  *  LinkedList.java, Mario.java, Peach.java, PennDraw.java, StdAudio.java,
  *  image files, audio files
  *
  *  Runs the world of Unfair Donkey Kong Arcade. Runs instructions, then the game,
  *  then a play-again feature.
  * 
  *
  *
  ***************************************************************************/
public class  World {
    public static void main(String[] args) {

        //DRAWING THE INSTRUCTIONS **************************************
        PennDraw.clear();
        PennDraw.setFontSize(15);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.text(0.5, 0.8, "UnFair Donkey Kong");
        PennDraw.text(0.5, 0.7, "use 'a' to move left, 'd' for right,");
        PennDraw.text(0.5, 0.6, "'w' to jump, and 's' to move down.");
        PennDraw.text(0.5, 0.5, "Climb the ladders to get to Princess Peach " + 
                      "to win");
        PennDraw.text(0.5, 0.4, "Avoid the barrels - you lose if one hits you");
       // PennDraw.text(0.5, 0.27, "Press 'f' to activate the special power up " +
                   //   "once you reach the");
       // PennDraw.text(0.5, 0.23, "third floor up.");
        PennDraw.setFontBold();
        PennDraw.text(0.5, 0.1, "Press 'y' to start the game");
        int fps=30;
        
        //waits until user presses 'y' to begin the game
        char c = 0;
        while (c != 'y') {
            if (PennDraw.hasNextKeyTyped())
                c = PennDraw.nextKeyTyped();
        }
        //***************************************************************
        
        //begin playing background music immediately
        StdAudio.loop("bacmusic.wav");
        
        boolean playAgain = true;
        while (playAgain) {
            
            //create timer to keep track of frames
            int timer = 0;
            int lightningTimer = 0;
            
            //direction tracker for animation
            int rightDir = 0;
            int leftDir = 0;
            
            // 0 = right and 1 = left
            int direction = 0;
            
            //lightning is happening?
            boolean lightning = false;
            
            //jumping variable
            boolean jumping = false;
            
            //false if not climbing, true if climbing
            //int climb to keep track of which climbing frame
            boolean climbing = false;
            int climb = 0;
            
            //if false he is facing left, if true he is facing right
            boolean facing = false;
            
            //Initialize floors***********************************************
            Floor[] floors = new Floor[6]; //6 floors
            
            //initialize the floors alternating which edge they touch
            for (int i = 0; i < floors.length; i++) {
                if (i % 2 == 0) {
                    floors[i] = new Floor(0.4, 0.8 - i * 0.15);
                }
                else {
                    floors[i] = new Floor(0.6, 0.65 - (i - 1) * 0.15);
                }
            }
            //floors**********************************************************
            
            //initialize ladders**********************************************
            Ladder[] ladders = new Ladder[7];
            ladders[0] = new Ladder(0.45, 0.125);// Ground
            ladders[1] = new Ladder(0.7, 0.275);
            ladders[5] = new Ladder(0.24, 0.275);
            ladders[2] = new Ladder(0.3, 0.425);
            ladders[3] = new Ladder(0.6, 0.575);
            ladders[4] = new Ladder(0.45, 0.725);
            ladders[6] = new Ladder(0.75,0.725);//Top


            //Spikes spikes=new Spikes(0.35,0.210);
            Spikes[] spikes=new Spikes[5];
            spikes[0]=new Spikes(0.35,0.210);
            spikes[4]=new Spikes(.45,0.36);
            spikes[1]=new Spikes(0.7,0.66);
            spikes[2]=new Spikes(0.75,0.810);
            //spikes[3]=new Spikes(0.52, 0.660);
            spikes[3]=new Spikes(0.55, 0.810);

            Fakespike fakespike1=new Fakespike(0.55,0.810);
            Fakespike fakespike2=new Fakespike(0.6,0.509);

            FloorHole floorHole1=new FloorHole(0.55,0.2);
            FloorHole floorHole2=new FloorHole(0.5,0.65);






            //spike at y=.275, x=between .24 && .45

            //ladders**********************************************************
            
            //initialize barrel linkedList
            LinkedList<Barrel> barrels = new LinkedList<Barrel>();
            
            //set mario/peach/donkeykong to their starting points
            Mario mario = new Mario(0.5, floors[5].getY() 
                                        + Floor.getHeight() + 0.025);
            Peach peach = new Peach(0.70, floors[0].getY() 
                                        + Floor.getHeight() + 0.035);
            DonkeyKong donkey = new DonkeyKong(0.15, floors[0].getY() 
                                                   + Floor.getHeight() + 0.04);
            
            //CHANGE ANIMATION SPEED FPS IF NEE


            PennDraw.enableAnimation(fps);

            boolean hasWon = false; //create a boolean to say mario hasn't won
            
            //Begin gameplay loop ********************************************
            while(mario.isAlive() && !hasWon) {

                PennDraw.clear(PennDraw.BLACK);
                
                //draw 4 barrels in top corner
                Barrel.draw4(floors);
                
                //draw all the floors and ladders
                for (int i = 0; i < floors.length; i++) {
                    if (i < floors.length)
                        floors[i].draw();

                }
                for (int i = 0; i < ladders.length; i++) {

                    if (i < ladders.length)
                        ladders[i].draw();

                }


                for (int i = 0; i < spikes.length; i++)
                {
                    //if(mario.getY()==spikes[i].getY() && mario.getX()==spikes[i].getX())
                    if(mario.spikesCollision(spikes[0]))
                    {
                            spikes[0].draw();
                            mario.isDead();
                    }
                    if(mario.spikesCollision(spikes[1]))
                    {
                        spikes[1].draw();
                        mario.isDead();
                    }
                    if(mario.spikesCollision(spikes[2]))
                    {
                        spikes[2].draw();
                        mario.isDead();
                    }
                    if(mario.spikesCollision(spikes[3]))
                    {
                        spikes[3].draw();
                        mario.isDead();
                    }
                    if(mario.spikesCollision(spikes[4]))
                    {
                        spikes[4].draw();
                        mario.isDead();
                    }
                }
                mario.checkPosition();
                if(mario.floorHoleCollision(floorHole1))
                {

                    mario.setY(mario.getY()-.05);

                    floorHole1.draw();



                }
                if(mario.floorHoleCollision(floorHole2)) {

                    mario.setY(mario.getY() - .05);

                    floorHole1.draw();

                }
                fakespike1.draw();
                fakespike2.draw();

                    //if(mario.getY()==spikes[i].getY() && mario.getX()==spikes[i].getX())

//                    if(mario.floorHoleCollision(spikes[1]))
//                    {
//                        spikes[1].draw();
//                        mario.isDead();
//                    }
//                    if(mario.floorHoleCollision(spikes[2]))
//                    {
//                        spikes[2].draw();
//                        mario.isDead();
//                    }
//                    if(mario.floorHoleCollision(spikes[3]))
//                    {
//                        spikes[3].draw();
//                        mario.isDead();
//                    }





                //draw donkey depending on what frame we are on using timer
                if (0 <= timer && timer < 145) {
                    donkey.drawOriginal();
                }
                else if (145 <= timer && timer < 155) {
                    donkey.drawLeft();
                }
                else if (155 <= timer && timer < 165) {
                    donkey.drawCenter();
                }
                else if (165 <= timer && timer < 185) {
                    donkey.drawRight();
                }
                else donkey.drawOriginal();
                
                //draw peach
                peach.draw();

                
                //make sure mario is in an acceptable position before drawing
                mario.checkPosition();

                //if mario is above 0.35, use pikachu images
                if (mario.getY() > 0.35)
                {
                    PennDraw.enableAnimation(fps);
                    if (direction == 1) {
                        mario.pDrawRight(rightDir);
                    } else if (direction == 2) {
                        mario.pDrawLeft(leftDir);
                    } else {
                        mario.pDraw(facing);
                    }
                }

                else if (mario.ladderCollision(ladders) && climbing) {
                    mario.drawClimbing(climb);
                } else if (direction == 1) {
                    mario.drawRight(rightDir);
                } else if (direction == 2) {
                    mario.drawLeft(leftDir);
                } else if (!(mario.floorCollision(floors)) &&
                        !(mario.ladderCollision(ladders))) {
                    mario.drawJump(facing);
                } else {
                    mario.draw(facing);
                }

                mario.checkPosition();
                if (mario.getY() > 0.525)
                {
                    PennDraw.enableAnimation(fps);
                    if (direction == 1) {
                        mario.pDrawRight(rightDir);
                    } else if (direction == 2) {
                        mario.pDrawLeft(leftDir);
                    } else {
                        mario.pDraw(facing);
                    }
                }
                
                if (lightningTimer >= 30) {
                    lightningTimer = 0;
                    lightning = false;
                }
                
                //resets direction before input so mario can stand if not moving
                direction = 0;
                
                //checks for arrow pad input (w, a, s, d)
                if (PennDraw.hasNextKeyTyped()) {
                    char dir = PennDraw.nextKeyTyped();
                    if (dir == 'a') {
                        //if is not in the ladder and on the floor move
                        if (!(mario.ladderCollision(ladders)))
                        {

                             if(!(mario.floorCollision(floors)))
                             {
                                 mario.moveLefttAir();
                             }
                             else
                             {
                                 mario.moveLeft();
                             }
                            leftDir++;
                            rightDir = 0;
                            climbing = false;
                            facing = false;
                            direction = 2;
                        }
                        else if (!(mario.ladderCollision(ladders) &&
                                !mario.floorCollision(floors)))
                        {
                            mario.moveLeft();
                            leftDir++;
                            rightDir = 0;
                            climbing = false;
                            facing = false;
                            direction = 2;
                        }
                    } 
                    else if (dir == 'd') {
                        //if mario is not in the ladder and on the floor
                        if (!(mario.ladderCollision(ladders)))
                             {
                                 if(!(mario.floorCollision(floors)))
                                 {
                                     mario.moveRightAir();
                                 }
                                 else
                                 {
                                     mario.moveRight();
                                 }
                            rightDir++;
                            leftDir = 0;
                            climbing = false;
                            facing = true;
                            direction = 1;
                        }else if (!(mario.ladderCollision(ladders) &&
                                !mario.floorCollision(floors)))
                        {
                            mario.moveRight();
                            rightDir++;
                            leftDir = 0;
                            climbing = false;
                            facing = true;
                            direction = 1;
                        }
                    }
                    //if mario is not in the ladder and on the floor

                    if (dir == 'w') {
                        //if mario is in ladder, move up ladder
                        if (mario.ladderCollision(ladders)) {
                            // System.out.println("we made it here");
                            climbing = true;
                            climb++;
                            mario.moveUp();
                        }
                        //otherwise if he is on the floor, jump
                        else if (mario.floorCollision(floors)) {
                            StdAudio.play("jump.wav");
                            climbing = false;
                            jumping = true;
                            mario.jump();
                        }
                    }
                    //jump and move left once
                /*    if (dir == 'w' && dir== 'a') {
                        //if mario is in ladder, move up ladder
                        if (mario.floorCollision(floors)) {
                            StdAudio.play("jump.wav");
                            climbing = false;
                            jumping = true;
                            mario.jump();
                            mario.moveLefttAir();
                            leftDir++;
                            rightDir = 0;

                            facing = false;

                            direction = 2;
                        }
                    }
                    if (dir == 'w' && dir== 'd') {
                        //if mario is in ladder, move up ladder
                        if (mario.floorCollision(floors)) {
                            StdAudio.play("jump.wav");
                            climbing = false;
                            jumping = true;
                            mario.jump();
                            mario.moveRightAir();
                            rightDir++;
                            leftDir = 0;
                            facing = true;
                            direction = 1;

                        }
                    }
*/
                    else if (dir == 's') {
                        //if mario is in ladder and not on the floor, move down
                        if (mario.ladderCollision(ladders) &&
                            !mario.floorCollision(floors)) { 
                            climbing = true;
                            climb--;
                            mario.moveDown();
                        }
                    } else if (dir == 'f' && mario.getY() > 0.35) {
                        mario.lightning(mario.getX(), mario.getY() + 0.285);
                        lightningTimer++;
                        lightning = true;
                    }
                }
                
                //let lightning happen for several frames and draw lightning
                if (lightningTimer > 0) {
                    mario.lightning(mario.getX(), mario.getY() + 0.285);
                    lightningTimer++;
                }
                
                //update mario's y position for jumping
                mario.updateY();
                
                //checks colliding with floors
                int counter = 0;
                for (int i = 0; i < floors.length; i++) {
                    if ((floors[i].collision(mario))) {
                        counter++;
                    }
                }
              
                //checks colliding with ladders
                if(counter <= 0.0 && !(mario.ladderCollision(ladders))) {
                    mario.fall();
                } else if (mario.getVelY() < 0.0) {
                    mario.stop(floors);
                }
                
                //if timer gets to 180 (frames), add a new barrel
                if (timer % 180 == 0) {
                    barrels.add(new Barrel(0.2, floors[0].getY() 
                                               + floors[0].getHeight() + 0.025));
                } else if (barrels.size() > 5) {
                    barrels.remove(0);
                }
                //checks mario collision with spikes
                /*if(mario.spikesCollision(spikes))
                {
                    mario.isDead();
                }
                */

                //checks barrels collision with floors
                int counter1 = 0;
                while (counter1 < barrels.size()) {
                    if (barrels.get(counter1).floorCollision(floors)) {
                        if (barrels.get(counter1).getFloorLevel() % 2 == 0) {
                            barrels.get(counter1).rollRight();
                        } else {
                            barrels.get(counter1).rollLeft();
                        }
                    } 
                    
                    //falls or stops barrels if neccesary
                    if (!(barrels.get(counter1).floorCollision(floors))) {
                        barrels.get(counter1).fall();
                    } else if (barrels.get(counter1).getVelY() < 0.0) {
                        int temp =  barrels.get(counter1).getFloorLevel();
                        barrels.get(counter1).setFloorLevel(temp + 1);
                        barrels.get(counter1).stop(floors);
                    }
                    
                    barrels.get(counter1).updateY();
                    counter1++;
                }
                
                //removes barrel if hit by pikachu's lightning
                int counter3 = 0;
                while(counter3 < barrels.size()) {
                    if ((barrels.get(counter3).getX() <= mario.getX() + 0.001 &&
                         mario.getX() - 0.001 <= barrels.get(counter3).getX())
                            && barrels.get(counter3).getY() > mario.getY()
                            && lightning == true) {
                        barrels.remove(counter3);
                    } 
                    counter3++;
                }
                
                //draws all barrels
                int counter2 = 0;
                while (counter2 < barrels.size()) {
                    barrels.get(counter2).draw();
                    counter2++;
                }
                
                //checks for mario collision with barrels
                mario.barrelCollision(barrels);
                
                timer++;
                if (timer >= 180) {
                    timer = 0;
                }
                
                PennDraw.advance();
                hasWon = mario.hasWon(peach); //check mario's location to peachs
            }//end gameplay loop **********************************************
            
            PennDraw.disableAnimation();
            
            //functions depending on if won or lost
            if (hasWon) {
                StdAudio.play("win1.wav");
                PennDraw.setPenColor(PennDraw.GREEN);
                PennDraw.setFontSize(100);
                PennDraw.text(0.5, 0.5, "YOU WON!");
            }
            else if (!mario.isAlive()) {
                StdAudio.play("death.wav");
                PennDraw.setPenColor(PennDraw.RED);
                PennDraw.setFontSize(100);
                PennDraw.text(0.5, 0.5, "YOU LOST!");
            }
            
            //function to see if user wants to play again************
            PennDraw.setPenColor(PennDraw.WHITE);
            PennDraw.setFontSize(25);
            PennDraw.text(0.5, 0.4, "Press 'y' to play again or 'n' to not");
            
            char d = 0;
            while (d != 'y' && d != 'n') {
                if (PennDraw.hasNextKeyTyped())
                    d = PennDraw.nextKeyTyped();
                if (d == 'n') return;
            }
        } //close loop for play again   
    } //close main 
} //close World.java


