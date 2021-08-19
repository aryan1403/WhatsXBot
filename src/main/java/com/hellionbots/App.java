package com.hellionbots;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class App {
	public static void main(String[] args) {
		TelegramBotsApi telegramBotsApi;
		try {
			telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
			telegramBotsApi.registerBot(new WhatsX());
			System.out.println("Bot Started Successfully");
		} catch (TelegramApiException e) {
		}
	}

}
