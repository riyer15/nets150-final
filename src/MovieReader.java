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
        String userID = "";
        String productID = "";
        String profileName = "";
        String helpfulness = "";
        String score  = "";
        String time = "";
        String summary = "";
        String text = "";
        Set<Review> rev = new HashSet<Review>();
        Set<User> user = new HashSet<User>();
        Set<Movie> mov = new HashSet<Movie>();


        while(x != null){
            System.out.println(x);
            //if(x.equals("")){
                for(int i = 0; i < 8; i++){
                    System.out.println(x);
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
                    x = br1.readLine();
                }
            Review re = new Review(userID, productID, text, score, helpfulness);
            User u = new User(userID, mov, rev, profileName);
            Movie m = new Movie(productID, rev, user);
            if(movieSet.contains(m)){
                if(userSet.contains(u)){
                    m.addReview(re, u);
                }
                else{
                    userSet.add(u);
                    //u.add
                }
                
            }
            else{
                movieSet.add(m);
            }
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
