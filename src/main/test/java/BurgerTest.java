import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    Burger burgerMock;

    @Mock
    Bun bunMock;

    @Mock
    Ingredient ingredientMock;

    @Mock
    Ingredient ingredientMock1;

    @Mock
    Ingredient ingredientMock2;

    @InjectMocks
    Burger burger;

    @Before
    public void setUp() {
        Mockito.when(bunMock.getName()).thenReturn("Сладкая булочка");
        Mockito.when(ingredientMock.getName()).thenReturn("Абрикосовый джем");

        Mockito.when(ingredientMock1.getName()).thenReturn("Мармеладный стейк");
        Mockito.when(ingredientMock2.getName()).thenReturn("Шипучка");
    }

    @Test
    public void setBunsInvokeWithCorrectArgsOneTimes() {
        burgerMock.setBuns(bunMock);

        //Проверь, что метод setBuns() был вызван 1 раз с корректным аргументом bun
        Mockito.verify(burgerMock, Mockito.times(1)).setBuns(bunMock);
    }

    @Test
    public void setBunsInvokeGetCorrectBunName() {
        burger.setBuns(bunMock);

        //Проверь, что после вызова setBuns в burger есть bun с именем Сладкая булочка
        Assert.assertEquals("Должен вернуть: \"Cладкая булочка\"", "Сладкая булочка", burger.bun.getName());
    }

    @Test
    public void addIngredientInvokeWithCorrectArgsTwoTimes() {

        burgerMock.addIngredient(ingredientMock);
        burgerMock.addIngredient(ingredientMock);

        //Проверь, что метод addIngredient() был вызван 2 раза с корректным аргументом ingredient
        Mockito.verify(burgerMock, Mockito.times(2)).addIngredient(ingredientMock);
    }

    @Test
    public void addIngredientGetCorrectIngredientName() {
        burger.addIngredient(ingredientMock);

        //Проверь, что после добавления ingredient в burger появился добавленный ingredient
        Assert.assertEquals("Должен вернуть: \"Абрикосовый джем\"", "Абрикосовый джем", burger.ingredients.get(0).getName());
    }

    @Test
    public void removeIngredientInvokeWithCorrectArgsOneTimes() {

        burgerMock.removeIngredient(1);

        //Проверь, что метод removeIngredient() был вызван 1 раз с корректным аргументом index
        Mockito.verify(burgerMock, Mockito.times(1)).removeIngredient(1);
    }

    @Test
    public void removeIngredientGetCorrectIngredientWithRemovedIndx() {

        burger.addIngredient(ingredientMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        burger.removeIngredient(0);

        //Проверь, что после удаления ingredientMock в списке ingredients burger(а) под индексом 0 находится ingredientMock1
        Assert.assertEquals("Должен вернуть: \"Мармеладный стейк\"", "Мармеладный стейк", burger.ingredients.get(0).getName());
    }

    @Test
    public void moveIngredientInvokeWithCorrectArgsOneTimes() {

        burgerMock.moveIngredient(0, 1);

        //Проверь, что метод moveIngredient был вызван 1 раз с корректными аргументами index и newindex
        Mockito.verify(burgerMock, Mockito.times(1)).moveIngredient(0, 1);
    }

    @Test
    public void moveIngredientGetCorrectIngredientWithNewIndx() {

        burger.addIngredient(ingredientMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        burger.moveIngredient(0, 2);
        burger.moveIngredient(1, 0);

        //Проверь, что после "перемешивания" ингредиентов место Абрикосового джема заняла Шипучка (Было А0М1Ш2 -> Первый вызов М0Ш1А2 -> Второй вызов Ш0М1А2).
        Assert.assertEquals("Должен вернуть: \"Шипучка\"", "Шипучка", burger.ingredients.get(0).getName());
    }
}