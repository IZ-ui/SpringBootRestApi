package com.bell.dem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.bell.dem.view.OrganizationView;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class OrganizationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper om = new ObjectMapper();

    @Test
    public void getByFilterTest() throws Exception {
        OrganizationView organizationView = new OrganizationView();
        organizationView.setName("Zed");
        mockMvc.perform(
                post("/api/organization/list")
                        .content("{\"name\":\"Zed\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()", is(1)))
                .andExpect(jsonPath("$.data[0].id", is(3)))
                .andExpect(jsonPath("$.data[0].name", is("Zed")))
                .andExpect(jsonPath("$.data[0].isActive", is(true)));
    }

    @Test
    public void getByFilterErrorTest() throws Exception {
        mockMvc.perform(
                post("/api/organization/list")
                        .content("{\"name\":null}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.error", is("Organization not found")));
    }

    @Test
    public void getByIdTest() throws Exception {
        OrganizationView organizationView = new OrganizationView();
        organizationView.setId(1);
        organizationView.setName("Альфа");
        organizationView.setFullName("ООО Альфа");
        organizationView.setInn("9977001234");
        organizationView.setKpp("987654321");
        organizationView.setAddress("ул.Мира, 24");
        organizationView.setPhone("84951112233");
        organizationView.setIsActive(true);
        mockMvc.perform(get("/api/organization/1"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id", is(organizationView.getId())))
                .andExpect(jsonPath("$.data.name", is(organizationView.getName())))
                .andExpect(jsonPath("$.data.fullName", is(organizationView.getFullName())))
                .andExpect(jsonPath("$.data.inn", is(organizationView.getInn())))
                .andExpect(jsonPath("$.data.kpp", is(organizationView.getKpp())))
                .andExpect(jsonPath("$.data.address", is(organizationView.getAddress())))
                .andExpect(jsonPath("$.data.phone", is(organizationView.getPhone())))
                .andExpect(jsonPath("$.data.isActive", is(organizationView.getIsActive())));
    }

    @Test
    public void getByIdErrorTest() throws Exception {
        mockMvc.perform(
                get("/api/organization/99"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.error", is("Organization with ID 99 not found")));
    }

    @Test
    public void updateTest() throws Exception {
        Integer id = 2;
        OrganizationView organizationView = new OrganizationView();
        organizationView.setId(id);
        organizationView.setName("Обновленное имя");
        organizationView.setFullName("Обновленное полное имя");
        organizationView.setInn("0001112223");
        organizationView.setKpp("000111222");
        organizationView.setAddress("Обновленный адрес, 1");
        organizationView.setPhone("01234567890");
        organizationView.setIsActive(false);
        mockMvc.perform(
                post("/api/organization/update")
                        .content(om.writeValueAsString(organizationView))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("success")));
        getByView(organizationView);
    }

    @Test
    public void updateErrorTest() throws Exception {
        Integer id = 2;
        OrganizationView organizationView = new OrganizationView();
        organizationView.setId(id);
        organizationView.setName("Обновленное имя");
        organizationView.setFullName("Обновленное полное имя");
        organizationView.setInn("0001112223");
        organizationView.setKpp("000111222");
        mockMvc.perform(
                post("/api/organization/update")
                        .content(om.writeValueAsString(organizationView))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error", is("address must be not empty")));
    }

    @Test
    public void createTest() throws Exception {
        OrganizationView organizationView = new OrganizationView();
        organizationView.setName("Новое имя");
        organizationView.setFullName("Новое полное имя");
        organizationView.setInn("0001112223");
        organizationView.setKpp("000111222");
        organizationView.setAddress("Новый адрес, 1");
        organizationView.setPhone("01234567890");
        organizationView.setIsActive(false);
        mockMvc.perform(
                post("/api/organization/save")
                        .content(om.writeValueAsString(organizationView))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("success")));
        organizationView.setId(4);
        getByView(organizationView);
    }

    @Test
    public void createErrorTest() throws Exception {
        OrganizationView organizationView = new OrganizationView();
        organizationView.setName("Обновленное имя");
        organizationView.setFullName("Обновленное полное имя");
        organizationView.setInn("0001112223");
        organizationView.setKpp("000111222");
        mockMvc.perform(
                post("/api/organization/save")
                        .content(om.writeValueAsString(organizationView))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error", is("address must be not empty")));
    }

    private void getByView(OrganizationView organizationView) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/organization/{id}", organizationView.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id", is(organizationView.getId())))
                .andExpect(jsonPath("$.data.name", is(organizationView.getName())))
                .andExpect(jsonPath("$.data.fullName", is(organizationView.getFullName())))
                .andExpect(jsonPath("$.data.inn", is(organizationView.getInn())))
                .andExpect(jsonPath("$.data.kpp", is(organizationView.getKpp())))
                .andExpect(jsonPath("$.data.address", is(organizationView.getAddress())))
                .andExpect(jsonPath("$.data.phone", is(organizationView.getPhone())))
                .andExpect(jsonPath("$.data.isActive", is(organizationView.getIsActive())));
    }
}
