package ru.hogwarts.school.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.hogwarts.school.model.*;
import ru.hogwarts.school.repository.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class StudentControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private StudentRepository studentRepository;

    private Faculty faculty = new Faculty();
    private Student student = new Student();
    private JSONObject jsonObject = new JSONObject();
    private JSONObject studentJson = new JSONObject();


    @BeforeEach
    public void setUp() throws JSONException {
        faculty.setName("fuc");
        faculty.setColor("col");
        facultyRepository.save(faculty);

        student.setName("Ann");
        student.setAge(15);
        student.setFaculty(faculty);
        studentRepository.save(student);

        jsonObject.put("id", 0).put("name", "Bob").put("age", 15);
        jsonObject.put("facultyId", faculty.getId());
    }

    @AfterEach
    void tearDown() {
        studentRepository.deleteAll();
        facultyRepository.deleteAll();
    }

    @Test
    void createStudent() throws Exception {

        mockMvc.perform(post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value(jsonObject.get("name")))
                .andExpect(jsonPath("$.age").value(jsonObject.get("age")))
                .andExpect(jsonPath("$.facultyId").value(faculty.getId()));
    }

    @Test
    void editStudent() throws Exception {

        mockMvc.perform(patch("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJson
                                .put("id",student.getId())
                                .put("name", "Den")
                                .put("age",12)
                                .put("facultyId", faculty.getId())
                                .toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Den"))
                .andExpect(jsonPath("$.age").value(12))
                .andExpect(jsonPath("$.facultyId").value(faculty.getId()));
    }

    @Test
    void deleteStudent() throws Exception {

        mockMvc.perform(delete("/student/" + student.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void getStudent() throws Exception {

        mockMvc.perform(get("/student/" + student.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect(jsonPath("$.age").value(student.getAge()))
                .andExpect(jsonPath("$.facultyId").value(faculty.getId()));
    }

    @Test
    void getStudentsByAge() throws Exception {
        mockMvc.perform(get("/student?age=" + student.getAge()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].age").value(student.getAge()));

    }

    @Test
    void getFacultyByStudentId() throws Exception {
        mockMvc.perform(get("/student/" + student.getId() + "/faculty"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(faculty.getName()))
                .andExpect(jsonPath("$.color").value(faculty.getColor()))
                .andExpect(jsonPath("$.id").value(faculty.getId()));
    }

    @Test
    void getMidlifeStudents() throws Exception {

        mockMvc.perform(get("/student/midlife"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber());
    }

    @Test
    void getYoungestStudents() throws Exception {
        mockMvc.perform(get("/student/youngest"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].age").value(student.getAge()));
    }

}