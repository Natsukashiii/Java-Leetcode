package com.natsu.threaddemo.threadConcurrency.Character;

public class Hero_Syn {

  public String name;
  public float hp;

  public int damage;

  public Hero_Syn(String name, float hp, int damage) {
    this.name = name;
    this.hp = hp;
    this.damage = damage;
  }

  public Hero_Syn(String name, float hp) {
    this.name = name;
    this.hp = hp;
  }

  //回血
  public void recover() {
    hp = hp + 1;
  }

  //掉血
  public void hurt() {
    //使用this作为同步对象
    synchronized (this) {
      hp = hp - 1;
    }
  }

  public void attackHero(Hero h) {
    h.hp -= damage;
    System.out.format("%s is attacking %s, %s's hp is %.0f%n", name, h.name, h.name, h.hp);
    if (h.isDead()) {
      System.out.println(h.name + "死了！");
    }
  }

  public boolean isDead() {
    return 0 >= hp ? true : false;
  }
}
