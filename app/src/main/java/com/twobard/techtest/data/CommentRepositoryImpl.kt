package com.twobard.techtest.data

import androidx.annotation.StringRes
import com.twobard.techtest.R
import com.twobard.techtest.domain.repository.Comment
import com.twobard.techtest.domain.repository.CommentRepository
import jakarta.inject.Inject
import java.io.IOException
import java.net.UnknownHostException

class CommentRepositoryImpl @Inject constructor(val apiService: JsonPlaceholderApiService) : CommentRepository {
    private val api = apiService

    override suspend fun getComments(): Result<List<Comment>> {
        return try {
            val dtos = api.getComments()
            Result.success(dtos.map { it.toDomainModel() })
        } catch (e: UnknownHostException) {
            Result.failure(NetworkError.NoInternet(e))
        } catch (e: IOException) {
            Result.failure(NetworkError.NetworkFailure(e))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

sealed class NetworkError(@StringRes val messageRes: Int, cause: Throwable? = null) : Throwable(null, cause) {
    class NoInternet(cause: Throwable? = null) : NetworkError(R.string.no_internet, cause)
    class NetworkFailure(cause: Throwable? = null) : NetworkError(R.string.network_error, cause)
}