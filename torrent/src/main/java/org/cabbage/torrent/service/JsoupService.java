package org.cabbage.torrent.service;

import java.util.List;
import java.util.Map;

import org.cabbage.torrent.entity.BaseResult;

public interface JsoupService {
	List<Map<String,Object>> data(String page,String rows,String keyword) throws Exception;
	BaseResult  torrent(String url,String type) throws Exception;
}
