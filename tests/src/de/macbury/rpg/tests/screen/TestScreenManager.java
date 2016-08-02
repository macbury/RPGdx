package de.macbury.rpg.tests.screen;

import de.macbury.rpg.tests.GdxTestRunner;
import de.macbury.rpg.tests.support.DummyScreen;
import de.macbury.rpg.tests.support.TestCaseWithRpgGame;
import de.macbury.screen.BaseScreen;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;


public class TestScreenManager extends TestCaseWithRpgGame {
  @Test
  public void setShouldPreloadAndCreateScreen() {
    BaseScreen screen = mock(BaseScreen.class);

    assertNull(game.screens.getCurrent());
    game.screens.set(screen);
    assertEquals(screen, game.screens.getCurrent());


    verify(screen).preload();
    verify(screen).create();
    verify(screen).link(game.screens);
  }

  @Test
  public void pushingScreenShouldAlwaysCreateScreenOnce() {
    BaseScreen screen = spy(new DummyScreen(game));
    game.screens.push(screen);
    game.screens.pop();
    game.screens.push(screen);

    verify(screen, times(1)).create();
    verify(screen, times(1)).preload();
  }

  @Test
  public void popScreenShouldUnlinkScreen() {
    BaseScreen screen = mock(BaseScreen.class);

    game.screens.set(screen);

    BaseScreen popedScreen = game.screens.pop();
    assertEquals(screen, popedScreen);

    verify(screen).unlink();
    verify(screen).hide();
  }
}
