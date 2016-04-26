import java.util.Set;

public class User {
    
    private  Set<Review> reviews;
    private Set<Movie> movies;
    private String userID;
    private String profileName;
    
    public User(String id, Set<Movie> m, Set<Review> r, String pn){
        userID = id;
        movies = m;
        reviews = r;
        profileName = pn;
    }
    
    public User(String id){
        userID = id;
    }
    
    public void addReview(Review r){
        reviews.add(r);
        movies.add(r.getMovie());
    }
    
    public void addMovie(Movie m){
        movies.add(m);
    }
    
    public String getID(){
        return userID;
    }
    
    public Set<Movie> getMovies(){
        return movies;
    }
    
    public Set<Review> getReviews(){
        return reviews;
    }
    @Override
    public boolean equals(Object u){
        return this.userID.equals(((User)u).getID());
    }
}
