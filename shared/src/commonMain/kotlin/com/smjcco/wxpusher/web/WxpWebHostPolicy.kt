package com.smjcco.wxpusher.web

import io.ktor.http.URLBuilder

object WxpWebHostPolicy {
    /**
     * 网安2403 班级管理系统站点（内部使用）。
     * 加入白名单后，页面可正常在 App 内置 WebView 中打开，不显示「第三方页面」提示条。
     */
    const val CLASS_SITE_HOST = "wangan.xdyzf.cn"

    val DEFAULT_WHITELIST_HOSTS = setOf(
        "wxpusher.zjiecode.com",
        "wxpusher.test.zjiecode.com",
        "10.0.0.11",
        "10.0.2.2",
        "127.0.0.1",
        CLASS_SITE_HOST
    )

    fun isHostInWhitelist(host: String?): Boolean {
        return host != null && DEFAULT_WHITELIST_HOSTS.contains(host)
    }

    fun isUrlInWhitelist(url: String?): Boolean {
        if (url.isNullOrBlank()) {
            return false
        }
        val host = runCatching {
            URLBuilder(url).build().host
        }.getOrNull()
        return isHostInWhitelist(host)
    }
}
