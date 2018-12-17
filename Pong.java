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

// Main class

public class Pong extends JFrame {

    private int fps;
    private int windowHeight;
    private int windowWidth;
    private boolean isRunning;

    private int x;
    private int y;

    private int v1;
    private int v2;

    private int paddlespeed;

    private int count1;
    private int count2;

    private int xball;
    private int yball;
    private int xballspeed;
    private int yballspeed;

    private ArrayList<Integer> rands;


    private BufferedImage backBuffer;
    private Insets insets;

    private InputHandler input;

    


    public Pong() {
        this.fps = 60;
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

        this.rands = new ArrayList<Integer>();
        this.rands.add(0, 0);


        //this.backBuffer;
        //this.insets;

        this.input = new InputHandler(this);

    }

    public void run() {

        this.initialize();

        while(isRunning) {

            long time = System.currentTimeMillis();

            
            this.update();
            this.normalise();
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

    public void update() {
        if(input.isKeyDown(KeyEvent.VK_2)) {
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
        }  
    }

    public void normalise(){
        if (xball <= 55 && xball >= (55 - windowWidth/15) && (yball > v1 - 25 && yball < v1 + 100)){
            xballspeed = Math.abs(xballspeed)+rands.get((int)Math.random()*rands.size());
            yballspeed += (int)Math.round(2.0*((yball - (v1+50))/50));
            if(input.isKeyDown(KeyEvent.VK_9)) {
                yballspeed -= 1;
            }
            if(input.isKeyDown(KeyEvent.VK_8)) {
                yballspeed += 1;
            }
        }
        if (xball >= 568 && (xball <= 568 + windowWidth/15) && (yball > v2 - 25 && yball < v2 + 100)){
            xballspeed = -1*Math.abs(xballspeed)-rands.get((int)Math.random()*rands.size());
            yballspeed += (int)Math.round(2.0*(yball - (v2+50))/50);
            if(input.isKeyDown(KeyEvent.VK_9)) {
                yballspeed -= 1;
            }
            if(input.isKeyDown(KeyEvent.VK_8)) {
                yballspeed += 1;
            }
        }
        if (xball < 0){
            xball = windowWidth/2;
            yball = windowHeight/2;
            yballspeed = 0;
            xballspeed = -5;
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
        if (count1 >9 || count2 > 9){
            fps = 120;                                
        }
        if (count1 >=10 || count2 >= 10){
            fps = 60;
            count1 = 0;
            count2 = 0;                                
        }
        xball += xballspeed;
        yball += yballspeed;
      
    }

    public void draw() {

        Graphics g = getGraphics(); 
        Graphics bbg = this.backBuffer.getGraphics();

        bbg.setColor(Color.BLACK); 
        bbg.fillRect(0, 0, this.windowWidth, this.windowHeight); 

        bbg.setColor(Color.GREEN);
        bbg.drawString(Integer.toString(this.count1), this.windowWidth/15, this.windowHeight/10);
        bbg.fillRect(this.windowWidth/15, v1, 20, 100); 
        bbg.drawString(Integer.toString(this.count2), 14*this.windowWidth/15, this.windowHeight/10);
        bbg.fillRect(14*this.windowWidth/15, v2, 20, 100); 
        bbg.setColor(Color.GREEN);
        bbg.fillOval(xball, yball, 40, 40); 
;        

        g.drawImage(backBuffer,this.insets.left, this.insets.top, this); 

    }
}