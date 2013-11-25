package com.acjvstest.selenium;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.*;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		format={"pretty", "html:target/cucumber"},
		features="src/com/acjvstest/features"
		)
public class CukesRunner {}
