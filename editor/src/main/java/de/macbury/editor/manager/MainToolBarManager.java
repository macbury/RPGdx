package de.macbury.editor.manager;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import de.macbury.editor.state.EditorState;
import de.macbury.editor.state.StateChangeListener;

/**
 * Manages state of toolbar in main window. Initializes whole ui
 */
public class MainToolBarManager implements ITableProvider, StateChangeListener {
  private final VisTable table;

  public MainToolBarManager(EditorState state) {
    table = new VisTable(false);
    table.defaults().pad(4, 0, 4, 3);
    table.setBackground(VisUI.getSkin().getDrawable("button-over"));

    table.add(new VisLabel("Toolbar content")).expand().row();

    state.addListener(this);
  }

  @Override
  public Table getTable() {
    return table;
  }

  @Override
  public void onStateChange(EditorState state) {

  }
}
