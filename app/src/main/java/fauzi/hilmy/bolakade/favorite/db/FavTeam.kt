package fauzi.hilmy.bolakade.favorite.db

class FavTeam(
        val id: Long?,
        val teamId: String?,
        val teamName: String?,
        val teamLogo: String?,
        val teamYear: String?,
        val teamManager: String?,
        val teamStadium: String?,
        val teamDescription: String?) {
    companion object {
        const val TABLE_TEAM: String = "TABLE_TEAM"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_LOGO: String = "TEAM_LOGO"
        const val TEAM_YEAR: String = "TEAM_YEAR"
        const val TEAM_MANAGER: String = "TEAM_MANAGER"
        const val TEAM_STADIUM: String = "TEAM_STADIUM"
        const val TEAM_DESCRIPTION: String = "TEAM_DESCRIPTION"
    }
}