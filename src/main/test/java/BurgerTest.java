import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;
    private Ingredient ingredient;

    @Mock
    Burger burgerMock;

    @Before
    public void setUp() {
        burger = new Burger();
        ingredient = new Ingredient(IngredientType.SAUCE, "Абрикосовый джем", 10);
    }

    @Test
    public void setBunsInvokeWithCorrectArgsOneTimes() {

        Bun bun = new Bun("Сладкая булочка", 50);
        burgerMock.setBuns(bun);

        //Проверь, что метод setBuns() был вызван 1 раз с корректным аргументом bun
        Mockito.verify(burgerMock, Mockito.times(1)).setBuns(bun);
    }

    @Test
    public void setBunsInvokeGetCorrectBunName() {
        Bun smartBun = new Bun("Умная булочка", 999);
        burger.setBuns(smartBun);

        //Проверь, что после вызова setBuns в burger есть bun с именем smartBun(Умная булочка)
        Assert.assertEquals(String.format("Должен вернуть: %s", smartBun.getName()), smartBun.getName(), burger.bun.getName());
    }

    @Test
    public void addIngredientInvokeWithCorrectArgsTwoTimes() {

        burgerMock.addIngredient(ingredient);
        burgerMock.addIngredient(ingredient);

        //Проверь, что метод addIngredient() был вызван 2 раза с корректным аргументом ingredient
        Mockito.verify(burgerMock, Mockito.times(2)).addIngredient(ingredient);
    }

    @Test
    public void addIngredientGetCorrectIngredientName() {
        burger.addIngredient(ingredient);

        //Проверь, что после добавления ingredient в burger появился добавленный ingredient
        Assert.assertEquals(String.format("Должен вернуть: %s", ingredient.getName()), ingredient.getName(), burger.ingredients.get(0).name);
    }

    @Test
    public void removeIngredientInvokeWithCorrectArgsOneTimes() {

        burgerMock.removeIngredient(1);

        //Проверь, что метод removeIngredient() был вызван 1 раз с корректным аргументом index
        Mockito.verify(burgerMock, Mockito.times(1)).removeIngredient(1);
    }

    @Test
    public void removeIngredientGetCorrectIngredientWithRemovedIndx() {
        Ingredient ingredient_1 = new Ingredient(IngredientType.FILLING, "Мармеладный стейк", 100);
        Ingredient ingredient_2 = new Ingredient(IngredientType.SAUCE, "Шипучка", 15);

        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient_1);
        burger.addIngredient(ingredient_2);

        burger.removeIngredient(0);

        //Проверь, что после удаления ingredient в списке ingredients burger(а) под индексом 0 находится ingredient_1
        Assert.assertEquals(String.format("Должен вернуть: %s", ingredient_1.getName()), ingredient_1.getName(), burger.ingredients.get(0).getName());
    }

    @Test
    public void moveIngredientInvokeWithCorrectArgsOneTimes() {

        burgerMock.moveIngredient(0, 1);

        //Проверь, что метод moveIngredient был вызван 1 раз с корректными аргументами index и newindex
        Mockito.verify(burgerMock, Mockito.times(1)).moveIngredient(0, 1);
    }

    @Test
    public void moveIngredientGetCorrectIngredientWithNewIndx() {
        Ingredient ingredient_1 = new Ingredient(IngredientType.FILLING, "Мармеладный стейк", 100);
        Ingredient ingredient_2 = new Ingredient(IngredientType.SAUCE, "Шипучка", 15);

        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient_1);
        burger.addIngredient(ingredient_2);

        burger.moveIngredient(0, 2);
        burger.moveIngredient(1, 0);

        //Проверь, что после "перемешивания" ингредиентов место Абрикосового джема заняла Шипучка (Было А0М1Ш2 -> Первый вызов М0Ш1А2 -> Второй вызов Ш0М1А2).
        Assert.assertEquals(String.format("Должен вернуть: %s", ingredient_2.getName()), ingredient_2.getName(), burger.ingredients.get(0).getName());
    }
}