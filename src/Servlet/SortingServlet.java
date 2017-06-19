package Servlet;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.JSONException;
import org.json.JSONObject;
import com.model.SortingModel;
import Utill.RandomSortingMethods;
import com.google.*;
import com.google.gson.Gson;

/**
 * 
 * @author 582571
 *Create SortingServlet for get the request data and save it to the particular file
 */
public class SortingServlet extends HttpServlet {

		
	   // Method to handle GET method request.
	   public void doGet(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
		   RandomSortingMethods randomSortingMethods = new RandomSortingMethods();
		   StringBuffer sortedValues = new StringBuffer();
		   StringBuilder sb = new StringBuilder();
	        BufferedReader br = request.getReader();
	        String str = null;
	        String sortingValues = null;
	        int[] results = null;
	        String[] sortingValuesArray = null;
	        while ((str = br.readLine()) != null) {
	            sb.append(str);
	        }
	       
	        JSONObject jObj;
			try {
				jObj = new JSONObject(sb.toString());
				 sortingValues = jObj.getString("sorting_values");
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
			 sortingValuesArray = sortingValues.split(",");
			   results = new int[sortingValuesArray.length];
			   for (int i = 0; i < sortingValuesArray.length; i++) {
				    try {
				        results[i] = Integer.parseInt(sortingValuesArray[i]);
				    } catch (NumberFormatException nfe) {
				        
				    };
				}
			   int positionChangeCount = randomSortingMethods.getNoOfPositionChanges(results,results.length);
			   long start = System.nanoTime();
			      Arrays.sort(results);
			      long end = System.nanoTime();
			      long timeInMillis = TimeUnit.MILLISECONDS.convert(end - start, TimeUnit.NANOSECONDS);
			      
			      for (int i = 0; i < results.length; i++) {
			          if(i != 0) {
			        	  sortedValues.append(",");
			          }
			          sortedValues.append(results[i]);                     
			       }

			      SortingModel sortingModel = new SortingModel();
			      sortingModel.setOriginalList(sortingValues);
			      sortingModel.setTiming(timeInMillis);
			      sortingModel.setSortingList(Arrays.toString(results));
			      sortingModel.setPositionChangeCount(positionChangeCount);
			     
			        String json = new Gson().toJson(sortingModel);
			        randomSortingMethods.savesortingData(json);
	                response.setContentType("application/json");
	                response.getWriter().write(json);

	   }

	   // Method to handle POST method request.
	   public void doPost(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
		  	      doGet(request, response);
	   }
	}


