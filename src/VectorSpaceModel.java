
import java.util.HashMap;
import java.util.Set;

/**
 * VectorSpaceModel code from class, except accounts for ReviewDocs instead of
 * Documents
 */
public class VectorSpaceModel {
    
    /**
     * The corpus of ReviewDocs.
     */
    private Corpus corpus;
    private HashMap<ReviewDoc, HashMap<String, Double>> tfIdfWeights;
    
    /**
     * Generates tf-idf vectors for each reviewdoc.
     */
    public VectorSpaceModel(Corpus corpus) {
        this.corpus = corpus;
        tfIdfWeights = new HashMap<ReviewDoc, HashMap<String, Double>>();
        
        createTfIdfWeights();
    }

    /**
     * This creates the tf-idf vectors.
     */
    private void createTfIdfWeights() {
        Set<String> terms = corpus.getInvertedIndex().keySet();
        for (ReviewDoc document : corpus.getReviews()) {
            HashMap<String, Double> weights = new HashMap<String, Double>();
            for (String term : terms) {
                double tf = document.getTermFrequency(term);
                double idf = corpus.getInverseDocumentFrequency(term);
                double weight = tf * idf;
                weights.put(term, weight);
            }
            tfIdfWeights.put(document, weights);
        }
    }
    
    /**
     * Returns the magnitude of a vector
     */
    private double getMagnitude(ReviewDoc document) {
        double magnitude = 0;
        HashMap<String, Double> weights = tfIdfWeights.get(document);
        
        for (double weight : weights.values()) {
            magnitude += weight * weight;
        }
        
        return Math.sqrt(magnitude);
    }
    
    /**
     * Returns the dot product of two ReviewDocs
     */
    private double getDotProduct(ReviewDoc d1, ReviewDoc d2) {
        double product = 0;
        HashMap<String, Double> weights1 = tfIdfWeights.get(d1);
        HashMap<String, Double> weights2 = tfIdfWeights.get(d2);
        
        for (String term : weights1.keySet()) {
            product += weights1.get(term) * weights2.get(term);
        }
        
        return product;
    }
    
    /**
     * Returns the cosine similarity between two ReviewDocs
     */
    public double cosineSimilarity(ReviewDoc d1, ReviewDoc d2) {
        return getDotProduct(d1, d2) / (getMagnitude(d1) * getMagnitude(d2));
    }
}