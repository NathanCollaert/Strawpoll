package com.example.strawpoll.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.strawpoll.persistence.StrawpollDatabase
import com.example.strawpoll.persistence.repositories.StrawpollRepository
import retrofit2.HttpException

class RefreshDataWork(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    companion object{
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = StrawpollDatabase.getInstance(applicationContext)
        val repository = StrawpollRepository(database)

        return try {
            repository.refreshStrawpolls()
            Result.success()
        } catch (exception : HttpException) {
            Result.retry()
        }
    }
}