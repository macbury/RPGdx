package de.macbury.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;
import de.macbury.GameContext;
import de.macbury.RPG;

import java.util.Stack;

/**
 * Manages in game screens.
 */
public class ScreenManager extends GameContext implements Disposable {
  private static final String TAG = "ScreenManager";
  /**
   * List of screens
   */
  private Stack<BaseScreen> screens;

  public ScreenManager(RPG rpg) {
    super(rpg);
    screens = new Stack<BaseScreen>();
  }

  /**
   * Reference to current screen
   * @return
   */
  public BaseScreen getCurrent() {
    if (screens.empty()) {
      return null;
    }
    return screens.peek();
  }

  /**
   * Hides screen, unlinks it dependency, and dispose if {@link BaseScreen#isDisposedAfterHide()} is true
   * @param screen
   */
  private void hide(BaseScreen screen) {
    screen.hide();
    if (screen.isDisposedAfterHide()) {
      screen.dispose();
      screens.remove(screen);
    }
    screen.unlink();
  }

  /**
   * Removes screen from stack and replace it with nextScreen
   * @param nextScreen
   */
  public void set(BaseScreen nextScreen) {
    BaseScreen currentScreen = getCurrent();
    if (currentScreen != null) {
      hide(screens.pop());
    }

    push(nextScreen);
  }

  /**
   * Adds next screen to stack
   * @param nextScreen
   */
  public void push(BaseScreen nextScreen) {
    if (getCurrent() != null) {
      hide(getCurrent());
    }
    nextScreen.link(this);
    screens.push(nextScreen);
    nextScreen.preload();
    nextScreen.show();
    nextScreen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
  }


  /**
   * Update size of current screen
   * @param width
   * @param height
   */
  public void resize(int width, int height) {
    if (haveCurrentScreen()) {
      getCurrent().resize(width, height);
    }
  }

  /**
   * Update and render current screen
   * @param delta
   */
  public void tick(float delta) {
    if (haveCurrentScreen()) {
      BaseScreen screen = getCurrent();
      screen.update(delta);
      screen.render();
    }
  }

  private boolean haveCurrentScreen() {
    return getCurrent() != null;
  }

  /**
   * Pause current screen
   */
  public void pause() {
    if (haveCurrentScreen()) {
      getCurrent().pause();
    }
  }

  /**
   * Resume current screen
   */
  public void resume() {
    if (haveCurrentScreen()) {
      getCurrent().resume();
    }
  }

  @Override
  public void dispose() {
    while (!screens.empty()) {
      screens.pop().dispose();
    }
    super.dispose();
  }
}
