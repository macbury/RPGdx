package de.macbury.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * This camera calculate viewport size to one side match {@link GameCamera#numberOfTilesForSmallerSide} and other side by aspect ratio
 */
public class GameCamera extends OrthographicCamera {
  private final int numberOfTilesForSmallerSide;

  public GameCamera(int numberOfTilesForSmallerSide ) {
    super();
    this.numberOfTilesForSmallerSide = numberOfTilesForSmallerSide;
    resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
  }

  /**
   * Recalculate viewport by screen size
   * @param width
   * @param height
   */
  public void resize(int width, int height) {
    float aspectRatio = (float)width / (float)height;
    float vWidth  = numberOfTilesForSmallerSide * aspectRatio;
    float vHeight =  numberOfTilesForSmallerSide;

    setToOrtho(false, vWidth, vHeight);
  }
}
