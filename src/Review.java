
public class Review {
    String userID;
    String movieID;
    String text;
    String rating;
    String helpfulness;
    
    public Review(String userID, String movieID, String text, String rating,
            String helpfulness){
        this.userID = userID;
        this.movieID = movieID;
        this.text = text;
        this.rating = rating;
        this.helpfulness = helpfulness;
    }
    
    public String getUserID(){
        return userID;
    }
    
    public String getMovieID(){
        return movieID;
    }
    
    public String getText(){
        return text;
    }
    
    public String getRating(){
        return rating;
    }
    
    public String getHelpfulness(){
        return helpfulness;
    }

}
