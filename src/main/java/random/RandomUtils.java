package random;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomUtils {
    /**
     * randInt return random int in [start, end)
     * @param start
     * @param end
     * @return
     */
    public static int randInt(int start, int end) {
        if (end < start) {
            throw new IllegalArgumentException("end must bigger than start");
        }
        Random random = new Random(System.nanoTime());
        return (random.nextInt(end - start) + start);
    }

    /**
     * randInts return n different randmon int in [start, end)
     * @param start
     * @param end
     * @param n
     * @return
     */
    public static Set<Integer> randInts(int start, int end, int n) {
        if ((end - start) < n) {
            throw new IllegalArgumentException("difference between end and start must bigger than n");
        }

        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < n) {
            Random random = new Random(System.nanoTime());
            set.add(random.nextInt(end - start) + start);
        }
        return set;
    }

    /**
     * randFloat return random float in [start, end)
     * @param start
     * @param end
     * @return
     */
    public static float randFloat(float start, float end) {
        if (end < start) {
            throw new IllegalArgumentException("end must bigger than start");
        }
        Random random = new Random(System.nanoTime());
        return (random.nextFloat()*(end - start) + start);
    }

    /**
     * randFloats return n different randmon float in [start, end)
     * @param start
     * @param end
     * @param n
     * @return
     */
    public static Set<Float> randFloats(float start, float end, int n) {
        if (end < start) {
            throw new IllegalArgumentException("end must bigger than start");
        }

        Set<Float> set = new HashSet<Float>();
        while (set.size() < n) {
            Random random = new Random(System.nanoTime());
            set.add(random.nextFloat()*(end - start) + start);
        }
        return set;
    }

    /**
     * randDouble return random double in [start, end)
     * @param start
     * @param end
     * @return
     */
    public static double randDouble(double start, double end) {
        if (end < start) {
            throw new IllegalArgumentException("end must bigger than start");
        }
        Random random = new Random(System.nanoTime());
        return (random.nextDouble()*(end - start) + start);
    }

    /**
     * randDoubles return n different randmon double in [start, end)
     * @param start
     * @param end
     * @param n
     * @return
     */
    public static Set<Double> randDoubles(double start, double end, int n) {
        if (end < start) {
            throw new IllegalArgumentException("end must bigger than start");
        }

        Set<Double> set = new HashSet<Double>();
        while (set.size() < n) {
            Random random = new Random(System.nanoTime());
            set.add(random.nextDouble()*(end - start) + start);
        }
        return set;
    }
}
