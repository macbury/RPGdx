package de.macbury.editor.manager;

import com.kotcrab.vis.ui.widget.VisWindow;
import de.macbury.editor.state.EditorState;
import de.macbury.editor.state.StateChangeListener;
import de.macbury.editor.state.actions.ChangeStateAction;
import de.macbury.editor.state.actions.OpenTilesetsEditorAction;
import de.macbury.editor.state.reducers.Reducer;

/**
 * Handles displaying tileset editor window
 */
public class TilesetsEditorManager implements Reducer, StateChangeListener {
  public final static String KEY_TILESET_EDITOR_VISIBILITY = "TILESET_EDITOR_VISIBILITY";
  private final VisWindow window;

  public TilesetsEditorManager(EditorState state) {
    state.addReducer(this);
    state.addListener(this);

    this.window = new VisWindow("Tilesets editor");
    window.setModal(true);
    window.setWidth(800);
    window.setHeight(600);
  }

  public VisWindow getWindow() {
    return window;
  }

  @Override
  public void reduce(ChangeStateAction action, EditorState state) {
    if (OpenTilesetsEditorAction.class.isInstance(action)) {
      state.put(KEY_TILESET_EDITOR_VISIBILITY, true);
    }
  }

  @Override
  public void onStateChange(EditorState state) {
    boolean windowVisible = state.getBool(KEY_TILESET_EDITOR_VISIBILITY);
    window.setVisible(windowVisible);
    if (windowVisible) {
      window.centerWindow();
    }
  }
}
