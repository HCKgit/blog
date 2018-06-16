package com.example.blog

import com.samskivert.mustache.Mustache
import org.springframework.boot.Banner
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableConfigurationProperties(BlogProperties::class)
class BlogApplication{


// For deployment notes ... when running standalone ... not needed.
//
//    If you intend to start your application as a war or as an executable application, you need to share the customizations of the builder in a method that is both available to the SpringBootServletInitializer callback and in the main method in a class similar to the following:
//
//    @SpringBootApplication
//    public class Application extends SpringBootServletInitializer {
//
//        @Override
//        protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//            return configureApplication(builder);
//        }
//
//        public static void main(String[] args) {
//            configureApplication(new SpringApplicationBuilder()).run(args);
//        }
//
//        private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
//            return builder.sources(Application.class).bannerMode(Banner.Mode.OFF);
//        }
//
//    //  private fun configureApplication(builder: SpringApplicationBuilder): SpringApplicationBuilder {
//    //       return builder.sources(BlogApplication::class.java!!)
//    //                 //.bannerMode(Banner.Mode.OFF)
//    //  }
//
//    }

    // The nullable Mustache.TemplateLoader? means that it is an
    // optional bean (in order to avoid failure when running JPA-only tests).
    @Bean
    fun mustacheCompiler(loader: Mustache.TemplateLoader?) =
            Mustache.compiler().escapeHTML(false).withLoader(loader)


    @Bean
    fun databaseInitializer(userRepository: UserRepository,
                            articleRepository: ArticleRepository) = CommandLineRunner {

        val smaldini = User("smaldini", "Stéphane", "Maldini")
        userRepository
                .save(smaldini)

        articleRepository
                .save(Article(
                "Reactor Bismuth is out",
                "Lorem ipsum",
                "dolor **sit** amet https://projectreactor.io/",
                smaldini,
                1
        ))
        articleRepository
                .save(Article(
                "Reactor Aluminium has landed",
                "Lorem ipsum",
                "dolor **sit** amet https://projectreactor.io/",
                smaldini,
                2
        ))
    }
}

fun main(args: Array<String>) {
    runApplication<BlogApplication>(*args){
        setBannerMode(Banner.Mode.CONSOLE)
    }
}
