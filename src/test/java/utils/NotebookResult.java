package utils;

public class NotebookResult implements Comparable{
    private String name;
    private double diagonal, weight;

    public NotebookResult(String name, double diagonal, double weight) {
        this.name = name;
        this.diagonal = diagonal;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(double diagonal) {
        this.diagonal = diagonal;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(Object o) {
        NotebookResult second =  (NotebookResult)o;
        if(diagonal == second.getDiagonal()){
            return Double.compare(weight, second.getWeight());
        }
        else{
            return Double.compare(diagonal, second.getDiagonal());
        }
    }
}
