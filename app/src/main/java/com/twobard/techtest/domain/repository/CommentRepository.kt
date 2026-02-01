import com.twobard.techtest.domain.repository.Comment


interface CommentRepository {
    suspend fun getComments(): List<Comment>
}