package utils;

public class UtilNoteBook {
    String name;
    int min, max;

    public UtilNoteBook(String name, int min, int max){
        this.max = max;
        this.min = min;
        this.name = name;
    }

    @Override
    public String toString() {
        return "UtilNoteBook{" +
                "name='" + name + '\'' +
                ", min=" + min +
                ", max=" + max +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
