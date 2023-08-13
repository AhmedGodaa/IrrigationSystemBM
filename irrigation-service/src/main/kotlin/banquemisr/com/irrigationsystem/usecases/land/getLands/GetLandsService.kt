package banquemisr.com.irrigationsystem.usecases.land.getLands

interface GetLandsService {
    fun getLands(getLandsRequest: GetLandsRequest?): GetLandsResponse?
}