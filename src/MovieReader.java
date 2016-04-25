import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class MovieReader {
	/*For later parts in the code, we need to access previously made movie/
	 * user/review objects. We can't just add our "dummy" objects because 
	 * then we lose all the data we're storing (specifically in their adjacency lists)
	 *  Sets don't allow you to find a specific object without using an iterator, (look it up)
	 * so I changed their representation to Lists. Though the List doesn't guarantee 
	 * uniqueness, we check for pre-existence in our code when we 
	 * create our objects, so the list implementation is basically unique anyway. 
	 * - Rani 
	 */
    List<Movie> movieSet; 
   
    List<User> userSet;
    List<Review> reviewSet;
    
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
        Set<Review> rev = new HashSet<Review>();
        Set<User> user = new HashSet<User>();
        Set<Movie> mov = new HashSet<Movie>();


        //while(x != null){
          //  System.out.println(x);
        int z = 0;
        while(x != null && z < 2000){
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
            User u = new User(userID, mov, rev, profileName);
            Movie m = new Movie(productID, rev, user);
            
            /*
             * For this part: keep in mind that once we add an object to
             * another object's adjacency lists, any changes we made to the first 
             * object *will* be updated in the other object's list, because we're passing
             * by reference. For example, if I add a Movie A to a User B, and then add
             * Review C to Movie A, Movie A in User B's list will have has Review C. 
             * That's why fact that some of the objects are incomplete when we add them
             * to the list isn't that big of a deal -- updates to one object are reflected
             * in all their associated objects. Text me if you're still confused (or maybe
             * look up the 120 Notes?) - Rani 
             */
            if(movieSet.contains(m)){
            	System.out.println(m);
                m = movieSet.get(movieSet.indexOf(m));
            	if(userSet.contains(u)){
            		u = userSet.get(userSet.indexOf(u));
            		m.addReview(re, u);
            		u.addReview(re);
                }
                else{
                    userSet.add(u);
                    u.addReview(re);
                    m.addReview(re, u);                    
                }
                
            }
            else{
                movieSet.add(m);
                if(userSet.contains(u)){
                	u = userSet.get(userSet.indexOf(u));
                	u.addReview(re);
                	m.addReview(re, u);
                }
                else{
                userSet.add(u);
                u.addReview(re);
                m.addReview(re, u);
                }
            }
            
               // }
           // }
            z++;
            x = br1.readLine();

           // System.out.println(score);
        }}
    //}
    
    public List<Movie> getMovieSet(){
        return movieSet;
    }
    public List<User> getUserSet(){
        return userSet;
    }
    
    public static void main(String[]args) throws IOException{
        MovieReader mr = new MovieReader();
        //MovieReader mr = new MovieReader();
        //MovieReader x = new MovieReader();
    }
}