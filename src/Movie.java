import java.util.*;

public class Movie {
    
    private String id;
    private Set<Review> reviews;
    private Set<User> reviewers;
    
    public Movie(String id) {
        this.id = id;
        this.reviews = new HashSet<Review>();
        this.reviewers = new HashSet<User>();
    }
    
    public String getId() {
        return id;
    }
    
    public Set<Review> getReviews() {
        return reviews;
    }


	public Set<User> getReviewers() {
		return reviewers;
	}
	
	public void addReview(Review r, User u) {
		reviews.add(r);
		reviewers.add(u);
	}
	
	@Override
	public boolean equals(Object m) {
		return this.id.equals(((Movie)m).getId());
	}
    
}
