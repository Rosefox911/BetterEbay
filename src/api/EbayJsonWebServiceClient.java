package api;

import classes.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class EbayJsonWebServiceClient {
	private String APIURL = "http://open.api.ebay.com/shopping?appid=JorgeDel-89b1-4723-8ee2-8812f73e42fe&version=517&siteid=0&callname=FindItems&QueryKeywords={{KEYWORD}}&responseencoding=JSON&MaxEntries=10";
	public List<Product> getProductsforKeyword(String keyword) {
		keyword = keyword.replace(" ", "+");
		String CompleteAPIURL = APIURL.replace("{{KEYWORD}}", keyword);
		//System.out.println(CompleteAPIURL);
		
		List<Product> products = new ArrayList<Product>();
		
		try {
			URL url = new URL(CompleteAPIURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			InputStream stream = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader buffer = new BufferedReader(reader);
			String line;
			String json = "";
			while((line = buffer.readLine()) != null) {
				json += line;
			}
			
			JSONParser parser = new JSONParser();
		try {
			
				JSONObject root = (JSONObject) parser.parse(json);
				JSONArray ItemArray = (JSONArray) root.get("Item");
				
				if(ItemArray != null) {
				Integer length = ItemArray.size();
				
					for (int i = 0; i < length; i++ ) {
						JSONObject FirstItem = (JSONObject) ItemArray.get(i);
						String ItemID = FirstItem.get("ItemID").toString();
						String EndTime = FirstItem.get("EndTime").toString();
						String ItemURL = FirstItem.get("ViewItemURLForNaturalSearch").toString();
						String ListingType = FirstItem.get("ListingType").toString();
						String GalleryURL = FirstItem.get("GalleryURL").toString();
						String PrimaryCategoryID = FirstItem.get("PrimaryCategoryID").toString();
						String PrimaryCategoryName = FirstItem.get("PrimaryCategoryName").toString();
						Long BidCount = (Long) FirstItem.get("BidCount");
						
						JSONObject ConvertedCurrentPriceList = (JSONObject) FirstItem.get("ConvertedCurrentPrice");
						
						
						Double ConvertedCurrentPrice = (Double) ConvertedCurrentPriceList.get("Value");
						String ListingStatus = FirstItem.get("ListingStatus").toString();
						String TimeLeft = FirstItem.get("TimeLeft").toString();
						String Title = FirstItem.get("Title").toString();
						
						JSONObject ShippingCostSummaryList = (JSONObject) FirstItem.get("ShippingCostSummary");
						JSONObject ShippingServiceCostList = (JSONObject) ShippingCostSummaryList.get("ShippingServiceCost");
						
						String ShippingType = ShippingCostSummaryList.get("ShippingType").toString();
								
						Product product = new Product(ItemID, EndTime,ItemURL,ListingType, GalleryURL,PrimaryCategoryID, PrimaryCategoryName, BidCount, ConvertedCurrentPrice, ListingStatus, TimeLeft, Title, ShippingType);
						products.add(product);
					
					}
				}
				return products;
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(NullPointerException e) {
				e.printStackTrace();
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return null;
	}	
	
	public static void main(String[] args) {
		EbayJsonWebServiceClient test1 = new EbayJsonWebServiceClient();
		
		List<Product> products = null;
		
		products = test1.getProductsforKeyword("keyboard usb");
		
		for (Product p : products) {
			System.out.println(p.getItemID());
		}	
		

	}

}
