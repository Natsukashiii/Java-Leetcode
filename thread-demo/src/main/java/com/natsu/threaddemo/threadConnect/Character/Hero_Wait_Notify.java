package com.natsu.threaddemo.threadConnect.Character;

public class Hero_Wait_Notify {

  public String name;
  public float hp;

  public int damage;

  public Hero_Wait_Notify(String name, float hp, int damage) {
    this.name = name;
    this.hp = hp;
    this.damage = damage;
  }

  public Hero_Wait_Notify(String name, float hp) {
    this.name = name;
    this.hp = hp;
  }

  //回血
  public synchronized void recover() {
    hp = hp + 1;
    System.out.printf("%s is recovering 1hp,，%s hp is %.0f%n", name, name, hp);
    // 通知那些等待在this对象上的线程，可以醒过来了，如第20行，等待着的减血线程，苏醒过来
    this.notify();
  }

  //掉血
  public synchronized void hurt() {
    if (hp == 1) {
      try {
        // 让占有this的减血线程，暂时释放对this的占有，并等待
        this.wait();
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    hp = hp - 1;
    System.out.printf("%s is hurting 1hp, %s hp is %.0f%n", name, name, hp);
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
