package de.macbury.assets;

import com.badlogic.gdx.assets.AssetManager;

/**
 * Loads and stores assets like textures, bitmapfonts, tile maps, sounds, music and so on.
 * Automaticaly map asssets to path
 * */
public class Assets extends AssetManager {

  public Assets() {
    super(new EngineFileHandleResolver());
  }
}
