package core;

public class IrcMessage {
	private String senderNick = "",
			senderIp = "",
			chan = "",
			text = "";
	
	public IrcMessage(String incomingMessage){
		String[] words = incomingMessage.split(" ");
		int atIndex = words[0].indexOf("!");
		if(atIndex>-1 && words.length > 3){
			senderNick = words[0].substring(1,atIndex);
			senderIp = words[0].substring(atIndex+1);
			
			words[3] = words[3].substring(1);
			for(int i = 3; i < words.length; i++){
				text += words[i] + " ";
			}
			chan = words[2];
		}
		
		
	}

	public String getSenderNick() {
		return senderNick;
	}

	public void setSenderNick(String senderNick) {
		this.senderNick = senderNick;
	}

	public String getSenderIp() {
		return senderIp;
	}

	public void setSenderIp(String senderIp) {
		this.senderIp = senderIp;
	}

	public String getChan() {
		return chan;
	}

	public void setChan(String chan) {
		this.chan = chan;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
