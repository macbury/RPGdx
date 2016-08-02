package de.macbury.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;
import de.macbury.GameContext;
import de.macbury.RPG;

import java.util.Stack;

/**
 * Manages in game screenStack.
 */
public class ScreenManager extends GameContext implements Disposable {
  private static final String TAG = "ScreenManager";
  /**
   * List of screenStack
   */
  private Stack<BaseScreen> screenStack;

  public ScreenManager(RPG rpg) {
    super(rpg);
    Gdx.app.debug(TAG, "Initialized");
    screenStack = new Stack<BaseScreen>();
  }

  /**
   * Reference to current screen
   * @return
   */
  public BaseScreen getCurrent() {
    if (screenStack.empty()) {
      return null;
    }
    return screenStack.peek();
  }

  /**
   * Hides screen, unlinks it dependency, and dispose if {@link BaseScreen#isDisposedAfterHide()} is true
   * @param screen
   */
  private void hide(BaseScreen screen) {
    screen.hide();
    if (screen.isDisposedAfterHide()) {
      screen.dispose();
      screenStack.remove(screen);
      Gdx.app.debug(TAG, "Hided and disposed screen: " + screen.toString());
    } else {
      Gdx.app.debug(TAG, "Hide " + screen.toString());
    }
    screen.unlink();
  }

  /**
   * Removes screen from stack and replace it with nextScreen
   * @param nextScreen
   */
  public void set(BaseScreen nextScreen) {
    pop();
    push(nextScreen);
  }

  /**
   * Removes current screen from stack
   * @return current screen or null
   */
  public BaseScreen pop() {
    BaseScreen currentScreen = getCurrent();
    if (currentScreen != null) {
      Gdx.app.debug(TAG, "Removed old screen from stack " + currentScreen.toString());
      hide(screenStack.pop());
    }
    return currentScreen;
  }

  /**
   * Adds next screen to stack, and hides old one
   * @param nextScreen
   */
  public void push(BaseScreen nextScreen) {
    if (getCurrent() != null) {
      hide(getCurrent());
    }
    nextScreen.link(this);
    screenStack.push(nextScreen);
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
    while (!screenStack.empty()) {
      screenStack.pop().dispose();
    }
    super.dispose();
  }
}
