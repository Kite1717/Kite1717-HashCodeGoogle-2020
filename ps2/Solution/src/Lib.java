import java.util.HashMap;
import java.util.Map;

public class Lib implements ISort{

    public int singUpTime ;
    public  int scanPerDay;
    public Map<Integer,Integer> books;
    public boolean isUsed;

    public Lib(int singUpTime, int scanPerDay, HashMap<Integer, Integer> books) {
        this.singUpTime = singUpTime;
        this.scanPerDay = scanPerDay;
        this.books = ISort.sortByValueReverseOrder(books);
        this.isUsed = false;
    }


    public  void deleteScannedBook(int[] indexes)
    {
        for (int i : indexes)
            books.remove(i);

    }
}
