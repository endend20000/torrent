package org.cabbage.torrent.entity;

public class BaseResult {
	public BaseResult(String status,String msg,Object dataset){
		this.status=status;
		this.msg=msg;
		this.dataset=dataset;
	}
	
	public BaseResult(String status,String msg){
		this.status=status;
		this.msg=msg;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	private String status;
	public Object getDataset() {
		return dataset;
	}
	public void setDataset(Object dataset) {
		this.dataset = dataset;
	}
	private Object dataset;
	private String msg;
}
