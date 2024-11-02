    ![logo](https://github.com/Ahmetkaragunlu/UnscrambleApp/blob/main/App.png?raw=true)

Unscramble App is an interactive word game that challenges players to guess scrambled words. The app is developed using Jetpack Compose for a modern, responsive user interface and uses a clean architecture with different components for better maintainability.

The application includes a ViewModel that provides separation between the user interface and business logic to manage game logic and state. It uses StateFlow to observe changes in game state and update the user interface accordingly. The game provides a diverse gaming experience by dynamically selecting words from a predefined list.

Users can enter their predictions through an intuitive interface, track their scores based on correct answers, and receive feedback on incorrect attempts. The application includes functions to reset the game and skip words, improving the user experience.

Key components include GameButtons for user interaction, GameLayout to display the current word and input field, and GameStatus to display the score.

To install the app, clone the repository and build the project in Android Studio, making sure the necessary Jetpack Compose dependencies are included.
