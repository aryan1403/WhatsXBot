package com.hellionbots;

import com.hellionbots.Helpers.configuration;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import icu.jnet.whatsjava.listener.ClientActionInterface;
import icu.jnet.whatsjava.messages.generic.WAChat;
import icu.jnet.whatsjava.messages.generic.WAContact;
import icu.jnet.whatsjava.messages.generic.WAEmoji;
import icu.jnet.whatsjava.messages.generic.WAMessage;
import icu.jnet.whatsjava.messages.generic.WAStatusMessage;
import icu.jnet.whatsjava.whatsapp.WAClient;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class WhatsX extends TelegramLongPollingBot {
    WAClient client = new WAClient();

    @Override
    public void onUpdateReceived(Update update) {
        WAClient client = new WAClient();
        /*
         * ExecutorService executorService = Executors.newFixedThreadPool(11);
         * executorService.execute(new Runnable() {
         * 
         * @Override public void run() { if(cmd.startsWith(getHandler()))
         * sendRequest(update, cmd); } }); executorService.shutdown();
         */

        client.setPrintQRCode(false);
        client.addClientActionListener(new ClientActionInterface() {
            @Override
            public void onQRCodeScanRequired(BufferedImage image) {
                sendMessage(update, "Authentication required! Please scan the QR code! within 10 Seconds.");
                File outputfile = new File("saved.png");
                try {
                    ImageIO.write(image, "png", outputfile);
                    File file = new File("saved.png");

                    SendPhoto photo = new SendPhoto(chatId(update), new InputFile(file));
                    execute(photo);
                    file.delete();
                } catch (IOException | TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onReceiveLoginResponse(int response) {
                if (response == 200) {
                } else
                    sendMessage(update, "Get me to the Doctor! " + support());
            }

            @Override
            public void onWAChat(WAChat[] wachat) {

            }

            @Override
            public void onWAContact(WAContact[] contacts) {

            }

            @Override
            public void onWAEmoji(WAEmoji[] emoji) {

            }

            @Override
            public void onWAMessage(WAMessage[] messages) {
                
            }

            @Override
            public void onWAStatus(WAStatusMessage[] statusMessages) {

            }
        });
        client.openConnection();

        String cmd = update.getMessage().getText();
        String[] meow2 = cmd.split(" ");

        if (cmd.equalsIgnoreCase(getHandler() + "start")) {
            sendMessage(update, "Hello, " + update.getMessage().getFrom().getFirstName() + ",\n"
                    + "Welcome To WhatsX Bot\nI can Help you Manage your WhatsApp Account Directly from Telegram!\n"
                    + "Type /help to see all the Available Command's"
                    + "Note : Don't Spam the Bot Otherwise will Block you\n\n" + "Join " + support()
                    + " for more information about Bot's Like me\n" + "Join " + channel() + " for Regular Updates");
        }

        else if(cmd.equalsIgnoreCase(getHandler()+"spam "+meow2[1]+" "+meow2[2]+" "+cmd.replace(getHandler()+"spam "+meow2[1]+" "+meow2[2]+" ", ""))) {
            try {
                for (int i = 0; i < Integer.parseInt(meow2[2]); i++) {
                    client.sendMessage(meow2[1]+"@s.whatsapp.net", cmd.replace(getHandler()+"spam "+meow2[1]+" "+meow2[2]+" ", ""));
                }
                sendMessage(update, "Send Messages Successfully\n\n⚠️Note : We don't Promote any Illegal activities the Owner will not be responsible for any harm to anybody via this Bot.\nThis is for Educational Purpose Only");
            } 
            catch(Exception e){
                sendMessage(update, "Kindly See the Format\n\nFormat for spam is :\n/spam <Target Number> <Number of Messages to be sent> <Text>\n\nExample : /spam 12345 10 hi");
            }
        }

        else if(cmd.equalsIgnoreCase(getHandler()+"info "+ cmd.replace(getHandler()+"info ", ""))) {
            try{
                long start = System.nanoTime();
                Message meow = sendMessage(update, "Getting Info...");
                String number = cmd.replace(getHandler()+"info ", "");
                if(number.startsWith("+")) {
                    number = number.replace("+", "");
                }
                BufferedImage bi = client.getChatPicture(number+"@s.whatsapp.net");
                File outputfile = new File("pfp.png");
                ImageIO.write(bi, "png", outputfile);
                SendPhoto sendPhoto = new SendPhoto(chatId(update), new InputFile(outputfile));
                String status = client.getStatus(number+"@s.whatsapp.net");
                DeleteMessage deleteMessage = new DeleteMessage(chatId(update), meow.getMessageId());
                execute(deleteMessage);
                long end = System.nanoTime();
                sendPhoto.setCaption(
                    "Information Fetched in "+(Math.pow(10, -9)*(end-start)+"").substring(0,3) +"s\n\n"
                    + "Status : " 
                    + status
                );
                execute(sendPhoto);
                outputfile.delete();
            }catch(Exception e) {
                sendMessage(update, "Kindly Check your Number");
            }
        }

        else if (cmd.equalsIgnoreCase(getHandler() + "help")) {
            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
            InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
            InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
            InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();

            inlineKeyboardButton1.setText("Send Message");
            inlineKeyboardButton1.setCallbackData("msg");

            inlineKeyboardButton2.setText("Get Status");
            inlineKeyboardButton2.setCallbackData("status");

            inlineKeyboardButton3.setText("Get Profile Pic");
            inlineKeyboardButton3.setCallbackData("pfp");

            inlineKeyboardButton4.setText("Get Chats");
            inlineKeyboardButton4.setCallbackData("gchats");

            List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
            List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
            List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
            List<InlineKeyboardButton> keyboardButtonsRow4 = new ArrayList<>();

            keyboardButtonsRow1.add(inlineKeyboardButton1);
            keyboardButtonsRow2.add(inlineKeyboardButton2);
            keyboardButtonsRow3.add(inlineKeyboardButton3);
            keyboardButtonsRow4.add(inlineKeyboardButton4);

            List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
            rowList.add(keyboardButtonsRow1);
            rowList.add(keyboardButtonsRow2);
            rowList.add(keyboardButtonsRow3);
            rowList.add(keyboardButtonsRow4);

            inlineKeyboardMarkup.setKeyboard(rowList);

            SendMessage sm = new SendMessage();
            sm.setReplyToMessageId(update.getMessage().getMessageId());
            sm.setChatId(chatId(update));
            sm.setText("Welcome to Help Section of WhatsXBot\n\n" + "Choose From the Below!");
            sm.setReplyMarkup(inlineKeyboardMarkup);

            try {
                execute(sm);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        else if (cmd.equalsIgnoreCase(getHandler() + "msg " + meow2[1] + " " +meow2[2])) {
            try {
                client.sendMessage(meow2[1] + "@s.whatsapp.net", meow2[2]);
                sendMessage(update, "Message Sent Successfully! 🥳");
            } catch (Exception e) {
                sendMessage(update, "Kindly Check the Number!");
            }
        }

    }

    public void sendRequest(WAClient client, Update update, String cmd) {

    }

    public String getHandler() {
        return configuration.handler;
    }

    public String chatId(Update update) {
        return update.getMessage().getChatId().toString();
    }

    public void editMessage(Update update, int mid, String text) {
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(chatId(update));
        editMessageText.setMessageId(mid);
        editMessageText.setText(text);

        try {
            execute(editMessageText);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public Message sendMessage(Update update, String text) {
        Message m;
        SendMessage sMessage = new SendMessage(chatId(update), text);
        sMessage.enableMarkdown(true);

        try {
            m = execute(sMessage);
            return m;
        } catch (TelegramApiException e) {
            e.getMessage();
        }

        return null;
    }

    public String support() {
        return "@HellionBotSupport";
    }

    public String channel() {
        return "@HellionBots";
    }

    @Override
    public String getBotUsername() {
        String s = "WhatsXBot";
        return s;
    }

    @Override
    public String getBotToken() {
        String s = "1893058621:AAE4OYku_jp4wjC1iXWnlRHJRIZRZkRYC4g";
        return s;
    }
}
