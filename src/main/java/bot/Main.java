package bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {

  private static final String BOT_NAME = "smart_guard_2019_bot";
  private static final String BOT_TOKEN = "857493034:AAFaXRDTcFiTe2XR5YtJBk2qAPXVIW72Xm4" /* your bot's token here */;

//  private static final String PROXY_HOST = "79.138.99.254";
//  private static final Integer PROXY_PORT = 8080;


  public static void main(String[] args) {
    try {

      ApiContextInitializer.init();

      TelegramBotsApi botsApi = new TelegramBotsApi();
//      DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
//      botOptions.setProxyHost(PROXY_HOST);
//      botOptions.setProxyPort(PROXY_PORT);
//      botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);
//      Bot bot = new Bot(BOT_TOKEN, BOT_NAME, botOptions);

      Bot bot = new Bot(BOT_TOKEN, BOT_NAME);

      botsApi.registerBot(bot);

    } catch (TelegramApiException e) {
      e.printStackTrace();
    }
  }
}
