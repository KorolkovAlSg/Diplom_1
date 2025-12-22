package stellarburgers;

import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.ArrayList;
import java.util.List;

public class TBurger {

    private final Burger burger;
    private final String bunTName;
    private final List<String> ingredientsTNames;

    public TBurger(Burger burger, String bunTName, List<String> ingredientsTNames){
        this.burger = burger;
        this.bunTName = bunTName;
        this.ingredientsTNames = ingredientsTNames;
    }

    // Пусть данный класс зависит от класса Database, чтобы не создавать новые экзепляры и списки в каждом тесте
    private final Database database = new Database();
    private final List<Bun> buns = database.availableBuns();
    private final List<Ingredient> ingredients = database.availableIngredients();

    public int getIndexTBunFromList(List<Bun> buns, String bunTName){

        int indexBun = -1;

        for (Bun bun : buns){
            if(bun.getName().equals(bunTName)){
                indexBun = buns.indexOf(bun);
            }
        }
        return indexBun;
    }

    public List<Integer> getListIndexesTIngredientsFromList(List<Ingredient> ingredients, List<String> ingredientsTNames){
        List<Integer> ingredientIndexes = new ArrayList<>();

        for (String ingredientsTName : ingredientsTNames) {
            for (Ingredient ingredient : ingredients) {
                if (ingredient.getName().equals(ingredientsTName)) {
                    ingredientIndexes.add(ingredients.indexOf(ingredient));
                }
            }
        }
        return ingredientIndexes;
    }

    public void createTBurger() {

        //Получить индекс тестируемой булочки из списка булочек stellarburgers
        //и добавить тестируемую булочку в бургер
        burger.setBuns(buns.get(getIndexTBunFromList(buns, bunTName)));

        //Получить список индексов тестируемых ингредиентов из списка ингредиентов stellarburgers
        //и добавить каждый тестируемый ингредиент в бургер
        for (Integer numIndex : getListIndexesTIngredientsFromList(ingredients, ingredientsTNames)) {
            burger.addIngredient(ingredients.get(numIndex));
        }
    }

    public IngredientType getTypeForIngredientTNames(List<Ingredient> ingredients, String ingredientTName){

        IngredientType ingredientType = null;

        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equals(ingredientTName)) {
                ingredientType = ingredient.getType();
            }
        }
        return ingredientType;
    }

    public String getTestedValueReceipt(String bunTName, List<String> ingredientsTNames, float checkT){

        StringBuilder receipt = new StringBuilder(String.format("(==== %s ====)%n", bunTName));

        for (String ingredientName : ingredientsTNames){
            receipt.append(String.format("= %s %s =%n", getTypeForIngredientTNames(ingredients, ingredientName).toString().toLowerCase(), ingredientName));
        }
        receipt.append(String.format("(==== %s ====)%n", bunTName)).append(String.format("%nPrice: %f%n", checkT));

        return receipt.toString();
    }
}
