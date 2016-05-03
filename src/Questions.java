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
		
		//makes the comparison
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
		
		//finds the percent
		double percent = ((double) countTotal) / ((double) count);
		System.out.println("Question1: " + percent);
	}
	
	//Question 2: Sam : Does the same reviewer review movies all nicely or 
	//all harshly (the rating number)?
	public void question2() {
		Map<User, Double> stDev = new HashMap<User, Double>();
		
		//finds the standard deviation of each user and puts it in the map
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
		//finds the average
		double count = 0.0;
		for (double d : stDev.values()) {
			count = count + d;
		}
		
		double avgStDev = count / ((double) stDev.keySet().size());
		System.out.println("Question 2: " + avgStDev);
	}
	
	//Question 3: Talia : Do reviewers use similar words in all of their reviews?
	public void question3() throws Exception {
	    double totalAvg = 0.0; //keeps track of total avg
	    double curAvg = 0.0; //keeps track of current user's avg
	    Iterator<User> iter = users.iterator();
	    int ctr = 0; //keeps track of users with > 2 reviews
	    while(iter.hasNext()){
	        User u = iter.next();
	        if(u.getReviews().size()>2){ //if user wrote more than 2 reviews
	            ctr++;
	            curAvg = 0.0;
	            ArrayList<ReviewDoc> all = new ArrayList<ReviewDoc>();
	            Iterator<Review> iter2 = u.getReviews().iterator();
	            while(iter2.hasNext()){
	                Review x = iter2.next();
	                ReviewDoc y = new ReviewDoc(x.getText());
	                all.add(y);
	            }
	            
	           Corpus allRev = new Corpus(all); //makes a corpus of the user's reviews


	            VectorSpaceModel vectorSpace = new VectorSpaceModel(allRev);
	            double total = 0.0;
	            
	            //Computing cosine similarity between user's reviews
	            for (int i = 0; i < all.size(); i++) {
	                for (int j = i + 1; j < all.size(); j++) {
	                    ReviewDoc doc1 = all.get(i);
	                    ReviewDoc doc2 = all.get(j);
	                    double curr = vectorSpace.cosineSimilarity(doc1, doc2);
	                    total += curr;
	                    curAvg += curr;
	                }
	            }
	            curAvg = total/all.size();
	            
	            /*makes sure the cosine similarity is <= 1 (should not be greater
	            than one*/
	            if((total/all.size()) <=  1){
                    curAvg = total/all.size();                    
                }
	            //if it's not <= 1
                else{
                    ctr--;
                    curAvg = 0;
                }
                totalAvg += curAvg;

	        }
	    }
	    System.out.println("Number of users with more than 2 reviews: " + ctr);
	    System.out.println("Question 3: Avg cosine similarity for a user's reviews: "
	            + totalAvg/((double)ctr));

		
	}
	
	//Question 4: Talia : Do all the reviewers reviewing movie A use similar words to describe them?
	public void question4() {
	    double totalAvg = 0.0; //keeps track of total avg
        double curAvg = 0.0; //keeps track of current user's avg
        double total = 0.0;
        Iterator<Movie> iter2 = movies.iterator();
        int ctr = 0; //keeps track of movies with > 2 reviews
        while(iter2.hasNext()){
            Movie u = iter2.next();
            curAvg = 0.0;
            

            if(u.getReviews().size()>2){ //if movie has more than 2 reviews
                ctr++;
                ArrayList<ReviewDoc> all = new ArrayList<ReviewDoc>();
                Iterator<Review> iter = u.getReviews().iterator();
                while(iter.hasNext()){
                    Review x = iter.next();
                    ReviewDoc y = new ReviewDoc(x.getText());
                    if(!all.contains(y)){
                        all.add(y);

                    }
                }
                
               Corpus allRev = new Corpus(all); ////makes a corpus of the movie's reviews


                VectorSpaceModel vectorSpace = new VectorSpaceModel(allRev);
                total = 0.0;
                
                //Computing cosine similarity between movie's reviews
                for (int i = 0; i < all.size(); i++) {
                    for (int j = i + 1; j < all.size(); j++) {
                        ReviewDoc doc1 = all.get(i);
                        ReviewDoc doc2 = all.get(j);
                        
                        double curr = vectorSpace.cosineSimilarity(doc1, doc2);
                        total += curr;
                        curAvg += curr;
                    }
                }
                /*makes sure the cosine similarity is <= 1 (should not be greater
                than one*/                
                if((total/all.size()) <=  1){
                    curAvg = total/all.size();
                }
                //if not <=1
                else{
                    ctr--;
                    curAvg = 0;
                }
                totalAvg += curAvg;

            }
        }
        System.out.println("Number of movies with more than 2 reviews: " + ctr);
        System.out.println("Question 4: Avg cosine similarity for a movie's reviews: " + totalAvg/((double)ctr));
	}
	
	
	public static void main(String[]args) throws Exception{
	    Questions m = new Questions();
	    m.question1();
	    m.question2();
	    m.question3();
	    m.question4();
	}

}
