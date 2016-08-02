package de.macbury.editor.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuBar;
import com.kotcrab.vis.ui.widget.MenuItem;
import de.macbury.GameContext;
import de.macbury.screen.BaseScreen;

/**
 * Screen on wich is menu bar, map editor, tileset picker and map painter. All dialogs appear in this screen
 */
public class MainEditorScreen extends BaseScreen {
  private MenuBar menuBar;
  private Stage stage;

  /**
   * Automatic link to other context on creation
   *
   * @param otherContext
   */
  public MainEditorScreen(GameContext otherContext) {
    super(otherContext);
  }

  @Override
  public void preload() {

  }

  @Override
  public void create() {
    stage = new Stage(new ScreenViewport());
    Table root = new Table();
    root.setFillParent(true);
    stage.addActor(root);

    menuBar = new MenuBar();
    root.add(menuBar.getTable()).growX().row();
    root.add().grow();

    createMenus();

    //stage.addActor(new TestWindow());
  }

  private void createMenus () {
    Menu startTestMenu = new Menu("start test");
    Menu fileMenu = new Menu("file");
    Menu editMenu = new Menu("edit");

    startTestMenu.addItem(new MenuItem("listview", new ChangeListener() {
        @Override
        public void changed (ChangeListener.ChangeEvent event, Actor actor) {
            //stage.addActor(new TestListView());
        }
    }));

    startTestMenu.addItem(new MenuItem("tabbed pane", new ChangeListener() {
        @Override
        public void changed (ChangeEvent event, Actor actor) {
            //stage.addActor(new TestTabbedPane());
        }
    }));

    startTestMenu.addItem(new MenuItem("collapsible", new ChangeListener() {
        @Override
        public void changed (ChangeEvent event, Actor actor) {
            //stage.addActor(new TestCollapsible());
        }
    }));

    //creating dummy menu items for showcase
    fileMenu.addItem(new MenuItem("menuitem #1"));
    fileMenu.addItem(new MenuItem("menuitem #2").setShortcut("f1"));
    fileMenu.addItem(new MenuItem("menuitem #3").setShortcut("f2"));

    editMenu.addItem(new MenuItem("menuitem #4"));
    editMenu.addItem(new MenuItem("menuitem #5"));
    editMenu.addSeparator();
    editMenu.addItem(new MenuItem("menuitem #6"));
    editMenu.addItem(new MenuItem("menuitem #7"));

    menuBar.addMenu(startTestMenu);
    menuBar.addMenu(fileMenu);
    menuBar.addMenu(editMenu);
  }

  @Override
  public void show() {
    Gdx.input.setInputProcessor(stage);
  }

  @Override
  public void update(float delta) {
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
    super.dispose();
  }
}
