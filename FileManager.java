import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class FileManager {

    public FileManager(){
        //Just initialization
}
    public Double[] getHighScoreList(String file, double score){
        Double[] highScores = readFromFile(file);
        //try to sort
        System.out.println("try to sort" + Arrays.toString(highScores));

        Arrays.sort(highScores);
        if(score> highScores[0]) {
            highScores[0] = score;
            Arrays.sort(highScores);
            writeToFile(file, highScores);
            System.out.println("New HighScore");
        }
        else{
            System.out.println("No new HighScore");
        }
        return highScores;
    }

    public void resetScores(String file){
        Double[] highScore1 = new Double[0];
        writeToFile(file, highScore1);
        System.out.println("HighScores reset");
    }







    public static Double[] readFromFile(String filename){
        Double[] scores = new Double[3];
        File testFile = new File(filename);
        try {

            if (testFile.createNewFile()) {
                System.out.println("File has been created.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException ioException){
            System.out.println(ioException);
            System.out.println("IO Exception");
        }


        try{
            Scanner inFile1 = new Scanner(testFile);
            int count1 = 0;

            while (inFile1.hasNext()) {
                if (count1 >= 3){
                    break;
                }
                scores[count1] = Double.parseDouble(inFile1.nextLine());

                count1++;
            }
            for(int j=0;j<scores.length;j++) {
                if(scores[j]==null){
                    scores[j] = 0.0;
                }
            }
            System.out.println("print file read in " + Arrays.toString(scores));
            inFile1.close();
        } catch (FileNotFoundException fnfe){
            System.out.println(fnfe);
            System.out.println("File missing");
        }

        //for(int i=0;i<scores.length;i++) {
        //    System.out.println(scores[i]);
        //}
        return scores;
    }
    ////////////////////////////////////////////////////
    public static void writeToFile(String filename, Double[] array){
        PrintWriter writeFile;

        try{
            writeFile = new PrintWriter(filename);
            for(int i=0;i<array.length;i++){
                writeFile.println(array[i]);
            }
            writeFile.close();
        } catch (FileNotFoundException fnfe2){
            System.out.println(fnfe2);
            System.out.println("File missing");
        }

    }

}
/*
https://stackoverflow.com/questions/19844649/java-read-file-and-store-text-in-an-array
Example by user https://stackoverflow.com/users/1005102/utku-%c3%96zdemir

And https://stackoverflow.com/questions/19788989/error-unreported-exception-filenotfoundexception-must-be-caught-or-declared-to
For File not found
By user https://stackoverflow.com/users/2936460/sqb

http://zetcode.com/java/createfile/
To make sure you can make a file

https://www.daniweb.com/programming/software-development/threads/281973/printing-out-a-2d-array-into-txt
For printing to a file

https://www.tutorialspoint.com/java/util/arrays_sort_double.htm
For sorting arrays using built in library
 */