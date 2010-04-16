package org.nicerobot.lobo;

import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;

import org.lobobrowser.gui.BrowserPanel;
import org.lobobrowser.main.PlatformInit;

@SuppressWarnings ("serial")
public class Browser extends JFrame {

  public static void main (final String[] args) throws Exception {
    // Initialize logging.
    PlatformInit.getInstance().initLogging(false);

    // This step is necessary for extensions to work:
    PlatformInit.getInstance().init(false, false);

    // Create frame with a specific size.
    final JFrame frame = new Browser();
    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    frame.setSize(600, 400);
    frame.setVisible(true);
  }

  public Browser () throws Exception {
    this.setTitle("Lobo Demo");
    final BrowserPanel bp = new BrowserPanel();
    bp.setBorder(BorderFactory.createTitledBorder(new EtchedBorder(EtchedBorder.RAISED),
                                                  "Embedded browser"));
    bp.navigate("http://lobobrowser.org/browser/home.jsp");
    this.getContentPane().add(bp);

    this.addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing (final WindowEvent e) {
        // This needs to be called in order
        // to inform extensions about the
        // window closing.
        bp.windowClosing();
      }
    });
  }
}
