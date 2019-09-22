package com.dsl.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.JsonPath;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class DslAccountControllerTest {

    @Autowired
    DslAccountRepository dslAccountRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void web() throws Exception {
        DslAccount dslAccount = new DslAccount();
        dslAccount.setLName("Hwang");
        dslAccount.setFName("Mike");
        dslAccount.setUserName("sir Donboskko");
        dslAccount.setLesson("math");
        dslAccountRepository.save(dslAccount);

        mockMvc.perform(get("/lesson/" + dslAccount.getId()))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void webs() throws Exception {

        DslAccount dslAccount = new DslAccount();
        dslAccount.setLName("Hwang");
        dslAccount.setFName("Mike");
        dslAccount.setUserName("sir Donboskko");
        dslAccount.setLesson("math");
        dslAccountRepository.save(dslAccount);

        DslAccount dslAccount1 = new DslAccount();
        dslAccount1.setLName("1Hwang");
        dslAccount1.setFName("1Mike");
        dslAccount1.setUserName("sir Donboskko");
        dslAccount1.setLesson("geometry");
        dslAccountRepository.save(dslAccount1);

        DslAccount dslAccount2 = new DslAccount();
        dslAccount1.setLName("2Hwang");
        dslAccount1.setFName("2Mike");
        dslAccount1.setUserName("sir Donboskko");
        dslAccount1.setLesson("statistics");
        dslAccountRepository.save(dslAccount2);



        mockMvc.perform(get("/lessons").param("page", "0")
        .param("size","10")
        .param("sort", "lesson")
        .param("sort", "fName"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].lesson").exists());


    }
    @Test
    public void webs2() throws Exception {

        createAccounts();

        mockMvc.perform(get("/accounts").param("page", "2")
                .param("size","10")
                .param("sort", "lesson"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].lesson").exists());


    }
    public void createAccounts(){
        int cnt =100;
        while(cnt > 0){
            DslAccount dslAccount1 = new DslAccount();
            dslAccount1.setUserName("sir Donboskko");
            dslAccount1.setLesson(cnt + "geometry");
            dslAccountRepository.save(dslAccount1);
            cnt--;
        }

    }

}