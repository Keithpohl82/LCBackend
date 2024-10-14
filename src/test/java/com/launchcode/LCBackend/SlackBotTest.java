package com.launchcode.LCBackend;

import com.launchcode.LCBackend.service.SlackBot;
import org.junit.jupiter.api.Test;

public class SlackBotTest {

    @Test
    public void slack_post_message_test () {
        SlackBot.postMessage("This message was sent from Java server");
    }
}
