import java.util.ArrayList;

public class Controller{



    public int fitness;
    public ArrayList<Integer> topology;
    public ArrayList<ArrayList<Node>> nodes;

    public Controller(ArrayList<Integer> topology){
        this.fitness = 0;
        this.topology = topology;
        this.nodes = new ArrayList<ArrayList<Node>>();
        for (int i = 1; i < this.topology.size(); i++){
            ArrayList<Node> layer = new ArrayList<Node>();
            this.nodes.set(i, layer);
            for (int j = 0; j < this.topology.get(i); j++){
                Node node = new Node(this.topology.get(i-1));
                this.nodes.get(i).set(j, node);
            }
        }
    }

    public Movement chooseMovement(ArrayList<Double> inputs){
        ArrayList<ArrayList<Double>> outputValues = new ArrayList<ArrayList<Double>>();
        for (int i = 0; i < this.topology.get(0); i++){
            outputValues.get(0).set(i, inputs.get(i));
        }
        for (int i = 1; i < topology.size(); i++){
            for (int j = 0; j < topology.get(i); j++){
                nodes.get(j).getValue(outputValues.get(i-1));
            }
        }
        int maxIndex = 0;
        for (int i = 1; i < topology.get(topology.size()-1); i++){
            if (outputValues.get(topology.size()-1).get(i) > outputValues.get(topology.size()-1).get(maxIndex)){
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
}