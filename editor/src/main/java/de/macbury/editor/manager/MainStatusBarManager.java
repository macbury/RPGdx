package de.macbury.editor.manager;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import de.macbury.editor.state.EditorState;

/**
 * Manage, update status bar at bottom of the main editor screen
 */
public class MainStatusBarManager implements ITableProvider {
  private final VisTable table;

  public MainStatusBarManager(EditorState state) {
    table = new VisTable(false);
    table.defaults().pad(4, 0, 4, 3);
    table.setBackground(VisUI.getSkin().getDrawable("button-over"));

    table.add().expand();
    table.add(new VisLabel("Status bar"));
  }

  @Override
  public Table getTable() {
    return table;
  }
}
