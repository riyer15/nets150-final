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
						//System.out.println(users.get(j).getMovies());
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
	    double totalAvg = 0.0;
	    double curAvg = 0.0;
	    List<User> userSet = m.getUserSet();
	    Iterator<User> iter2 = userSet.iterator();
	    int ctr = 0;
	    while(iter2.hasNext()){
	        User u = iter2.next();
	        if(u.getReviews().size()>2){
	            ctr++;
	            curAvg = 0.0;
	            double curSize = u.getReviews().size();
	            ArrayList<ReviewDoc> all = new ArrayList<ReviewDoc>();
	            Iterator<Review> iter = u.getReviews().iterator();
	            while(iter.hasNext()){
	                Review x = iter.next();
	                ReviewDoc y = new ReviewDoc(x.getText());
	                all.add(y);
	            }
	            
	           Corpus allRev = new Corpus(all);


	            VectorSpaceModel vectorSpace = new VectorSpaceModel(allRev);
	            double total = 0.0;
	            
	            //all plays considered
	            System.out.println("\nAll plays considered:");
	            for (int i = 0; i < all.size(); i++) {
	                for (int j = i + 1; j < all.size(); j++) {
	                    ReviewDoc doc1 = all.get(i);
	                    ReviewDoc doc2 = all.get(j);
	                    
	                    System.out.println("\nComparing " + doc1 + " and " +  doc2);
	                    double curr = vectorSpace.cosineSimilarity(doc1, doc2);
	                    System.out.println(curr);
	                    total += curr;
	                    curAvg += curr;
	                }
	            }
	            curAvg = total/all.size();
	            totalAvg += curAvg;

	        }
	    }
	    System.out.println(ctr);
	    System.out.println(totalAvg/((double)ctr));

		
	}
	
	//Question 4: Talia : Do all the reviewers reviewing movie A use similar words to describe them?
	public void question4() {
	    double totalAvg = 0.0;
        double curAvg = 0.0;
        
        Iterator<Movie> iter2 = movies.iterator();
        int ctr = 0;
        while(iter2.hasNext()){
            Movie u = iter2.next();
            if(u.getReviews().size()>2){
                ctr++;
                curAvg = 0.0;
                double curSize = u.getReviews().size();
                ArrayList<ReviewDoc> all = new ArrayList<ReviewDoc>();
                Iterator<Review> iter = u.getReviews().iterator();
                while(iter.hasNext()){
                    Review x = iter.next();
                    ReviewDoc y = new ReviewDoc(x.getText());
                    all.add(y);
                }
                
               Corpus allRev = new Corpus(all);


                VectorSpaceModel vectorSpace = new VectorSpaceModel(allRev);
                double total = 0.0;
                
                //all plays considered
                System.out.println("\nAll plays considered:");
                for (int i = 0; i < all.size(); i++) {
                    for (int j = i + 1; j < all.size(); j++) {
                        ReviewDoc doc1 = all.get(i);
                        ReviewDoc doc2 = all.get(j);
                        
                        System.out.println("\nComparing " + doc1 + " and " +  doc2);
                        double curr = vectorSpace.cosineSimilarity(doc1, doc2);
                        System.out.println(curr);
                        total += curr;
                        curAvg += curr;
                    }
                }
                curAvg = total/all.size();
                totalAvg += curAvg;

            }
        }
        System.out.println(ctr);
        System.out.println(totalAvg/((double)ctr));
		
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
	
	public static void main(String[]args) throws IOException{
	    Questions m = new Questions();
	    System.out.println("GL");

	    //System.out.println(m.getMovies().size());
	    m.question4();
	}

}
