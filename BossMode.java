import java.util.ArrayList;

public class BossMode extends Controller{

    public Movement chooseMovement(ArrayList<Double> inputs){
        if (inputs.get(0) > inputs.get(3)){
            return Movement.DOWN;
        } else if (inputs.get(0) < inputs.get(3)){
            return Movement.UP;
        }
        return Movement.NONE;
    }

    public void setFitness(Double ye, Double et){
        ye += et;
    }
}