import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
 * Evaluations function to testify a fictional restaurant   
 *  
 */
public class Evaluation {

	public static EvaluateReturn evaluator(Restaurants restaurant, City city) {
		double retR = 0.0;
		Map<String, double[]> totalAttributes = restaurant.getTotalAttributes();
		Map<String, Boolean> featureMap = city.getFeatureMap();
		int numOfR = restaurant.getCounter();
	    ListOfRestaurants[] listOfR = restaurant.getListOfR();


		int[] matchedResRanking = new int[numOfR];
		
		
		double best10stars = 0.0;
		double overalstars = 0.0;
	

		for (int i = 0; i < numOfR; i++) {
			matchedResRanking[i] = 0;
		}

//		Iterator itor1 = totalAttributes.entrySet().iterator();
//		while (itor1.hasNext()) {
//			Map.Entry entry = (Map.Entry) itor1.next();
//
//			String key = (String) entry.getKey();
//			System.out.println("totalAttributes =  " + key);			
//
//		}

		
		
		
		
		Iterator itor = featureMap.entrySet().iterator();
		
		
		
		while (itor.hasNext()) {
			
			Map.Entry entry = (Map.Entry) itor.next();
			String key = (String) entry.getKey();
			
			
			Boolean value = (Boolean) entry.getValue();

			double[] array = new double[numOfR];

			if(totalAttributes.containsKey(key)){
			array =	totalAttributes.get(key);
  
			for (int i = 0; i < numOfR/2; i++) {
				
				

				
				
				if (array[i] == (value == true ? 1 : 0)) {

					matchedResRanking[i]++;
				}

			}

		}
		}
		
//		int maxIndex = 0;
//		int max = 0;
//		for (int i = 0; i < matchedResRanking.length; i++) {
//		    if (matchedResRanking[i] > max) {
//		        max = matchedResRanking[i];
//		        maxIndex = i;
//		    }
//		}
//		System.out.println(matchedResRanking[maxIndex]);
		
		
		int[] max = new int[10];
			max = 	maxIndex(matchedResRanking,10);

		for (int i = 0; i < 10; i ++)
		{
//			System.out.println("index = " + max[i]+  ", value = " + matchedResRanking[max[i]] + "stars = " + listOfR[max[i]].getStars());
			best10stars += listOfR[max[i]].getStars();			
		}
				for (int i = 0; i < numOfR; i++) {
			
			overalstars = overalstars +  listOfR[i].getStars();
		}
		overalstars = overalstars/(double)numOfR;
		best10stars = (double)best10stars/10.0;
		

		System.out.println("overral stars:" +  overalstars);
		System.out.println("best 10 stars:" +  best10stars);


		EvaluateReturn er = new EvaluateReturn(); 
		er.best10 = best10stars;
		er.overall  = overalstars;
		return er;

	}
	
	static int[] maxIndex(int[] array, int top_k) {
	    double[] max = new double[top_k];
	    int[] maxIndex = new int[top_k];
	    Arrays.fill(max, Double.NEGATIVE_INFINITY);
	    Arrays.fill(maxIndex, -1);

	    top: for(int i = 0; i < array.length; i++) {
	        for(int j = 0; j < top_k; j++) {
	            if(array[i] > max[j]) {
	                for(int x = top_k - 1; x > j; x--) {
	                    maxIndex[x] = maxIndex[x-1]; max[x] = max[x-1];
	                }
	                maxIndex[j] = i; max[j] = array[i];
	                continue top;
	            }
	        }
	    }
	    return maxIndex;
	}
}
