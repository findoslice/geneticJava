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

    public GeneticPong(Controller left, Controller right){
        super(left, right);
        this.left = left;
        this.right = right;
    }

}