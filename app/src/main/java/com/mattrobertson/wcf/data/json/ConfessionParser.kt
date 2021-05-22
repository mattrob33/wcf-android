package com.mattrobertson.wcf.data.json

import com.mattrobertson.wcf.WCFApplication
import com.mattrobertson.wcf.data.json.model.ConfessionJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class ConfessionParser {

    private val moshi = Moshi.Builder().build()
    private val confessionJsonAdapter: JsonAdapter<ConfessionJson> = moshi.adapter(ConfessionJson::class.java)

    private val assets = WCFApplication.context.assets
    private val json = assets.open("wcf.json").bufferedReader().use { it.readText() }

    val jsonConfession = confessionJsonAdapter.fromJson(json)!!

    val confession = mapJsonToConfession(jsonConfession)

}