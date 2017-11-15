package com.developers.usingfirebasejobdispatcher

import com.firebase.jobdispatcher.JobParameters
import com.firebase.jobdispatcher.JobService
import java.util.logging.Logger

/**
 * Created by Amanjeet Singh on 16/11/17.
 */
class MyJobService : JobService() {

    companion object {
        val log = Logger.getLogger(MyJobService::class.java.name)
    }

    override fun onStopJob(job: JobParameters?): Boolean {
        log.info("Stopped Job")
        return false
    }

    override fun onStartJob(job: JobParameters?): Boolean {
        log.info("Started Job")
        return false
    }

}