package com.example.bookappreview

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.bookappreview.helpers.NotificationWorker
import dagger.hilt.android.HiltAndroidApp
import jakarta.inject.Inject
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class BookAppReviewApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()
        val workRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
            .setInitialDelay(10, TimeUnit.SECONDS)
            .build()
        WorkManager.getInstance(applicationContext).enqueue(workRequest)
    }
}