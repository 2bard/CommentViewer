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
import com.twobard.techtest.domain.repository.Comment
import com.twobard.techtest.ui.list.CommentPreviewProvider
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