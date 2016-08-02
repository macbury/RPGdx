package de.macbury;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import de.macbury.screen.BaseScreen;
import de.macbury.screen.ScreenManager;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public abstract class RPG extends GameContext implements ApplicationListener {
  //private MenuBar menuBar;
  //private Stage stage;

  /**
   * Reference all managers here
   */
  public RPG() {
    this.screens = new ScreenManager(this);
  }

  @Override
  public void create () {
    screens.set(getInitialBaseScreen());
  }

  /**
   * Create and return {@link BaseScreen} that you want to be first
   * @return
   */
  public abstract BaseScreen getInitialBaseScreen();

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