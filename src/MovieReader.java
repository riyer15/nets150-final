import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class MovieReader {
    Set<Movie> movieSet;
    Set<User> userSet;
    Set<Review> reviewSet;
    
    public MovieReader() throws IOException{
        movieSet = new HashSet<Movie>();
        userSet = new HashSet<User>();
        reviewSet = new HashSet<Review>();
        
        Reader r = new FileReader("movies.txt");
        BufferedReader br1 = new BufferedReader(r);
        String x = br1.readLine();
        String userID;
        String productID;
        String profileName;
        String helpfulness;
        String score  = "";
        String time;
        String summary = "";
        String text;
        while(x != null){
            System.out.println(x);
            //if(x.contains("product/productId:")){
                System.out.println(x);
                //int i = 0;
                //while(i < 8){
                    if(x.contains("productId")){
                        productID = x.substring(x.indexOf(" ") + 1);
                    }
                    else if(x.contains("review/userId:")){
                        userID = x.substring(x.indexOf(" ") + 1);
                    }
                    else if(x.contains("review/profileName:")){
                        profileName = x.substring(x.indexOf(" ") + 1);
                    }
                    else if(x.contains("review/helpfulness:")){
                        helpfulness = x.substring(x.indexOf(" ") + 1);
                    }
                    else if(x.contains("review/score:")){
                        score = x.substring(x.indexOf(" ") + 1);
                    }
                    else if(x.contains("review/time:")){
                        time = x.substring(x.indexOf(" ") + 1);
                    }
                    else if(x.contains("review/summary:")){
                        summary = x.substring(x.indexOf(" ") + 1);
                    }
                    else if(x.contains("review/text:")){
                        text = x.substring(x.indexOf(" ") + 1);
                    }
          
                    //Movie mov = new Movie(productID, new HashSet<Review>(), new HashSet<User>());
                    
             
               // }
           // }
            x = br1.readLine();

           // System.out.println(score);
        }
    }
    
    public static void main(String[]args) throws IOException{
        MovieReader mr = new MovieReader();
    }
}
