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

        items.forEach { item ->
            if (item.name == sulfuras)
                return

            when (item.name) {
                agedBrie -> {
                    incrementQuality(item, maxQuality)
                    decrementSellIn(item)
                    if (isOldProperty(item)) incrementQuality(item, maxQuality)
                }
                backstagePasses -> {
                    updateBackstagePassQuality(item, upcomingDays, maxQuality, thisWeek)
                    decrementSellIn(item)
                    if (isOldProperty(item)) clearQuality(item)
                }
                else -> {
                    decrementQuality(item, minimumQuality)
                    decrementSellIn(item)

                    if (isOldProperty(item)) decrementQuality(item, minimumQuality)
                }
            }

//            decrementSellIn(item)

//            if (isOldProperty(item)) {
//                when (item.name) {
//                    agedBrie -> {
//                        incrementQuality(item, maxQuality)
//                    }
//                    backstagePasses -> {
//                        clearQuality(item)
//                    }
//                    else -> {
//                        decrementQuality(item, minimumQuality)
//                    }
//                }
//            }
        }
    }

    private fun isOldProperty(item: Item) = item.sellIn < minimumSellInDays

    private fun clearQuality(item: Item) {
        item.quality = 0
    }

    private fun decrementSellIn(item: Item) {
        item.sellIn = item.sellIn - 1
    }

    private fun decrementQuality(item: Item, minimumQuality: Int) {
        if (item.quality > minimumQuality) {
            item.quality = item.quality - 1
        }
    }

    private fun updateBackstagePassQuality(
        item: Item,
        upcomingDays: Int,
        maxQuality: Int,
        thisWeek: Int
    ) {
        if (item.sellIn < upcomingDays) {
            incrementQuality(item, maxQuality)
        }
        if (item.sellIn < thisWeek) {
            incrementQuality(item, maxQuality)
        }
        incrementQuality(item, maxQuality)
    }

    private fun incrementQuality(item: Item, maxQuality: Int) {
        if (item.quality < maxQuality) {
            item.quality = item.quality + 1
        }
    }

}

