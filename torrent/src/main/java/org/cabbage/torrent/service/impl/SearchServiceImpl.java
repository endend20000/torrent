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

@Service
public class SearchServiceImpl  implements  SearchService{
	
    private final String baseUrl="http://www.zhongzidi.com";	
		
	 @Override
	public List<DataDTO> data(SearchDTO searchDTO) throws Exception{
		 	if(searchDTO.getKeyword()==null){
		 		return new ArrayList<DataDTO>();
		 	}

		 	List<DataDTO> data=new ArrayList<DataDTO>();
		 	
		 	int rate=searchDTO.getSize()/15;
		 	
		 	int begin=rate * (searchDTO.getIndex()-1)+1;
		 	int end=rate * searchDTO.getIndex();
		 	
		 	for(int i=begin;i<=end;i++){
		 		String url=baseUrl+"/list/"+searchDTO.getKeyword()+"/"+i;
		 		Document doc =null;
		 		try{
		 			doc= Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2").timeout(1000*60).get();
					
		 		}catch(Exception ex){
		 			return data;
		 		}
			
				Elements elements = doc.select("tbody");
				
				for(Element element:elements){
					DataDTO dataDto=new DataDTO();
					
					Element a = element.select("a").first();
					
					dataDto.setName(a.text());
					dataDto.setUrl(baseUrl+a.attr("href"));
					
					Elements strongs=element.select("strong");
				    String time = strongs.get(0).text();				
					String size = strongs.get(1).text();				
					String hot = strongs.get(2).text();
					
					dataDto.setTime(time);
					dataDto.setSize(size);
					dataDto.setHot(hot);

					data.add(dataDto);
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
				 return new BaseResult(1,"成功",href);				 
				}
}
