import java.util.Set;

public class Movie {
    
    private String id;
    private Set<Review> reviews;
    private Set<User> reviewers;
    
    public Movie(String id, Set<Review> reviews, Set<User> reviewers) {
        this.id = id;
        this.reviews = reviews;
        this.reviewers = reviewers;
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