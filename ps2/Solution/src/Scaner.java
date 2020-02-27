import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Scaner {

    private int currentDeadline;
    private ArrayList<Lib> libs;
    private final int booksCount;


    private double avarageScore = 0.0;


    private int numberScanBook;
    private ArrayList<String> logs ;
    private  int round = 1;
    public Scaner(int currentDeadline,ArrayList<Lib> libs,int booksCount)
    {
        this.currentDeadline = currentDeadline;
        this.libs = libs;
        this.booksCount = booksCount;
        numberScanBook = 0;

        logs = new ArrayList<>();

    }

    public int findBestLibAndAdd()
    {
      int scannedLib = 0;
      int scannedBooksCount = 0;
      while (true) {
          calculateAverageScore();
          StringBuilder log = new StringBuilder();
          //I found the library with the maximum score

          //try one success
          //int index = findMaxScoreLibIndex_1();
          //try two success
          //int index = findMaxScoreLibIndex_2();
          //try three success
         // int index = findMaxScoreLibIndex_3();
          //try four success
          int index = findMaxScoreLibIndex_4();


          if(index == -1) break;

          //little CORNER but really corner case xD
        if(libs.get(index).getBooks().size() == 0) {
            libs.get(index).setUsed(true);
            continue;
        }

          log.append(index).append(" ");
          libs.get(index).setUsed(true);
          scannedLib++;
          currentDeadline -= libs.get(index).getSingUpTime();


          //I got the book indices to be deleted
          ArrayList<Integer> removedBookIndexes = getRemoveIndex(libs.get(index).getBooks(), currentDeadline * libs.get(index).getScanPerDay());
          scannedBooksCount += removedBookIndexes.size();


              log.append(removedBookIndexes.size()).append("\n");
              for (int i : removedBookIndexes)
                  log.append(i).append(" ");
              log.append("\n");

              Helper.logs.add(log.toString());


          //I deleted scanned books from other libraries
          bookDeletion(removedBookIndexes);


        /*  System.out.println("Scanned lib count: " + scannedLib);
          System.out.println("Current deadline : " + currentDeadline);
          System.out.println("Scanned books count : " + scannedBooksCount +"\n"); */

          //exit control
          if(libs.size() == scannedLib || currentDeadline <= 0 || booksCount == scannedBooksCount)
              break;


      }

        //Helper.writer(logs);
      return scannedLib;
    }


    private int findMaxScoreLibIndex_4()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Round : ").append(round).append("\n");
        int maxIndex = -1;
        int roundedMaxScore = Integer.MIN_VALUE;
        double maxScore = Double.MIN_VALUE;
        int minEmptyTime = Integer.MAX_VALUE;

        for (int i = libs.size() - 1; i >= 0; i--)
        {
            if(!libs.get(i).isUsed())
            {
                /*double score = calculateLibScore(libs.get(i));
                score /= libs.get(i).getSingUpTime(); * C için düz hesap puan ile ort bakmaya gerek yok */

                 /*double score = calculateLibScore(libs.get(i));
                score /= Math.pow(libs.get(i).getSingUpTime(),1.3); * F için ortalamaya bakmak gerek*/

                 /*double score = calculateLibScore(libs.get(i));
                score /= Math.pow(libs.get(i).getSingUpTime()); * E için ortalamaya bakmak gerek*/

                double score = calculateLibScore(libs.get(i));
                if(score != -1.0)
                {
                    score /= libs.get(i).getSingUpTime();

                    if(score > maxScore )
                    {
                        maxScore = score;
                        maxIndex = i;
                    }
                   /* int round = (int) Math.round(score);
                    if(roundedMaxScore <= round)
                    {
                        if(roundedMaxScore == round)
                        {
                            double candidateBookScore = calculateLibScore(libs.get(i)) * 1.0;
                            double maxBookScore = calculateLibScore(libs.get(maxIndex)) * 1.0;
                            if(candidateBookScore > maxBookScore )
                            {
                                roundedMaxScore = round;
                                maxScore = score;
                                maxIndex = i;
                            }
                        }
                        else
                        {
                            roundedMaxScore = round;
                            maxScore = score;
                            maxIndex = i;
                        }

                    }
                    */

                }


            }
        }

        //sb.append("\nMax Score : ").append(maxScore);
        //logs.add(sb.toString());
        round ++;
        // System.out.println(maxScore);
        return maxIndex;
    }

    private void  calculateAverageScore()
    {
        HashMap<Integer,Integer> map  = new HashMap<>();

        for(Lib lib : libs)
        {
            map.putAll(lib.getBooks());
        }
        int sum = 0;
        for(int i : map.values())
            sum += i;

        avarageScore = (sum * 1.0) / map.size();
    }

    private int findMaxScoreLibIndex_1()
    {
        int maxIndex = -1 , maxScore = Integer.MIN_VALUE;
        for (int i = 0; i < libs.size() ;i++)
        {
            if(!libs.get(i).isUsed())
            {
                int score = calculateLibScore(libs.get(i));
                if(maxScore < score && score != -1)
                {
                    maxScore = score;
                    maxIndex = i;
                }
            }
        }

       // System.out.println(maxScore);
        return maxIndex;
    }
    private int findMaxScoreLibIndex_2()
    {
        int maxIndex = -1 , minSignUpTime = Integer.MAX_VALUE;
        for (int i = 0; i < libs.size() ;i++)
        {
            if(!libs.get(i).isUsed())
            {
                int signUpTime  = libs.get(i).getSingUpTime();
                if(minSignUpTime > signUpTime)
                {
                    minSignUpTime = signUpTime;
                    maxIndex = i;
                }
            }
        }

        // System.out.println(maxScore);
        return maxIndex;
    }



    private int findMaxScoreLibIndex_3()
    {
        int maxIndex = -1 , minSignUpTime = Integer.MAX_VALUE;
        int maxScore = Integer.MIN_VALUE;

        for (int i = libs.size() - 1; i >= 0; i--)
        {
            if(!libs.get(i).isUsed())
            {

                int signUpTime  = libs.get(i).getSingUpTime();

                int score = calculateLibScore(libs.get(i));
                if(minSignUpTime > signUpTime && maxScore < score && score != -1 )
                {
                    minSignUpTime = signUpTime;
                    maxScore = score;
                    maxIndex = i;
                }


            }
        }

        return maxIndex;
    }



   private int calculateLibScore(Lib lib)
   {
       int lifeTime = currentDeadline - lib.getSingUpTime();
       //corner case
       if(lifeTime  < 0) return  -1;

       numberScanBook = lifeTime * lib.getScanPerDay();

       return getScore(lib.getBooks());
   }



    private  int getScore(Map<Integer,Integer> map)
    {
        int score = 0;
        if(numberScanBook > map.size())
        {
            for(int i : map.values())
            {
                if(avarageScore <= i)
                    score += i;
            }

        }
        else {
            int counter = 0;
            for(int i : map.values())
            {
                if(avarageScore <= i)
                {
                    score += i;
                    counter++;
                }
                if(counter == numberScanBook) break;
            }
        }

      return  score;
    }

    private  ArrayList<Integer> getRemoveIndex(Map<Integer,Integer> map,int maxNumberScanBook)
    {

        ArrayList<Integer> removed = new ArrayList<>();
        if(maxNumberScanBook > map.size())
        {
            removed.addAll(map.keySet());
        }
        else {
            int counter = 0;

            for(int i : map.keySet())
            {

                removed.add(i);
                counter++;
                if(counter == maxNumberScanBook) break;
            }
        }

        return removed;

    }

   private void bookDeletion(ArrayList<Integer> removedIndexes)
   {
       for(Lib lib : libs)
           if(!lib.isUsed())
               lib.deleteScannedBook(removedIndexes);

   }


}
