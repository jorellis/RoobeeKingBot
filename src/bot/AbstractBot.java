package bot;

import java.io.IOException;
import java.util.List;

import core.BotResponse;
import core.BotState;
import core.IBotCommand;
import core.IrcConnection;
import core.IrcMessage;

public abstract class AbstractBot {
	
	abstract  IrcConnection getConnection();
	abstract List<IBotCommand> getCommands();
	private BotState botState = new BotState();

	
	public  void processLine() throws IOException{
		
		String str = getConnection().readLine();
		System.out.println(str);
		//Response to server PING
		if (str.startsWith("PING ")) {
			getConnection().writeLine("PONG " + str.substring(5) + "\r\n");
			getConnection().flush();
		}
		
		//Make sure string is start of IRC message
		if (str.charAt(0) != ':')
			return;

		//Reply to IRC Welcome message
		if (str.split(" ")[1].equals("001")) {
			getConnection().writeLine("MODE " + getConnection().getNick() + " +B\r\n" + "JOIN " + getConnection().getChan() + "\r\n");
			getConnection().flush();
		} 

		//Check message for commands
		IrcMessage ircMessage = new IrcMessage(str);
		
		
		
		//Iterate through all commands for current bot
		for(IBotCommand botCommand : getCommands()){
			
			String strCommand;
			//Check if command is case sensitive
			if(!botCommand.isCaseSensitive()){
				ircMessage.setText(ircMessage.getText().toLowerCase()); 
				strCommand =botCommand.getCommand().toLowerCase();
			}else{
				strCommand = botCommand.getCommand();
			}
			
			
			//TODO implement REGEX for command instead of String
			if(ircMessage.getText().contains(strCommand)){
				//Process response from BotCommand
				BotResponse response = botCommand.processCommand(ircMessage,botState);
				
				if(response.getResponseMessage() != null){
					getConnection().writeLine("PRIVMSG " + getConnection().getChan()+ " :"+response.getResponseMessage()+"\r\n");
					getConnection().flush();
					
				}
				
				if(response.getConsoleMessage()!= null){
					System.out.println(response.getConsoleMessage());
				}
				
				if(response.getRequestType() != null){
					//TODO -- Process Request
				}
				
				if(response.getPrivateMessage() != null){
					//TODO --Process PrivateMessage
				}
			}
			
		}
		
	}

}
