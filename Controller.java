import java.util.ArrayList;

public abstract class Controller{

    public abstract Movement chooseMovement(ArrayList<Double> inputs);

    public abstract void setFitness(Double ourScore, Double theirScore);

}