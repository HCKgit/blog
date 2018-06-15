/* ----------------------------------------------------------------------------
From https://spring.io/guides/tutorials/spring-boot-kotlin/
Development fo Digital Ocean Server Test and Kotlin project piloting

The recommended way to manage your application properties is to leverage
@ConfigurationProperties. Immutable properties are not yet supported,
but you can use lateinit var when you need to deal with non-nullable properties.

MehStuff.com & HCK copyright 2018
---------------------------------------------------------------------------- */

package com.example.blog

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("blog")
class BlogProperties {

    lateinit var title: String
    val banner = Banner()

    class Banner {
        var title: String? = null
        lateinit var content: String
    }
}