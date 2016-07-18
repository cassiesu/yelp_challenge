
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.json.*;

public class Java_Parser_Test {

	public static void main(String[] args) throws FileNotFoundException {
		File inputFile = new File(
				"/Volumes/work/课程/Information Retrieval/yelp_dataset_challenge_academic_dataset 2/yelp_academic_dataset_business.json");
		Scanner sc = new Scanner(inputFile);
		String str = "";
		while (sc.hasNext()) {
			str += sc.nextLine();
		}
		System.out.println(str.trim());
		JSONObject obj = new JSONObject(str.trim());
		System.out.println(obj);
		System.out.println(obj.get("null"));
	}

}
