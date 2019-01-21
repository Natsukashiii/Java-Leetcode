package com.natsu.threaddemo.threadCreate.Character;

public class Hero {

  public String name;
  public float hp;

  public int damage;

  public Hero(String name, float hp, int damage) {
    this.name = name;
    this.hp = hp;
    this.damage = damage;
  }

  public Hero(String name, float hp) {
    this.name = name;
    this.hp = hp;
    this.damage = damage;
  }

  public void attackHero(Hero h) {
    try {
      //为了表示攻击需要时间，每次攻击暂停1000毫秒
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    h.hp -= damage;
    System.out.format("%s is attacking %s, %s hp is %.0f%n", name, h.name, h.name, h.hp);

    if (h.isDead()) {
      System.out.println(h.name + " is dead!");
    }
  }

  public boolean isDead() {
    return 0 >= hp ? true : false;
  }
}
