package core;

public class BotResponse {

	public enum RequestType{
		NAMES, OP, PRIVMSG
	}
	
	private String responseMessage = null;
	private String consoleMessage = null;
	private String privateMessage = null;
	private RequestType requestType = null;
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public String getConsoleMessage() {
		return consoleMessage;
	}
	public void setConsoleMessage(String consoleMessage) {
		this.consoleMessage = consoleMessage;
	}
	public String getPrivateMessage() {
		return privateMessage;
	}
	public void setPrivateMessage(String privateMessage) {
		this.privateMessage = privateMessage;
	}
	public RequestType getRequestType() {
		return requestType;
	}
	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}
	
	
	
}
