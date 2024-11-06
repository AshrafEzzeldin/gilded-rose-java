package com.gildedrose;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    static final String normalItem = "foo";
    static final String agedBrie = "Aged Brie";
    static final String backstagePasses = "Backstage passes to a TAFKAL80ETC concert";
    static final String sulfuras = "Sulfuras, Hand of Ragnaros";

    @ParameterizedTest(name = "[{index}] {3}: sellIn={0} -> expected sellIn={1}, quality={2} -> expected quality={3}")
    @CsvSource({
            // Normal item tests
            "2, 1, 5, 4, 'Normal item before sell date'",
            "0, -1, 5, 3, 'Normal item after sell date'",
            "0, -1, 1, 0, 'Normal item after sell date at minimum quality'",
            "1, 0, 0, 0, 'Normal item with zero quality, never negative'"
    })
    void normalItemTests(int sellIn,int expectedSellIn, int quality, int expectedQuality, String scenario) {
        Item[] items = new Item[] { new Item(normalItem, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(expectedSellIn, app.items[0].sellIn);
        assertEquals(expectedQuality, app.items[0].quality);
    }

    @ParameterizedTest(name = "[{index}] {3}: sellIn={0} -> expected sellIn={1}, quality={2} -> expected quality={3}")
    @CsvSource({
            "2, 1, 1, 2, 'Aged Brie before sell date increases in quality'",
            "0, -1, 1, 3, 'Aged Brie after sell date increases in quality twice'",
            "1, 0, 50, 50, 'Aged Brie at max quality stays at max'",
            "-1, -2, 49, 50, 'Aged Brie near max quality on sell date stops at 50'"
    })
    void agedBrieTests(int sellIn,int expectedSellIn, int quality, int expectedQuality, String scenario) {
        Item[] items = new Item[] { new Item(agedBrie, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(expectedSellIn, app.items[0].sellIn);
        assertEquals(expectedQuality, app.items[0].quality);
    }

    @ParameterizedTest(name = "[{index}] {3}: sellIn={0} -> expected sellIn={1}, quality={2} -> expected quality={3}")
    @CsvSource({
            "10, 9, 49, 50, 'Backstage passes 10 days or less, near max quality'",
            "5, 4, 48, 50, 'Backstage passes 5 days or less, near max quality'",
            "12, 11, 10, 11, 'Backstage passes more than 10 days increases by 1'",
            "9, 8, 10, 12, 'Backstage passes 10 days or less increases by 2'",
            "5, 4, 10, 13, 'Backstage passes 5 days or less increases by 3'",
            "0, -1, 10, 0, 'Backstage passes after concert drops to 0'"
    })
    void backstagePassesTests(int sellIn, int expectedSellIn, int quality, int expectedQuality,String scinario) {
        Item[] items = new Item[] { new Item(backstagePasses, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(expectedSellIn, app.items[0].sellIn);
        assertEquals(expectedQuality, app.items[0].quality);
    }

    @ParameterizedTest(name = "[{index}] {3}: sellIn={0} -> expected sellIn={1}, quality={2} -> expected quality={3}")
    @CsvSource({
            "5, 5, 18, 'Sulfuras quality and sellIn never change'"
    })
    void sulfurasTests(int sellIn, int expectedSellIn, int quality, String scenario) {
        Item[] items = new Item[] { new Item(sulfuras, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(expectedSellIn, app.items[0].sellIn, "SellIn should not change for Sulfuras");
        assertEquals(quality, app.items[0].quality, "Quality should not change for Sulfuras");
    }
}