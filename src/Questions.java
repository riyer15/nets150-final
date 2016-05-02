import java.io.IOException;
import java.util.List;
import java.util.*;

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
	//
	public void question1() {
		int countTotal = 0;
		int count = 0;
		for (int i = 0; i < users.size(); i++) {
			for (int j = i; j < users.size(); j++) {
				for (Movie m1 : users.get(i).getMovies()) {
					for (Movie m2 : users.get(j).getMovies()) {
						if (m1 != null && m2 != null && m1.equals(m2)) {
							countTotal++;
						}
						if (m1 != null && m2 != null) {
							count++;
						}
					}
				}
			}
		}
		
		double percent = ((double) countTotal) / ((double) count);
		System.out.println(percent);
	}
	
	//Question 2: Sam : Does the same reviewer review movies all nicely or 
	//all harshly (the rating number)?
	public void question2() {
		Map<User, Double> stDev = new HashMap<User, Double>();
		for (User u : users) {
			if (u.getReviews().size() > 1) {
				double avg = 0.0;
				for (Review r : u.getReviews()) {
					avg = avg + Double.parseDouble(r.getRating());
				}
				avg = (avg / u.getReviews().size());
				
				double newSum = 0.0;
				for (Review r2 : u.getReviews()) {
					newSum = newSum + Math.pow((Double.parseDouble(r2.getRating()) - avg), 2);
				}
				newSum = (newSum / u.getReviews().size());
				
				stDev.put(u, Math.sqrt(newSum));
			}
		}
		double count = 0.0;
		for (double d : stDev.values()) {
			count = count + d;
		}
		
		double avgStDev = count / ((double) stDev.keySet().size());
		System.out.println(avgStDev);
	}
	
	//Question 3: Talia : Do reviewers use similar words in all of their reviews?
	public static void question3(MovieReader m) {
	    Set<Review> revSet = m.getUserSet().get(1).getReviews();
	   
        ArrayList<ReviewDoc> all = new ArrayList<ReviewDoc>();
        Iterator<Review> iter = revSet.iterator();
        while(iter.hasNext()){
            Review x = iter.next();
            System.out.println(x.getText());
            ReviewDoc y = new ReviewDoc(x.getText());
            all.add(y);
        }
        
       Corpus allRev = new Corpus(all);
       //System.out.println(revSet.size());
       System.out.println("number of users" + m.getUserSet().size());
       //System.out.println(m.getMovieSet().size());

        VectorSpaceModel vectorSpace = new VectorSpaceModel(allRev);
        double total = 0.0;
        
        //all plays considered
        System.out.println("\nAll plays considered:");
        for (int i = 0; i < all.size(); i++) {
            for (int j = i + 1; j < all.size(); j++) {
                ReviewDoc doc1 = all.get(i);
                ReviewDoc doc2 = all.get(j);
                
                    System.out.println(doc1.getTermList().size());
                    System.out.println(doc2.getTermList().size());
                    
                
                System.out.println("\nComparing " + doc1 + " and " +  doc2);
                double curr = vectorSpace.cosineSimilarity(doc1, doc2);
                System.out.println(curr);
                total += curr;
            }
        }
        
		
	}
	
	//Question 4: Talia : Do all the reviewers reviewing movie A use similar words to describe them?
	public void question4() {
		
	}
	
	
	public static void main(String[]args) throws IOException{
	    Questions m = new Questions();
	    System.out.println("GL");
	    m.question1();
	    //System.out.println(m.getMovies().size());
	}

}
