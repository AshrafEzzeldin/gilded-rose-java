package com.gildedrose;

import com.gildedrose.factory.ItemUpdateStrategyFactory;
import com.gildedrose.strategy.ItemUpdateStrategy;

class GildedRose {

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            ItemUpdateStrategy itemUpdateStrategy = ItemUpdateStrategyFactory.getStrategy(item.name);
            itemUpdateStrategy.update(item);
        }
    }


}