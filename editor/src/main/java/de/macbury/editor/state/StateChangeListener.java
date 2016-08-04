package de.macbury.editor.state;

public interface StateChangeListener {
  /**
   * State of the editor did change. Update ui here(do not modify state here!)
   * @param state
   */
  public void onStateChange(EditorState state);
}
