package Utill;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.google.gson.Gson;
import com.model.SortingModel;

/** 
 * @author 582571
 *Create the RandomSortingMethods which contains the available sorting methods
 */
public class RandomSortingMethods {
	
	
	 //This method is used to save the sortingdata to json file	 
	public static String savesortingData(String sortingModel){   
		Writer output;
        try(PrintWriter output1 = new PrintWriter(new FileWriter("C:\\ec\\testout.json",true))) 
        {
            output1.printf("%s\r\n", sortingModel);
        } 
        catch (Exception e) {}
		return null;    
    }
	
	 // This method is used to display the history of sorted values
	public ArrayList displaySortingData() throws Exception{    
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\ec\\testout.json"), "Cp1252")); 	      
	      ArrayList historyArray = new ArrayList();
	      Gson gson = new Gson();
        String line;    
        while ((line = br.readLine()) != null) {
        	SortingModel sortingModel = gson.fromJson(line, SortingModel.class);
        	historyArray.add(sortingModel);
        }         
        
		return historyArray;    
  } 
	
	//This method is used to get the no of position changes completed for the particular sorting	
	public int getNoOfPositionChanges(int[] ar) {
	    int n = ar.length;
	    Map<Integer, Integer> m = new HashMap<>();
	    for (int i = 0; i < n; i++) {
	        m.put(ar[i], i);
	    }
	    Arrays.sort(ar);
	    for (int i = 0; i < n; i++) {
	        ar[i] = m.get(ar[i]);
	    }
	    m = null;
	    int swaps = 0;
	    for (int i = 0; i < n; i++) {
	        int val = ar[i];
	        if (val < 0) continue;
	        while (val != i) {
	            int new_val = ar[val];
	            ar[val] = -1;
	            val = new_val;
	            swaps++;
	        }
	        ar[i] = -1;
	    }
	    return swaps;
	}
	
}
