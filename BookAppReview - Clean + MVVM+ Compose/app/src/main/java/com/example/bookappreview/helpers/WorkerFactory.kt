//package com.example.bookappreview.helpers
//
//import android.content.Context
//import androidx.work.Worker
//import androidx.work.WorkerFactory
//import androidx.work.WorkerParameters
//import com.example.bookappreview.data.webclient.aiservice.AiService
//import com.example.bookappreview.domain.repository.LivroRepository
//
//class CustomWorkerFactory(
//    private val livroRepository: LivroRepository,
//    private val aiService: AiService
//) : WorkerFactory() {
//    override fun createWorker(
//        appContext: Context,
//        workerClassName: String,
//        workerParameters: WorkerParameters
//    ): NotificationWorker? {
//        return when (workerClassName) {
//            NotificationWorker::class.java.name -> NotificationWorker(
//                appContext,
//                workerParameters,
//                livroRepository,
//                aiService
//            )
//            else -> null
//        }
//    }
//}