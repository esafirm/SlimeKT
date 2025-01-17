/*
 * Copyright (C) 2021, Kasem S.M
 * All rights reserved.
 */
package kasem.sm.article.domain.observers

import javax.inject.Inject
import kasem.sm.article.datasource.cache.ArticleDatabaseService
import kasem.sm.article.domain.interactors.toDomain
import kasem.sm.article.domain.model.Article
import kasem.sm.core.domain.ObserverInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ObserveArticles @Inject constructor(
    private val cache: ArticleDatabaseService,
) : ObserverInteractor<String, List<Article>>() {
    override suspend fun execute(params: String): Flow<List<Article>> {
        val articles = cache.getAllArticles(
            query = params
        ).map {
            it.toDomain()
        }
        return articles
    }
}

class ObserveArticlesByTopic @Inject constructor(
    private val cache: ArticleDatabaseService,
) : ObserverInteractor<String, List<Article>>() {
    override suspend fun execute(params: String): Flow<List<Article>> {
        val pagedArticles = cache.getArticlesByTopic(
            topic = params
        ).map {
            it.toDomain()
        }
        return pagedArticles
    }
}
