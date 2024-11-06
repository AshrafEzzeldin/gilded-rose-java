package com.gildedrose.factory;

import com.gildedrose.strategy.ItemUpdateStrategy;
import com.gildedrose.strategy.NormalItemStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemUpdateStrategyFactoryTest {

    @Test
    void getStrategy() {
        ItemUpdateStrategy itemUpdateStrategy=ItemUpdateStrategyFactory.getStrategy("Normal");
        assertTrue(itemUpdateStrategy instanceof NormalItemStrategy);
    }
}