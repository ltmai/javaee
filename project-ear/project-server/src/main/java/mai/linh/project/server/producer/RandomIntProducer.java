package mai.linh.project.server.producer;

import java.util.Random;

import javax.enterprise.inject.Produces;

/**
 * RandomProducer: injects object whose value may vary at run-time
 */
public class RandomIntProducer {

    private static final int RANDOM_BOUND = 999;
    private Random random = new Random(System.currentTimeMillis());

    @Produces
    @RandomInt
    int next() {
        return random.nextInt(RANDOM_BOUND);
    }
}