import java.util.ArrayList;
import java.util.HashMap;

public class Scaner {

    private int currentDeadline;
    private ArrayList<Lib> libs;
    private final int booksCount;


    private int totalScore = 0;
    private double averageScore = 0.0;
    private double averageSignUpTime = 0.0;

    public int getTotalScore() {
        return totalScore;
    }




    public Scaner(int currentDeadline,ArrayList<Lib> libs,int booksCount)
    {
        this.currentDeadline = currentDeadline;
        this.libs = libs;
        this.booksCount = booksCount;

    }


    public int findBestLibAndAdd()
    {
      int scannedLib = 0;
      int scannedBooksCount = 0;

      int temp = 1;



      while (true) {
          //!!!!!
          calculateAverageScoreAndSignUpTime();
         // bookScoreOrganize();

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

          //int index = calculateLibScoreFindIndex();


          if(index == -1) break;

          //little CORNER but really corner case xD
        if(libs.get(index).getBooks().size() == 0) {
            libs.get(index).setUsed(true);
            continue;
        }


          libs.get(index).setUsed(true);
          scannedLib++;
          currentDeadline -= libs.get(index).getSingUpTime();



          //I got the book indices to be deleted
          ArrayList<Integer> removedBookIndexes = getRemoveIndex(libs.get(index));
          scannedBooksCount += removedBookIndexes.size();


             // System.out.println(totalScore);
              totalScore += getAbsoluteScore(libs.get(index));



              log.append(index).append(" ").append(removedBookIndexes.size()).append("\n");
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
          {
              break;
          }



      }




      return scannedLib;

    }


    private int findMaxScoreLibIndex_4()
    {

        int maxIndex = -1;

        double maxScore = Double.MIN_VALUE;


        for (int i = libs.size() - 1; i >= 0; i--)
        {
            if(!libs.get(i).isUsed())
            {
                /****double score = calculateLibScore(libs.get(i));
               * C için düz hesap puan ile ort bakmaya gerek yok */



                 /*********double score = calculateLibScore(libs.get(i));
                 *  ortalamaya bakmak gerek*/

                double score = calculateLibScore(libs.get(i));
                if(score != -1.0 )
                {
                    score /= libs.get(i).getSingUpTime();

                    if(score > maxScore  )
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


        return maxIndex;
    }

    private void bookScoreOrganize()
    {

        for (int i = 0; i < booksCount ; i++)
        {
            int counter = 0;
            double value = -1.0;
            ArrayList<Integer> existingLibsIndex = new ArrayList<>();
            for(int j = 0; j < libs.size() ; j++)
            {
                if(!libs.get(j).isUsed()) {

                    if (libs.get(j).getBooks().containsKey(i)) {
                        if (counter == 0) {
                            value = libs.get(j).getBooks().get(i);
                        }
                        counter++;
                        existingLibsIndex.add(j);
                    }
                }

            }
            if(value != -1.0)
            {
                double newValue = value / (1.0 * counter);

                for(int k = 0 ; k < existingLibsIndex.size() ; k++)
                {
                    libs.get(existingLibsIndex.get(k)).getBooks().replace(i,newValue);
                }

            }
        }

        for (Lib lib : libs)
            lib.setBooks(ISort.sortByValueReverseOrder(lib.getBooks()));

    }


    private void  calculateAverageScoreAndSignUpTime()
    {
        HashMap<Integer,Double> map  = new HashMap<>();


        int ssum = 0 , count = 0;
        for(Lib lib : libs)
        {
            if(!lib.isUsed())
            {
                ssum += lib.getSingUpTime();
                count ++;


            }
            map.putAll(lib.getBooks());



        }
        int sum = 0;
       for(double i : map.values())
            sum += i;

        averageSignUpTime =(ssum * 1.0) / (count * 1.0);
       averageScore = (sum * 1.0) / map.size();

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
       if(lifeTime  <= 0) return  -1;

       int numberScanBook = lifeTime * lib.getScanPerDay();




       int score = 0;
       if(numberScanBook >= lib.getBooks().size())
       {
           for(double i : lib.getBooks().values())
           {
               if(averageScore <= i)
                   score += i;
           }

       }
       else {
           int counter = 0;
           for(double i : lib.getBooks().values())
           {
              if(averageScore <= i)
               {
                   score += i;
                   counter++;
               }
               if(counter == numberScanBook) break;
           }
       }

       return  score;
   }





    private  int getAbsoluteScore(Lib lib)
    {


        int score = 0;
       int  scannedBookCount = currentDeadline * lib.getScanPerDay();
        if(scannedBookCount >= lib.getBooks().size())
        {
            for(double i : lib.getBooks().values())
            {
                    score += i;
            }

        }
        else {
            int counter = 0;
            for(double i : lib.getBooks().values())
            {

                    score += i;
                    counter++;

                if(counter == scannedBookCount) break;
            }
        }

        return  score;
    }
    private  ArrayList<Integer> getRemoveIndex(Lib lib)
    {


        int scannedBookCount = currentDeadline * lib.getScanPerDay();
        ArrayList<Integer> removed = new ArrayList<>();
        if(scannedBookCount >= lib.getBooks().size())
        {
            removed.addAll(lib.getBooks().keySet());
        }
        else {
            int counter = 0;

            for(int i : lib.getBooks().keySet())
            {

                removed.add(i);
                counter++;
                if(counter == scannedBookCount) break;
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
