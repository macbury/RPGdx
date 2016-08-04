package de.macbury.desktop;

import de.macbury.RPG;
import de.macbury.screen.TestTilesetsDrawScreen;


public class DesktopRPG extends RPG {

  @Override
  protected void onGameCreate() {
    screens.set(new TestTilesetsDrawScreen(this));
  }
}
