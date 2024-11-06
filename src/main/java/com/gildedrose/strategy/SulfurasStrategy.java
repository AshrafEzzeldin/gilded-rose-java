package com.gildedrose.strategy;

import com.gildedrose.Item;
import com.gildedrose.constants.ItemConstants;

public class SulfurasStrategy implements ItemUpdateStrategy {


    @Override
    public void update(Item item) {
        item.quality = ItemConstants.SULFURAS_QUALITY;
        if (item.sellIn <= 0)
            item.sellIn = 1;
    }
}
