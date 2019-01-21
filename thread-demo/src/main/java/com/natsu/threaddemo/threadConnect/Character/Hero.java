package com.natsu.threaddemo.threadConnect.Character;

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
  }

  // 回血
  public synchronized void recover() {
    hp = hp + 1;
  }

  // 掉血
  public synchronized void hurt() {
    hp = hp - 1;
  }

  public void attackHero(Hero hero) {
    hero.hp -= damage;
    System.out.format("%s is attacking %s, %s 'hp is %.0f%n", name, hero.name, hero.name, hero.hp);
    if (hero.isDead()) {
      System.out.println(hero.name + " is dead");
    }
  }

  public boolean isDead() {
    return 0 >= hp ? true : false;
  }
}
