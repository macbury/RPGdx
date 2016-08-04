package de.macbury;

import com.badlogic.gdx.utils.Disposable;
import de.macbury.assets.Assets;
import de.macbury.screen.ScreenManager;

/**
 * Reference to all managers
 */
public abstract class GameContext implements Disposable {
  /**
   * Manage in game screens
   */
  public ScreenManager screens;
  public Assets assets;

  /**
   * Automatic link to other context on creation
   * @param otherContext
   */
  public GameContext(GameContext otherContext) {
    link(otherContext);
  }

  public GameContext() {

  }

  /**
   * Links references to current {@link GameContext}
   * @param context
   */

  public void link(GameContext context) {
    screens = context.screens;
    assets  = context.assets;
  }

  /**
   * Unlink references to current {@link GameContext}
   */
  public void unlink() {
    screens = null;
    assets  = null;
  }

  @Override
  public void dispose() {
    unlink();
  }
}
