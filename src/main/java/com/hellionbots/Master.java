package com.hellionbots;

import org.telegram.telegrambots.meta.api.objects.Update;

import icu.jnet.whatsjava.whatsapp.WAClient;

public interface Master {
    public abstract void handleRequests(WAClient client, Update update, String cmd);
}