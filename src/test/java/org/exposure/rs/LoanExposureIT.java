package org.exposure.rs;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.validation.Const.MAX_AMOUNT;
import static org.validation.Const.MIN_TERM;

import lombok.NoArgsConstructor;
import org.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(LoanExposure.class)
@ContextConfiguration(classes = {Application.class})
@NoArgsConstructor
public class LoanExposureIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldApplyWhenCorrectParamsPassed() throws Exception {
        this.mockMvc.perform(post("/apply")
            .param("amount", "2.22")
            .param("term", "2007-07-16T19:20:30.45"))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    public void shouldNotApplyWhenMaxAmountAndTermBetween0And6() throws Exception {
        this.mockMvc.perform(post("/apply")
            .param("amount", MAX_AMOUNT)
            .param("term", "2007-07-16T05:20:30.45"))
            .andDo(print())
            .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotApplyWhenMaxTermExceed() throws Exception {
        this.mockMvc.perform(post("/apply")
            .param("amount", MAX_AMOUNT)
            .param("term", "2020-02-01T19:00:00.00"))
            .andDo(print())
            .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotApplyWhenMaxAmountExceed() throws Exception {
        this.mockMvc.perform(post("/apply")
            .param("amount", "10000.00")
            .param("term", MIN_TERM))
            .andDo(print())
            .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotApplyWhenTermIsBelowMinTerm() throws Exception {
        this.mockMvc.perform(post("/apply")
            .param("amount", "2.22")
            .param("term", "1990-07-16T19:20:30.45"))
            .andDo(print())
            .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotApplyWhenMissingParams() throws Exception {
        this.mockMvc.perform(post("/apply")
            .param("term", "1990-07-16T19:20:30.45"))
            .andDo(print())
            .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldExtend() {
    }
}