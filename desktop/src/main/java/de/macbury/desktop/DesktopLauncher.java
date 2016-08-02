package de.macbury.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.macbury.RPG;

/** Launches the desktop (LWJGL) application. */
public class DesktopLauncher {
  public static void main(String[] args) {
    createApplication();
  }

  private static LwjglApplication createApplication() {
    return new LwjglApplication(new RPG(), getDefaultConfiguration());
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