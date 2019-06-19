package com.developers.handlerthread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.mcwenderlich.FoodRunnable
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference


class MainActivity : AppCompatActivity() {


    private lateinit var foodRunnable: FoodRunnable
    private lateinit var orderHandlerThread: OrderHandlerThread
    private lateinit var foodListAdapter: FoodListAdapter
    private lateinit var uiHandler: UiHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        foodListAdapter = FoodListAdapter(mutableListOf(), applicationContext)
        orderRecyclerView.layoutManager = LinearLayoutManager(this)
        orderRecyclerView.adapter = foodListAdapter
        uiHandler = UiHandler()
        uiHandler.setRecyclerView(orderRecyclerView)
        uiHandler.setAdapter(foodListAdapter)
    }

    override fun onStart() {
        super.onStart()
        orderHandlerThread = OrderHandlerThread(uiHandler)
        orderHandlerThread.start()
        foodRunnable = FoodRunnable(orderHandlerThread)
        foodRunnable.setMaxOrders(10)
        foodRunnable.start()
    }


    override fun onDestroy() {
        super.onDestroy()
        foodRunnable.stop()
        orderHandlerThread.quit()
    }

    class UiHandler : Handler() {

        private lateinit var weakRefFoodListAdapter: WeakReference<FoodListAdapter>
        private lateinit var weakRefOrderRecyclerView: WeakReference<RecyclerView>

        fun setAdapter(foodListAdapter: FoodListAdapter) {
            weakRefFoodListAdapter = WeakReference(foodListAdapter)
        }

        fun setRecyclerView(foodRecyclerView: RecyclerView) {
            weakRefOrderRecyclerView = WeakReference(foodRecyclerView)
        }

        private fun addAndNotifyForOrder(foodOrder: FoodOrder, position: Int) {
            weakRefFoodListAdapter.get()?.getOrderList()?.add(foodOrder)
            weakRefOrderRecyclerView.get()?.adapter?.notifyItemInserted(position)
        }

        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            val position = weakRefFoodListAdapter.get()?.getOrderList()?.size
            addAndNotifyForOrder(msg?.obj as FoodOrder, position!!)
            weakRefOrderRecyclerView.get()?.smoothScrollToPosition(weakRefFoodListAdapter
                    .get()?.itemCount!!)
        }

    }

}
