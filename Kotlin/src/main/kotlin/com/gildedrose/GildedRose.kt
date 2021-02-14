package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        val agedBrie = "Aged Brie"
        val backstagePasses = "Backstage passes to a TAFKAL80ETC concert"
        val sulfuras = "Sulfuras, Hand of Ragnaros"
        val maxQuality = 50
        items.forEach { item ->
            if (item.name != agedBrie && item.name != backstagePasses) {
                if (item.quality > 0) {
                    if (item.name != sulfuras) {
                        item.quality = item.quality - 1
                    }
                }
            } else {
                if (item.quality < maxQuality) {
                    item.quality = item.quality + 1

                    if (item.name == backstagePasses) {
                        val upcomingDays = 11
                        if (item.sellIn < upcomingDays) {
                            if (item.quality < maxQuality) {
                                item.quality = item.quality + 1
                            }
                        }

                        val thisWeek = 6
                        if (item.sellIn < thisWeek) {
                            if (item.quality < maxQuality) {
                                item.quality = item.quality + 1
                            }
                        }
                    }
                }
            }

            if (item.name != sulfuras) {
                item.sellIn = item.sellIn - 1
            }

            if (item.sellIn < 0) {
                if (item.name != agedBrie) {
                    if (item.name != backstagePasses) {
                        if (item.quality > 0) {
                            if (item.name != sulfuras) {
                                item.quality = item.quality - 1
                            }
                        }
                    } else {
                        item.quality = item.quality - item.quality
                    }
                } else {
                    if (item.quality < maxQuality) {
                        item.quality = item.quality + 1
                    }
                }
            }
        }
    }

}

