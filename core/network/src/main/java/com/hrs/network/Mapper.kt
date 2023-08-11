package com.hrs.network
interface Mapper<in L, out R> {
    fun map(from: L): R
}