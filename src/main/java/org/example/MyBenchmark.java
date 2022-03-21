/*
 * Copyright (c) 2005, 2014, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package org.example;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

public class MyBenchmark {

    static public String longestCommonSubstring(String first, String second) {
        int[][] matrix = new int[first.length()][second.length()];
        StringBuilder answer = new StringBuilder();
        int max = 0;
        int maxI = 0;
        int diagonal;
        for (int i = 0; i < first.length(); i++) {
            for (int j = 0; j < second.length(); j++) {
                if (first.charAt(i) == second.charAt(j)) {
                    if ((i - 1 >= 0) & (j - 1 >= 0)) {
                        diagonal = matrix[i - 1][j - 1] + 1;
                    } else {
                        diagonal = 1;
                    }

                    if (diagonal > max) {
                        max = diagonal;
                        maxI = i;
                    }

                    matrix[i][j] = diagonal;
                }

            }
        }

        while (max > 0) {
            answer.append(first.charAt(maxI));
            maxI--;
            max--;
        }

        return answer.reverse().toString();
    }

    static public String longestCommonSubstring1(String first, String second) {
        StringBuilder answer = new StringBuilder();
        int max = 0;
        int maxI = 0;
        for (int i = 0; i <= first.length() - 1; i++) {
            for (int j = 0; j <= second.length() - 1; j++) {
                if (first.charAt(i) == second.charAt(j)) {
                    int ii = i;
                    int jj = j;
                    int length = 0;
                    while ((ii < first.length()) && (jj < second.length()) && (first.charAt(ii) == second.charAt(jj))) {
                        length++;
                        ii++;
                        jj++;
                    }

                    if (length > max) {
                        max = length;
                        maxI = i;
                    }
                }
            }
        }

        while (max > 0) {
            answer.append(first.charAt(maxI));
            maxI++;
            max--;
        }


        return answer.toString();
    }


    @State(Scope.Thread)
    public static class MyState {

        @Setup(Level.Iteration)
        public void doSetup() {
            StringBuilder first1 = new StringBuilder();
            StringBuilder second1 = new StringBuilder();

//            Первый случай
//            for (int i = 0; i < 1000; i++) {
//                int f = 0 + (int) (Math.random() * ((30000 - 0) + 1));
//                int s = 30001 + (int) (Math.random() * ((60000 - 30001) + 1));
//                char c = 0;
//                char c1 = 0;
//                c += f;
//                c1 += s;
//                first1.append(c);
//                second1.append(c1);
//            }

//            Второй случай
//            for (int i = 0; i < 2000; i++) {
//                int f = 0 + (int) (Math.random() * ((30000 - 0) + 1));
//                int s = 0 + (int) (Math.random() * ((30000 - 0) + 1));
//                char c = 0;
//                char c1 = 0;
//                c += f;
//                c1 += s;
//                first1.append(c);
//                second1.append(c1);
//            }
//
//            for (int i = 0; i < 30; i++) {
//                int f = 0 + (int) (Math.random() * ((30001 - 0) + 1));
//                char c = 0;
//                c += f;
//                first1.append(c);
//                second1.append(c);
//            }
//
//            for (int i = 0; i < 2000; i++) {
//                int f = 0 + (int) (Math.random() * ((30000 - 0) + 1));
//                int s = 0 + (int) (Math.random() * ((30000 - 0) + 1));
//                char c = 0;
//                char c1 = 0;
//                c += f;
//                c1 += s;
//                first1.append(c);
//                second1.append(c1);
//            }

//            Третий случай
//            for (int i = 0; i < 100; i++) {
//                int f = 0 + (int) (Math.random() * ((100 - 0) + 1));
//                char c = 0;
//                c += f;
//                first1.append(c);
//            }
//
//            for (int i = 0; i < 10; i++) {
//                int f = 0 + (int) (Math.random() * ((100 - 0) + 1));
//                char c = 0;
//                c += f;
//                first1.append(c);
//                second1.append(c);
//            }
//
//            for (int i = 0; i < 100; i++) {
//                int f = 0 + (int) (Math.random() * ((100 - 0) + 1));
//                char c = 0;
//                c += f;
//                first1.append(c);
//            }

            first = first1.toString();
            second = second1.toString();
        }


        String first;
        String second;

    }

    @Benchmark
    @Fork(1)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void testMethod(MyState str) {
        // place your benchmarked code here\
        String s = longestCommonSubstring(str.first, str.second);
    }

}
