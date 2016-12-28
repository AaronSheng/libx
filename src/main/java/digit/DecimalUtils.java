package digit;

import java.math.BigDecimal;

public class DecimalUtils {
    public static float getFloat(float val, int round) {
        BigDecimal bigDecimal = new BigDecimal(val);
        return bigDecimal.setScale(round, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    public static double getDouble(double val, int round) {
        BigDecimal bigDecimal = new BigDecimal(val);
        return bigDecimal.setScale(round, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
