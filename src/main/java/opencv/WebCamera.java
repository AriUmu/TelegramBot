package opencv;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.image.BufferedImage;

public class WebCamera implements Runnable {

  private static final int INDEX = 0;
  private static boolean isRun = true;
  private static boolean isEnd = false;

  public void openCam() {
    CvUtils.init();
    JFrame window = setWindow();
    JLabel label = new JLabel();
    window.setContentPane(label);
    window.setVisible(true);
    VideoCapture camera = new VideoCapture(INDEX);
    if (!camera.isOpened()) {
      window.setTitle("Unable to open the camera");
      isRun = false;
      isEnd = true;
      return;
    }

    try {
      camera.set(Videoio.CAP_PROP_FRAME_WIDTH, 640);
      camera.set(Videoio.CAP_PROP_FRAME_HEIGHT, 480);

      Mat frame = new Mat();
      BufferedImage img = null;
      while (isRun) {
        if (camera.read(frame)) {
          img = CvUtils.MapToBufferedImage(frame);
          if (img != null) {
            ImageIcon imageIcon = new ImageIcon(img);
            label.setIcon(imageIcon);
            label.repaint();
            window.pack();
          }
          Utils.sleep(100);
        } else {
          System.out.println("Unable catch frame");
        }
      }
    } finally {
      camera.release();
      isRun = false;
      isEnd = true;
    }
    window.setTitle("Cam is closed");
  }


  private JFrame setWindow() {
    JFrame window = new JFrame("Watching video");
    window.setSize(640, 480);
    window.setLocationRelativeTo(null);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 27) {
          isRun = false;
        }
      }
    });
    return window;
  }

  @Override
  public void run() {
    new WebCamera().openCam();
  }
}
