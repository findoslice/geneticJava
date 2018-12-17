import java.util.ArrayList;
import java.util.Arrays;

public class RunPong{

    private static ArrayList<Integer> topology;
    private static ArrayList<GeneticController> generation, champions;
    private static int genCount;
    private static GeneticController left;
    private static GeneticController right;
    public static Pong pong;

    public RunPong(){
        topology = new ArrayList<Integer>(Arrays.asList(6,5,3));
        genCount = 0;
        generation = new ArrayList<GeneticController>();
        for (int i = 0; i < 50; i++){
            GeneticController controller = new GeneticController(topology, 0.05);
            generation.add(controller);
        }
        champions = new ArrayList<GeneticController>();
        left = generation.get(genCount);
        genCount++;
        right = generation.get(genCount);
        genCount++;
        System.out.println(genCount);
        pong = new GeneticPong(left, right);
    }

    public static void main(String[] args){
        for (genCount = 2; genCount < generation.size(); genCount++){
            GeneticController champion = (GeneticController)(pong.run());
            champions.add(champion);
            pong = new GeneticPong((Controller)(generation.get(genCount++)), (Controller)(generation.get(genCount++)));
            System.out.println(genCount);
        }
    }
}