import java.util.*;


public class City {
	public Map<String,PearsonType> cityMap;
	public Map<String,Boolean> featureMap = new HashMap<String,Boolean>();// Output result
	public Map<String, Boolean> getFeatureMap() {
		return featureMap;
	}
	public ValueComparator bvc;
	public TreeMap<String, PearsonType> sorted_cityMap;
    public City(Map<String,PearsonType> cityMap1)
    {
    	cityMap = cityMap1;
    	bvc= new ValueComparator(cityMap);
        sorted_cityMap = new TreeMap<String, PearsonType>(
                bvc);

	  
    }
    public Map<String,Boolean> finalFeature()
    {
       sorted_cityMap.clear();
       sorted_cityMap.putAll(cityMap);
       int featureNum = cityMap.size();
       System.out.println(featureNum);
       Iterator it = this.sorted_cityMap.entrySet().iterator();
       int posNum = 0;
       int negNum = 0;
	 while (it.hasNext()) 
	 {
		   Map.Entry entry =(Map.Entry) it.next();
		   String key = (String) entry.getKey();
		   PearsonType value=(PearsonType) entry.getValue();
		   if(value.pearsonValue>0)
			   posNum++;
		   if(value.pearsonValue<0)
			   negNum++;
	  }
     Iterator it1 = this.sorted_cityMap.entrySet().iterator();
     int num =0;
     int pos = posNum/5;
     int neg = negNum/5;
     System.out.println(pos);
     System.out.println(neg);
	 while (it1.hasNext()) 
	 {
		   Map.Entry entry =(Map.Entry) it1.next();
		   String key = (String) entry.getKey();
		   PearsonType value=(PearsonType) entry.getValue();
		   if(num<pos)
		   {
			   this.featureMap.put(key, true);
		   }
		   if(num>featureNum-neg)
		   {
			   this.featureMap.put(key, false);
		   }
		   num++;
	  }
      return this.featureMap;
    }
	public String title;
}
