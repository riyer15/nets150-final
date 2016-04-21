
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
    
    public boolean equals(Review rev1){
        if(!rev1.getUserID().equals(userID))
            return false;
        if(!rev1.getMovieID().equals(movieID))
            return false;
        if(!rev1.getText().equals(text))
            return false;
        if(!rev1.getRating().equals(rating))
            return false;
        if(!rev1.getHelpfulness().equals(helpfulness))
            return false;
        return true;
    }

}
