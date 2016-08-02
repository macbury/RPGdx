package de.macbury.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.macbury.RPG;
import de.macbury.screen.BaseScreen;
import de.macbury.screen.TestScreen;

/** Launches the desktop (LWJGL) application. */
public class DesktopLauncher {
  public static void main(String[] args) {
    createApplication();
  }

  private static LwjglApplication createApplication() {
    RPG game = new RPG() {
      @Override
      public BaseScreen getInitialBaseScreen() {
        return new TestScreen(this);
      }
    };
    return new LwjglApplication(game, getDefaultConfiguration());
  }

  private static LwjglApplicationConfiguration getDefaultConfiguration() {
    LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
    configuration.title = "RPGdx";
    configuration.width = 1368;
    configuration.height = 768;
    configuration.vSyncEnabled = true;
    return configuration;
  }
}