import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class MovieReader {
   
    private List<Movie> movieSet; 
    private List<User> userSet;
    private List<Review> reviewSet;

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
        //Set<Review> rev = new HashSet<Review>();
        Set<User> user = new HashSet<User>();
        Set<Movie> mov = new HashSet<Movie>();


        //while(x != null){
          //  System.out.println(x);
        int z = 0;
        while(x != null && z < 10000){
            //System.out.println(x);
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
            System.out.println(re.getUserID());
            //User u = new User(userID, mov, rev, profileName);
            User u = new User(userID, profileName);
            Movie m = new Movie(productID);
            
           // System.out.println(u.getID());
            //Movie m = new Movie(productID, new HashSet<Review>(), user);
            
          
            if(movieSet.contains(m)){
                System.out.println("please");
                //System.out.println(m);
                m = movieSet.get(movieSet.indexOf(m));
                if(userSet.contains(u)){
                    u = userSet.get(userSet.indexOf(u));
                    m.addReview(re, u);
                    u.addReview(re);
                    u.addMovie(m);
                }
                else{
                    //System.out.println(u.getID());
                    //userSet.add(u);
                    
                    u.addReview(re);
                    System.out.println(u.getReviews().size());
                    m.addReview(re, u);  
                    u.addMovie(m);
                    userSet.add(u);
                    System.out.println("add" + re.getUserID());
                }
                
            }
            else{
                //m = new Movie(productID, new HashSet<Review>(), user);
                movieSet.add(m);
                if(userSet.contains(u)){
                    u = userSet.get(userSet.indexOf(u));
                    u.addReview(re);
                    m.addReview(re, u);
                    u.addMovie(m);
         
                }
                else{
                    System.out.println("add" + re.getUserID());


                //userSet.add(u);
                u.addReview(re);
                m.addReview(re, u);
                u.addMovie(m);
                userSet.add(u);
                }
            }
            
               // }
           // }
            z++;
            x = br1.readLine();

           // System.out.println(score);

        }
    
    }
    
    public List<Movie> getMovies() {
        return movieSet;
    }
    
    public List<User> getUsers() {
        return userSet;
    }
    
    public List<Review> getReviews() {
        return reviewSet;
        }
    //}
    
    public List<Movie> getMovieSet(){
        return movieSet;
    }
    public List<User> getUserSet(){
        return userSet;
    }
    
    public List<Review> getReviewSet(){
        return reviewSet;
    }
    
    public static void main(String[]args) throws IOException{
        MovieReader mr = new MovieReader();
        System.out.println("done");
        List<User> x = mr.getUserSet();
        System.out.println(x.get(1).getReviews().size());
        //System.out.println("u1\n" + x.get(0).getReviews());
       // System.out.println("u2\n" + x.get(1).getReviews());        
        //MovieReader mr = new MovieReader();

        //MovieReader x = new MovieReader();
        //System.out.println(x.getMovies().get(7).getId());
        //System.out.println(movieSet.get(1000).getReviewers().size());
        //System.out.println(movieSet.get(1998).getReviewers().size());

        //System.out.println(movieSet.get(700).getId());

        //MovieReader x = new MovieReader();
    }
}