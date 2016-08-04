package de.macbury.rpg.tests;

import de.macbury.GameContext;
import de.macbury.rpg.tests.support.TestCaseWithRpgGame;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestGameContext extends TestCaseWithRpgGame {

  @Test
  public void linkShouldCopyReferencesAndUnlinkShouldRemoveThem() {
    GameContext gameContext = new GameContext(game) {};
    assertNotNull(gameContext.screens);
    assertNotNull(gameContext.assets);
    assertEquals(game.screens, gameContext.screens);
    assertEquals(game.assets, gameContext.assets);
    gameContext.unlink();
    assertNull(gameContext.screens);
    assertNull(gameContext.assets);
  }

}
