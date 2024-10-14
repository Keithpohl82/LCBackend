package com.launchcode.LCBackend;

import com.launchcode.LCBackend.service.DiscordBot;
import com.launchcode.LCBackend.service.SlackBot;
import org.junit.jupiter.api.Test;

public class SlackBotTest {

    @Test
    public void slack_post_message_test () {
        SlackBot.postMessage("This Slack message was sent from Java server");
    }
    @Test
    public void discord_post_message_test () {
        DiscordBot.postMessage("This Discord message was sent from Java server");
    }
}
