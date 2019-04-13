import bot.StartBot;
import opencv.WebCamera;

public class Main {

  public static void main(String[] args) {
    new Thread(new StartBot()).start();
    new Thread(new WebCamera()).start();
  }
}
