package com.gildedrose.strategy;

import com.gildedrose.Item;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class AgedBrieStrategyTest {


    @ParameterizedTest(name = "[{index}] {3}: sellIn={0} -> expected sellIn={1}, quality={2} -> expected quality={3}")
    @CsvSource({
            "2, 1, 1, 2, 'Aged Brie before sell date increases in quality'",
            "0, -1, 1, 3, 'Aged Brie after sell date increases in quality twice'",
            "1, 0, 50, 50, 'Aged Brie at max quality stays at max'",
            "-1, -2, 49, 50, 'Aged Brie near max quality on sell date stops at 50'"
    })
    void agedBrieTests(int sellIn, int expectedSellIn, int quality, int expectedQuality, String scenario) {
        Item item = new Item("customName", sellIn, quality);
        ItemUpdateStrategy strategy = new AgedBrieStrategy();
        strategy.update(item);
        assertEquals(expectedSellIn, item.sellIn);
        assertEquals(expectedQuality, item.quality);
    }
}