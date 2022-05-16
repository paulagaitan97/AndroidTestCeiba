package com.gaitan.dev.user_data.repository

import com.gaitan.dev.core.utils.Resource
import com.gaitan.dev.user_data.util.mockError
import com.gaitan.dev.user_data.util.mockNameSearch
import com.gaitan.dev.user_data.util.mockedUser
import com.gaitan.dev.user_domain.repository.UserRepository
import com.gaitan.dev.user_domain.usecase.GetUsersByNameCase
import com.gaitan.dev.user_domain.usecase.GetUsersCase
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
class FlowCallsTest {

    @Mock
    lateinit var repository: UserRepository

    private lateinit var listUser: GetUsersCase

    private lateinit var searchByName: GetUsersByNameCase

    @Before
    fun setUp() {
        listUser = GetUsersCase(repository)
        searchByName = GetUsersByNameCase(repository)
    }

    @Test
    fun `invoke calls repository`() {
        runBlocking {
            val users = listOf(mockedUser.copy(id = 1))
            whenever(repository.getUsers()).thenReturn(flow {
                Resource.Success(users)
                }
            )
            when (val result = listUser.invoke()) {
                is Resource.Success<*> -> {
                    assertEquals(users, result.data)
                }
            }
        }
    }


    @Test
    fun `invoke calls repository with error`() {
        runBlocking {
            val error = mockError
            whenever(repository.getUsers()).thenReturn(flow {
                Resource.Fail(mockError, null)
                }
            )
            when (val result = listUser.invoke()) {
                is Resource.Fail<*> -> {
                    assertEquals(error, result.data)
                }
            }
        }
    }

    @Test
    fun `invoke calls repository with text name`() {
        runBlocking {
            val users = listOf(mockedUser.copy(id = 1))
            val nameSearch = mockNameSearch
            whenever(repository.getUsersByName(nameSearch)).thenReturn(flow {
                Resource.Success(users)
                }
            )
            when (val result = searchByName.invoke(nameSearch)) {
                is Resource.Success<*> -> {
                    assertEquals(users, result.data)
                }
            }
        }
    }
}
