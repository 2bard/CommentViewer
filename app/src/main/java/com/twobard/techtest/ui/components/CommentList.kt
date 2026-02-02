import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.twobard.techtest.domain.repository.Comment
import com.twobard.techtest.ui.theme.ThemePadding


@Preview
@Composable
fun ListItem(
    @PreviewParameter(CommentPreviewProvider::class) comment: Comment,
    onClickItem: (Comment) -> Unit = {}){
    Card(
        modifier = Modifier.clickable{
            onClickItem.invoke(comment)
        },
        elevation = CardDefaults.cardElevation(ThemePadding.defaultElevation())) {
        Column(modifier = Modifier.padding(ThemePadding.boxPadding()).fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(ThemePadding.listPadding())) {
            Text(text = comment.name, style = MaterialTheme.typography.titleMedium)
            Text(text = comment.body,  style = MaterialTheme.typography.bodyMedium)
        }
    }
}

class CommentListPreviewProvider : PreviewParameterProvider<List<Comment>> {
    override val values = sequenceOf(
        emptyList(),
        listOf(
            Comment(postId = 1, id = 1, name = "C Comment long C Comment long C Comment long C Comment long C Comment long C Comment long C Comment long C Comment long ", email = "test@test.com", body = "Hello 1 Hello 1 Hello 1 Hello 1 Hello 1 Hello 1 Hello 1 Hello 1 Hello 1 "),
            Comment(postId = 2, id = 2, name = "A Comment", email = "test2@test.com", body = "Hello 2"),
            Comment(postId = 3, id = 3, name = "B Comment", email = "test3@test.com", body = "Hello 3"),
            Comment(postId = 4, id = 4, name = "D Comment", email = "test3@test.com", body = "Hello 3"),
            Comment(postId = 5, id = 5, name = "E Comment", email = "test3@test.com", body = "Hello 4"),
            Comment(postId = 6, id = 6, name = "F Comment", email = "test3@test.com", body = "Hello 5"),
            Comment(postId = 7, id = 7, name = "G Comment", email = "test3@test.com", body = "Hello 6"),
            Comment(postId = 8, id = 8, name = "H Comment", email = "test3@test.com", body = "Hello 7"),
            Comment(postId = 9, id = 9, name = "I Comment", email = "test3@test.com", body = "Hello 8"),
            Comment(postId = 10, id = 20, name = "J Comment", email = "test3@test.com", body = "Hello 9")
        )
    )
}

class CommentPreviewProvider : PreviewParameterProvider<Comment> {
    override val values = sequenceOf(
        Comment(postId = 1, id = 1, name = "Normal name", email = "test@test.com", body = "Normal body comment"),
        Comment(postId = 1, id = 1, name = "Long name. Long name. Long name. Long name. Long name. Long name.  ", email = "test+test+test+test+test+test+test+test+test+test+test+test+test@test.com", body = "Long comment. Long comment. Long comment. Long comment. Long comment. Long comment. Long comment. "),
    )
}