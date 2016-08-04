package de.macbury.editor.state.reducers;

import de.macbury.editor.state.EditorState;
import de.macbury.editor.state.actions.ChangeStateAction;

/**
 * Simple reducer implementation(like redux)
 */
public interface Reducer<State extends EditorState> {
  /**
   * Update state based on action
   * @param action
   * @param state
   * @return
   */
  void reduce(ChangeStateAction action, State state);
}
