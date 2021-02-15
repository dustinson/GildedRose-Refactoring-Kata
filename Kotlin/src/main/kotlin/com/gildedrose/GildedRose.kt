package com.gildedrose

class GildedRose(var items: Array<Item>) {

    private val agedBrie = "Aged Brie"
    private val backstagePasses = "Backstage passes to a TAFKAL80ETC concert"
    private val sulfuras = "Sulfuras, Hand of Ragnaros"
    private val maxQuality = 50
    private val minimumSellInDays = 0
    private val minimumQuality = 0
    private val upcomingDays = 11
    private val thisWeek = 6
    fun updateQuality() {
        var standardProductManager = StandardProductManager()

        items.forEach { item ->
            if (item.name == sulfuras)
                return
            else if (item.name == agedBrie) {
                updateQuality(item)
                standardProductManager.decrementSellIn(item)
                if (isOldProperty(item)) updateQuality(item)
            } else if (item.name == backstagePasses) {
                updateQuality(item)
                if (item.sellIn < upcomingDays) {
                    updateQuality(item)
                }
                if (item.sellIn < thisWeek) {
                    updateQuality(item)
                }
                standardProductManager.decrementSellIn(item)
                if (isOldProperty(item)) clearQuality(item)
            } else {
                decrementQuality(item)
                standardProductManager.decrementSellIn(item)

                if (isOldProperty(item)) {
                    decrementQuality(item)
                }
            }
        }
    }

    private fun decrementQuality(item: Item) {
        if (item.quality > minimumQuality) {
            item.quality = item.quality - 1
        }
    }

    private fun updateQuality(item: Item) {
        if (item.quality < maxQuality) {
            item.quality = item.quality + 1
        }
    }

    private fun isOldProperty(item: Item) = item.sellIn < minimumSellInDays

    private fun clearQuality(item: Item) {
        item.quality = 0
    }


}

class StandardProductManager {
    fun decrementSellIn(item: Item) {
        item.sellIn = item.sellIn - 1
    }
}

