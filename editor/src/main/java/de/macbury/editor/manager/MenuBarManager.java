package de.macbury.editor.manager;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuBar;
import com.kotcrab.vis.ui.widget.MenuItem;
import de.macbury.editor.state.EditorState;
import de.macbury.editor.state.StateChangeListener;
import de.macbury.editor.state.actions.OpenTilesetsEditorAction;

/**
 * Creates main menu bar in editor
 */
public class MenuBarManager implements ITableProvider, StateChangeListener {
  private final MenuBar menuBar;
  private final EditorState state;
  private MenuItem tilesetsEditorMenuItem;

  public MenuBarManager(EditorState state) {
    this.state = state;
    menuBar = new MenuBar();
    createGameMenu();
    createEditMenu();

    state.addListener(this);
  }

  private void createEditMenu() {
    Menu menu = new Menu("Edit");
    menuBar.addMenu(menu);

    menu.addItem(createMenuItem("Cut", "Ctrl+X"));
    menu.addItem(createMenuItem("Copy", "Ctrl+C"));
    menu.addItem(createMenuItem("Paste", "Ctrl+V"));
    menu.addItem(createMenuItem("Delete", "Delete"));
  }

  private void createGameMenu() {
    Menu menu = new Menu("Game");
    menuBar.addMenu(menu);

    menu.addItem(createMenuItem("Characters/Classes", "ctrl+1"));
    menu.addItem(createMenuItem("Items", "Ctrl+2"));
    menu.addItem(createMenuItem("Monsters", "Ctrl+3"));
    tilesetsEditorMenuItem = createMenuItem("Tilesets", "ctrl + 4");
    menu.addItem(tilesetsEditorMenuItem);
    tilesetsEditorMenuItem.addListener(new ChangeListener() {
      @Override
      public void changed(ChangeEvent event, Actor actor) {
        state.dispatch(new OpenTilesetsEditorAction());
      }
    });

    menu.addSeparator();
    menu.addItem(createMenuItem("Run", "F5"));
    menu.addSeparator();

    menu.addItem(createMenuItem("Exit", "Esc"));
  }

  /**
   * Create new menu item
   * @param text
   * @return
   */
  private MenuItem createMenuItem(String text, String shortcut) {
    final MenuItem menuItem = new MenuItem(text);
    //menuItem.setShortcut(shortcut);
    return menuItem;
  }
  @Override
  public Table getTable() {
    return menuBar.getTable();
  }

  @Override
  public void onStateChange(EditorState state) {

  }
}
