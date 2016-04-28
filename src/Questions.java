import java.io.IOException;
import java.util.List;

public class Questions {
	
	private List<User> users;
	private List<Review> reviews;
	private List<Movie> movies;
	
	public Questions() throws IOException {
		MovieReader mr = new MovieReader();
		this.users = mr.getUsers();
		this.reviews = mr.getReviews();
		this.movies = mr.getMovies();
	}
	
	//Question 1: Sam : If user A and user B both review Movie C, does this 
	//mean they will both review Movie D?
	public void question1() {
		
	}
	
	//Question 2: Sam : Does the same reviewer review movies all nicely or 
	//all harshly (the rating number)?
	public void question2() {
		double overall = 0.0;
		for (User u : users) {
			double avg = 0.0;
			for (Review r : u.getReviews()) {
				avg = avg + Double.parseDouble(r.getRating());
			}
			overall = overall + (avg / u.getReviews().size());
		}
		
	}
	
	//Question 3: Talia : Do reviewers use similar words in all of their reviews?
	public void question3() {
		
	}
	
	//Question 4: Talia : Do all the reviewers reviewing movie A use similar words to describe them?
	public void question4() {
		
	}
	
	//Question 5: Rani : Does the helpfulness of the review correspond to the 
	//style (negativity/positivity) of which the review is written?
	public void question5() {
		
	}
	
	//Question 6: Rani : Choose a movie: what are trends we notice about the 
	//words used in describing the movie? Are these trends picked up from other 
	//reviews of the same movie, or from all the movie reviews in general?
	public void quesion6() {
		
	}

}
