package pay;

/**
 * Created by aaron on 1/4/17.
 */
public class PayOrder {
    public Boolean existed;
    public Long totalFee;

    public PayOrder() {
    }

    public PayOrder(Boolean existed, Long totalFee) {
        this.existed = existed;
        this.totalFee = totalFee;
    }
}
