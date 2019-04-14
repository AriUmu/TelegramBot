package bot;

import org.apache.log4j.Logger;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.InputStream;
import java.util.Properties;

public class StartBot implements Runnable {

  private static final Logger logger = Logger.getLogger(StartBot.class);

  @Override
  public void run() {
    try {
      ApiContextInitializer.init();
      TelegramBotsApi botsApi = new TelegramBotsApi();
      AbilityBot bot = new Bot(getProperty().getProperty("token"), getProperty().getProperty("name"));
      botsApi.registerBot(bot);

    } catch (TelegramApiException e) {
      e.printStackTrace();
    }
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
