package banquemisr.com.irrigationsystem.controller

import banquemisr.com.irrigationsystem.model.Land
import banquemisr.com.irrigationsystem.model.dto.request.ChangeStatusRequest
import banquemisr.com.irrigationsystem.model.dto.request.LandIrrigateRequest
import banquemisr.com.irrigationsystem.toJson
import banquemisr.com.irrigationsystem.utils.Constants.BASE_URL
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class LandControllerImplTest {


    @Autowired
    lateinit var mockMvc: MockMvc


    @Test
    fun getLandById() {
        val url = "$BASE_URL/api/getLandById"
        mockMvc.perform(
            MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("id", "645d6f762334870f406fa264")
        )
            .andExpect(MockMvcResultMatchers.status().isOk())

    }

    @Test
    fun getListOfLand() {
        val url = "$BASE_URL/api/getAllLands"
        mockMvc.perform(
            MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
            .andExpect(MockMvcResultMatchers.status().isOk())

    }

    @Test
    fun updateLand() {
        val land = Land(
            id = "64616db5f410750aeb7344e1",
            name = "Land 1",
            area = 100.0,
            location = "Egypt",
            irrigationDate = "123456",
            irrigationEndDate = "123456"
        )
        val url = "$BASE_URL/api/updateLand"
        mockMvc
            .perform(
                MockMvcRequestBuilders.post(url)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(land.toJson())
            )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun createLand() {
        val land = Land(
            id = "123456789",
            name = "Land Test",
            area = 100.0,
            location = "Egypt",
            irrigationDate = "123456",
            irrigationEndDate = "123456"
        )
        val url = "$BASE_URL/api/createLand"
        mockMvc
            .perform(
                MockMvcRequestBuilders.post(url)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(land.toJson())
            )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun deleteLand() {
        val url = "$BASE_URL/api/deleteLand"
        mockMvc.perform(
            MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("id", "123456789")
        )
            .andExpect(MockMvcResultMatchers.status().isOk())
    }


    @Test
    fun irrigate() {
        val irrigate = LandIrrigateRequest(
            id = "64616db5f410750aeb7344e1",
            irrigationDate = "123456",
            irrigationEndDate = "123456"
        )
        val url = "$BASE_URL/api/irrigate"
        mockMvc.perform(
            MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(irrigate.toJson())
        )
            .andExpect(MockMvcResultMatchers.status().isOk())
    }

    @Test
    fun changeStatus() {
        val url = "$BASE_URL/api/changeStatus"
        val irrigationStatus = ChangeStatusRequest(
            id = "64616db5f410750aeb7344e1",
            irrigationStatus = true
        )
        mockMvc.perform(
            MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(irrigationStatus.toJson())
        )
            .andExpect(MockMvcResultMatchers.status().isOk())
    }
}