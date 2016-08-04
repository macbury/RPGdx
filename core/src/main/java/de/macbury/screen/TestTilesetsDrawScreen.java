package de.macbury.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import de.macbury.GameContext;
import de.macbury.graphics.GameCamera;

/**
 * Created by macbury on 03.08.16.
 */
public class TestTilesetsDrawScreen extends AbstractScreen {
  private GameCamera gameCamera;
  private SpriteBatch renderer;
  private Texture texture;
  protected Rectangle viewBounds;
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

    viewBounds = new Rectangle();
  }

  @Override
  public void resize(int width, int height) {
    gameCamera.resize(width, height);
  }

  @Override
  public void render() {
    Gdx.gl.glClearColor(0f, 0f, 0f, 1.0f);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    gameCamera.position.x += 3f * Gdx.graphics.getDeltaTime();
    gameCamera.position.y += 3f * Gdx.graphics.getDeltaTime();

    gameCamera.update();

    renderer.setProjectionMatrix(gameCamera.combined);
    float width = gameCamera.viewportWidth * gameCamera.zoom;
    float height = gameCamera.viewportHeight * gameCamera.zoom;
    viewBounds.set(gameCamera.position.x - width / 2, gameCamera.position.y - height / 2, width, height);

    int sx = (int)Math.floor(viewBounds.x);
    int sy = (int)Math.floor(viewBounds.y);

    int ex = (int) (Math.ceil(viewBounds.x) + viewBounds.width);
    int ey = (int) (Math.ceil(viewBounds.y) + viewBounds.height);

    renderer.begin(); {
      for (int x = sx; x <= ex; x++) {
        for (int y = sy; y <= ey; y++) {
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
