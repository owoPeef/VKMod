package ru.owopeef.urls;

import ru.owopeef.urls.methods.Messages;
import ru.owopeef.vkinminecraft.Config;

public class DialogueInitialization {
    public static void Init()
    {
        Config.TITLES.clear();
        Config.MESSAGES.clear();
        Messages.getDialogs(5, 0);
    }
}
