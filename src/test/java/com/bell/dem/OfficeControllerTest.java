package com.bell.dem;

import com.bell.dem.view.OfficeInView;
import com.bell.dem.view.OfficeOutView;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class OfficeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper om = new ObjectMapper();

    @Test
    public void getByFilterTest() throws Exception {
        OfficeInView officeInView = new OfficeInView();
        officeInView.setOrgId(2);
        mockMvc.perform(
                post("/api/office/list")
                        .content(om.writeValueAsString(officeInView))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()", is(2)))
                .andExpect(jsonPath("$.data[0].id", is(1)))
                .andExpect(jsonPath("$.data[0].name", is("Офис1 Альфа")))
                .andExpect(jsonPath("$.data[0].isActive", is(true)))
                .andExpect(jsonPath("$.data[1].id", is(2)))
                .andExpect(jsonPath("$.data[1].name", is("Офис2 Альфа")))
                .andExpect(jsonPath("$.data[1].isActive", is(true)));
    }

    @Test
    public void getByFilterErrorTest() throws Exception {
        OfficeInView officeInView = new OfficeInView();
        officeInView.setOrgId(99);
        mockMvc.perform(
                post("/api/office/list")
                        .content(om.writeValueAsString(officeInView))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error", is("Ошибка сервера 404")));
    }

    @Test
    public void getByIdTest() throws Exception {
        OfficeOutView officeOutView = new OfficeOutView();
        officeOutView.setId(1);
        officeOutView.setName("Офис1 Альфа");
        officeOutView.setAddress("ул.Мира, 24");
        officeOutView.setPhone("84951112233");
        officeOutView.setIsActive(true);
        mockMvc.perform(get("/api/office/1"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id", is(officeOutView.getId())))
                .andExpect(jsonPath("$.data.name", is(officeOutView.getName())))
                .andExpect(jsonPath("$.data.address", is(officeOutView.getAddress())))
                .andExpect(jsonPath("$.data.phone", is(officeOutView.getPhone())))
                .andExpect(jsonPath("$.data.isActive", is(officeOutView.getIsActive())));
    }

    @Test
    public void getByIdErrorTest() throws Exception {
        mockMvc.perform(
                get("/api/office/99"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error", is("Ошибка сервера 404")));
    }

    @Test
    public void updateTest() throws Exception {
        Integer id = 2;
        OfficeInView officeInView = new OfficeInView();
        officeInView.setId(id);
        officeInView.setName("Обновленное имя");
        officeInView.setAddress("Обновленный адрес, 1");
        officeInView.setPhone("01234567890");
        officeInView.setIsActive(false);
        mockMvc.perform(
                post("/api/office/update")
                        .content(om.writeValueAsString(officeInView))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("success")));
        getByView(officeInView);
    }

    @Test
    public void updateErrorTest() throws Exception {
        Integer id = 2;
        OfficeInView officeInView = new OfficeInView();
        officeInView.setId(id);
        officeInView.setName("Обновленное имя");
        mockMvc.perform(
                post("/api/office/update")
                        .content(om.writeValueAsString(officeInView))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error", is("Ошибка сервера 422")));
    }

    @Test
    public void createTest() throws Exception {
        OfficeInView officeInView = new OfficeInView();
        officeInView.setOrgId(1);
        officeInView.setName("Новое имя");
        officeInView.setAddress("Новый адрес, 1");
        officeInView.setPhone("01234567890");
        officeInView.setIsActive(false);
        mockMvc.perform(
                post("/api/office/save")
                        .content(om.writeValueAsString(officeInView))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("success")));
        officeInView.setId(4);
        getByView(officeInView);
    }

    @Test
    public void createErrorTest() throws Exception {
        OfficeInView officeInView = new OfficeInView();
        officeInView.setName("Обновленное имя");
        mockMvc.perform(
                post("/api/office/save")
                        .content(om.writeValueAsString(officeInView))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error", is("Ошибка сервера 422")));
    }

    private void getByView(OfficeInView officeInView) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/office/{id}", officeInView.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id", is(officeInView.getId())))
                .andExpect(jsonPath("$.data.name", is(officeInView.getName())))
                .andExpect(jsonPath("$.data.address", is(officeInView.getAddress())))
                .andExpect(jsonPath("$.data.phone", is(officeInView.getPhone())))
                .andExpect(jsonPath("$.data.isActive", is(officeInView.getIsActive())));
    }
}
