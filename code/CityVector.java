import java.util.*;
import java.util.Map.Entry;
import java.math.*;

public class CityVector {
	public Vector<City> cityVector = new Vector<City>();
//	public Vector<City> cityNormal = new Vector<City>();
	public Vector<CityRecommend> recommendMap = new Vector<CityRecommend>();

	int size;
	// tuneable variable
	Double validPos = 0.5;
	Double validNeg = -0.5;
	Double recPos = 0.1;
	Double recNeg = -0.1;
	Double[][] simMatrix;

	public CityVector(Vector<City> cityV) {
		this.cityVector = cityV;
		this.size = this.cityVector.size();
		simMatrix = new Double[size][size]; 
	}

//	private void normalize() {
//		for (int i = 0; i < cityVector.size(); i++) {
//			City curCity = new City();
//			Map<String, Double> cityMap = cityVector.get(i).cityMap;
//			for (Entry<String, Double> cityEntry : cityMap.entrySet()) {
//				String key = cityEntry.getKey();
//				Double value = cityEntry.getValue();
//				if (value > this.validPos || value <= this.validNeg) {
//					curCity.cityMap.put(key, value);
//				} else {
//					curCity.cityMap.put(key, 0.0);
//				}
//			}
//		}
//	}

	public void recommendCity()
	{
		this.size = this.cityVector.size();
		
		for(int i=0;i<size;i++)
		{
			simMatrix[i] = new Double[size];
		}
		
		for(int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				if(i!=j)
				{
					simMatrix[i][j] = similarity.pearsonSimilar(this.cityVector.get(i), this.cityVector.get(j));
				}
			}
		}
		for(int i=0;i<size;i++)
		{
			City curCity = this.cityVector.get(i);
			CityRecommend curR = new CityRecommend();
			for(String str : ListOfRestaurants.totalCate)
			{
				double curV = similarity.checkValue(curCity.featureMap,str);
				if(curV==0.0)
				{	
					for(int j=0;j<size;j++)
					{
						if(i!=j)
						{
							curV += similarity.checkValue(this.cityVector.get(j).featureMap,str)*this.simMatrix[i][j];
						}
					}
					if(curV>recPos)
					{
						//System.out.println(curV);
						curR.posMap.put(str, curV);
						curCity.featureMap.put(str, true);
					}
					if(curV<recNeg)
					{
						System.out.println(curV);
						//curR.negMap.put(str, curV);
						curCity.featureMap.put(str, false);
					}
				}
			}
			this.recommendMap.add(curR);
		}
	}
}

class CityRecommend {
	// all feature's recommendation value;
	public Map<String, Double> negMap = new HashMap<String, Double>();
	// recommendation feature's List
	public Map<String, Double> posMap = new HashMap<String, Double>();
}

class similarity {
	static double checkValue(Map<String,Boolean> curMap1, String str)
	{
		double cur1 = 0.0;
		if(curMap1.containsKey(str))
		{
			if(curMap1.get(str))
			{
				cur1 = 1.0;
			}
			else
			{
				cur1 = -1.0;
			}
		}
		else
		{
			cur1 = 0.0;
		}
		return cur1;
	}
	static double pearsonSimilar(City city1, City city2) {
		double similar;
		double x1 = 0.0;
		double sumX = 0.0;
		double sumY = 0.0;
		double sumX2 = 0.0;
		double sumY2 = 0.0;
		double sumXY = 0.0;
		double featureNum = 281;
		Map<String,Boolean> curSet = new HashMap<String,Boolean>();
		curSet.putAll(city1.featureMap);
		curSet.putAll(city2.featureMap);		
		for (String str: curSet.keySet()) 
		{
			double cur1,cur2;
			cur1 = -2.0;
			cur2 = -2.0;
			cur1 = checkValue(city1.featureMap,str);
			cur2 = checkValue(city2.featureMap,str);
			
//			String cityKey = cityEntry.getKey();
//			Double cur1 = cityEntry.getValue();
//			Double cur2 = city2.cityMap.get(cityKey);
			if (cur1 != -2.0 && cur2 != -2.0) {
				sumXY += cur1 * cur2;
				sumX += cur1;
				sumY += cur2;
				sumX2 += Math.pow(cur1, 2);
				sumY2 += Math.pow(cur2, 2);
			}
		}
		// p=(Σxy-Σx*Σy/n)/Math.sqrt((Σx2-(Σx)2/n)(Σy2-(Σy)2/n));
		double sN = sumXY - sumX * sumY / featureNum;
		double sD = Math.sqrt((sumX2 - Math.pow(sumX, 2) / featureNum)
				* (sumY2 - Math.pow(sumY, 2) / featureNum));
		similar = sN / sD;
		return similar;
	}
//	static double disSimilar(City city1, City city2) {
//		double distance = 0.0;
//		for (Entry<String, Double> cityEntry : city1.cityMap.entrySet()) {
//			String key = cityEntry.getKey();
//			Double value1 = city1.cityMap.get(key);
//			Double value2 = city2.cityMap.get(key);
//			distance += Math.pow(value1 - value2, 2.0);
//		}
//		return distance;
//	}
}
