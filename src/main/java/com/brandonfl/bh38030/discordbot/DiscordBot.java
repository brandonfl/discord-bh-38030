/*
 * MIT License
 *
 * Copyright (c) 2024 Fontany--Legall Brandon
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.brandonfl.bh38030.discordbot;

import static net.dv8tion.jda.api.interactions.commands.OptionType.ATTACHMENT;

import com.brandonfl.bh38030.config.BotProperties;
import java.util.EnumSet;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DiscordBot extends ListenerAdapter {

  public final BotProperties botProperties;

  @PostConstruct
  public void startBot() {
    JDA jda = JDABuilder.createLight(botProperties.getSetting().getToken(), EnumSet.noneOf(GatewayIntent.class))
        .addEventListeners(this)
        .setActivity(Activity.customStatus("testing"))
        .build();

    CommandListUpdateAction commands = jda.updateCommands();

    commands.addCommands(
        Commands.slash("test", "test.")
            .addOptions(new OptionData(ATTACHMENT, "screenshot", "screenshot")
                .setRequired(true))
            .setGuildOnly(true)
    );

    commands.queue();
  }


  @Override
  public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
    if (event.getGuild() == null)
      return;
    switch (event.getName()) {
      case "test":
        test(event);
        break;
      default:
        event.reply("I can't handle that command right now :(").setEphemeral(true).queue();
    }
  }

  public void test(SlashCommandInteractionEvent event) {
    event.deferReply(true).queue();
    InteractionHook hook = event.getHook();
    hook.setEphemeral(true);
    hook.sendMessage("OK").queue();
  }
}