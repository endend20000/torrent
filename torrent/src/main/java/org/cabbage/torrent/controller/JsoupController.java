package org.cabbage.torrent.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.cabbage.torrent.entity.BaseResult;
import org.cabbage.torrent.service.JsoupService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/jsoup")
public class JsoupController {
	
	@Resource
	private JsoupService jsoupService;	

	@RequestMapping(value = "/index")
    public ModelAndView baseType(HttpServletRequest request){
		return new ModelAndView("jsoup/index");
    }

	@RequestMapping(value = "/data")
	@ResponseBody
	public Object data(String page,String rows,String keyword,HttpServletRequest request){
		Map<String, Object> retD = new HashMap<String, Object>();
        List<Map<String, Object>> list;;
		try {
			list = jsoupService.data(page, rows, keyword);
	        retD.put("total", 100000);
	        retD.put("rows", list);  
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	    return retD;
	}
	
	@RequestMapping(value = "/torrent")
	@ResponseBody//type 1 torrent 0 megent:
	public Object torrent(String url,String type,HttpServletRequest request){
		BaseResult br =null;
		try {
			br= jsoupService.torrent(url, type);
		} catch (Exception e) {
			e.printStackTrace();
			br= new BaseResult("0","系统异常");
		}
	    return br;
	}
}
