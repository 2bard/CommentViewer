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
    @PreviewParameter(CommentPreviewProvider ::class) comment: Comment,
    onClickItem: (Comment) -> Unit = {}){
    Card(
        modifier = Modifier.clickable{
            onClickItem.invoke(comment)
        },
        elevation = CardDefaults.cardElevation(ThemePadding().defaultElevation())) {
        Column(modifier = Modifier.padding(ThemePadding().boxPadding()).fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(ThemePadding().listPadding())) {
            Text(text = comment.name, style = MaterialTheme.typography.titleMedium)
            Text(text = comment.body,  style = MaterialTheme.typography.bodyMedium)
        }
    }
}

class CommentListPreviewProvider : PreviewParameterProvider<List<Comment>> {
    override val values = sequenceOf(
        emptyList(),
        listOf(
            Comment(postId = 1, id = 1, name = "C Comment", email = "test@test.com", body = "Hello 1"),
            Comment(postId = 2, id = 2, name = "A Comment", email = "test2@test.com", body = "Hello 2"),
            Comment(postId = 3, id = 3, name = "B Comment", email = "test3@test.com", body = "Hello 3")
        )
    )
}

class CommentPreviewProvider : PreviewParameterProvider<Comment> {
    override val values = sequenceOf(
        Comment(postId = 1, id = 1, name = "Normal name", email = "test@test.com", body = "Normal body comment"),
        Comment(postId = 1, id = 1, name = "Long name. Long name. Long name. Long name. Long name. Long name.  ", email = "test@test.com", body = "Long comment. Long comment. Long comment. Long comment. Long comment. Long comment. Long comment. "),
    )
}