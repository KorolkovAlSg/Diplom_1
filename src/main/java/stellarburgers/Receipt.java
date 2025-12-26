package stellarburgers;

import java.util.List;

public class Receipt {

    public String getReceipt(List<String> tBuns, List<List<String>> tIngredients) {
        StringBuilder receipt = new StringBuilder(String.format("(==== %s ====)%n", tBuns.get(0)));

        for (List<String> list: tIngredients) {
            receipt.append(String.format("= %s %s =%n", list.get(0).toLowerCase(),
                    list.get(1)));
        }

        receipt.append(String.format("(==== %s ====)%n", tBuns.get(0)));
        receipt.append(String.format("%nPrice: %f%n", getPrice(tBuns, tIngredients)));

        return receipt.toString();
    }

    public float getPrice(List<String> tBuns, List<List<String>> tIngredients) {
        float price = Float.parseFloat(tBuns.get(1)) * 2;

        for (List<String> list: tIngredients) {
            price += Float.parseFloat(list.get(2));
        }
        return price;
    }
}
