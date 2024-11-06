package com.gildedrose;

import com.gildedrose.constants.ItemConstants;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (isAgedBrie(item)) {
                updateAgedBrie(item);
            } else if (isBackstagePass(item)) {
                updateBackstagePass(item);
            } else if (isSulfuras(item)) {
                updateSulfurasItem(item);
            } else {
                updateNormalItem(item);
            }
        }
    }


    private boolean isAgedBrie(Item item) {
        return ItemConstants.agedBrie.equals(item.name);
    }

    private void updateAgedBrie(Item item) {
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

    private boolean isBackstagePass(Item item) {
        return ItemConstants.backstagePasses.equals(item.name);
    }

    private void updateBackstagePass(Item item) {
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
        item.quality = ItemConstants.MIN_QUALITY;
    }

    private boolean isSulfuras(Item item) {
        return ItemConstants.sulfuras.equals(item.name);
    }

    private void updateSulfurasItem(Item item) {
        item.quality = ItemConstants.SULFURAS_QUALITY;
        if (item.sellIn <= 0)
            item.sellIn = 1;
    }

    private void updateNormalItem(Item item) {
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