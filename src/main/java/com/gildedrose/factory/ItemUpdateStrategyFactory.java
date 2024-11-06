package com.gildedrose.factory;

import com.gildedrose.constants.ItemConstants;
import com.gildedrose.strategy.*;

public class ItemUpdateStrategyFactory {

    public static ItemUpdateStrategy getStrategy(String itemName) {
        switch (itemName) {
            case ItemConstants.agedBrie:
                return new AgedBrieStrategy();
            case ItemConstants.backstagePasses:
                return new BackstagePassStrategy();
            case ItemConstants.sulfuras:
                return new SulfurasStrategy();
            default:
                return new NormalItemStrategy();
        }
    }
}
