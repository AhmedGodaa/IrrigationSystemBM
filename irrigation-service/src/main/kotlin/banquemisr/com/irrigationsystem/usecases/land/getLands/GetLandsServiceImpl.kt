package banquemisr.com.irrigationsystem.usecases.land.getLands

import org.springframework.stereotype.Service

@Service
class GetLandsServiceImpl(
    private val getAllLandsValidator: GetLandsValidator
) : GetLandsService{
    override fun getLands(getLandsRequest: GetLandsRequest?): GetLandsResponse? {
        getAllLandsValidator.validate(getLandsRequest)
        return null
    }
}