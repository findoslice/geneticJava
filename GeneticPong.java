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
        ArrayList<Double> inputs = new ArrayList<Double>(Arrays.asList((Double)(this.v1/(1.0*this.windowHeight)),
                                                                       (Double)(this.v2/(1.0*this.windowHeight)), 
                                                                       (Double)(this.xball/(1.0*this.windowWidth)), 
                                                                       (Double)(this.yball/(1.0*this.windowHeight)), 
                                                                       (Double)(this.xballspeed/5.0-1.0),
                                                                       (Double)(this.yballspeed/2.0),
                                                                       this.windowHeight*1.0,
                                                                       this.windowWidth*1.0));
        if (xball <= 55 && xball >= (55 - windowWidth/15) && (yball > v1 - 25 && yball < v1 + 100)){
            xballspeed = Math.abs(xballspeed)+rands.get((int)Math.random()*rands.size());
            yballspeed += (int)(Math.random()*3.0-1.5);
            Movement leftMove = this.leftController.chooseMovement(inputs);
            if (leftMove == Movement.UP && (v1 - paddlespeed > 0)){
                yballspeed -= 1;
            }
            else if (leftMove == Movement.DOWN && (v1 + paddlespeed < windowHeight - 50)){
                yballspeed += 1;
            }
        }
        if (xball >= 568 && (xball <= 568 + windowWidth/15) && (yball > v2 - 25 && yball < v2 + 100)){
            xballspeed = -1*Math.abs(xballspeed)-rands.get((int)Math.random()*rands.size());
            yballspeed += (int)(Math.random()*3.0-1.5);
            Movement rightMove = this.rightController.chooseMovement(inputs);
            if (rightMove == Movement.UP && (v2 - paddlespeed > 0)){
                yballspeed -= 1;
            }
            else if (rightMove == Movement.DOWN && (v2 + paddlespeed < windowHeight - 50)){
                    yballspeed += 1;
            }
        }
        if (Math.abs(xballspeed) < 5){
            if (xballspeed < 0){
                xballspeed = -5;
            } else{
                xballspeed = 5;
            }
            
        }
        if (xball < 0){
            xball = windowWidth/2;
            yball = windowHeight/2;
            yballspeed = (int)(Math.round(Math.random()*2.0-1.0));
            Double yeet = Math.random();
            if (yeet > 0.5){
                xballspeed = (int)(yeet*5);
            } else{
                xballspeed = -1*(int)(yeet*10);
            }
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
            yballspeed = (int)(Math.round(Math.random()*2.0-1.0));
            Double yeet = Math.random();
            if (yeet > 0.5){
                xballspeed = (int)(yeet*5);
            } else{
                xballspeed = -1*(int)(yeet*10);
            }
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
            fps = 300;                                
        }
        if (count1 >=10){
            fps = 300;
            count1 = 0;
            count2 = 0;
            if (left instanceof GeneticController){
                if (count2 != 0){
                    left.setFitness(count1/1.0, count2/1.0);
                } else {
                    left.setFitness(count1/1.0, 2.0);
                }
            } else{
                left.setFitness(count2/1.0, count1/1.0);
            }
            return left;
        }
        if (count2 >= 10){
            fps = 300;
            count1 = 0;
            count2 = 0;
            if (count1 != 0){
                right.setFitness(count2/1.0, count1/1.0);
            } else {
                right.setFitness(count2/1.0, 2.0);
            }
            return right;
        }
        xball += xballspeed;
        yball += yballspeed;
        return null;
      
    }

}