import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class RunPong{

    private static ArrayList<Integer> topology;
    private static ArrayList<GeneticController> generation = new ArrayList<GeneticController>();
    private static ArrayList<GeneticController> champions = new ArrayList<GeneticController>();
    private static int genCount;
    private static GeneticController left;
    private static GeneticController right;
    public static Pong pong;

    public RunPong(){
        /*topology = new ArrayList<Integer>(Arrays.asList(6,10,10,3));
        genCount = 0;
        //generation = new ArrayList<GeneticController>();
        for (int i = 0; i < 50; i++){
            GeneticController controller = new GeneticController(topology, 0.05);
            generation.add(controller);
        }
        //champions = new ArrayList<GeneticController>();
        left = generation.get(genCount);
        genCount++;
        right = generation.get(genCount);
        genCount++;
        //System.out.println(genCount);
        pong = new GeneticPong(left, right);*/
    }

    public static void main(String[] args){
        topology = new ArrayList<Integer>(Arrays.asList(6,5,3));
        for (int k = 0; k < 50; k++){
            genCount = 0;
            generation = new ArrayList<GeneticController>();
            if (champions == null || champions.size() == 0){
                for (int i = 0; i < 50; i++){
                    GeneticController controller = new GeneticController(topology, 0.07);
                    generation.add(controller);
                }
            } else{
                Collections.sort(champions, new Comparator<GeneticController>() {
                    public int compare(GeneticController champ, GeneticController ion){
                        return champ.fitness > ion.fitness ? 1 : champ.fitness < ion.fitness ? -1 : 0;
                    }
                });
                Collections.reverse(champions);
                for ( int i = 0; i < 10; i++){
                    GeneticController champ = champions.get(i);
                    for (int j = 0; j < 5; j++ ){
                        GeneticController champCopy = champ;
                        champCopy.mutate();
                        generation.add(champCopy);
                    }
                    Collections.shuffle(generation);
                }
            }
            champions = new ArrayList<GeneticController>();
            left = generation.get(genCount);
            genCount++;
            right = generation.get(genCount);
            genCount++;
            //System.out.println(genCount);
            pong = new GeneticPong(left, right);
            for (genCount = 2; genCount < generation.size(); genCount++){
                GeneticController champion = (GeneticController)(pong.run());
                champions.add(champion);
                pong = new GeneticPong((Controller)(generation.get(genCount++)), (Controller)(generation.get(genCount++)));
                pong.generation = k;
                //System.out.println(genCount);
                pong.yeet();
            }
            pong.yeet();
        }
    }
}