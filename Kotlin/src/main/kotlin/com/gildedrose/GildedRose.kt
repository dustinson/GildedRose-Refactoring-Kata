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
            if (item.name == agedBrie) {
                if (item.name == backstagePasses) {
                    if (item.sellIn < upcomingDays) {
                        incrementQuality(item, maxQuality)
                    }

                    if (item.sellIn < thisWeek) {
                        incrementQuality(item, maxQuality)
                    }
                }
                incrementQuality(item, maxQuality)
            } else {
                if (item.name == backstagePasses) {
                    if (item.sellIn < upcomingDays) {
                        incrementQuality(item, maxQuality)
                    }

                    if (item.sellIn < thisWeek) {
                        incrementQuality(item, maxQuality)
                    }
                    incrementQuality(item, maxQuality)
                } else {
                    if (item.quality > minimumQuality) {
                        if (item.name == sulfuras) {
                        } else {
                            item.quality = item.quality - 1
                        }
                    }
                }
            }

            if (item.name == sulfuras) {
            } else {
                item.sellIn = item.sellIn - 1
            }

            if (item.sellIn < minimumSellInDays) {
                if (item.name == agedBrie) {
                    incrementQuality(item, maxQuality)
                } else {
                    if (item.name == backstagePasses) {
                        item.quality = item.quality - item.quality
                    } else {
                        if (item.quality > minimumQuality) {
                            if (item.name == sulfuras) {
                            } else {
                                item.quality = item.quality - 1
                            }
                        }
                    }
                }
            }
        }
    }

    private fun incrementQuality(item: Item, maxQuality: Int) {
        if (item.quality < maxQuality) {
            item.quality = item.quality + 1
        }
    }

}

