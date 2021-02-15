package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        val agedBrie = "Aged Brie"
        val backstagePasses = "Backstage passes to a TAFKAL80ETC concert"
        val sulfuras = "Sulfuras, Hand of Ragnaros"
        val maxQuality = 50
        val minimumSellInDays = 0
        val minimumQuality = 0
        val upcomingDays = 11
        val thisWeek = 6

        items.forEach { item ->
            when (item.name) {
                sulfuras -> return
                agedBrie -> {
                    incrementQuality(item, maxQuality)
                }
                backstagePasses -> {
                    updateBackstagePassQuality(item, upcomingDays, maxQuality, thisWeek)
                }
                else -> {
                    decrementQuality(item, minimumQuality)
                }
            }

            decrementSellIn(item)

            if (item.sellIn < minimumSellInDays) {
                when (item.name) {
                    agedBrie -> {
                        incrementQuality(item, maxQuality)
                    }
                    backstagePasses -> {
                        clearQuality(item)
                    }
                    else -> {
                        decrementQuality(item, minimumQuality)
                    }
                }

            }
        }
    }

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

