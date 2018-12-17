import java.util.ArrayList;
import java.util.Arrays;

public class RunPong{

    private static ArrayList<Integer> topology= new ArrayList<Integer>(Arrays.asList(6,10,3));
    private static GeneticController left = new GeneticController(topology, 0.05);
    private static GeneticController right = new GeneticController(topology, 0.05);
    public static Pong pong = new GeneticPong(left, right);

    public RunPong(){
        topology = new ArrayList<Integer>(Arrays.asList(6,10,3));
        left = new GeneticController(topology, 0.05);
        right = new GeneticController(topology, 0.05);
        pong = new GeneticPong(left, right);
    }

    public static void main(String[] args){
        pong.run();
    }
}