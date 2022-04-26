package org.example

public class Data {
    companion object {
        fun sortTemperatures(list: MutableList<Double>) {
//Трудоемкость:O(n*log(n))
//Ресурсоемкость:O(n)
            return list.sort()
        }

        fun sortTemp(list: MutableList<Double>): MutableList<Double> {
//Трудоемкость:O(n)
//Ресурсоемкость:O(n)
            val negativeNumber = IntArray(2732)
            val positiveNumber = IntArray(5002)
            val listAnswer = mutableListOf<Double>()
            for (i in list) {
                val element = (i * 10.0).toInt()
                if (element >= 0) {
                    positiveNumber[element]++
                } else negativeNumber[element * -1]++
            }
            for (i in 2731 downTo 0) {
                var check = negativeNumber[i]
                while (check > 0) {
                    listAnswer.add(i / 10.0)
                    check--
                }
            }
            for (i in 0..5001) {
                var check = positiveNumber[i]
                while (check > 0) {
                    listAnswer.add(i / 10.0)
                    check--
                }
            }
            return listAnswer
        }

        fun sortTemp2(list: MutableList<Double>) {
            var sorted = false
            while (!sorted) {
                sorted = true
                for (i in 1 until list.size) {
                    val previous = list[i - 1]
                    val current = list[i]
                    if (previous > current) {
                        val buffer = list[i - 1]
                        list[i - 1] = list[i]
                        list[i] = buffer
                        sorted = false
                    }
                }
            }
        }
    }

}