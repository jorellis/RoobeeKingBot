package commands;

import core.BotResponse;
import core.BotState;
import core.IBotCommand;
import core.IrcMessage;

public class RoobeeRanksCommand implements IBotCommand{

	@Override
	public String getCommand() {
		return "roobeeranks";
	}

	@Override
	public BotResponse processCommand(IrcMessage txt, BotState botState) {
		BotResponse response = new BotResponse();
		response.setResponseMessage("Current Ranks:"+botState.getMemory().get("roobeeranks").toString());
		response.setConsoleMessage(botState.getMemory().toString());
		return response;
	}

	@Override
	public boolean isCaseSensitive() {
		return false;
	}

}
