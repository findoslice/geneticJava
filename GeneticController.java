import java.util.ArrayList;
import java.lang.Math;

public class GeneticController extends Controller {



    public Double fitness;
    public Double learningRate;
    public ArrayList<Integer> topology;
    public ArrayList<ArrayList<Node>> nodes;

    public GeneticController(ArrayList<Integer> topology, Double learningRate){
        this.fitness = 0.00;
        this.learningRate = learningRate;
        this.topology = topology;
        this.nodes = new ArrayList<ArrayList<Node>>();
        ArrayList<Node> layer = new ArrayList<Node>();
        //this.nodes.add(0, layer);
        for (int i = 1; i < this.topology.size(); i++){
            layer = new ArrayList<Node>();
            this.nodes.add(layer);
            for (int j = 0; j < this.topology.get(i); j++){
                Node node = new Node(this.topology.get(i-1), this.learningRate);
                node.initialise();
                this.nodes.get(i-1).add(node);
            }
        }
    }

    public Movement chooseMovement(ArrayList<Double> inputs){
        ArrayList<ArrayList<Double>> outputValues = new ArrayList<ArrayList<Double>>();
        for (int i = 0; i < topology.size(); i++){
            ArrayList<Double> layer = new ArrayList<Double>();
            outputValues.add(layer);
        }
        for (int i = 0; i < this.topology.get(0); i++){
            outputValues.get(0).add(inputs.get(i));
        }
        for (int i = 1; i < topology.size(); i++){
            for (int j = 0; j < topology.get(i); j++){
                outputValues.get(i).add(this.nodes.get(i-1).get(j).getValue(outputValues.get(i-1)));
            }
        }
        System.out.println(this.nodes);
        System.out.println(outputValues);
        int maxIndex = 0;
        for (int i = 1; i < topology.get(topology.size()-1); i++){
            if (outputValues.get(outputValues.size()-1).get(i) > outputValues.get(outputValues.size()-1).get(maxIndex)){
                maxIndex = i;
            }
        }
        switch (maxIndex){
            case 0:
                return Movement.UP;
            case 2:
                return Movement.DOWN;
            default:
                return Movement.NONE;
        }
    }

    public void setFitness(Double ourScore, Double theirScore){
        if (theirScore > 0){
            this.fitness = ourScore/2;
        }
        this.fitness = ourScore/theirScore;
    }
    public Double getFitness(){
        return this.fitness;
    }
}