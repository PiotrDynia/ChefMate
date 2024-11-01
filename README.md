# ChefMate

ChefMate is an Android application based on Spoonacular API that helps users find favourite recipes. 
Built with a focus on user experience, the app allows for setting dietary preferences, 
searching for recipes, bookmarking them and talking to a chatbot.

## Presentation

![Demo](demo/Navigation.gif)

## Features
- Set dietary preferences (cuisine, diet, intolerance)
- Search for recipes (including advanced search with filters)
- View recipe details
- Bookmark a recipe for offline use
- Add recipe ingredients to shopping list
- Manage a shopping list
- Talk to a chatbot

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/PiotrDynia/ChefMate.git
   ```
2. Open the project in Android Studio.
3. Build the project and run on an emulator or connected device.
4. For most features to work, you need an API key from Spoonacular. You can get one for free from 
https://spoonacular.com/food-api/console#Dashboard
5. To use the API key, copy the key and paste it into `local.properties` file in your root folder
like this - `API_KEY=your_api_key`

## Technologies Used

- Kotlin
- Jetpack Compose
- Room Database
- Dagger/Hilt
- Retrofit
- JUnit 4
- Lottie
- Coil

## Architecture

The app follows the MVI (Model-View-Intent) architecture to ensure separation of concerns and facilitate testability and scalability.

## How to Use

### Onboarding screen
1. Swipe to go to the next screen or press skip to skip the onboarding.
2. Choose your diet preferences on the last screen.

### Home screen
1. Type in the search bar to search for recipes. Autocomplete feature is implemented.
2. Click on `Search` to perform search. Click on `Advanced search` to go to search screen.
3. Click on any of preferences items to update dietary preferences.
4. Click on any recommended recipe to view the details.
5. Click on `See all` to see all recommendations.

### Search screen
1. Type in the search bar to search for recipes.
2. Select your desired filters.
3. Click `Search` to search for recipes.

### Results screen
1. Type in the search bar to search for recipes with the same filters.
2. Click on any filter chip to go back to search screen and change the filters.

### Details screen
1. Click on back arrow to go to previous screen.
2. Click on bookmark icon to bookmark the recipe for offline use.
3. In the ingredients section, click the `+` button to add an ingredient to shopping list. Click
`Add all` to add all ingredients to shopping list.

### Bookmarks screen
1. Very similar to results screen, just without filters.

### Shopping list screen
1. Click on a trash icon to delete the ingredient from shopping list.

### Chatbot
1. Type a desired message to chat with a chatbot.
2. Wait for an answer.