package com.gildedrose.strategy;

import com.gildedrose.Item;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BackstagePassStrategyTest {


    @ParameterizedTest(name = "[{index}] {3}: sellIn={0} -> expected sellIn={1}, quality={2} -> expected quality={3}")
    @CsvSource({
            "10, 9, 49, 50, 'Backstage passes 10 days or less, near max quality'",
            "5, 4, 48, 50, 'Backstage passes 5 days or less, near max quality'",
            "12, 11, 10, 11, 'Backstage passes more than 10 days increases by 1'",
            "9, 8, 10, 12, 'Backstage passes 10 days or less increases by 2'",
            "5, 4, 10, 13, 'Backstage passes 5 days or less increases by 3'",
            "0, -1, 10, 0, 'Backstage passes after concert drops to 0'"
    })
    void backstagePassesTests(int sellIn, int expectedSellIn, int quality, int expectedQuality, String scenario) {
        Item item = new Item("customName", sellIn, quality);
        ItemUpdateStrategy strategy = new BackstagePassStrategy();
        strategy.update(item);
        assertEquals(expectedSellIn, item.sellIn);
        assertEquals(expectedQuality, item.quality);
    }

}