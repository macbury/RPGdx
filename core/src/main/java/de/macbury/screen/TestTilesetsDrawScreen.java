package de.macbury.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.macbury.GameContext;
import de.macbury.graphics.GameCamera;

/**
 * Created by macbury on 03.08.16.
 */
public class TestTilesetsDrawScreen extends AbstractScreen {
  private GameCamera gameCamera;
  private SpriteBatch renderer;
  private Texture texture;

  /**
   * Automatic link to other context on creation
   *
   * @param otherContext
   */
  public TestTilesetsDrawScreen(GameContext otherContext) {
    super(otherContext);
  }

  @Override
  public void preload() {
    assets.load("textures:grass.png", Texture.class);
  }

  @Override
  public void create() {
    this.gameCamera = new GameCamera(16);
    this.renderer = new SpriteBatch();
    this.texture  = assets.get("textures:grass.png");

    gameCamera.position.set(10, 5, 0);
    gameCamera.update(true);
  }

  @Override
  public void resize(int width, int height) {
    gameCamera.resize(width, height);
  }

  @Override
  public void render() {
    Gdx.gl.glClearColor(0f, 0f, 0f, 1.0f);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    gameCamera.update();
    renderer.setProjectionMatrix(gameCamera.combined);
    renderer.begin(); {
      for (int x = 0; x < 10; x++) {
        for (int y = 0; y < 10; y++) {
          renderer.draw(texture, x,y, 1, 1);
        }
      }
    } renderer.end();
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
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void dispose() {
    renderer.dispose();
    assets.unload("textures:grass.png");
    super.dispose();
  }
}
