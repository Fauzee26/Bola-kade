package fauzi.hilmy.bolakade.server

import fauzi.hilmy.bolakade.TestContextProvider
import fauzi.hilmy.bolakade.api.ApiRepository
import com.google.gson.Gson
import fauzi.hilmy.bolakade.api.TheSportDBApi
import fauzi.hilmy.bolakade.model.match.DataLastNext
import fauzi.hilmy.bolakade.matches.next.NextMatchPresenter
import fauzi.hilmy.bolakade.matches.next.NextMatchView
import fauzi.hilmy.bolakade.model.match.ResponsePrevNext
import fauzi.hilmy.bolakade.util.MyConstant.ID_LIGA
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