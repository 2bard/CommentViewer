# Tech Test

## Technologies Used

- Android MVVM architecture (ViewModel/UseCase/Repo) 
- Jetpack Compose UI
- Hilt for DI
- Networking using Retrofit (okhttp) + Moshi
- Mockito + Paparazzi for testing

## Project Structure

- `/data` -> api/dto/repo
- `/di` -> hilt modules
- `/domain` -> business logic
- `/ui` -> frontend

## UI Details

- Hilt compose navigation
- Stateless UI elements where possible
- Errors show via snackbar 

## Testing

`app/src/tests` contains unit/integration/ui tests:

- UseCase tests
- ViewModel tests
- API integration test
- Repository tests
- UI snapshot tests ([test report](app/build/reports/paparazzi/debug/index.html))

## Further work

- Instrumentation tests would be nice
- UI tests could be expanded to test across a wide range of device configs
- Proper caching could be added to repository (probably room)
- Use SavedStateHandle in ViewModels