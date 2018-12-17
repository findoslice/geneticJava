import java.util.ArrayList;
import java.lang.Math;

public class Node{

    public ArrayList<Double> biases;
    public Double learningRate;
    private Integer previousLayer;
    public ArrayList<Double> coefficients;

    public Node(int previousLayer, Double learningRate){
        this.biases = new ArrayList<Double>();
        this.learningRate = learningRate;
        this.previousLayer = previousLayer;
        this.coefficients = new ArrayList<Double>();
        //extracted(previousLayer);
    }


	public void initialise() {
        for (int i = 0; i < this.previousLayer; i++) {
            this.biases.add(i, Math.random());
            this.coefficients.add(i, Math.random());
        }
    }

    public Double getValue(ArrayList<Double> inputs) {
        Double sum = 0.0;
        for (int i = 0; i < inputs.size(); i++){
            sum += (Double)(coefficients.get(i)*inputs.get(i) - biases.get(i));
        }
        return sum;
    }

    public Node mutate(){
        for (int i = 0; i < this.biases.size(); i++){
            if (Math.random() <= this.learningRate){
                this.biases.set(i, this.biases.get(i) + ((((Math.random()*1000)-500)%100)/1000));
            }
            if (Math.random() <= this.learningRate){
                this.coefficients.set(i, this.coefficients.get(i) + ((((Math.random()*1000)-500)%100)/1000));
            }
        }
        return this;
    }
}