import java.util.ArrayList;
import java.util.Map;

public class Lib implements ISort ,Cloneable{

    private final int singUpTime ;
    private final int scanPerDay;
    private Map<Integer,Double> books;
    private boolean isUsed;

    public Lib(int singUpTime, int scanPerDay) {
        this.singUpTime = singUpTime;
        this.scanPerDay = scanPerDay;
        this.isUsed = false;
    }


    public  void deleteScannedBook(ArrayList<Integer> indexes)
    {
        for (int i : indexes)
            books.remove(i);

    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public int getSingUpTime() {
        return singUpTime;
    }


    public int getScanPerDay() {
        return scanPerDay;
    }

    public Map<Integer, Double> getBooks() {
        return books;
    }

    public void setBooks(Map<Integer, Double> books) {
        this.books = ISort.sortByValueReverseOrder(books);
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }
}
