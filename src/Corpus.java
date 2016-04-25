

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class represents a corpus of documents.
 * It will create an inverted index for these documents.
 * @author swapneel
 *
 */
public class Corpus {
    
    /**
     * An arraylist of all documents in the corpus.
     */
    private ArrayList<ReviewDoc> reviews;
    
    /**
     * The inverted index. 
     * It will map a term to a set of documents that contain that term.
     */
    private HashMap<String, Set<ReviewDoc>> invertedIndex;
    
    /**
     * The constructor - it takes in an arraylist of documents.
     * It will generate the inverted index based on the documents.
     * @param documents the list of documents
     */
    public Corpus(ArrayList<ReviewDoc> reviews) {
        this.reviews = reviews;
        invertedIndex = new HashMap<String, Set<ReviewDoc>>();
        
        createInvertedIndex();
    }
    
    /**
     * This method will create an inverted index.
     */
    private void createInvertedIndex() {
        System.out.println("Creating the inverted index");
        
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
     * This method returns the idf for a given term.
     * @param term a term in a document
     * @return the idf for the term
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
     * @return the documents
     */
    public ArrayList<ReviewDoc> getReviews() {
        return reviews;
    }

    /**
     * @return the invertedIndex
     */
    public HashMap<String, Set<ReviewDoc>> getInvertedIndex() {
        return invertedIndex;
    }
}