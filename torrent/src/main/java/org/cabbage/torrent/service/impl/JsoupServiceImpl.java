package org.cabbage.torrent.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cabbage.torrent.entity.BaseResult;
import org.cabbage.torrent.service.JsoupService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class JsoupServiceImpl  implements  JsoupService{
	
    String baseUrl="http://www.zhongzidi.com";	
		
	 @Override
	public List<Map<String,Object>> data(String page,String rows,String keyword) throws Exception{
		 	if(keyword==null){
		 		return new ArrayList<Map<String,Object>>();
		 	}

		 	List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
		 	
		 	int total=0;
		 	int pagei=Integer.parseInt(page);
		 	int rowsi=Integer.parseInt(rows);
		 	
		 	int rate=rowsi/15;
		 	
		 	int begin=rate * (pagei-1)+1;
		 	int end=rate * pagei;
		 	
		 	for(int i=begin;i<=end;i++){
		 		String url=baseUrl+"/list/"+keyword+"/"+i;
		 		Document doc =null;
		 		try{
		 			doc= Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2").timeout(1000*60).get();
					
		 		}catch(Exception ex){
		 			return data;
		 		}
			
				Elements elements = doc.select("tbody");
				
				total=total+elements.size();
				
				for(Element element:elements){
					Map<String,Object> map=new HashMap<String,Object>();
					
					Element a = element.select("a").first();
					
					map.put("text", a.text());
					map.put("href",baseUrl+a.attr("href"));
					
					Elements strongs=element.select("strong");
				    String createdTime = strongs.get(0).text();				
					String size = strongs.get(1).text();				
					String hot = strongs.get(2).text();
					
					map.put("createdTime", createdTime);
					map.put("size", size);
					map.put("hot", hot);
					data.add(map);
				}
		 	}
			return data;
	}

			 
			 @Override
				public BaseResult  torrent(String url,String type) throws Exception{
				
				 Document doc = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2").timeout(1000*60).get();
				 Elements elements = doc.select(".btn-success");
				 Element a =elements.get(Integer.parseInt(type));
				 String href=a.attr("href");
				 return new BaseResult("1","成功",href);				 
				}
}
