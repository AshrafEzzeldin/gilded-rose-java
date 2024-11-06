package com.gildedrose.strategy;

import com.gildedrose.Item;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NormalItemStrategyTest {

    @ParameterizedTest(name = "[{index}] {3}: sellIn={0} -> expected sellIn={1}, quality={2} -> expected quality={3}")
    @CsvSource({
            // Normal item tests
            "2, 1, 5, 4, 'Normal item before sell date'",
            "0, -1, 5, 3, 'Normal item after sell date'",
            "0, -1, 1, 0, 'Normal item after sell date at minimum quality'",
            "1, 0, 0, 0, 'Normal item with zero quality, never negative'"
    })
    void normalItemTests(int sellIn, int expectedSellIn, int quality, int expectedQuality, String scenario) {
        Item item = new Item("customName", sellIn, quality);
        ItemUpdateStrategy strategy = new NormalItemStrategy();
        strategy.update(item);
        assertEquals(expectedSellIn, item.sellIn);
        assertEquals(expectedQuality, item.quality);
    }
}