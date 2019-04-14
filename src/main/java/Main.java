import bot.InstantBot;

public class Main {

  public static void main(String[] args) {

    System.getProperties().put( "proxySet", "true" );
    System.getProperties().put( "socksProxyHost", "127.0.0.1" );
    System.getProperties().put( "socksProxyPort", "9150" );

    new Thread(new InstantBot()).start();
   //new Thread(new WebCamera()).start();
  }
}
