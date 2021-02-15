package com.gildedrose

private const val minimumSellInDays = 0
private const val maxQuality = 50
private const val minimumQuality = 0

class GildedRose(var items: Array<Item>) {
    private val agedBrie = "Aged Brie"
    private val backstagePasses = "Backstage passes to a TAFKAL80ETC concert"
    private val sulfuras = "Sulfuras, Hand of Ragnaros"
    private val upcomingDays = 11
    private val thisWeek = 6
    fun updateQuality() {
        var standardProductManager = StandardProductManager()
        var agedBrieManager = AgedBrieManager(standardProductManager)

        items.forEach { item ->
            if (item.name == sulfuras)
                return
            else if (item.name == agedBrie) {
                agedBrieManager.Update(item)
            } else if (item.name == backstagePasses) {
                standardProductManager.updateQuality(item)
                if (item.sellIn < upcomingDays) {
                    standardProductManager.updateQuality(item)
                }
                if (item.sellIn < thisWeek) {
                    standardProductManager.updateQuality(item)
                }
                standardProductManager.decrementSellIn(item)
                if (standardProductManager.isOldProperty(item)) standardProductManager.clearQuality(item)
            } else {
                standardProductManager.decrementQuality(item)
                standardProductManager.decrementSellIn(item)

                if (standardProductManager.isOldProperty(item)) {
                    standardProductManager.decrementQuality(item)
                }
            }
        }
    }


}

class AgedBrieManager(val manager: StandardProductManager) {

    fun Update(item: Item) {
        manager.updateQuality(item)
        manager.decrementSellIn(item)
        if (manager.isOldProperty(item))
            manager.updateQuality(item)
    }
}

class StandardProductManager {
    fun decrementSellIn(item: Item) {
        item.sellIn = item.sellIn - 1
    }

    fun updateQuality(item: Item) {
        if (item.quality < maxQuality) {
            item.quality = item.quality + 1
        }
    }

    fun decrementQuality(item: Item) {
        if (item.quality > minimumQuality) {
            item.quality = item.quality - 1
        }

    }

    fun clearQuality(item: Item) {
        item.quality = 0
    }

    fun isOldProperty(item: Item) = item.sellIn < minimumSellInDays
}

