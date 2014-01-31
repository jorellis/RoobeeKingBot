package main;

import java.io.IOException;
import java.net.UnknownHostException;

import bot.Bot;

public class RoobeeKingDriver {

	public static void main(String args[]) throws UnknownHostException, IOException{
		Bot bot = new Bot();
		bot.start();
	}
}
