package com.hellionbots.Helpers;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvBuilder;

public class configuration {
    private static final Dotenv dotenv = new DotenvBuilder().ignoreIfMissing().load();

    public static String botToken = dotenv.get("botToken");
    public static String botUserName = dotenv.get("botUserName");
    public static String handler = dotenv.get("handler");
    //public static String conn = dotenv.get("MONGODB_URI");
}
