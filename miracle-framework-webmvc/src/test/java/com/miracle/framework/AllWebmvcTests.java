package com.miracle.framework;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.miracle.framework.webmvc.controller.ApiDataNotFoundErrorHandlerTest;
import com.miracle.framework.webmvc.controller.ApiValidateErrorHandlerTest;
import com.miracle.framework.webmvc.vo.FeedbacksTest;

@RunWith(Suite.class)
@SuiteClasses({
		FeedbacksTest.class, 
		ApiValidateErrorHandlerTest.class, 
		ApiDataNotFoundErrorHandlerTest.class
		})
public final class AllWebmvcTests { }
