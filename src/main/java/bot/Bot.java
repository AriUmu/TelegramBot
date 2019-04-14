package bot;

import org.apache.log4j.Logger;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.InputStream;
import java.util.Properties;

import static org.telegram.abilitybots.api.objects.Locality.ALL;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;

public class Bot extends AbilityBot {

  private static final Logger logger = Logger.getLogger(Bot.class);

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
            .name("hello")
            .info("hello bot")
            .locality(ALL)
            .privacy(PUBLIC)
            .action(ctx -> silent.send( ctx.user().getFirstName() + " you are pidor" + " !", ctx.chatId()))
            .build();
  }

  public Ability getPicture() {
    return Ability.builder()
            .name("pic")
            .info("give me pic")
            .locality(ALL)
            .privacy(PUBLIC)
            .action(ctx -> {
              try {
                execute(sendImageFromUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPe1sE9LqHWorWBpqs0aUrwyQldmaY2X-a4KEEpG2CcY6o6TQN", ctx.chatId()));
              } catch (TelegramApiException e) {
                e.printStackTrace();
              }
            })
            .build();
  }

  public SendPhoto sendImageFromUrl(String url, Long chatId) {
    SendPhoto sendPhotoRequest = new SendPhoto();
    sendPhotoRequest.setChatId(chatId);
    sendPhotoRequest.setPhoto(url);
    return sendPhotoRequest;
  }

  @Override
  public String getBotUsername() {
    return getProperty().getProperty("name");
  }

  @Override
  public String getBotToken() {
    return getProperty().getProperty("token");
  }


  private Properties getProperty() {
    Properties prop = new Properties();
    String propFileName = "app.properties";

    try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
    ) {
      if (inputStream != null) {
        prop.load(inputStream);
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return prop;
  }

}