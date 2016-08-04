package de.macbury.rpg.tests.support;

import de.macbury.GameContext;
import de.macbury.screen.AbstractScreen;

/**
 * Created by macbury on 02.08.16.
 */
public class DummyScreen extends AbstractScreen {
  /**
   * Automatic link to other context on creation
   *
   * @param otherContext
   */
  public DummyScreen(GameContext otherContext) {
    super(otherContext);
  }

  @Override
  public void preload() {

  }

  @Override
  public void show() {

  }

  @Override
  public void update(float delta) {

  }

  @Override
  public boolean isDisposedAfterHide() {
    return false;
  }

  @Override
  public void hide() {

  }

  @Override
  public void create() {

  }

  @Override
  public void resize(int width, int height) {

  }

  @Override
  public void render() {

  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }
}
