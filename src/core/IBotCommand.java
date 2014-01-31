package core;


public interface IBotCommand {
	
	public String getCommand();
	public BotResponse processCommand(IrcMessage ircMessage, BotState botState);
	public boolean isCaseSensitive();

}
