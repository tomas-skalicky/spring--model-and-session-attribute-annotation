package cz.skalicky.spring.modelandsessionattributeannotation.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import cz.skalicky.spring.modelandsessionattributeannotation.config.CarSpringConfig;
import cz.skalicky.spring.modelandsessionattributeannotation.model.CarTypeEnum;

@ContextConfiguration(classes = CarSpringConfig.class)
@WebAppConfiguration
public class UpdateCarOrderIT extends AbstractTestNGSpringContextTests {

    @Inject
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private MockHttpSession mockHttpSession;

    @BeforeMethod
    public void initMockMvc() {

        mockMvc = webAppContextSetup(wac).build();
        mockHttpSession = new MockHttpSession(wac.getServletContext());
    }

    @Test
    public void updateCarType() throws Exception {

        sendUpdateRequest("carType", CarTypeEnum.COMBI.getId());
        sendUpdateRequest("carModel", "Octavia");

        final String getResponseContent = sendGetRequest();
        final ReadContext parsedJson = JsonPath.parse(getResponseContent);

        assertThat(parsedJson.read("$.totalPrice", Integer.class), is(16_000));
    }

    private String sendGetRequest() throws Exception {

        // @formatter:off
        final MvcResult mvcResult = mockMvc
                .perform(
                    get(CarController.GET_CAR_ORDER_URI)
                        .session(mockHttpSession)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();
        // @formatter:on

        final String responseContent = mvcResult.getResponse().getContentAsString();
        return responseContent;
    }

    private void sendUpdateRequest(final String key, final Object value) throws Exception {

        // @formatter:off
        mockMvc
                .perform(
                    put(CarController.UPDATE_CAR_ORDER_URI)
                        .session(mockHttpSession)
                        .param(key, value.toString())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
        // @formatter:on
    }

}
