	import org.json.*;

	import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
	 
	public class Restaurants {
		
	    public List<JSONObject> resList = new LinkedList<JSONObject>();
	    
	    public Map<String, Integer> cityNum = new HashMap<String, Integer>();
	 
	    public Map<String, PearsonType> pearsonAttri = new HashMap<String, PearsonType>();
	 
	    public ValueComparator bvc = new ValueComparator(pearsonAttri);
	    public TreeMap<String, PearsonType> sorted_pearsonAttri = new TreeMap<String, PearsonType>(
	            bvc);
	    
	    public int counter = 0;
	    public String cityName;
	    public int getCounter() {
			return counter;
		}
	    public Restaurants(String name)
	    {
	    	this.cityName = name;
	    }

		public Set<String> ParkingSet = new HashSet<String>();
		public Set<String> cateSet = new HashSet<String>();
	    
	    public ListOfRestaurants[] listOfR = new ListOfRestaurants[5000];
	    
        public ListOfRestaurants[] getListOfR() {
			return listOfR;
		}

		Map<String,double[]> totalAttributes = new HashMap<String,double[]>(); // TODO map totalAttributes 

	    
	    public Map<String, double[]> getTotalAttributes() {
			return totalAttributes;
		}

		public void add(JSONObject res) {
	        resList.add(res);
	        counter++;
	    }
	 
	    public City getAttributes() {
	        int n = resList.size();
	        this.ParkingSet.add("lot");
	        this.ParkingSet.add("validated");
	        this.ParkingSet.add("street");
	        this.ParkingSet.add("garage");
	        this.ParkingSet.add("valet");
	        double[] starsArray = new double[n];
	        double[] review_countArray = new double[n];
	        double[] priceArray = new double[n];
	        double[] wifiArray = new double[n];
	 
	        double[] catersArray = new double[n];
	 
	        double[] deliveryArray = new double[n];
	        double[] goodForKidsArray = new double[n];
	        double[] reservationsArray = new double[n];
	        double[] wheelchairArray = new double[n];
	        double[] dancingArray = new double[n];
	        double[] coatCheckArray = new double[n];
	        double[] smokingArray = new double[n];
	        double[] creditCardsArray = new double[n];
	        double[] takeoutArray = new double[n];
	        double[] happyhourArray = new double[n];
	        double[] outdoorSeatingArray = new double[n];
	        double[] waiterServiceArray = new double[n];
	        double[] dessertArray = new double[n];
	        double[] latenightArray = new double[n];
	        double[] lunchArray = new double[n];
	        double[] dinnerArray = new double[n];
	        double[] brunchArray = new double[n];
	        double[] breakfastArray = new double[n];
	        double[] tvArray = new double[n];
	        double[] goodForGroupsArray = new double[n];
	        double[] romanticArray = new double[n];
	        double[] intimateArray = new double[n];
	        double[] touristyArray = new double[n];
	        double[] hipsterArray = new double[n];
	        double[] diveyArray = new double[n];
	        double[] classyArray = new double[n];
	        double[] trendyArray = new double[n];
	        double[] upscaleArray = new double[n];
	        double[] casualArray = new double[n];
	        
	        double[] noiseArray = new double[n];  
	        Map<String,double[]> cate = new HashMap<String,double[]>(); 
	        Map<String,double[]> parking = new HashMap<String,double[]>();
	        

	        
	 
	        for (int i = 0; i < resList.size(); i++) {
	            listOfR[i] = new ListOfRestaurants();
	            listOfR[i].setId(i);
	 
	            JSONObject cur = resList.get(i);
	            listOfR[i]
	                    .setStars(Double.parseDouble(cur.get("stars").toString()));
	 
	            listOfR[i].setReview_count((Integer.parseInt(cur
	                    .get("review_count").toString())));
	 
	            String attributes = cur.get("attributes").toString();
	                 
	  
	            JSONArray category = cur.getJSONArray("categories");
				for(int k=0;k<category.length();k++)
				{
					String cur1 =category.get(k).toString();
					if(cur1!="Restaurants")
					{
						cateSet.add(cur1);
						ListOfRestaurants.totalCate.add(cur1);
						listOfR[i].Categories.add(cur1);
					}
					
				}
	            JSONObject attrObject = cur.getJSONObject("attributes"); 
	            
	            if(attrObject.has("Noise Level"))
	            {
	            	String noise = attrObject.getString("Noise Level");
	            	int noiseLevel1 = 0;
	            	switch (noise) {
		                case "quiet":  noiseLevel1 = 1;
	                    	break;
		                case "average": noiseLevel1 = 2;
	                    	break;
		                case "loud":  noiseLevel1 = 3;
	                    	break;
		                case "very_loud": noiseLevel1 = 4;
	            	}
	            	listOfR[i].noiseLevel = noiseLevel1;
	            }
	            
	            if(attrObject.has("Parking"))
	            {
	            	JSONObject parking1 = attrObject.getJSONObject("Parking");	         
	            	for(String key:parking1.keySet())
	            	{
	            		Boolean value1 = (Boolean) parking1.get(key);
	            		if(value1)
	            		{
	            			listOfR[i].Parking.put(key, 1);
	            		}
	            		else
	            		{
	            			listOfR[i].Parking.put(key, -1);
	            		}
	            	}	
	            }
		        else
	        	{
	        		for(String key:this.ParkingSet)
	            	{
	            		listOfR[i].Parking.put(key,0);
	            	}
	        	}
	           
	            if (attributes.indexOf("Price Range") != -1) {
	 
	                String price = attributes.substring(
	                        attributes.indexOf("Price Range") + 13,
	                        attributes.indexOf("Price Range") + 14);
	                listOfR[i].setPrice(Integer.parseInt(price));
	 
	            }
	 
	            listOfR[i].setWifi(checkAttribute("Wi-Fi", attributes, "free"));
	            listOfR[i].setCaters(checkAttribute("Caters", attributes, "true"));
	            listOfR[i].setDelivery(checkAttribute("Delivery", attributes,
	                    "true"));
	            listOfR[i].setGoodForKids(checkAttribute("Good for Kids",
	                    attributes, "true"));
	            listOfR[i].setReservations(checkAttribute("Takes Reservations",
	                    attributes, "true"));
	            listOfR[i].setWheelchair(checkAttribute("Wheelchair Accessible",
	                    attributes, "true"));
	            listOfR[i].setDancing(checkAttribute("Good For Dancing",
	                    attributes, "true"));
	            listOfR[i].setCoatCheck(checkAttribute("Coat Check", attributes,
	                    "true"));
	            listOfR[i].setSmoking(checkAttribute("Smoking", attributes, "out"));
	 
	            listOfR[i].setCreditCards(checkAttribute("Accepts Credit Cards",
	                    attributes, "true"));
	            listOfR[i]
	                    .setTakeout(checkAttribute("Take-out", attributes, "true"));
	            listOfR[i].setHappyhour(checkAttribute("Happy Hour", attributes,
	                    "true"));
	            listOfR[i].setOutdoorSeating(checkAttribute("Outdoor Seating",
	                    attributes, "true"));
	            listOfR[i].setWaiterService(checkAttribute("Waiter Service",
	                    attributes, "true"));
	            listOfR[i]
	                    .setDessert(checkAttribute("dessert", attributes, "true"));
	            listOfR[i].setLatenight(checkAttribute("latenight", attributes,
	                    "true"));
	            listOfR[i].setLunch(checkAttribute("lunch", attributes, "true"));
	            listOfR[i].setDinner(checkAttribute("dinner", attributes, "true"));
	            listOfR[i].setBrunch(checkAttribute("brunch", attributes, "true"));
	            listOfR[i].setBreakfast(checkAttribute("breakfast", attributes,
	                    "true"));
	            listOfR[i].setTv(checkAttribute("Has TV", attributes, "true"));
	            listOfR[i].setGoodForGroups(checkAttribute("Good For Groups",
	                    attributes, "true"));
	 
	            listOfR[i].setRomantic(checkAttribute("romantic", attributes,
	                    "true"));
	            listOfR[i].setIntimate(checkAttribute("intimate", attributes,
	                    "true"));
	            listOfR[i].setTouristy(checkAttribute("touristy", attributes,
	                    "true"));
	            listOfR[i]
	                    .setHipster(checkAttribute("hipster", attributes, "true"));
	            listOfR[i].setDivey(checkAttribute("divey", attributes, "true"));
	            listOfR[i].setClassy(checkAttribute("classy", attributes, "true"));
	            listOfR[i].setTrendy(checkAttribute("trendy", attributes, "true"));
	            listOfR[i]
	                    .setUpscale(checkAttribute("upscale", attributes, "true"));
	            listOfR[i].setCasual(checkAttribute("casual", attributes, "true"));

	            starsArray[i] = listOfR[i].getStars();
	            review_countArray[i] = listOfR[i].getReview_count();
	            noiseArray[i] = listOfR[i].noiseLevel;
	 
	            wifiArray[i] = listOfR[i].getWifi();
	            catersArray[i] = listOfR[i].getCaters();
	            deliveryArray[i] = listOfR[i].getDelivery();
	            goodForKidsArray[i] = listOfR[i].getGoodForKids();
	            reservationsArray[i] = listOfR[i].getReservations();
	            wheelchairArray[i] = listOfR[i].getWheelchair();
	            dancingArray[i] = listOfR[i].getDancing();
	            coatCheckArray[i] = listOfR[i].getCoatCheck();
	            smokingArray[i] = listOfR[i].getSmoking();
	            creditCardsArray[i] = listOfR[i].getCreditCards();
	            takeoutArray[i] = listOfR[i].getTakeout();
	            happyhourArray[i] = listOfR[i].getHappyhour();
	            outdoorSeatingArray[i] = listOfR[i].getOutdoorSeating();
	            waiterServiceArray[i] = listOfR[i].getWaiterService();
	            dessertArray[i] = listOfR[i].getDessert();
	            latenightArray[i] = listOfR[i].getLatenight();
	            lunchArray[i] = listOfR[i].getLunch();
	            dinnerArray[i] = listOfR[i].getDinner();
	            brunchArray[i] = listOfR[i].getBrunch();
	            breakfastArray[i] = listOfR[i].getBreakfast();
	            tvArray[i] = listOfR[i].getTv();
	            goodForGroupsArray[i] = listOfR[i].getGoodForGroups();
	            romanticArray[i] = listOfR[i].getRomantic();
	            intimateArray[i] = listOfR[i].getIntimate();
	            touristyArray[i] = listOfR[i].getTouristy();
	            hipsterArray[i] = listOfR[i].getHipster();
	            diveyArray[i] = listOfR[i].getDivey();
	            classyArray[i] = listOfR[i].getClassy();
	            trendyArray[i] = listOfR[i].getTrendy();
	            upscaleArray[i] = listOfR[i].getUpscale();
	            casualArray[i] = listOfR[i].getCasual();	 
	        }
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	        
	        for(String cateString: cateSet)
	        {
	        	cate.put(cateString, new double[n]);
	        }
	        for (int i = 0; i < resList.size(); i++) 
	        {
		        for(String cateString: cateSet)
		        {
		        	Boolean hasCate = this.listOfR[i].Categories.contains(cateString);
	        		double[] curArray = cate.get(cateString);
		        	if(hasCate)
		        	{
		        		curArray[i] = 1.0;
		        	}
		        	else
		        	{
		        		curArray[i] = 0.0;
		        	}
		        }
	        }
	        
	        for(String parkingString: this.ParkingSet)
	        {
	        	parking.put(parkingString, new double[n]);
	        }
	        for (int i = 0; i < resList.size(); i++) 
	        {
		        for(String parkingString: this.ParkingSet)
		        {
		        	Integer parkingNum = this.listOfR[i].Parking.get(parkingString);
		            double[] curArray = parking.get(parkingString);
		            if(parkingNum!=null)
		            {
		            	curArray[i] = (double)parkingNum;
		            }
		            else
		            {
		            	curArray[i] = 0;
		            }
		        }
	        }
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	    
	        for(String cateString: cateSet)
	        {
	        	 pearsonAttri
	                .put(cateString, getPearsonCorrelation(starsArray, cate.get(cateString)));
	        }
	        for(String parkingString: this.ParkingSet)
	        {
	        	pearsonAttri
                .put("Parking_"+parkingString, getPearsonCorrelation(starsArray, parking.get(parkingString)));
	        }
	        pearsonAttri
	                .put("Stars", getPearsonCorrelation(starsArray, starsArray));
	        totalAttributes.put("Stars",starsArray);
	        pearsonAttri.put("Review_count",
	                getPearsonCorrelation(starsArray, review_countArray));
	        totalAttributes.put("Review_count",review_countArray);

	        pearsonAttri
	                .put("price", getPearsonCorrelation(starsArray, priceArray));
	        totalAttributes.put("price",priceArray);	        
	        pearsonAttri.put("wifi", getPearsonCorrelation(starsArray, wifiArray));
	        totalAttributes.put("wifi",wifiArray);	        

	 
	        pearsonAttri.put("caters",
	                getPearsonCorrelation(starsArray, catersArray));
	        totalAttributes.put("caters",catersArray);	        

	        pearsonAttri.put("delivery",
	                getPearsonCorrelation(starsArray, deliveryArray));
	        totalAttributes.put("delivery",deliveryArray);	        

	        
	        pearsonAttri.put("goodForKids",
	                getPearsonCorrelation(starsArray, goodForKidsArray));
	        totalAttributes.put("goodForKids",goodForKidsArray);	        

	        pearsonAttri.put("reservations",
	                getPearsonCorrelation(starsArray, reservationsArray));
	        totalAttributes.put("reservations",reservationsArray);	        

	        pearsonAttri.put("wheelchair",
	                getPearsonCorrelation(starsArray, wheelchairArray));
	        totalAttributes.put("wheelchair",wheelchairArray);	        

	        
	        pearsonAttri.put("dancing",
	                getPearsonCorrelation(starsArray, dancingArray));
	        totalAttributes.put("dancing",dancingArray);	        

	        pearsonAttri.put("coatCheck",
	                getPearsonCorrelation(starsArray, coatCheckArray));
	        totalAttributes.put("coatCheck",coatCheckArray);	        

	        pearsonAttri.put("smoking",
	                getPearsonCorrelation(starsArray, smokingArray));
	        totalAttributes.put("smoking",smokingArray);	        

	        pearsonAttri.put("creditCards",
	                getPearsonCorrelation(starsArray, creditCardsArray));
	        totalAttributes.put("creditCards",creditCardsArray);	        

	        pearsonAttri.put("takeout",
	                getPearsonCorrelation(starsArray, takeoutArray));
	        totalAttributes.put("takeout",takeoutArray);	        

	        pearsonAttri.put("happyhour",
	                getPearsonCorrelation(starsArray, happyhourArray));
	        totalAttributes.put("happyhour",happyhourArray);	        

	        pearsonAttri.put("outdoorSeating",
	                getPearsonCorrelation(starsArray, outdoorSeatingArray));
	        totalAttributes.put("outdoorSeating",outdoorSeatingArray);	        

	        pearsonAttri.put("waiterService",
	                getPearsonCorrelation(starsArray, waiterServiceArray));
	        totalAttributes.put("waiterService",waiterServiceArray);	        

	        pearsonAttri.put("dessert",
	                getPearsonCorrelation(starsArray, dessertArray));
	        totalAttributes.put("dessert",dessertArray);	        

	        pearsonAttri.put("latenight",
	                getPearsonCorrelation(starsArray, latenightArray));
	        totalAttributes.put("latenight",latenightArray);	        

	        pearsonAttri
	                .put("lunch", getPearsonCorrelation(starsArray, lunchArray));
	        totalAttributes.put("lunch",lunchArray);	        

	        pearsonAttri.put("dinner",
	                getPearsonCorrelation(starsArray, dinnerArray));
	        totalAttributes.put("dinner",dinnerArray);	        

	        pearsonAttri.put("brunch",
	                getPearsonCorrelation(starsArray, brunchArray));
	        totalAttributes.put("brunch",brunchArray);	        

	        pearsonAttri.put("breakfast",
	                getPearsonCorrelation(starsArray, breakfastArray));
	        totalAttributes.put("breakfast",breakfastArray);	        

	        pearsonAttri.put("tv", getPearsonCorrelation(starsArray, tvArray));
	        totalAttributes.put("tv",tvArray);	        

	        pearsonAttri.put("goodForGroups",
	                getPearsonCorrelation(starsArray, goodForGroupsArray));
	        totalAttributes.put("goodForGroups",goodForGroupsArray);	        

	        pearsonAttri.put("romantic",
	                getPearsonCorrelation(starsArray, romanticArray));
	        totalAttributes.put("romantic",romanticArray);	        

	        pearsonAttri.put("intimate",
	                getPearsonCorrelation(starsArray, intimateArray));
	        totalAttributes.put("intimate",intimateArray);	        

	        pearsonAttri.put("touristy",
	                getPearsonCorrelation(starsArray, touristyArray));
	        totalAttributes.put("touristy",touristyArray);	        

	        pearsonAttri.put("hipster",
	                getPearsonCorrelation(starsArray, hipsterArray));
	        totalAttributes.put("hipster",hipsterArray);	        

	        pearsonAttri
	                .put("divey", getPearsonCorrelation(starsArray, diveyArray));
	        totalAttributes.put("divey",diveyArray);	        

	        pearsonAttri.put("classy",
	                getPearsonCorrelation(starsArray, classyArray));
	        totalAttributes.put("classy",classyArray);	        

	        pearsonAttri.put("trendy",
	                getPearsonCorrelation(starsArray, trendyArray));
	        totalAttributes.put("trendy",trendyArray);	        

	        pearsonAttri.put("upscale",
	                getPearsonCorrelation(starsArray, upscaleArray));
	        totalAttributes.put("upscale",upscaleArray);	        

	        pearsonAttri.put("casual",
	                getPearsonCorrelation(starsArray, casualArray));
	        totalAttributes.put("casual",casualArray);	        

	        pearsonAttri.put("noiseLevel",
	                getPearsonCorrelation(starsArray, noiseArray));
	        totalAttributes.put("noiseLevel",noiseArray);	        

	        
	        sorted_pearsonAttri.putAll(pearsonAttri);
//	        System.out.println(sorted_pearsonAttri.toString());
//	        System.out.println(pearsonAttri.get("Stars"));
	        
//	        System.out.println(sorted_pearsonAttri.get("Stars"));
			  Iterator it = sorted_pearsonAttri.entrySet().iterator();
			  while (it.hasNext()) {
			   Map.Entry entry =(Map.Entry) it.next();
			   String key = (String) entry.getKey();
			   PearsonType value=(PearsonType) entry.getValue();
//			   System.out.println(key+":");
//			   System.out.println(value.pearsonValue);
//			   System.out.println(value.TrueNum);
			  }
			  City returnCity = new City(pearsonAttri);
			  
			  
			  
			  totalAttributes.putAll(cate);
			  totalAttributes.putAll(parking);
			  
			  System.out.println("totalAttributes size = " + totalAttributes.size());

			  
			  return returnCity;
	    }
	 

	 
	    public static PearsonType getPearsonCorrelation(double[] scores1,
	            double[] scores2) {
	 
	        double result = 0;
	        double sum_sq_x = 0;
	        double sum_sq_y = 0;
	        double sum_coproduct = 0;
	        double mean_x = scores1[0];
	        double mean_y = scores2[0];
	 
	        for (int i = scores1.length/2; i < scores1.length + 1; i += 1) {
	            double sweep = Double.valueOf(i - 1) / i;
	            double delta_x = scores1[i - 1] - mean_x;
	            double delta_y = scores2[i - 1] - mean_y;
	            sum_sq_x += delta_x * delta_x * sweep;
	            sum_sq_y += delta_y * delta_y * sweep;
	            sum_coproduct += delta_x * delta_y * sweep;
	            mean_x += delta_x / i;
	            mean_y += delta_y / i;
	        }
	 
	        double pop_sd_x = (double) Math.sqrt(sum_sq_x / scores1.length);
	        double pop_sd_y = (double) Math.sqrt(sum_sq_y / scores1.length);
	        double cov_x_y = sum_coproduct / scores1.length;
	        result = cov_x_y / (pop_sd_x * pop_sd_y);
	        int trueNum = 0;
	        for(int i=scores1.length/2;i< scores1.length;i++)
	        {
	        	if(scores2[i]>0)
	        	{
	        		trueNum++;
	        	}
	        }
	        PearsonType returnValue = new PearsonType();
	        returnValue.pearsonValue = result;
	        returnValue.TrueNum = trueNum++;
	        return returnValue;
	    }
	 
	    public int checkAttribute(String checker, String attributes,

	            String container)  {
	        int rValue = 0;
	        if (attributes.indexOf(checker) != -1&&attributes.indexOf(checker)+checker.length()+7<attributes.length()) {
	            String toBeChecked = attributes.substring(
	 
	            attributes.indexOf(checker) + checker.length() + 2,
	                    attributes.indexOf(checker) + checker.length() + 7);
	            if (toBeChecked.contains(container))
	                rValue = 1;
	        }
	 
	        return rValue;
	 
	    }
	 

	}	
	

	

