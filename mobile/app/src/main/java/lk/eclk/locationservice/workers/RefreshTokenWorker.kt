package lk.eclk.locationservice.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import lk.eclk.locationservice.data.proviers.JWTProvider
import lk.eclk.locationservice.data.repository.Repository
import lk.eclk.locationservice.internal.ResponseStates
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class RefreshTokenWorker(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params), KodeinAware {

    override val kodein: Kodein by closestKodein(context)
    private val repository: Repository by instance()

    override suspend fun doWork(): Result = coroutineScope {
        when (repository.refreshAccessToken()) {
            ResponseStates.AUTHENTICATED -> Result.success()
            ResponseStates.NO_CONNECTIVITY -> Result.retry()
            else -> Result.retry()
        }
    }

}