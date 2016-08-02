package de.macbury.editor.editor;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.kotcrab.vis.ui.VisUI;
import de.macbury.RPG;
import de.macbury.screen.BaseScreen;

/**
 * Main context for rpg editor with viszui
 */
public class RPGEditor extends RPG {

  @Override
  public void create() {
    VisUI.load(VisUI.SkinScale.X1);
    Gdx.app.setLogLevel(Application.LOG_DEBUG);
    super.create();
  }

  @Override
  public void dispose() {
    super.dispose();
    VisUI.dispose();
  }

  @Override
  public BaseScreen getInitialBaseScreen() {
    return new MainEditorScreen(this);
  }
}
