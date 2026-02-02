# Tech Test

## Technologies Used

- Kotlin+Coroutines
- Android MVVM architecture (ViewModel+Flows/Repositories/UseCases)
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
- Scaffold for screen structure
- Stateless UI elements where possible
- Errors show via Snackbar (LaunchedEffect)

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
- Navigation structure change to handle tablets

# Paparazzi screenshots

## Comment list - loaded list
![Comment list - empty state](app/build/reports/paparazzi/debug/images/6dc73f25b9d28ccbdfae389918e80a3e7f00c421.png)

## Comment list - empty state
![Comment list - empty state](app/build/reports/paparazzi/debug/images/529b6e37a66fab11d3d7fe27ebad11315a8eedac.png)

## Comment list - loading state
![screenshot](app/build/reports/paparazzi/debug/images/cf5d09d930559929a94e405338be7969d4591df8.png)

## Comment detail
![screenshot](app/build/reports/paparazzi/debug/images/9ce6c9d7681cb8be518ae87a5eb8eaeb050bbdfc.png)

## Comment detail - long text
![Comment detail](app/build/reports/paparazzi/debug/images/51c56d7a9b3075910e14ab2d889606a279184b6e.png)
