package de.macbury.rpg.tests.screen;

import de.macbury.rpg.tests.support.DummyScreen;
import de.macbury.rpg.tests.support.TestCaseWithRpgGame;
import de.macbury.screen.AbstractScreen;
import de.macbury.screen.ScreenManager;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;


public class TestScreenManager extends TestCaseWithRpgGame {
  @Test
  public void setShouldPreloadAndCreateScreen() {
    AbstractScreen screen = mock(AbstractScreen.class);

    assertNull(game.screens.getCurrent());
    game.screens.set(screen);
    assertEquals(screen, game.screens.getCurrent());


    verify(screen).preload();
    verify(screen).create();
    verify(screen).link(game.screens);
  }

  @Test
  public void pushingScreenShouldAlwaysCreateScreenOnce() {
    AbstractScreen screen = spy(new DummyScreen(game));
    game.screens.push(screen);
    game.screens.pop();
    game.screens.push(screen);

    verify(screen, times(1)).create();
    verify(screen, times(1)).preload();
  }

  @Test
  public void disposeScreenOnPopIfMarkedToDispose() {
    AbstractScreen screen = spy(new DummyScreen(game));
    when(screen.isDisposedAfterHide()).thenReturn(true);
    game.screens.push(screen);
    game.screens.pop();

    verify(screen, times(1)).dispose();
  }

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Test
  public void throwExceptionOnAddingTheSameScreen() {
    exception.expect(ScreenManager.Exception.class);
    AbstractScreen screen = new DummyScreen(game);

    game.screens.push(screen);
    game.screens.push(screen);
  }

  @Test
  public void throwExceptionIfTriesToAddNull() {
    exception.expect(ScreenManager.Exception.class);
    game.screens.push(null);
  }

  @Test
  public void popScreenShouldUnlinkScreen() {
    AbstractScreen screen = mock(AbstractScreen.class);

    game.screens.set(screen);

    AbstractScreen popedScreen = game.screens.pop();
    assertEquals(screen, popedScreen);

    verify(screen).unlink();
    verify(screen).hide();
  }
}
