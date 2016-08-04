package de.macbury.editor.core;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.kotcrab.vis.ui.VisUI;
import de.macbury.RPG;
import de.macbury.screen.AbstractScreen;

/**
 * Main context for rpg editor with viszui
 */
public class RPGEditor extends RPG {

  @Override
  protected void onGameCreate() {
    VisUI.load(VisUI.SkinScale.X1);
    Gdx.app.setLogLevel(Application.LOG_DEBUG);
    screens.set(new MainEditorScreen(this));
  }

  @Override
  public void dispose() {
    super.dispose();
    VisUI.dispose();
  }
}
