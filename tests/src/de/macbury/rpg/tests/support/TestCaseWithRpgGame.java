package de.macbury.rpg.tests.support;

import de.macbury.RPG;
import de.macbury.rpg.tests.GdxTestRunner;
import de.macbury.screen.AbstractScreen;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(GdxTestRunner.class)
public abstract class TestCaseWithRpgGame {
  protected RPG game;

  @Before
  public void configureRPGGame() {
    this.game = new RPG() {
      @Override
      protected void onGameCreate() {

      }
    };

    game.create();
  }

  @After
  public void disposeRPGGame() {
    game.dispose();
    game = null;
  }
}
