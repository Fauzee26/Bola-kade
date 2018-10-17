package fauzi.hilmy.bola_kade.server

import fauzi.hilmy.bola_kade.TestContextProvider
import fauzi.hilmy.bola_kade.api.ApiRepository
import com.google.gson.Gson
import fauzi.hilmy.bola_kade.api.TheSportDBApi
import fauzi.hilmy.bola_kade.model.DataLastNext
import fauzi.hilmy.bola_kade.matches.next.NextMatchPresenter
import fauzi.hilmy.bola_kade.matches.next.NextMatchView
import fauzi.hilmy.bola_kade.model.ResponsePrevNext
import fauzi.hilmy.bola_kade.util.MyConstant
import fauzi.hilmy.bola_kade.util.MyConstant.ID_LIGA
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class NextMatchTest {
    @Mock
    lateinit var view: NextMatchView

    @Mock
    lateinit var apiRepository: ApiRepository

    @Mock
    lateinit var gson: Gson

    lateinit var presenter: NextMatchPresenter

    @Before
    fun setupEnv() {
        MockitoAnnotations.initMocks(this)
        presenter = NextMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testgetNext() {
        val teams: MutableList<DataLastNext> = mutableListOf()
        val response = ResponsePrevNext(teams)

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getNext(ID_LIGA)),
                ResponsePrevNext::class.java
        )).thenReturn(response)

        presenter.getNextList(ID_LIGA)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showTeamList(teams)
        Mockito.verify(view).hideLoading()
    }
}