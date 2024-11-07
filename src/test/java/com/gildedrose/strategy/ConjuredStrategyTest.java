package com.gildedrose.strategy;

import com.gildedrose.Item;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConjuredStrategyTest {


    @ParameterizedTest(name = "[{index}] {3}: sellIn={0} -> expected sellIn={1}, quality={2} -> expected quality={3}")
    @CsvSource({
            // Normal item tests
            "2, 1, 5, 3, 'conjured item before sell date'",
            "0, -1, 5, 1, 'conjured item after sell date'",
            "1, 0, 0, 0, 'conjured item with zero quality, never negative'"
    })
    void conjuredTests(int sellIn, int expectedSellIn, int quality, int expectedQuality, String scenario) {
        Item item = new Item("customName", sellIn, quality);
        ItemUpdateStrategy strategy = new ConjuredStrategy();
        strategy.update(item);
        assertEquals(expectedSellIn, item.sellIn);
        assertEquals(expectedQuality, item.quality);
    }
}
