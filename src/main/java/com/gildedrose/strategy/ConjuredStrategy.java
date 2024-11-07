package com.gildedrose.strategy;

import com.gildedrose.Item;
import com.gildedrose.constants.ItemConstants;

public class ConjuredStrategy implements ItemUpdateStrategy {

    @Override
    public void update(Item item) {
        item.quality = Math.max(ItemConstants.MIN_QUALITY, item.quality - 2);
        item.sellIn--;
        handleExpiredConjuredItem(item);
    }

    private void handleExpiredConjuredItem(Item item) {
        if (item.sellIn >= 0) return;
        item.quality = Math.max(ItemConstants.MIN_QUALITY, item.quality - 2);
    }
}
