package fauzi.hilmy.bola_kade.util

import org.junit.Assert.assertEquals
import org.junit.Test

class UtilTest {

    @Test
    fun formatDate() {
        assertEquals("Friday, 12 Oct 2018", Util.FormatDate("2018-10-12"))
    }

    @Test
    fun formatTime() {
        assertEquals("18:00 WIB", Util.timeFormat("11:00:00+00:00"))
    }

    @Test
    fun formatPlayer() {
        assertEquals("Hector Bellerin, Shkodran Mustafi, Sokratis Papastathopoulos, Nacho Monreal,", Util.formatPlayer("Hector Bellerin; Shkodran Mustafi; Sokratis Papastathopoulos; Nacho Monreal;"))
    }

    @Test
    fun formatNumPlayer() {
        assertEquals("Luciano Dario Vietto 57'\nAndre Schuerrle 70'\n\n", Util.formatNumPlayer("57':Luciano Dario Vietto;70':Andre Schuerrle;"))
    }

}