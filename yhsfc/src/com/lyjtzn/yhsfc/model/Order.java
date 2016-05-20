package com.lyjtzn.yhsfc.model;

import java.io.Serializable;

/**
 * @author csh
 * @date 2016年5月15日
 * 打车订单
 */
public class Order implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Double DLatitude;
	private Double DLongitude;
	private Double FLatitude;
	private Double FLongitude;
	private String OType;
	private String destination;
	private Double distance;
	private String fromAddress;
	private String id;
	private String planAmount;
	private String status;
	private String vid;
	public Double getDLatitude() {
		return DLatitude;
	}
	public void setDLatitude(Double dLatitude) {
		DLatitude = dLatitude;
	}
	public Double getDLongitude() {
		return DLongitude;
	}
	public void setDLongitude(Double dLongitude) {
		DLongitude = dLongitude;
	}
	public Double getFLatitude() {
		return FLatitude;
	}
	public void setFLatitude(Double fLatitude) {
		FLatitude = fLatitude;
	}
	public Double getFLongitude() {
		return FLongitude;
	}
	public void setFLongitude(Double fLongitude) {
		FLongitude = fLongitude;
	}
	public String getOType() {
		return OType;
	}
	public void setOType(String oType) {
		OType = oType;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public String getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPlanAmount() {
		return planAmount;
	}
	public void setPlanAmount(String planAmount) {
		this.planAmount = planAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
}
