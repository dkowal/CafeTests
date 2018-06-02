package pl.dkowal;

import org.junit.Assert;
import org.junit.Test;

public class CafeTests {

    @Test
    public void canBrewEspresso() {
        Cafe cafe = new Cafe();
        cafe.restockBeans(7);

        Coffee coffee = cafe.brew(CoffeeType.Espresso);

        Assert.assertEquals(CoffeeType.Espresso, coffee.getType());
        Assert.assertEquals(0, coffee.getMilk());
        Assert.assertEquals(7, coffee.getBeans());
    }

    @Test
    public void brewingEspressoConsumeBeans() {
        Cafe cafe = new Cafe();
        cafe.restockBeans(7);

        Coffee coffee = cafe.brew(CoffeeType.Espresso);

        Assert.assertEquals(0, cafe.getBeansInStock());
    }

    @Test(expected = IllegalStateException.class)
    public void latteRequiresMilk() {
        Cafe cafe = new Cafe();
        cafe.restockBeans(7);

        cafe.brew(CoffeeType.Latte);
    }

}
