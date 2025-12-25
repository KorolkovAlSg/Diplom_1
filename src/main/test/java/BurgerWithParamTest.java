import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;
import stellarburgers.Receipt;

import java.util.List;

@RunWith(Parameterized.class)
public class BurgerWithParamTest {

    private Bun bunMock;
    private Ingredient ingredientMock;
    private Receipt receipt;

    private Burger burger;

    private final List<String> tBuns;
    private final List<List<String>> tIngredients;

    public BurgerWithParamTest(List<String> tBuns, List<List<String>> tIngredients) {
        this.tBuns = tBuns;
        this.tIngredients = tIngredients;
    }

    @Parameterized.Parameters(name = "Булка: {0} | Ингредиенты: {1}")
    public static Object[][] getInfo() {
        return new Object[][]{
                {List.of("Флюоресцентная булка R2-D3", "988"), List.of(List.of("SAUCE", "Соус Spicy-X", "90"), List.of("FILLING", "Мясо бессмертных моллюсков Protostomia", "1337"))},
                {List.of("Флюоресцентная булка R2-D3", "988"), List.of(List.of("SAUCE", "Соус Spicy-X", "90"), List.of("FILLING", "Говяжий метеорит (отбивная)", "3000"))},
                {List.of("Флюоресцентная булка R2-D3", "988"), List.of(List.of("SAUCE", "Соус Spicy-X", "90"), List.of("FILLING", "Биокотлета из марсианской Магнолии", "424"))},
                {List.of("Флюоресцентная булка R2-D3", "988"), List.of(List.of("SAUCE", "Соус фирменный Space Sauce", "80"), List.of("FILLING", "Филе Люминесцентного тетраодонтимформа", "988"))},
                {List.of("Краторная булка N-200i", "1255"), List.of(List.of("SAUCE", "Соус фирменный Space Sauce", "80"), List.of("FILLING", "Хрустящие минеральные кольца", "300"))},
                {List.of("Краторная булка N-200i", "1255"), List.of(List.of("SAUCE", "Соус традиционный галактический", "15"), List.of("FILLING", "Плоды Фалленианского дерева", "874"))},
                {List.of("Краторная булка N-200i", "1255"), List.of(List.of("SAUCE", "Соус традиционный галактический", "15"), List.of("FILLING", "Кристаллы марсианских альфа-сахаридов", "762"))},
                {List.of("Краторная булка N-200i", "1255"), List.of(List.of("SAUCE", "Соус с шипами Антарианского плоскоходца", "88"), List.of("FILLING", "Мини-салат Экзо-Плантаго", "4400"))},
                {List.of("Краторная булка N-200i", "1255"), List.of(List.of("SAUCE", "Соус с шипами Антарианского плоскоходца", "88"), List.of("FILLING", "Сыр с астероидной плесенью", "4142"))},
        };
    }

    @Before
    public void setVar() {
        burger = new Burger();
        receipt = new Receipt();

        bunMock = Mockito.mock(Bun.class);
        ingredientMock = Mockito.mock(Ingredient.class);

        Mockito.when(bunMock.getName()).thenReturn(tBuns.get(0));
        Mockito.when(bunMock.getPrice()).thenReturn(Float.valueOf(tBuns.get(1)));

        Mockito.when(ingredientMock.getName()).thenReturn(tIngredients.get(0).get(1), tIngredients.get(1).get(1));
        Mockito.when(ingredientMock.getPrice()).thenReturn(Float.valueOf(tIngredients.get(0).get(2)), Float.valueOf(tIngredients.get(1).get(2)));
        Mockito.when(ingredientMock.getType()).thenReturn(IngredientType.valueOf(tIngredients.get(0).get(0)), IngredientType.valueOf(tIngredients.get(1).get(0)));
    }

    @Test
    public void getPriceInvokeReturnCorrectPrice() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);
        burger.addIngredient(ingredientMock);

        //Проверь полученную стоимость созданного тестового бургера с ценой из метода класса Burger
        Assert.assertEquals(String.format("Метод должен вернуть: %s", receipt.getPrice(tBuns, tIngredients)), receipt.getPrice(tBuns, tIngredients), burger.getPrice(), 0.0);
    }

    @Test
    public void getReceiptInvokeReturnCorrectReceipt(){
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);
        burger.addIngredient(ingredientMock);

        //Проверь, что тестовая квитанция receipt равна квитанции, полученной из метода класса Burger
        Assert.assertEquals(String.format("Метод должен вернуть: %s", receipt.getReceipt(tBuns, tIngredients)), receipt.getReceipt(tBuns, tIngredients), burger.getReceipt());
    }

}
