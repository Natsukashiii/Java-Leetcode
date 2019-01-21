package com.natsu.threaddemo.threadCreate;

import com.natsu.threaddemo.threadCreate.Character.Hero;
import java.util.concurrent.Callable;

public class Kill_Callable implements Callable {

  private Hero hero1;
  private Hero hero2;

  public Kill_Callable(Hero hero1, Hero hero2) {
    this.hero1 = hero1;
    this.hero2 = hero2;
  }

  @Override
  public Object call() throws Exception {
    int attack_times = 0;
    while (!hero2.isDead()) {
      hero1.attackHero(hero2);
      attack_times++;
    }
    return attack_times;
  }
}
