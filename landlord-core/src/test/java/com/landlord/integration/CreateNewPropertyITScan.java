package com.landlord.integration;


import com.landlord.config.Config;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@ContextConfiguration(classes = Config.class)
@CucumberOptions(format = "json:target/cucumber-report.json")
public class CreateNewPropertyITScan {
}
