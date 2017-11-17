package org.cabbage.torrent.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.cabbage.torrent.dto.DataDTO;
import org.cabbage.torrent.dto.SearchDTO;
import org.cabbage.torrent.entity.BaseResult;
import org.cabbage.torrent.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	
	@Resource
	private SearchService searchService;	

	@RequestMapping(value = "/")
    public ModelAndView baseType(HttpServletRequest request){
		return new ModelAndView("jsoup/index");
    }

	@RequestMapping(value = "/data")
	@ResponseBody
	public Object data(SearchDTO searchDTO){
        List<DataDTO> list=null;
		try {
			list = searchService.data(searchDTO);
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	    return new BaseResult(list);
	}
	
	@RequestMapping(value = "/torrent")
	@ResponseBody//type 1 torrent 0 megent:
	public Object torrent(String url,String type){
		BaseResult br =null;
		try {
			br= searchService.torrent(url, type);
		} catch (Exception e) {
			e.printStackTrace();
			br= new BaseResult(0,"系统异常");
		}
	    return br;
	}
}
