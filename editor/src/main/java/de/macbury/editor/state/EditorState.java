package de.macbury.editor.state;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;
import de.macbury.editor.state.actions.ChangeStateAction;
import de.macbury.editor.state.reducers.Reducer;

/**
 * Editor state. Updating any value in this object will trigger in next frame {@link StateChangeListener}
 */
public class EditorState implements Disposable {
  private final ObjectMap<String, Object> content;
  private final Array<StateChangeListener> listeners;
  private final Array<Reducer> reducers;
  private final Array<ChangeStateAction> actions;
  private boolean dirty;

  public EditorState() {
    this.content = new ObjectMap<String, Object>();
    this.listeners = new Array<StateChangeListener>();
    dirty = true;
    reducers = new Array<Reducer>();
    actions  = new Array<ChangeStateAction>();
  }

  /**
   * Check if there is any value for passed key or if key exists
   * @param key
   * @return
   */
  public boolean has(String key) {
    return content.containsKey(key) && content.get(key) != null;
  }

  /**
   * Return value for passed key
   * @param key
   * @return
   */
  public Object get(String key) {
    return content.get(key);
  }

  /**
   * Saves object into state, and marks it to trigger {@link StateChangeListener} in next frame
   * @param key
   * @param value
   */
  public void put(String key, Object value) {
    content.put(key, value);
    dirty = true;
  }

  /**
   * Dispatch action. It will be triggered on next update
   * @param action
   */
  public void dispatch(ChangeStateAction action) {
    actions.add(action);
  }

  /**
   * Add new reducer to state
   * @param reducer
   */
  public void addReducer(Reducer reducer) {
    if (!reducers.contains(reducer, true)) {
      reducers.add(reducer);
      dirty = true;
    }
  }

  /**
   * Adds listener to list
   * @param listener
   */
  public void addListener(StateChangeListener listener) {
    if (!listeners.contains(listener, true)) {
      listeners.add(listener);
    }
    dirty = true;
  }

  /**
   * Removes listener from list.
   * @param listener
   */
  public void removeListener(StateChangeListener listener) {
    listeners.removeValue(listener, true);
  }

  /**
   * This method should be executed on beginning of each frame. If state was changed it will trigger all listeners
   */
  public void update() {
    while(actions.size > 0) {
      ChangeStateAction action = actions.removeIndex(0);
      for (Reducer reducer: reducers) {
        reducer.reduce(action, this);
      }
    }
    if (dirty) {
      for (int i = 0; i < listeners.size; i++) {
        listeners.get(i).onStateChange(this);
      }
      dirty = false;
    }
  }

  @Override
  public void dispose() {
    content.clear();
    listeners.clear();
    reducers.clear();
    actions.clear();
  }

  /**
   * Return bool state value
   * @param key
   * @return
   */
  public boolean getBool(String key) {
    return (Boolean)content.get(key, false);
  }
}
