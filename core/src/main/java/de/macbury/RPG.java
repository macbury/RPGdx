package de.macbury;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import de.macbury.assets.Assets;
import de.macbury.screen.AbstractScreen;
import de.macbury.screen.ScreenManager;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public abstract class RPG extends GameContext implements ApplicationListener {
  /**
   * Reference all managers here
   */
  public RPG() {
    this.screens = new ScreenManager(this);
    this.assets  = new Assets();
  }

  @Override
  public void create () {
    screens.link(this);

    onGameCreate();
  }

  /**
   * Initialize platform specific objects here
   */
  protected abstract void onGameCreate();

  @Override
  public void pause() {
    screens.pause();
  }

  @Override
  public void resume() {
    screens.resume();
  }

  @Override
  public void resize (int width, int height) {
    screens.resize(width, height);
  }

  @Override
  public void render () {
    screens.tick(Gdx.graphics.getDeltaTime());
  }

  @Override
  public void dispose () {
    screens.dispose();
  }
}