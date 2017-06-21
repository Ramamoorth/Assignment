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
import java.util.Properties;

import com.google.gson.Gson;
import com.model.SortingModel;

/** 
 * @author 582571
 *Create the RandomSortingMethods which contains the available sorting methods
 */
public class RandomSortingMethods {
	
	static int CeilIndex(int A[], int l, int r, int key)
    {
        while (r - l > 1)
        {
            int m = l + (r - l)/2;
            if (A[m]>=key)
                r = m;
            else
                l = m;
        }
 
        return r;
    }
	
	//This method is used to get the no of position changes completed for the particular sorting	
	 public int getNoOfPositionChanges(int A[], int size)
	    {
	        // Add boundary case, when array size is one
	 
	        int[] tailTable   = new int[size];
	        int len; // always points empty slot
	 
	        tailTable[0] = A[0];
	        len = 1;
	        for (int i = 1; i < size; i++)
	        {
	            if (A[i] < tailTable[0])
	                // new smallest value
	                tailTable[0] = A[i];
	 
	            else if (A[i] > tailTable[len-1])
	                // A[i] wants to extend largest subsequence
	                tailTable[len++] = A[i];
	 
	            else
	                // A[i] wants to be current end candidate of an existing
	                // subsequence. It will replace ceil value in tailTable
	                tailTable[CeilIndex(tailTable, -1, len-1, A[i])] = A[i];
	        }
	 
	        return len;
	    }
	
	 //This method is used to save the sortingdata to json file	 
	public static String savesortingData(String sortingModel){   
		Writer output;
        try(PrintWriter output1 = new PrintWriter(new FileWriter("C:\\RandomSorting\\testout.json",true))) 
        {
            output1.printf("%s\r\n", sortingModel);
        } 
        catch (Exception e) {}
		return null;    
    }
	
	 // This method is used to display the history of sorted values
	public ArrayList displaySortingData() throws Exception{    
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\RandomSorting\\testout.json"), "Cp1252")); 	      
	      ArrayList historyArray = new ArrayList();
	      Gson gson = new Gson();
        String line;    
        while ((line = br.readLine()) != null) {
        	SortingModel sortingModel = gson.fromJson(line, SortingModel.class);
        	historyArray.add(sortingModel);
        }         
        
		return historyArray;    
  }    
	
}
