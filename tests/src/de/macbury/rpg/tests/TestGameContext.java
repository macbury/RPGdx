package de.macbury.rpg.tests;

import de.macbury.GameContext;
import de.macbury.RPG;
import de.macbury.screen.BaseScreen;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(GdxTestRunner.class)
public class TestGameContext {
  private RPG game;

  @Before
  public void configureRPGGame() {
    this.game = new RPG() {
      @Override
      public BaseScreen getInitialBaseScreen() {
        return null;
      }
    };
  }

  @After
  public void disposeRPGGame() {
    game.dispose();
    game = null;
  }

  @Test
  public void linkShouldCopyReferences() {
    GameContext gameContext = new GameContext(game) {};
    assertNotNull(gameContext.screens);
    assertEquals(game.screens, gameContext.screens);
  }
}
