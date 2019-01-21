package com.natsu.threaddemo.threadMethod.Character;

public class Hero {

  public String name;
  public float hp;
  public int damage;

  public Hero(String name, float hp, int damage) {
    this.name = name;
    this.hp = hp;
    this.damage = damage;
  }

  public void attackHero(Hero hero) {
    //把暂停时间去掉，多条线程各自会尽力去占有CPU资源
    //线程的优先级效果才可以看得出来
//        try {
//
//            Thread.sleep(0);
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
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
