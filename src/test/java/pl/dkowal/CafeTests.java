package pl.dkowal;

import org.junit.Assert;
import org.junit.Test;

import static pl.dkowal.CoffeeType.Espresso;
import static pl.dkowal.CoffeeType.Latte;

public class CafeTests {

    public static final int ESPRESSO_BEANS = Espresso.getRequiredBeans();
    public static final int NO_MILK = 0;
    public static final int NO_BEANS = 0;
    public static final int LATTE_MILK = Latte.getRequiredMilk();

    @Test
    public void canBrewEspresso() {
        Cafe cafe = cafeWithBeans();

        Coffee coffee = cafe.brew(Espresso);

        Assert.assertEquals("Wrong caffee type",Espresso, coffee.getType());
        Assert.assertEquals("Wrong amount of milk", NO_MILK, coffee.getMilk());
        Assert.assertEquals("Wrong number of beans", ESPRESSO_BEANS, coffee.getBeans());
    }

    @Test
    public void brewingEspressoConsumeBeans() {
        Cafe cafe = cafeWithBeans();

        cafe.brew(Espresso);

        Assert.assertEquals(NO_BEANS, cafe.getBeansInStock());
    }

    @Test
    public void canBrewLatte() {
        Cafe cafe = cafeWithBeans();
        cafe.restockMilk(LATTE_MILK);
    }

    @Test(expected = IllegalArgumentException.class)
    public void mustRestoreMilk() {
        Cafe cafe = cafeWithBeans();

        cafe.restockMilk(NO_MILK);
    }

    @Test(expected = IllegalArgumentException.class)
    public void mustRestoreBeans() {
        Cafe cafe = cafeWithBeans();

        cafe.restockBeans(NO_BEANS);
    }


    @Test(expected = IllegalStateException.class)
    public void latteRequiresMilk() {
        Cafe cafe = cafeWithBeans();

        cafe.brew(CoffeeType.Latte);
    }

    private Cafe cafeWithBeans() {
        Cafe cafe = new Cafe();
        cafe.restockBeans(ESPRESSO_BEANS);
        return cafe;
    }
}
