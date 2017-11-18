package org.cabbage.torrent.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.cabbage.torrent.dto.DataDTO;
import org.cabbage.torrent.dto.SearchDTO;
import org.cabbage.torrent.entity.BaseResult;
import org.cabbage.torrent.service.SearchService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service(value="sobt")
public class SobtSearchServiceImpl  implements  SearchService{
	
    private final String baseUrl="http://www.sobt8.com/";	
		
	@Override
	public List<DataDTO> data(SearchDTO searchDTO) throws Exception{
		 	if(searchDTO.getKeyword()==null){
		 		return new ArrayList<DataDTO>();
		 	}

		 	List<DataDTO> data=new ArrayList<DataDTO>();
		 	
		 	int rate=searchDTO.getSize()/10;
		 	
		 	int begin=rate * (searchDTO.getIndex()-1)+1;
		 	int end=rate * searchDTO.getIndex();
		 	
		 	for(int i=begin;i<=end;i++){
		 		String url=baseUrl+"/q/"+searchDTO.getKeyword()+".html?sort=rel&page="+i;
		 		Document doc =null;
		 		try{
		 			doc= Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2").timeout(1000*60).get();
					
		 		}catch(Exception ex){
		 			return data;
		 		}
				Elements elements = doc.select(".search-item");
				
				for(Element element:elements){
					DataDTO dataDto=new DataDTO();
					dataDto.setType(2);
					
					String name = element.select(".item-title").select("a").first().text();
					dataDto.setName(name);
					
					String time = element.select(".item-bar").select("span").first().text().split("：")[1];								
					dataDto.setTime(time);
					
					String size = element.select(".item-bar").select("span").get(1).text();	
					dataDto.setSize(size);
					
					String hot = element.select(".item-bar").select("span").get(2).text();	
					dataDto.setHot(hot);

					String magnetUrl = baseUrl + element.select(".item-title").select("a").first().attr("href");
					dataDto.setUrl(magnetUrl);

					data.add(dataDto);
				}
		 	}
			return data;
	}

			 
 	@Override
	public BaseResult  torrent(String url,String type) throws Exception{				
	 Document doc = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2").timeout(1000*60).get();
	 String href = doc.select(".download").first().attr("href");
	 return new BaseResult(1,"成功",href);				 
	}
}
