package com.gildedrose

private const val minimumSellInDays = 0
private const val maxQuality = 50
private const val minimumQuality = 0

class GildedRose(var items: Array<Item>) {
    private val agedBrie = "Aged Brie"
    private val backstagePasses = "Backstage passes to a TAFKAL80ETC concert"
    private val sulfuras = "Sulfuras, Hand of Ragnaros"

    fun updateQuality() {
        var standardProductManager = StandardProductManager()
        var agedBrieManager = AgedBrieManager(standardProductManager)
        var backstagePassManager = BackstagePassManager(standardProductManager)
        var sulfurasManager = SulfurasManager(standardProductManager)

        items.forEach { item ->
            var manager: IProductManager
            when (item.name) {
                sulfuras -> manager = sulfurasManager
                agedBrie -> {
                    manager = agedBrieManager
                }
                backstagePasses -> {
                    manager = backstagePassManager
                }
                else -> {
                    manager = standardProductManager
                }
            }
            manager.Update(item)
        }
    }
}

class SulfurasManager(val manager: StandardProductManager) : IProductManager {
    override fun Update(item: Item) {
        return
    }

}

interface IProductManager {
    fun Update(item: Item)
}

class BackstagePassManager(val manager: StandardProductManager) : IProductManager {
    private val upcomingDays = 11
    private val thisWeek = 6

    override fun Update(item: Item) {
        manager.updateQuality(item)
        if (item.sellIn < upcomingDays) {
            manager.updateQuality(item)
        }
        if (item.sellIn < thisWeek) {
            manager.updateQuality(item)
        }
        manager.decrementSellIn(item)
        if (manager.isOldProperty(item)) manager.clearQuality(item)
    }
}

class AgedBrieManager(val manager: StandardProductManager) : IProductManager {

    override fun Update(item: Item) {
        manager.updateQuality(item)
        manager.decrementSellIn(item)
        if (manager.isOldProperty(item))
            manager.updateQuality(item)
    }
}

class StandardProductManager : IProductManager {
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

    override fun Update(item: Item) {
        decrementQuality(item)
        decrementSellIn(item)

        if (isOldProperty(item)) {
            decrementQuality(item)
        }
    }
}

