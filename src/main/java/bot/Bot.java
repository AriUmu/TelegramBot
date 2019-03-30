package bot;

import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.telegrambots.bots.DefaultBotOptions;

import static org.telegram.abilitybots.api.objects.Locality.ALL;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;

public class Bot extends AbilityBot {

  protected Bot(String botToken, String botUsername, DefaultBotOptions options) {
    super(botToken, botUsername, options);
  }

  public Bot(String botToken, String botUsername) {
    super(botToken, botUsername);
  }

  public int creatorId() {
    return 0;
  }

  public Ability hello() {
    return Ability.builder()
            .name("test")
            .info("hello bot")
            .locality(ALL)
            .privacy(PUBLIC)
            .action(ctx -> silent.send("hello!", ctx.chatId()))
            .build();
  }


  @Override
  public String getBotUsername() {
    return "smart_guard_2019_bot";
  }

  @Override
  public String getBotToken() {
    return "857493034:AAFaXRDTcFiTe2XR5YtJBk2qAPXVIW72Xm4";
  }
}