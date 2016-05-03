import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class MovieReader {
   
    private List<Movie> movieSet; //keeps track of movies
    private List<User> userSet; //keeps track of users
    private List<Review> reviewSet; //keeps track of reviews

    @SuppressWarnings({ "resource", "unused" })
	public MovieReader() throws IOException{
        movieSet = new ArrayList<Movie>(); 
        userSet = new ArrayList<User>();
        reviewSet = new ArrayList<Review>();
        
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
        Set<User> user = new HashSet<User>();
        Set<Movie> mov = new HashSet<Movie>();

        //keeps track of how many reviews we have looked at to stay under 10000
        int z = 0;
        while(x != null && z < 10000){
        		//reads each line for a specific part of the review to store 
        		//the entire review as a whole
                for(int i = 0; i < 8; i++){
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
            //creates the objects
            Review re = new Review(userID, productID, text, score, helpfulness);
            User u = new User(userID, profileName);
            Movie m = new Movie(productID);
            
            //checks to see if we have the movie already
            if(movieSet.contains(m)){
                m = movieSet.get(movieSet.indexOf(m));
                
                //checks to see if we have the user already
                if(userSet.contains(u)){
                    u = userSet.get(userSet.indexOf(u));
                    
                    //add information into our sets
                    m.addReview(re, u);
                    u.addReview(re);
                    u.addMovie(m);
                }
                else{
                	//we add the information to our sets and add the user to the 
                	//user set
                    u.addReview(re);
                    m.addReview(re, u);  
                    u.addMovie(m);
                    userSet.add(u);
                }
                
            }
            else{
                //add the movie to our set
                movieSet.add(m);
                
                //check to see if we have the user already
                if(userSet.contains(u)){
                    u = userSet.get(userSet.indexOf(u));
                    
                    //add new information in 
                    u.addReview(re);
                    m.addReview(re, u);
                    u.addMovie(m);
                }
                else{
                	//add new information and add the new user to set
                	u.addReview(re);
                	m.addReview(re, u);
                	u.addMovie(m);
                	userSet.add(u);
                }
            }
            
            z++;
            x = br1.readLine();

        }
    
    }
    
    //return the set of all movies
    public List<Movie> getMovies() {
        return movieSet;
    }
    
    //return the set of all users
    public List<User> getUsers() {
        return userSet;
    }
    
    //return the set of all reviews
    public List<Review> getReviews() {
        return reviewSet;
    }
}