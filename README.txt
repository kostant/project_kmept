Лабораторная работа: Retrofit

Что реализовано:
1. Подключен Retrofit + Gson Converter.
2. Добавлено разрешение INTERNET.
3. Создан API-клиент для https://dummyjson.com/recipes.
4. Реализована загрузка списка рецептов через Retrofit.
5. Вывод рецептов в RecyclerView в виде сетки.
6. При нажатии на карточку открывается экран деталей рецепта.
7. На экране деталей отображаются:
   - название,
   - параметры рецепта,
   - рейтинг,
   - ингредиенты,
   - шаги приготовления,
   - изображения.

Основные файлы:
- network/RetrofitClient.kt
- network/RecipeApiService.kt
- model/ApiRecipe.kt
- screens/GridFragment.kt
- screens/RecipeFragment.kt
- screens/ArsenchikActivity.kt
- adapter/GridItemAdapter.kt

Как запускать:
1. Открыть проект в Android Studio.
2. Дождаться Gradle Sync.
3. Запустить приложение на эмуляторе или устройстве.
4. На главном экране откроется список рецептов, загружаемый через Retrofit.

Примечание:
Google Doc с формулировкой задания из среды открывался не полностью, поэтому проект завершён как полноценная лабораторная по теме Retrofit на базе загруженного исходника.
