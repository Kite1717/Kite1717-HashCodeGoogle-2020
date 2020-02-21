import java.util.ArrayList;

public class Scaner {

    public int scannedBook;







    public void findBestLibAndAdd(ArrayList<Lib> libs,int deadLine)
    {
          int maxLib = -1;
          int score = 0;

       for (int i = 0; i< libs.size();i++)
       {
           if(!libs.get(i).isUsed)
           {

              calculateScore(libs.get(i).books,deadLine - libs.get(i).singUpTime )
           }
       }

    }

}
