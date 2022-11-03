package com.increff.Assure.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = QAConfig.class, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration("src/test/webapp")
@Transactional
public class ChannelListingControllerTest {

    @Test
    public void add()
    {

    }
}
