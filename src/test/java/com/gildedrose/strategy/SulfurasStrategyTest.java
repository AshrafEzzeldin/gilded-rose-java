package com.gildedrose.strategy;

import com.gildedrose.Item;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SulfurasStrategyTest {

    @ParameterizedTest(name = "[{index}] {2}: sellIn={0} -> expected sellIn={0}, quality={1} -> expected quality=80 ")
    @CsvSource({
            "5, 80, 'Sulfuras quality and sellIn never change'",
            "5, 30, 'Sulfuras quality is 80 and it never alters'",
            "-1, 80, 'Sulfuras sellin never has to be sold'",
    })
    void sulfurasTests(int sellIn, int quality, String scenario) {
        Item item = new Item("customName", sellIn, quality);
        ItemUpdateStrategy strategy = new SulfurasStrategy();
        strategy.update(item);
        assertTrue(item.sellIn > 0, "Sulfuras should not be sold");
        assertEquals(80, item.quality, "Quality should not change for Sulfuras");
    }
}