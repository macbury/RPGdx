package de.macbury.editor.manager;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuBar;
import com.kotcrab.vis.ui.widget.MenuItem;

/**
 * Creates main menu bar in editor
 */
public class MenuBarManager {
  private final MenuBar menuBar;

  public MenuBarManager() {
    menuBar = new MenuBar();
    createFileMenu();
    createEditMenu();
    createGameMenu();
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

    menu.addItem(createMenuItem("Tilesets", "F10"));
  }

  private void createFileMenu() {
    Menu menu = new Menu("File");
    menuBar.addMenu(menu);

    menu.addItem(createMenuItem("New Project...", "Ctrl+N"));
    menu.addItem(createMenuItem("Load Project...", "Ctrl+O"));
    menu.addItem(createMenuItem("Close Project", null));

    menu.addSeparator();

    menu.addItem(createMenuItem("Exit", "Esc"));
  }

  /**
   * Create new menu item
   * @param text
   * @return
   */
  private MenuItem createMenuItem(String text, String shortcut) {
    MenuItem menuItem = new MenuItem(text);
    menuItem.setShortcut(shortcut);
    return menuItem;
  }

  public Table getTable() {
    return menuBar.getTable();
  }
}
/*


private void createMenus () {
    Menu startTestMenu = new Menu("File");
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
 */