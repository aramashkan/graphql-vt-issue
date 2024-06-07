package com.example.graphql.graphql_vt_issue

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class GraphqlVtIssueApplication: ApplicationContextInitializer<GenericApplicationContext> {
	override fun initialize(applicationContext: GenericApplicationContext) {
		authInitializer.initialize(applicationContext)
	}
}

internal val authInitializer = beans {
	bean<MessageResource>()
}

fun main(args: Array<String>) {
	runApplication<GraphqlVtIssueApplication>(*args)
}
