package commands;

import java.util.HashMap;
import java.util.Map;

import core.BotResponse;
import core.BotState;
import core.IBotCommand;
import core.IrcMessage;

public class RoobeeCommand implements IBotCommand{

	private int initCount = 4;
	
	@Override
	public String getCommand() {
		return "+r";
	}

	@Override
	public BotResponse processCommand(IrcMessage txt, BotState botState) {
		BotResponse response = new BotResponse();
		String[] words = txt.getText().split(" ");
		for(String word : words){
			int index = word.indexOf(getCommand());
			if(index != -1){
				word = word.substring(0, index);
				if(word.length() > 0){
					if(!useRoobee(txt,botState)){
						response.setResponseMessage("You are out of roobees "+txt.getSenderNick());
						return response;
					}
					if(word.equals(txt.getSenderNick())){
						response.setResponseMessage("Nice try "+ txt.getSenderNick());
						return response;
					}
					if(!botState.contains("roobeeranks")){
						Map<String,Integer> roobeeranks = new HashMap<String,Integer>();
						botState.getMemory().put("roobeeranks", roobeeranks);
					}
					Map<String,Integer> roobeeranks = (Map<String, Integer>) botState.get("roobeeranks");
					if(roobeeranks.containsKey(word)){
						roobeeranks.put(word, roobeeranks.get(word)+1);
					}else{
						roobeeranks.put(word, 1);
					}
					botState.getMemory().put("roobeeranks",roobeeranks);
					
				response.setConsoleMessage(botState.getMemory().toString());
				response.setResponseMessage("Roobee awarded to " + word + " from " + txt.getSenderIp());
			}
		}
		}
		
		return response;
	}
	
	private boolean useRoobee(IrcMessage txt, BotState state){
		if(!state.contains("roobeecount")){
			Map<String,Integer> roobeecount = new HashMap<String,Integer>();
			state.getMemory().put("roobeecount", roobeecount);
		}
		Map<String,Integer> roobeeCount = (Map<String, Integer>) state.get("roobeecount");
		if(roobeeCount.containsKey(txt.getSenderIp())){
			int count = roobeeCount.get(txt.getSenderIp());
			if(count > 0){
				roobeeCount.put(txt.getSenderIp(),(count -1));
				state.getMemory().put("roobeecount", roobeeCount);
				return true;
			}else{
				return false;
			}
		}else{
			roobeeCount.put(txt.getSenderIp(), initCount);
			state.getMemory().put("roobeecount", roobeeCount);
			return true;
		}
		
	}

	@Override
	public boolean isCaseSensitive() {
		return false;
	}


}
