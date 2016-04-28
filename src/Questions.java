import java.io.IOException;
import java.util.*;

public class Questions {
	
	//Question 1: Sam : If user A and user B both review Movie C, does this 
	//mean they will both review Movie D?
	//
	public void question1() {
		
	}
	
	//Question 2: Sam : Does the same reviewer review movies all nicely or 
	//all harshly (the rating number)?
	public void question2() {
		
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
       //System.out.println(m.getUserSet().size());
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
	    MovieReader m = new MovieReader();
	    System.out.println("GL");
	    question3(m);
	    //System.out.println(m.getMovies().size());
	}

}
