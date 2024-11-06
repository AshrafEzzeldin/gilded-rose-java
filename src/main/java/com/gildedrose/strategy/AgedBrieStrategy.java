package com.gildedrose.strategy;

import com.gildedrose.Item;
import com.gildedrose.constants.ItemConstants;

public class AgedBrieStrategy implements ItemUpdateStrategy {
    @Override
    public void update(Item item) {
        if (item.quality < ItemConstants.MAX_QUALITY) {
            item.quality++;
        }
        item.sellIn--;
        handleExpiredAgedBrie(item);

    }

    private void handleExpiredAgedBrie(Item item) {
        if (item.sellIn >= 0) return;
        if (item.quality < ItemConstants.MAX_QUALITY) {
            item.quality++;
        }
    }
}
