package lab07;

public class BetterRandomNumberGenerator implements RandomNumberGenerator{
    private long a = 75;
    private  long c = 74;
    private long m = (long) (Math.pow(2,16) + 1);
    private long seed;
    /**
     * Generates and returns a random integer between [0,max).
     *
     * @param max - the upper bound for the range of the random number, non-inclusive
     * @return an integer between [0, max)
     */
    @Override
    public int nextInt(int max) {
        this.seed = (int) (((this.a * this.seed) + this.c) % this.m);
        return (int) this.seed % max;
    }

    /**
     * Sets a seed for the random number generator. A random number generator should
     * return the same sequence of numbers for the same seed.
     *
     * @param seed - the seed parameter used to initialize the random number generator.
     */
    @Override
    public void setSeed(long seed) {
        this.seed = seed;
    }

    /**
     * Sets two constants for use with the random generator, such as a linear congruential
     * generator (see https://en.wikipedia.org/wiki/Linear_congruential_generator).
     * If your generator does not use such constants, you are free to ignore the input from
     * this method.
     *
     * @param const1 - (long) a value that will be used in a linear congruential generator
     * @param const2 - (long) c value that will be used in a linear congruential generator
     */
    @Override
    public void setConstants(long const1, long const2) {
        this.a = const1;
        this.c = const2;

    }
}
