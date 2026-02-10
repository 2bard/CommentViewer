import com.twobard.techtest.di.dispatchers.DispatcherProvider
import kotlinx.coroutines.test.TestDispatcher


class TestDispatcherProvider(
    private val testDispatcher: TestDispatcher
) : DispatcherProvider {
    override val main = testDispatcher
    override val io = testDispatcher
    override val default = testDispatcher
}