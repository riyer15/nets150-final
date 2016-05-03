

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 *Document code from class except caters to ReviewDocs
 */
public class ReviewDoc implements Comparable<ReviewDoc>{
    
    /**
     * Hashmap for term frequencies.
     */
    private HashMap<String, Integer> termFrequency;
    
    /**
     * text of review
     */
    private String reviewname;
    
    /**
     * Preprocesses the text
     */
    public ReviewDoc(String reviewname) {
        this.reviewname = reviewname;
        termFrequency = new HashMap<String, Integer>();
        
        readReviewAndPreProcess();
    }
    
    /**
     * Every word is converted to lower case.
     * Every character that is not a letter or a digit is removed.
     */
    private void readReviewAndPreProcess() {
        Scanner in = new Scanner(new String(reviewname));
        
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
     * returns term frequency for a word
     */
    public double getTermFrequency(String word) {
        if (termFrequency.containsKey(word)) {
            return termFrequency.get(word);
        } else {
            return 0;
        }
    }
    
    /**
     * Returns set of all the terms which occur in this reviewdoc.
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
     * returns text of review
     */
    private String getReviewName() {
        return reviewname;
    }
    
    /**
     * Used for pretty-printing a Document object.
     */
    public String toString() {
        return reviewname;
    }

}