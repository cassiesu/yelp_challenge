import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.*;
import java.util.*;

public class ResultFilter {

	public static void main(String[] args) throws IOException {
		File inputFile = new File(
				"/Volumes/work/课程/BigData/dataset/yelp_academic_dataset_business.json");
		Scanner sc = new Scanner(inputFile);
		String curString = "";
		Pattern p1 = Pattern.compile("Restaurants");
		Pattern p2 = Pattern.compile("city\": \".*\"");
		Map<String,FileWriter> fileMap = new HashMap<String,FileWriter>();
		//space exceeded warning
		Map<String,Vector<JSONObject>> resCity = new HashMap<String,Vector<JSONObject>>();  
		List<FileWriter> fileList = new LinkedList<FileWriter>();
		int n=0;
		while (sc.hasNext()) {
			curString= sc.nextLine();
			Matcher m1 = p1.matcher(curString);
			Matcher m2 = p2.matcher(curString);
			Vector<JSONObject> curVec;
			if(m1.find())
			{
				n++;
				
				if(m2.find())
				{
					String cur = m2.group();
					String a[] = cur.split("\": \"");
					String b[] = a[1].split("\"");
					if(!fileMap.containsKey(b[0]))
					{	
						//initialize
						curVec = new Vector<JSONObject>();
						resCity.put(b[0], curVec);
						curVec.add(new JSONObject(curString.trim()));
						String fileName = "/Volumes/work/课程/BigData/dataset/restaurant/" + b[0]; // b[0] = cityname
						FileWriter writer=new FileWriter(fileName);
						fileList.add(writer);
						fileMap.put(b[0],writer);
						writer.write(curString);
						writer.write("\n");
					}
					else
					{
						curVec = resCity.get(b[0]);
						curVec.add(new JSONObject(curString.trim()));
						FileWriter writer = fileMap.get(b[0]);
						writer.write(curString);
						writer.write("\n");
					}

				}
			}
//			JSONObject obj = new JSONObject(curString.trim());
//			res.add(obj);	
		}
		
		Map<String, Restaurants> resMap = new HashMap<String, Restaurants>();
		
		for(String str:resCity.keySet())
		{
			Vector<JSONObject> vec = resCity.get(str);
			if(vec.size() > 50)
			{
				Restaurants res = new Restaurants(str);
				resMap.put(str, res);
				for(int j=0;j<vec.size();j++)
				{
					res.add(vec.get(j));
				}
			}
			
		}
		

		for(int i=0;i<fileList.size();i++)
		{
			FileWriter writer = fileList.get(i);

			writer.close();
		}
		System.out.println("total n = " + n);
		double sumAll = 0.0;
		double sumBest = 0.0;
		Vector<City> cityVector = new Vector<City>();
		for(String str: resMap.keySet())
		{
			System.out.println("===========================");
			System.out.println("City: "+str);
			System.out.println("City Size: " + resCity.get(str).size());
			Restaurants res = resMap.get(str);
			City city = res.getAttributes();
			city.finalFeature();
			cityVector.add(city);
			EvaluateReturn er = Evaluation.evaluator(res,city);
			sumAll += er.overall;
			sumBest += er.best10;
		}
		double CityNum = (double)resMap.size();
		System.out.println("===========================");
		System.out.println("Average Overall Stars: " +sumAll/CityNum);
		System.out.println("Average Best 10 Stars: " +sumBest/CityNum);
		CityVector cv = new CityVector(cityVector);
		cv.recommendCity();
		
		sumAll =0.0;
		sumBest = 0.0;
		int num = 0;
		for(String str: resMap.keySet())
		{
			System.out.println("===========================");
			System.out.println("City: "+str);
			System.out.println("City Size: " + resCity.get(str).size());
			Restaurants res = resMap.get(str);
//			City city = res.getAttributes();
			City city = cityVector.get(num);
			num++;
			city.finalFeature();
			EvaluateReturn er = Evaluation.evaluator(res,city);
			sumAll += er.overall;
			sumBest += er.best10;
		}
		System.out.println("===========================");
		System.out.println("Average Overall Stars: " +sumAll/CityNum);
		System.out.println("Average Best 10 Stars: " +sumBest/CityNum);
	}
}
