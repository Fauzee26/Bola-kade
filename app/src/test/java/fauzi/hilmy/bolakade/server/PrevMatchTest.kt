package fauzi.hilmy.bolakade.server

import fauzi.hilmy.bolakade.TestContextProvider
import fauzi.hilmy.bolakade.api.ApiRepository
import com.google.gson.Gson
import fauzi.hilmy.bolakade.api.TheSportDBApi
import fauzi.hilmy.bolakade.model.match.DataLastNext
import fauzi.hilmy.bolakade.matches.prev.PrevMatchPresenter
import fauzi.hilmy.bolakade.matches.prev.PrevMatchView
import fauzi.hilmy.bolakade.model.match.ResponsePrevNext
import fauzi.hilmy.bolakade.util.MyConstant.ID_LIGA
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class PrevMatchTest {
    @Mock
    lateinit var view: PrevMatchView

    @Mock
    lateinit var apiRepository: ApiRepository

    @Mock
    lateinit var gson: Gson

    lateinit var presenter: PrevMatchPresenter

    @Before
    fun setupEnv() {
        MockitoAnnotations.initMocks(this)
        presenter = PrevMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testgetPrevMatch() {
        val teams: MutableList<DataLastNext> = mutableListOf()
        val response = ResponsePrevNext(teams)

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getPrev(ID_LIGA)),
                ResponsePrevNext::class.java
        )).thenReturn(response)

        presenter.getPrevList(ID_LIGA)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showTeamList(teams)
        Mockito.verify(view).hideLoading()
    }
}