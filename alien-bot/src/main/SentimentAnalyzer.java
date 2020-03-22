package main;

import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.time.TimeExpression.Annotation;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.TypesafeMap;

public class SentimentAnalyzer {
	
	public SentimentAnalyzer()
	{
		
	}
	
	/*
	 * getSentiment returns -1 if neutral or error
	 * else returns a scale of 0(negative) - 1 (positive)
	 */
	
	public int getSentiment(String input) {
		 
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        int mainSentiment = 0;
        if (input != null && input.length() > 0) {
            int longest = 0;
            edu.stanford.nlp.pipeline.Annotation annotation = pipeline.process(input);
            for (CoreMap sentence : ((TypesafeMap) annotation).get(CoreAnnotations.SentencesAnnotation.class)) {
                Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
                int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
                String partText = sentence.toString();
                if (partText.length() > longest) {
                    mainSentiment = sentiment;
                    longest = partText.length();
                }
 
            }
        }
        if (mainSentiment == 2 || mainSentiment > 4 || mainSentiment < 0) {
            return -1;
        }
        return mainSentiment;
 
    }

}
