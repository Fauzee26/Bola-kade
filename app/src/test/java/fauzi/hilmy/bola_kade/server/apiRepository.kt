package fauzi.hilmy.bola_kade.server

import com.dicoding.kotlinacademy.api.ApiRepository
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class apiRepository {
    @Test
    fun testDoRequest() {
        val apiRepository = mock(ApiRepository::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League"
        apiRepository.doRequest(url)
        verify(apiRepository).doRequest(url)
    }
}