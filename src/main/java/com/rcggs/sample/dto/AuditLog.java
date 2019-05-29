package com.rcggs.sample.dto;

public class AuditLog {
	
	private String operationName;


	private String businessTxNumber;


	private Integer userID;


	private String startTime;


	private String type;


	private String message;


	private String source;

	public AuditLog() {
	}

	public AuditLog( String operationName, String businessTxNumber, Integer userID, String startTime, String type, String message, String source) {

		this.operationName = operationName;
		this.businessTxNumber = businessTxNumber;
		this.userID = userID;
		this.startTime = startTime;
		this.type = type;
		this.message = message;
		this.source = source;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public String getBusinessTxNumber() {
		return businessTxNumber;
	}

	public void setBusinessTxNumber(String businessTxNumber) {
		this.businessTxNumber = businessTxNumber;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	

}
