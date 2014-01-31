package bot;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import commands.RoobeeCommand;
import commands.RoobeeRanksCommand;
import core.IBotCommand;
import core.IrcConnection;

public class Bot extends AbstractBot{

	
	private IrcConnection connection = new IrcConnection("src/resource/config.properties");
	
	@Override
	IrcConnection getConnection() {
		return connection;
	}
	
	public void start() throws UnknownHostException, IOException{
		connection.connect();
		while(connection.isConnected()){
			processLine();
		}
		connection.close();
	}

	@Override
	List<IBotCommand> getCommands() {
		List<IBotCommand> commands = new ArrayList<IBotCommand>();
		commands.add(new RoobeeCommand());
		commands.add(new RoobeeRanksCommand());
		return commands;
	}


}
