/*
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */

package com.raywenderlich.mcwenderlich

import com.developers.handlerthread.FoodOrder
import com.developers.handlerthread.OrderHandlerThread
import java.util.*


class FoodRunnable(private var orderHandlerThread: OrderHandlerThread) : Runnable {

    private var thread: Thread = Thread(this)
    private var alive: Boolean = false
    private var count: Int = 0
    private var size: Int = 0

    override fun run() {
        alive = true
        while (alive && count < size) {
            count++
            val foodName = getRandomOrderName()
            val foodPrice = getRandomOrderPrice()
            orderHandlerThread.sendOrder(FoodOrder(foodName, foodPrice))

            try {
                Thread.sleep(1000)
            } catch (exception: InterruptedException) {
                exception.printStackTrace()
            }

        }
    }

    fun setMaxOrders(size: Int) {
        this.size = size
    }

    fun start() {
        if (!thread.isAlive)
            thread.start()
    }

    fun stop() {
        alive = false
    }

    private fun getRandomOrderName(): String {
        val random = Random()
        val randomOrder = random.nextInt(10)
        when (randomOrder) {
            0 ->
                return "McBurger"
            1 ->
                return "McCola"
            2 ->
                return "McPizza"
            3 ->
                return "McIceCream"
            4 ->
                return "McNoodles"
            5 ->
                return "McBeer"
            6 ->
                return "McLime"
            7 ->
                return "McCoffee"
            8 ->
                return "McCake"
            9 ->
                return "McOrange"
            10 ->
                return "McFries"
        }

        return "McFood"
    }

    private fun getRandomOrderPrice(): Float {
        val random = Random()
        val randomOrder = random.nextInt(10)
        when (randomOrder) {
            0 ->
                return 5f
            1 ->
                return 10f
            2 ->
                return 15f
            3 ->
                return 20f
            4 ->
                return 25f
            5 ->
                return 30f
            6 ->
                return 35f
            7 ->
                return 40f
            8 ->
                return 45f
            9 ->
                return 50f
            10 ->
                return 55f
        }

        return 60f
    }

}