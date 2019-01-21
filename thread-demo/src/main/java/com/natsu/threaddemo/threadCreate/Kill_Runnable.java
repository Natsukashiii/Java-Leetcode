package com.natsu.threaddemo.threadCreate;

import com.natsu.threaddemo.threadCreate.Character.Hero;

public class Kill_Runnable implements Runnable {

  private Hero hero1;
  private Hero hero2;

  public Kill_Runnable(Hero hero1, Hero hero2) {
    this.hero1 = hero1;
    this.hero2 = hero2;
  }

  @Override
  public void run() {
    while (!hero2.isDead()) {
      hero1.attackHero(hero2);
    }
  }
}
