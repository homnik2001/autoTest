package utils;

import java.util.List;

public class Data {
    private int maxPrice;
    List<UtilNoteBook> list;

    public Data(int maxPrice, List<UtilNoteBook> list) {
        this.maxPrice = maxPrice;
        this.list = list;
    }

    @Override
    public String toString() {
        return "Data{" +
                "maxPrice=" + maxPrice +
                ", list=" + list +
                '}';
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public List<UtilNoteBook> getList() {
        return list;
    }

    public void setList(List<UtilNoteBook> list) {
        this.list = list;
    }
}
