package core;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

public class IrcConnection {
	
	private Properties properties;
	private String bot_owner,nick,serv,chan;
	private int port;
	
	public IrcConnection(String propertyPath){
		Properties prop = new Properties();
		InputStream input = null;
	 
		try {
	 
			input = new FileInputStream(propertyPath);
	 
			// load a properties file
			prop.load(input);
	 
			// get the property value and print it out
			bot_owner = prop.getProperty("bot_owner");
			nick = prop.getProperty("nick");
			serv = prop.getProperty("serv");
			chan = prop.getProperty("chan");
			port = Integer.parseInt(prop.getProperty("port"));
	 
			System.out.println(bot_owner + " " + nick + " " + serv + " " + chan);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	 
	}
	
	
	private Socket s;
	private BufferedReader i;
	private PrintWriter o;
	
	public void connect() throws UnknownHostException, IOException{
		System.out.println("Connection to server " + serv);
		s =  new Socket(serv, port);
		System.out.println("Connection Established");
		
		i = new BufferedReader(new InputStreamReader(s.getInputStream()));
		o = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
		
		System.out.println("Logging in as user " +nick);
		o.print("USER " + nick + " 0 * :" + bot_owner + "\r\n" + "NICK " + nick+ "\r\n");
		o.flush();
	}
	
	public boolean isConnected(){
		return s.isConnected();
	}
	
	public String readLine() throws IOException{
		return i.readLine();
	}
	
	public void close() throws IOException{
		s.close();
	}
	
	public void writeLine(String str){
		o.print(str);
	}
	
	public void flush(){
		o.flush();
	}
	
	public String getBot_owner() {
		return bot_owner;
	}

	public void setBot_owner(String bot_owner) {
		this.bot_owner = bot_owner;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getServ() {
		return serv;
	}

	public void setServ(String serv) {
		this.serv = serv;
	}

	public String getChan() {
		return chan;
	}

	public void setChan(String chan) {
		this.chan = chan;
	}
	
	

}
