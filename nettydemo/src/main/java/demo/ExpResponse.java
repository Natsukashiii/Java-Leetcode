package demo;

/**
 * 指数RPC的输出
 */
public class ExpResponse {

  private long value;
  private long costInNanos;

  public ExpResponse() {

  }

  public ExpResponse(long value, long costInNanos) {
    this.value = value;
    this.costInNanos = costInNanos;
  }

  public long getCostInNanos() {
    return costInNanos;
  }

  public long getValue() {
    return value;
  }

  public void setCostInNanos(long costInNanos) {
    this.costInNanos = costInNanos;
  }

  public void setValue(long value) {
    this.value = value;
  }
}
