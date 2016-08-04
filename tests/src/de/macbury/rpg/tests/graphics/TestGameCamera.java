package de.macbury.rpg.tests.graphics;

import de.macbury.graphics.GameCamera;
import de.macbury.rpg.tests.GdxTestRunner;
import de.macbury.rpg.tests.support.TestCaseWithRpgGame;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class TestGameCamera extends TestCaseWithRpgGame {

  @Test
  public void ShouldCalculateProperViewportSize() {
    GameCamera gameCamera = new GameCamera(16);
    assertEquals("Viewport height", 16, gameCamera.viewportHeight, 0.0);
    assertEquals("Viewport height", 16, gameCamera.viewportHeight, 0.0);
  }
}
