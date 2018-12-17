import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;

//import java.io.*;
//import sun.audio.*;

import java.lang.Object;

import javax.management.monitor.GaugeMonitor;
import javax.swing.JFrame;
import javax.imageio.ImageIO;

import java.util.ArrayList;
import java.util.Arrays;


// Main class

public class Pong extends JFrame {

    protected int fps;
    protected int windowHeight;
    protected int windowWidth;
    protected boolean isRunning;

    protected int x;
    protected int y;

    protected int v1;
    protected int v2;

    protected int paddlespeed;

    protected int count1;
    protected int count2;

    protected int xball;
    protected int yball;
    protected int xballspeed;
    protected int yballspeed;

    protected ArrayList<Integer> rands;


    protected BufferedImage backBuffer;
    protected Insets insets;

    //protected InputHandler input;

    protected Controller leftController, rightController;

    public int generation;

    public Movement leftMove, rightMove;

    public ArrayList<Double> inputs;

    


    public Pong(Controller leftController, Controller rightController) {
        this.fps = 300;
        this.windowHeight = 450;
        this.windowWidth = 650;
        this.isRunning = true;

        this.x = 225;
        this.y = 325;

        this.v1 = windowHeight/2 - 50;
        this.v2 = windowHeight/2 - 50;

        this.paddlespeed = 12;

        this.count1 = 0;
        this.count2 = 0;

        this.xball = windowWidth/2;
        this.yball = windowHeight/2 - 10;
        this.xballspeed = 5;
        this.yballspeed = 0;

        this.rands = new ArrayList<Integer>(Arrays.asList(-1,-1,-1,-1,-1,-1,-1,-1, 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1));

        this.leftController = leftController;
        this.rightController = rightController;

        this.generation = 0;


        //this.backBuffer;
        //this.insets;

        //this.input = new InputHandler(this);

    }

    public Controller run() {

        this.initialize();

        while(isRunning) {

            long time = System.currentTimeMillis();

            
            this.update();
            Controller champion = this.normalise();
            if (champion != null){
                this.setVisible(false);
                return champion;
            }
            this.draw();

            time = (1000 / fps) - (System.currentTimeMillis() - time);

            if (time > 0) {
                try {
                    Thread.sleep(time);
                }
                catch(Exception e) {
                    System.out.println(e);
                }
            }
        }

        this.setVisible(false);
        return null;
    }

    public void initialize() {

        this.backBuffer = new BufferedImage(this.windowWidth, this.windowHeight, BufferedImage.TYPE_INT_RGB); 
        //JFrame frame = new JFrame("ayy lmao");
        this.setTitle("((( P 0 N G ™ )))"); 

        this.insets = getInsets();
        this.setSize(this.windowWidth, this.windowHeight); 
        this.setResizable(false); 

        System.out.println("insets.left");
        System.out.println(this.insets.left);
        System.out.println("insets.right");
        System.out.println(this.insets.right);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); 
        this.setVisible(true); 

    }

    public void yeet(){
        this.setVisible(false); 
    }

    public void update() {
        ArrayList<Double> inputs = new ArrayList<Double>(Arrays.asList((Double)((this.v1*1.0/windowHeight*1.0)*1.0),
                                                                       (Double)((this.v2*1.0/windowHeight*1.0)*1.0), 
                                                                       (Double)((this.xball*1.0/windowWidth*1.0)*1.0), 
                                                                       (Double)((this.yball*1.0/windowHeight*1.0)*1.0), 
                                                                       (Double)(this.xballspeed*1.0/5.0-1.0),
                                                                       (Double)(this.yballspeed*1.0/3.0)));
        System.out.println(inputs);
        Movement leftMove = this.leftController.chooseMovement(inputs);
        if (leftMove == Movement.UP && (v1 - paddlespeed > 0)){
            v1 -= paddlespeed;
        }
        else if (leftMove == Movement.DOWN && (v1 + paddlespeed < windowHeight - 50)){
                v1 += paddlespeed;
        }

        Movement rightMove = this.rightController.chooseMovement(inputs);
        if (rightMove == Movement.UP && (v2 - paddlespeed > 0)){
            v2 -= paddlespeed;
        }
        else if (rightMove == Movement.DOWN && (v2 + paddlespeed < windowHeight - 50)){
                v2 += paddlespeed;
        }

        /*if(input.isKeyDown(KeyEvent.VK_2)) {
            if (v1 - paddlespeed > 0){
                v1 -= paddlespeed;
            }
        }
        if(input.isKeyDown(KeyEvent.VK_1)) {
            if (v1 + paddlespeed < windowHeight - 50){
                v1 += paddlespeed;
            }
        }

        if(input.isKeyDown(KeyEvent.VK_9)) {
            if (v2 - paddlespeed > 0){
                v2 -= paddlespeed;
            }
        }
        if(input.isKeyDown(KeyEvent.VK_8)) {
            if (v2 + paddlespeed < windowHeight - 50 ){
                v2 += paddlespeed;
            }
        } */ 
    }

    public Controller normalise(){
        if (xball <= 55 && xball >= (55 - windowWidth/15) && (yball > v1 - 25 && yball < v1 + 100)){
            xballspeed = Math.abs(xballspeed)+rands.get((int)Math.random()*rands.size());
            yballspeed += (int)Math.round(2.0*((yball - (v1+50))/50));
            /*if(input.isKeyDown(KeyEvent.VK_9)) {
                yballspeed -= 1;
            }
            if(input.isKeyDown(KeyEvent.VK_8)) {
                yballspeed += 1;
            }*/
        }
        if (xball >= 568 && (xball <= 568 + windowWidth/15) && (yball > v2 - 25 && yball < v2 + 100)){
            xballspeed = -1*Math.abs(xballspeed)-rands.get((int)Math.random()*rands.size());
            yballspeed += 10*(int)Math.round(2.0*(yball - (v2+50))/50);
           /*if(input.isKeyDown(KeyEvent.VK_9)) {
                yballspeed -= 1;
            }
            if(input.isKeyDown(KeyEvent.VK_8)) {
                yballspeed += 1;
            }*/
        }
        if (xball < 0){
            xball = windowWidth/2;
            yball = windowHeight/2;
            yballspeed = 0;
            xballspeed = -5;
            //this.v1 = windowHeight/2 - 50;
            //this.v2 = windowHeight/2 - 50;
            count2++;
            System.out.print("Player 1 score: ");
            System.out.print(count1);
            System.out.print(" Player 2 score: ");
            System.out.println(count2);
            System.out.println("");
        }
        if (xball > windowWidth){
            xball = windowWidth/2;
            yball = windowHeight/2;
            yballspeed = 0;
            xballspeed = 5;
            //this.v1 = windowHeight/2 - 50;
            //this.v2 = windowHeight/2 - 50;
            count1++;
            System.out.print("Player 1 score: ");
            System.out.print(count1);
            System.out.print(" Player 2 score: ");
            System.out.println(count2);
            System.out.println("");
        }
        if (yball < 0){
            yball = 0;
            yballspeed = -1*yballspeed;
        }
        if (yball > windowHeight){
            yball = windowHeight;
            yballspeed = -1*yballspeed;
        }
        if (count1 > 9 || count2 > 9){
            fps = 1000;                                
        }
        if (count1 >=10){
            count1 = 0;
            count2 = 0;
            return this.leftController;
        }
        if (count2 >= 10){
            count1 = 0;
            count2 = 0;
            return this.rightController;
        }
        xball += xballspeed;
        yball += yballspeed;
        return null;
    }

    public void draw() {

        Graphics g = getGraphics(); 
        Graphics bbg = this.backBuffer.getGraphics();

        bbg.setColor(Color.BLACK); 
        bbg.fillRect(0, 0, this.windowWidth, this.windowHeight); 

        bbg.setColor(Color.GREEN);
        bbg.drawString(Integer.toString(this.generation), this.windowWidth/15, this.windowHeight/10);
        bbg.fillRect(this.windowWidth/15, v1, 20, 100); 
        bbg.fillRect(14*this.windowWidth/15, v2, 20, 100); 
        bbg.setColor(Color.GREEN);
        bbg.fillOval(xball, yball, 40, 40); 
;        

        g.drawImage(backBuffer,this.insets.left, this.insets.top, this); 

    }
}