package demo;

public class ExpRequest {

  private int base;
  private int exp;

  public ExpRequest() {

  }

  public ExpRequest(int base, int exp) {
    this.base = base;
    this.exp = exp;
  }

  public void setBase(int base) {
    this.base = base;
  }

  public int getBase() {
    return base;
  }

  public void setExp(int exp) {
    this.exp = exp;
  }

  public int getExp() {
    return exp;
  }
}
