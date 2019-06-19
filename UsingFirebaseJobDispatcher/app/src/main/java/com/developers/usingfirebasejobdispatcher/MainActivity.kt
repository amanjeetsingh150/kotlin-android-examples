package com.developers.usingfirebasejobdispatcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.firebase.jobdispatcher.FirebaseJobDispatcher
import com.firebase.jobdispatcher.GooglePlayDriver
import com.firebase.jobdispatcher.Trigger

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dispatcher = FirebaseJobDispatcher(GooglePlayDriver(this))
        val job = dispatcher.newJobBuilder()
                .setService(MyJobService::class.java)
                .setRecurring(true)
                //setTrigger(x,y)
                //x is know as windowStart, which is the earliest time (in seconds) the job should
                // be considered eligible to run. Calculated from when the job was scheduled (for new jobs)
                //y is known as windowEnd,
                // The latest time (in seconds) the job should be run in an ideal world. Calculated in the same way as windowStart.
                .setTrigger(Trigger.executionWindow(0, 60))
                .setTag("my_tag")
                .build()
        dispatcher.mustSchedule(job)

    }
}
