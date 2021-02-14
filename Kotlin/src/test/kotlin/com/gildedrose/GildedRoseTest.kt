package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {
    /*
        - All items have a SellIn value which denotes the number of days we have to sell the item
        - All items have a Quality value which denotes how valuable the item is
        - At the end of each day our system lowers both values for every item

        Pretty simple, right? Well this is where it gets interesting:

        - Once the sell by date has passed, Quality degrades twice as fast
        - The Quality of an item is never negative
        - "Aged Brie" actually increases in Quality the older it gets
        - The Quality of an item is never more than 50
        - "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
        - "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
        Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but
        Quality drops to 0 after the concert

        We have recently signed a supplier of conjured items. This requires an update to our system:

        - "Conjured" items degrade in Quality twice as fast as normal items*/
    @Test
    fun sellInDecreases() {
        val items = arrayOf<Item>(Item("foo", 3, 0))
        val app = GildedRose(items)
        assertSellInChangesBy(app, -1)

    }

    private fun assertSellInChangesBy(app: GildedRose, sellInDifference: Int) {
        val item = app.items[0]
        val expected = item.sellIn + sellInDifference
        app.updateQuality()
        assertEquals(expected, item.sellIn)
    }

}


