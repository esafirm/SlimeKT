/*
 * Copyright (C) 2021, Kasem S.M
 * All rights reserved.
 */
package kasem.sm.article.domain.interactors

import javax.inject.Inject
import kasem.sm.article.datasource.cache.ArticleDatabaseService
import kasem.sm.article.datasource.network.ArticleApiService
import kasem.sm.core.domain.SlimeDispatchers
import kasem.sm.core.domain.Stage
import kasem.sm.core.domain.start
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class GetArticle @Inject constructor(
    private val api: ArticleApiService,
    private val cache: ArticleDatabaseService,
    private val applicationScope: CoroutineScope,
    private val dispatchers: SlimeDispatchers,
) {
    fun execute(articleId: Int): Flow<Stage> {
        return dispatchers.default.start {
            val articleFromApi = api.getArticleById(articleId)
                .getOrThrow()

            articleFromApi?.data?.let {
                applicationScope.launch {
                    val triple = cache.getRespectiveTriplets(it.id)
                    cache.insert(it.toEntity(triple))
                }.join()
            }
        }
    }
}