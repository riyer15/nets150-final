

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class represents a corpus of ReviewDocs
 * Taken from code from class but uses ReviewDocs instead of Documents
 *
 */
public class Corpus {
    
    /**
     * An arraylist of all documents in the corpus.
     */
    private ArrayList<ReviewDoc> reviews;
    
    /**
     * The inverted index. 
     */
    private HashMap<String, Set<ReviewDoc>> invertedIndex;
    
    /**
     * Generates inverted index
     */
    public Corpus(ArrayList<ReviewDoc> reviews) {
        this.reviews = reviews;
        invertedIndex = new HashMap<String, Set<ReviewDoc>>();
        
        createInvertedIndex();
    }
    
    /**
     * Creates an inverted index.
     */
    private void createInvertedIndex() {
        
        for (ReviewDoc rev : reviews) {
            Set<String> terms = rev.getTermList();
            
            for (String term : terms) {
                if (invertedIndex.containsKey(term)) {
                    Set<ReviewDoc> list = invertedIndex.get(term);
                    list.add(rev);
                } else {
                    Set<ReviewDoc> list = new TreeSet<ReviewDoc>();
                    list.add(rev);
                    invertedIndex.put(term, list);
                }
            }
        }
    }
    
    /**
     * Returns the idf for a given term.
     */
    public double getInverseDocumentFrequency(String term) {
        if (invertedIndex.containsKey(term)) {
            double size = reviews.size();
            Set<ReviewDoc> list = invertedIndex.get(term);
            double documentFrequency = list.size();
            
            return Math.log10(size / documentFrequency);
        } else {
            return 0;
        }
    }

    /**
     * Returns the reviewdocs
     */
    public ArrayList<ReviewDoc> getReviews() {
        return reviews;
    }

    /**
     * Returns the invertedIndex
     */
    public HashMap<String, Set<ReviewDoc>> getInvertedIndex() {
        return invertedIndex;
    }
}