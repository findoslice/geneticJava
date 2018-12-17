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


public class GeneticPong extends Pong{

    private Controller left, right;
    private ArrayList<Controller> champions;

    public GeneticPong(Controller left, Controller right){
        super(left, right);
        this.left = left;
        this.right = right;
        this.champions = new ArrayList<Controller>();
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
            this.v1 = windowHeight/2 - 50;
            this.v2 = windowHeight/2 - 50;
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
            this.v1 = windowHeight/2 - 50;
            this.v2 = windowHeight/2 - 50;
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
        if (count1 >=10){
            fps = 60;
            count1 = 0;
            count2 = 0;
            return left;
        }
        if (count2 >= 10){
            fps = 60;
            count1 = 0;
            count2 = 0;
            return right;
        }
        xball += xballspeed;
        yball += yballspeed;
        return null;
      
    }

}