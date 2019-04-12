package bot;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.ApiContextInitializer;
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
      Properties property = getProperty();
      TelegramBotsApi botsApi = new TelegramBotsApi();
      Bot bot = new Bot(property.getProperty("token"), property.getProperty("name"));
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
