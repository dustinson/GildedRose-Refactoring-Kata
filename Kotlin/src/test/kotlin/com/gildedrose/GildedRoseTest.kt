package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class GildedRoseTest {
    /*
        - All items have a SellIn value which denotes the number of days we have to sell the item
        - All items have a Quality value which denotes how valuable the item is
        x- At the end of each day our system lowers both values for every item

        Pretty simple, right? Well this is where it gets interesting:

        - Once the sell by date has passed, Quality degrades twice as fast
        x- The Quality of an item is never negative
        x- "Aged Brie" actually increases in Quality the older it gets
        - The Quality of an item is never more than 50
        - "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
        - "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
        Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but
        Quality drops to 0 after the concert

        We have recently signed a supplier of conjured items. This requires an update to our system:

        - "Conjured" items degrade in Quality twice as fast as normal items*/

    private lateinit var regularItem: Item
    private lateinit var agedBrieItem: Item

    @BeforeEach
    internal fun setUp() {
        this.regularItem = getRegularItem()
        this.agedBrieItem = Item(name = "Aged Brie", sellIn = 5, quality = 12)
    }

    @Test
    fun sellInDecreases() {
        assertSellInChangesBy(regularItem, -1)
    }

    @Test
    internal fun qualityDecreases() {
        assertQualityChangesBy(regularItem, -1)
    }

    @Test
    internal fun qualityIsNeverNegative() {
        regularItem.quality = 0
        assertQualityChangesBy(regularItem, 0)
    }


    @Test
    internal fun qualityWillNotDecreaseBelowZero() {
        //interesting.  If a negative number is in the system, it will simply not decrement.
        //Since the quality is ONLY controlled by this system, we should never be in this state
        regularItem.quality = -42
        assertQualityChangesBy(regularItem, 0)
    }

    @Test
    internal fun agedBrieIncreasesInQuality() {
        assertQualityChangesBy(agedBrieItem, +1)
    }

    @Test
    internal fun agedBrieQualityMax() {
        agedBrieItem.quality = 50
        assertQualityChangesBy(agedBrieItem, 0)
    }

    //regular item with random values that don't mean anything
    private fun getRegularItem() = Item("foo", sellIn = 3, quality = 5)

    private fun assertQualityChangesBy(item: Item, qualityDifference: Int) {
        val expected = item.quality + qualityDifference

        val app = GildedRose(arrayOf(item))
        app.updateQuality()

        assertEquals(expected, item.quality)
    }

    private fun assertSellInChangesBy(item: Item, sellInDifference: Int) {
        val expected = item.sellIn + sellInDifference

        val app = GildedRose(arrayOf(item))
        app.updateQuality()

        assertEquals(expected, item.sellIn)
    }

}


