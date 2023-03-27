package ru.hogwarts.school.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class HouseControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    private FacultyRepository facultyRepository;

    private Faculty faculty = new Faculty();
    private JSONObject houseJson = new JSONObject();

    @BeforeEach
    void setUp()  {
        faculty.setName("fac");
        faculty.setColor("cool");
        facultyRepository.save(faculty);
    }

    @AfterEach
    void tearDown() {
        facultyRepository.deleteAll();
    }

    @Test
    void createFaculty() throws Exception {
        mockMvc.perform(post("/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( houseJson
                                .put("id",0)
                                .put("name","test")
                                .put("color", "anyColor")
                                .toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.color").value("anyColor"));

    }

    @Test
    void deleteFaculty() throws Exception {
        mockMvc.perform(delete("/faculty/" + faculty.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void getFaculty() throws Exception {
        mockMvc.perform(get("/faculty/" + faculty.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(faculty.getName()))
                .andExpect(jsonPath("$.color").value(faculty.getColor()));
    }

    @Test
    void getFacultiesByColor() throws Exception {
        mockMvc.perform(get("/faculty?color=" + faculty.getColor()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].color").value(faculty.getColor()));
    }

    @Test
    void getFacultiesByName() throws Exception {
        mockMvc.perform(get("/faculty?name=" + faculty.getName()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value(faculty.getName()));
    }
    @Test
    void getStudentsByFacultyId() throws Exception {
        mockMvc.perform(get("/faculty/" + faculty.getId() + "/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}