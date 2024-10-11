package com.example.chefmate.core.data.api.dto

data class RecipeSimple(
    val id: Int,
    val title: String,
    val image: String,
    val summary: String
)

val sampleRecipes = listOf(
    RecipeSimple(
        id = 1,
        title = "Spaghetti Carbonara",
        image = "https://cdn.aniagotuje.com/pictures/articles/2020/01/1967583-v-1500x1500.jpg",
        summary = "A classic Italian pasta dish made with eggs, cheese, pancetta, and pepper. Quick, creamy, and delicious!"
    ),
    RecipeSimple(
        id = 2,
        title = "Grilled Chicken Salad",
        image = "https://cdn.aniagotuje.com/pictures/articles/2020/01/1967583-v-1500x1500.jpg",
        summary = "Healthy and tasty salad with grilled chicken breast, mixed greens, avocado, cherry tomatoes, and a tangy vinaigrette."
    ),
    RecipeSimple(
        id = 3,
        title = "Vegan Buddha Bowl",
        image = "https://cdn.aniagotuje.com/pictures/articles/2020/01/1967583-v-1500x1500.jpg",
        summary = "A vibrant, nutrient-packed bowl with quinoa, roasted veggies, avocado, chickpeas, and a zesty tahini dressing."
    ),
    RecipeSimple(
        id = 4,
        title = "Beef Tacos",
        image = "https://cdn.aniagotuje.com/pictures/articles/2020/01/1967583-v-1500x1500.jpg",
        summary = "Soft tacos filled with seasoned ground beef, lettuce, cheese, salsa, and sour cream. A fiesta of flavors in every bite!"
    ),
    RecipeSimple(
        id = 5,
        title = "Chocolate Lava Cake",
        image = "https://cdn.aniagotuje.com/pictures/articles/2020/01/1967583-v-1500x1500.jpg",
        summary = "Decadent molten chocolate lava cake with a gooey center, served warm with a scoop of vanilla ice cream."
    )
)
