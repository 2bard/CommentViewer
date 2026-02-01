import org.w3c.dom.Comment


interface CommentRepository {
    suspend fun getComments(): List<Comment>
}