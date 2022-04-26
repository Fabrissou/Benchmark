package org.example;

import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MyBenchmark {

    static Random r = new Random(Calendar.getInstance().getTimeInMillis());
    @State(Scope.Thread)
    public static class MyState {

        @Setup(Level.Iteration)
        public void doSetup() {
            for (int i = 0; i < 5000; i++) {
                elements[i] = (double) (r.nextInt(773) - 273);
            }
        }

        public Double[] elements = new Double[5000];
    }

    @Benchmark
    @Fork(1) @BenchmarkMode(Mode.AverageTime) @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void testMethod(MyState a) {
        Data.Companion.sortTemp2(Arrays.asList(a.elements));
    }

}