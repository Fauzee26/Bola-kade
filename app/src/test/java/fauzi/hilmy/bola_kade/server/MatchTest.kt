package fauzi.hilmy.bola_kade.server

import fauzi.hilmy.bola_kade.TestContextProvider
import fauzi.hilmy.bola_kade.api.ApiRepository
import com.google.gson.Gson
import fauzi.hilmy.bola_kade.api.TheSportDBApi
import fauzi.hilmy.bola_kade.model.DataLastNext
import fauzi.hilmy.bola_kade.model.MatchPresenter
import fauzi.hilmy.bola_kade.model.MatchView
import fauzi.hilmy.bola_kade.model.ResponseLastNext
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MatchTest {
    @Mock
    lateinit var view: MatchView

    @Mock
    lateinit var apiRepository: ApiRepository

    @Mock
    lateinit var gson: Gson

    lateinit var presenter: MatchPresenter

    @Before
    fun setupEnv() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetTeamList() {
        val teams: MutableList<DataLastNext> = mutableListOf()
        val response = ResponseLastNext(teams)
        val league = "English Premiere League"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getNext(league)),
                ResponseLastNext::class.java
        )).thenReturn(response)

        presenter.getTeamList(league)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showTeamList(teams)
        Mockito.verify(view).hideLoading()
    }
}