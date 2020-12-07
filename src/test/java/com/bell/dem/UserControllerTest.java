package com.bell.dem;

import com.bell.dem.view.UserInView;
import com.bell.dem.view.UserOutView;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper om = new ObjectMapper();

    @Test
    public void getByFilterTest() throws Exception {
        UserInView userInView = new UserInView();
        userInView.setOfficeId(2);
        mockMvc.perform(
                post("/api/user/list")
                        .content(om.writeValueAsString(userInView))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()", is(1)))
                .andExpect(jsonPath("$.data[0].id", is(1)))
                .andExpect(jsonPath("$.data[0].firstName", is("John")))
                .andExpect(jsonPath("$.data[0].secondName", is("Doe")))
                .andExpect(jsonPath("$.data[0].middleName").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.data[0].position", is("manager")));
    }

    @Test
    public void getByFilterErrorTest() throws Exception {
        mockMvc.perform(
                post("/api/user/list")
                        .content("{\"officeId\":null}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error", is("Ошибка сервера 422")));
    }

    @Test
    public void getByIdTest() throws Exception {
        UserOutView userOutView = new UserOutView();
        userOutView.setId(1);
        userOutView.setFirstName("John");
        userOutView.setSecondName("Doe");
        userOutView.setPosition("manager");
        userOutView.setPhone("89990001122");
        userOutView.setCitizenshipCode(643);
        userOutView.setIsIdentified(true);
        mockMvc.perform(get("/api/user/1"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id", is(userOutView.getId())))
                .andExpect(jsonPath("$.data.firstName", is(userOutView.getFirstName())))
                .andExpect(jsonPath("$.data.secondName", is(userOutView.getSecondName())))
                .andExpect(jsonPath("$.data.middleName").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.data.position", is(userOutView.getPosition())))
                .andExpect(jsonPath("$.data.phone", is(userOutView.getPhone())))
                .andExpect(jsonPath("$.data.citizenshipCode", is(userOutView.getCitizenshipCode())))
                .andExpect(jsonPath("$.data.isIdentified", is(userOutView.getIsIdentified())));
    }

    @Test
    public void getByIdErrorTest() throws Exception {
        mockMvc.perform(
                get("/api/user/99"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error", is("Ошибка сервера 404")));
    }

    @Test
    public void updateTest() throws Exception {
        Integer id = 2;
        UserInView userInView = new UserInView();
        userInView.setId(id);
        userInView.setFirstName("Обновленное имя");
        userInView.setPosition("Новая должность");
        mockMvc.perform(
                post("/api/user/update")
                        .content(om.writeValueAsString(userInView))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("success")));
        getByView(userInView);
    }

    @Test
    public void updateErrorTest() throws Exception {
        Integer id = 2;
        UserInView userInView = new UserInView();
        userInView.setId(id);
        userInView.setFirstName("Обновленное имя");
        mockMvc.perform(
                post("/api/user/update")
                        .content(om.writeValueAsString(userInView))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error", is("Ошибка сервера 422")));
    }

    @Test
    public void createTest() throws Exception {
        UserInView userInView = new UserInView();
        userInView.setOfficeId(2);
        userInView.setFirstName("Новое имя");
        userInView.setPosition("должность");
        mockMvc.perform(
                post("/api/user/save")
                        .content(om.writeValueAsString(userInView))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("success")));
        userInView.setId(3);
        getByView(userInView);
    }

    @Test
    public void createErrorTest() throws Exception {
        UserInView userInView = new UserInView();
        userInView.setOfficeId(2);
        userInView.setFirstName("Новое имя");
        mockMvc.perform(
                post("/api/user/save")
                        .content(om.writeValueAsString(userInView))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error", is("Ошибка сервера 422")));
    }

    private void getByView(UserInView userInView) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/user/{id}", userInView.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id", is(userInView.getId())))
                .andExpect(jsonPath("$.data.firstName", is(userInView.getFirstName())))
                .andExpect(jsonPath("$.data.position", is(userInView.getPosition())));
    }
}
