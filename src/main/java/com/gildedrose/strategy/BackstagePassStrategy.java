package com.gildedrose.strategy;

import com.gildedrose.Item;
import com.gildedrose.constants.ItemConstants;

public class BackstagePassStrategy implements ItemUpdateStrategy {
    @Override
    public void update(Item item) {
        if (item.quality < ItemConstants.MAX_QUALITY) {
            item.quality++;
            if (item.sellIn < 11 && item.quality < ItemConstants.MAX_QUALITY) {
                item.quality++;
            }
            if (item.sellIn < 6 && item.quality < ItemConstants.MAX_QUALITY) {
                item.quality++;
            }
        }
        item.sellIn--;
        handleExpiredBackstagePassItem(item);
    }

    private void handleExpiredBackstagePassItem(Item item) {
        if (item.sellIn >= 0) return;
        item.quality=ItemConstants.MIN_QUALITY;
    }
}
