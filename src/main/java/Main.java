import bot.StartBot;

public class Main {

  public static void main(String[] args) {
    new Thread(new StartBot()).start();
  }
}