package de.macbury.editor.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.widget.*;
import de.macbury.GameContext;
import de.macbury.editor.manager.MainStatusBarManager;
import de.macbury.editor.manager.MainToolBarManager;
import de.macbury.editor.manager.MenuBarManager;
import de.macbury.editor.manager.TilesetsEditorManager;
import de.macbury.editor.state.EditorState;
import de.macbury.screen.AbstractScreen;

/**
 * Screen on which is menu bar, map editor, tileset picker and map painter. All dialogs appear in this screen
 */
public class MainEditorScreen extends AbstractScreen {
  private static final float MAIN_SPLIT_PANEL_SPLIT_AMOUNT = 0.2f;
  private final EditorState state;
  private Stage stage;
  private MenuBarManager menuBarManger;
  private MainToolBarManager mainToolbarManager;
  private MainStatusBarManager statusBarManager;
  private TilesetsEditorManager tilesetsEditorManager;

  /**
   * Automatic link to other context on creation
   *
   * @param otherContext
   */
  public MainEditorScreen(GameContext otherContext) {
    super(otherContext);
    this.state = new EditorState();
  }

  @Override
  public void preload() {

  }

  @Override
  public void create() {
    menuBarManger      = new MenuBarManager(state);
    mainToolbarManager = new MainToolBarManager(state);
    statusBarManager   = new MainStatusBarManager(state);
    tilesetsEditorManager = new TilesetsEditorManager(state);

    stage = new Stage(new ScreenViewport());
    VisTable root = new VisTable();
    root.setFillParent(true);
    stage.addActor(root);

    // main menu
    root.add(menuBarManger.getTable()).growX().row();
    //main toolbar
    root.add(mainToolbarManager.getTable()).growX().row();

    VisSplitPane tileAndMapContainer = new VisSplitPane(new VisTable(), new VisTable(), true);
    VisTable mapEditorContainer  = new VisTable();

    /**
     * Configure main split panel that splits left panel with tile picker and maps, and map editor
     */
    VisSplitPane contentWithMapSplitPane = new VisSplitPane(tileAndMapContainer, mapEditorContainer, false);
    contentWithMapSplitPane.setMinSplitAmount(MAIN_SPLIT_PANEL_SPLIT_AMOUNT);
    contentWithMapSplitPane.setMaxSplitAmount(0.5f);
    contentWithMapSplitPane.setSplitAmount(MAIN_SPLIT_PANEL_SPLIT_AMOUNT);
    root.add(contentWithMapSplitPane).fill().expand().row();

    // status bar
    root.add(statusBarManager.getTable()).growX().row();

    stage.addActor(tilesetsEditorManager.getWindow());
  }

  @Override
  public void show() {
    Gdx.input.setInputProcessor(stage);
  }

  @Override
  public void update(float delta) {
    state.update();
    stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
  }

  @Override
  public boolean isDisposedAfterHide() {
    return true;
  }

  @Override
  public void hide() {
    Gdx.input.setInputProcessor(null);
  }

  @Override
  public void resize(int width, int height) {
    stage.getViewport().update(width, height, true);
  }

  @Override
  public void render() {
    Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    stage.draw();
  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void dispose() {
    stage.dispose();
    state.dispose();
    super.dispose();
  }
}
