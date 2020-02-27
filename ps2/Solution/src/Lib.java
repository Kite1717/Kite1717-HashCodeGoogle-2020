import java.util.ArrayList;
import java.util.Map;

public class Lib implements ISort{

    private final int singUpTime ;
    private final int scanPerDay;
    private Map<Integer,Integer> books;
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







    public int getSingUpTime() {
        return singUpTime;
    }


    public int getScanPerDay() {
        return scanPerDay;
    }

    public Map<Integer, Integer> getBooks() {
        return books;
    }

    public void setBooks(Map<Integer, Integer> books) {
        this.books = ISort.sortByValueReverseOrder(books);
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }
}
