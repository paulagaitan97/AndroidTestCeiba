package com.gaitan.dev.post_data.repository

import com.gaitan.dev.core.utils.Resource
import com.gaitan.dev.post_data.util.mockedPostRelation
import com.gaitan.dev.post_data.util.mockedUserIdCode
import com.gaitan.dev.post_domain.repository.PostRepository
import com.gaitan.dev.post_domain.usecase.GetPostsByUserCase
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FlowCallsPostTest {

    @Mock
    lateinit var repository: PostRepository

    private lateinit var listPostByUser: GetPostsByUserCase


    @Before
    fun setUp() {
        listPostByUser = GetPostsByUserCase(repository)
    }


    @Test
    fun `invoke calls repository with text name`() {
        runBlocking {
            val postRelationUsers = listOf(mockedPostRelation)
            val userIdCode = mockedUserIdCode
            whenever(repository.getPostsByUser(userIdCode)).thenReturn(flow {
                Resource.Success(postRelationUsers)
                }
            )
            when (val result = listPostByUser.invoke(userIdCode)) {
                is Resource.Success<*> -> {
                    assertEquals(postRelationUsers, result.data)
                }
            }
        }
    }
}
