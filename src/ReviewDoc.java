

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * This class represents one document.
 * It will keep track of the term frequencies.
 *
 */
public class ReviewDoc implements Comparable<ReviewDoc>{
    
    /**
     * A hashmap for term frequencies.
     * Maps a term to the number of times this terms appears in this document. 
     */
    private HashMap<String, Integer> termFrequency;
    
    /**
     * The name of the file to read.
     */
    private String reviewname;
    
    /**
     * The constructor.
     * It takes in the name of a file to read.
     * It will read the file and pre-process it.
     * @param filename the name of the file
     */
    public ReviewDoc(String reviewname) {
        this.reviewname = reviewname;
        termFrequency = new HashMap<String, Integer>();
        
        readReviewAndPreProcess();
    }
    
    /**
     * This method will read in the file and do some pre-processing.
     * The following things are done in pre-processing:
     * Every word is converted to lower case.
     * Every character that is not a letter or a digit is removed.
     * We don't do any stemming.
     * Once the pre-processing is done, we create and update the 
     */
    private void readReviewAndPreProcess() {
        Scanner in = new Scanner(new String(reviewname));
        //System.out.println("Reading file: " + reviewname + " and preprocessing");
        
        while (in.hasNext()) {
            String nextWord = in.next();
            
            String filteredWord = nextWord.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
            
            if (!(filteredWord.equalsIgnoreCase(""))) {
                if (termFrequency.containsKey(filteredWord)) {
                    int oldCount = termFrequency.get(filteredWord);
                    termFrequency.put(filteredWord, ++oldCount);
                } else {
                    termFrequency.put(filteredWord, 1);
                }
            }
        }

    }
    
    /**
     * This method will return the term frequency for a given word.
     * If this document doesn't contain the word, it will return 0
     * @param word The word to look for
     * @return the term frequency for this word in this document
     */
    public double getTermFrequency(String word) {
        if (termFrequency.containsKey(word)) {
            return termFrequency.get(word);
        } else {
            return 0;
        }
    }
    
    /**
     * This method will return a set of all the terms which occur in this document.
     * @return a set of all terms in this document
     */
    public Set<String> getTermList() {
        return termFrequency.keySet();
    }

    
    /**
     * The overriden method from the Comparable interface.
     */
    public int compareTo(ReviewDoc other) {
        return reviewname.compareTo(other.getReviewName());
    }

    /**
     * @return the filename
     */
    private String getReviewName() {
        return reviewname;
    }
    
    /**
     * This method is used for pretty-printing a Document object.
     * @return the filename
     */
    public String toString() {
        return reviewname;
    }
    

    public static void main(String[]args) throws IOException{
        MovieReader y = new MovieReader();
        Set<Review> t = y.getUserSet().get(2).getReviews();
        //System.out.println(t.size());

        Iterator<Review> iter = t.iterator();
        while(iter.hasNext()){
            Review r = iter.next();
            //System.out.println(r.getUser().getID());
        }
        //System.out.println(t.size());
        //ReviewDoc x = new ReviewDoc(t.);
        //System.out.println(x.getTermList().size());
    }
}