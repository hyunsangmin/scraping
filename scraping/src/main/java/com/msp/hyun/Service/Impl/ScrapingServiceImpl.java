package com.msp.hyun.Service.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.msp.hyun.DTO.ScrapingDTO;
import com.msp.hyun.Service.ScrapingService;


@Service
public class ScrapingServiceImpl implements ScrapingService{
	
	
	@Override
	public ArrayList<Map<String,String>> getStockPriceList() {
	    final String stockList = "https://finance.naver.com/sise/sise_market_sum.nhn?&page=1";
	    Connection conn = Jsoup.connect(stockList);
	    ArrayList<Map<String,String>> arr = new ArrayList<Map<String,String>>();
	    Map<String,String> map = new HashMap<String,String>(); 	     

	    try {
	      Document document = conn.get();
	      ArrayList<String> arrthead = getStockHeader(document); // 칼럼명
	      ArrayList<ArrayList<String>> arrtbody = getStockList(document);   // 데이터 리스트
	      System.out.println(arrthead);
	      System.out.println(arrtbody);
	      for (int i =0; i < arrtbody.size(); i++) {	 
	    	 map.put(arrthead.get(0), arrtbody.get(i).get(0));
	    	 map.put(arrthead.get(1), arrtbody.get(i).get(1));
	    	 map.put(arrthead.get(2), arrtbody.get(i).get(2));
	    	 map.put(arrthead.get(3), arrtbody.get(i).get(3));
	    	 map.put(arrthead.get(4), arrtbody.get(i).get(4));
	    	 map.put(arrthead.get(5), arrtbody.get(i).get(5));
	    	 map.put(arrthead.get(6), arrtbody.get(i).get(6));
	    	 map.put(arrthead.get(7), arrtbody.get(i).get(7));
	    	 map.put(arrthead.get(8), arrtbody.get(i).get(8));
	    	 map.put(arrthead.get(9), arrtbody.get(i).get(9));
	    	 map.put(arrthead.get(10), arrtbody.get(i).get(10));
	    	 map.put(arrthead.get(11), arrtbody.get(i).get(11));
	    	 map.put(arrthead.get(12), arrtbody.get(i).get(12));
	    	 arr.add(map);
	      }
	      System.out.println(arr);

	    } catch (IOException ignored) {
	    }

		return arr;
	  }

	  public ArrayList<String> getStockHeader(Document document) {
	    Elements stockTableBody = document.select("table.type_2 thead tr");
	    StringBuilder sb = new StringBuilder();
	    ArrayList<String> arr = new ArrayList<String>();
	    for (Element element : stockTableBody) {
	      for (Element td : element.select("th")) {
	        sb.append(td.text());
	        sb.append("   ");	     
	        arr.add(td.text());
	      }
	      break;
	    }
	    //System.out.println(arr);
	    return arr;
	  }

	  public ArrayList<ArrayList<String>> getStockList(Document document) {
	    Elements stockTableBody = document.select("table.type_2 tbody tr");
	    StringBuilder sb = new StringBuilder();
	    ArrayList<ArrayList<String>> arr2 = new ArrayList<ArrayList<String>>();
	    for (Element element : stockTableBody) {
	      if (element.attr("onmouseover").isEmpty()) {
	        continue;
	      }

	      ArrayList<String> arr = new ArrayList<String>();
	      for (Element td : element.select("td")) {
	        String text;
	        if(td.select(".center a").attr("href").isEmpty()){
	          text = td.text();
	        }else{
	          text = "https://finance.naver.com"+td.select(".center a").attr("href");
	        }
	        sb.append(text);
	        sb.append("   ");
	        arr.add(text);
	      }
	      arr2.add(arr);
	      sb.append(System.getProperty("line.separator")); //줄바꿈
	    }
	    //System.out.println(arr2);
//	    return sb.toString();
	    return arr2;
	  }
}
