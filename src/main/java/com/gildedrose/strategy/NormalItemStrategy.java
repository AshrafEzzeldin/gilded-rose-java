package com.gildedrose.strategy;

import com.gildedrose.Item;
import com.gildedrose.constants.ItemConstants;

public class NormalItemStrategy implements ItemUpdateStrategy {

    @Override
    public void update(Item item) {
        if (item.quality > ItemConstants.MIN_QUALITY) {
            item.quality--;
        }
        item.sellIn--;
        handleExpiredNormalItem(item);

    }

    private void handleExpiredNormalItem(Item item) {
        if (item.sellIn >= 0) return;
        if (item.quality > ItemConstants.MIN_QUALITY) {
            item.quality--;
        }
    }
}
