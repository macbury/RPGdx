package de.macbury.rpg.tests;

import de.macbury.GameContext;
import de.macbury.RPG;
import de.macbury.rpg.tests.support.TestCaseWithRpgGame;
import de.macbury.screen.BaseScreen;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestGameContext extends TestCaseWithRpgGame {

  @Test
  public void linkShouldCopyReferencesAndUnlinkShouldRemoveThem() {
    GameContext gameContext = new GameContext(game) {};
    assertNotNull(gameContext.screens);
    assertEquals(game.screens, gameContext.screens);

    gameContext.unlink();
    assertNull(gameContext.screens);
  }

}
