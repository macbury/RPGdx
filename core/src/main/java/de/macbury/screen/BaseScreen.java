package de.macbury.screen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.utils.Disposable;
import de.macbury.GameContext;
import de.macbury.RPG;

/** <p>
 * Represents one of many application screens, such as a main menu, a settings menu, the game screen and so on.
 * </p>
 * <p>
 * Note that {@link #dispose()} is not called automatically.
 * </p>*/
public abstract class BaseScreen extends GameContext implements ApplicationListener, Disposable {
  private boolean created;
  /**
   * Automatic link to other context on creation
   *
   * @param otherContext
   */
  public BaseScreen(GameContext otherContext) {
    super(otherContext);
  }

  /**
   * Called before {@link BaseScreen#create()}. You can add assets to load here. If there are assets to load it shows loading screen
   */
  public abstract void preload();

  /** Called when this screen becomes the current screen for a Game. */
  public abstract void show ();

  /** Called when the screen should update itself.
   * @param delta The time in seconds since the last render. */
  public abstract void update(float delta);

  /**
   * If return true, after {@link BaseScreen#hide()} it will call {@link BaseScreen#dispose()}
   * @return
   */
  public abstract boolean isDisposedAfterHide();

  /**
   * This method is called before next screen will show
   */
  public abstract void hide ();

  public boolean isCreated() {
    return created;
  }

  public void setCreated(boolean created) {
    this.created = created;
  }
}
