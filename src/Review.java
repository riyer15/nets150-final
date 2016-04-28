
public class Review {
    private User user;
    private Movie movie;
    private String userID;
    private String movieID;
    private String text;
    private String rating;
    private String helpfulness;
    
    public Review(String userID, String movieID, String text, String rating,
            String helpfulness){
        this.userID = userID;
        this.movieID = movieID;
        this.text = text;
        this.rating = rating;
        this.helpfulness = helpfulness;
    }
    
    public String getUserID(){
        return user.getID();
    }
    
    public User getUser(){
        return user;
    }
    
    public String getMovieID(){
        return movie.getId();
    }
    
    public Movie getMovie(){
        return movie;
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
    
    @Override
    public boolean equals(Object rev1){
        if(!((Review) rev1).getUserID().equals(userID))
            return false;
        if(!((Review) rev1).getMovie().equals(movie))
            return false;
        if(!((Review) rev1).getText().equals(text))
            return false;
        if(!((Review) rev1).getRating().equals(rating))
            return false;
        if(!((Review) rev1).getHelpfulness().equals(helpfulness))
            return false;
        return true;
    }

}
