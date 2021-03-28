package com.example.siaappquiz.app.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName


data class NytMostPopulerResp(

    val status: String,
    val copyright: String,
    val num_results: Long,
    var results: List<ResultResp>
)


data class ResultResp(
    var uri: String,
    var url: String,
    var id: Long,
    var asset_id: Long,
    var source: String,
    var published_date: String,
    var updated: String,
    var section: String,
    var subsection: String,
    var nytdsection: String,
    var adx_keywords: String,
    var column: Any,
    var byline: String,
    var type: String,
    var title: String,

    var abstract: String,
    var des_facet: List<String>,
    var org_facet: List<String>,
    var per_facet: List<String>,
    var geo_facet: List<String>,
    var media: List<Medum>,
    var eta_id: Long
)

data class Medum(
    val type: String,
    val subtype: String,
    val caption: String,
    var copyright: String,
    var approved_for_syndication: Long,
    @field:SerializedName("media-metadata")
     var media: List<Metadaum>

)

data class Metadaum(
    var url: String,
    var format: String,
    var height: Long,
    var width: Long
)