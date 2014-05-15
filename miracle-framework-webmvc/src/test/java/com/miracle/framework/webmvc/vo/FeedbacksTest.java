package com.miracle.framework.webmvc.vo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.miracle.framework.repository.jpa.repository.exception.PrimaryKeyNotFoundException;

public final class FeedbacksTest {
	
	@Test
	public void getValidationFeedbacks() {
		Feedbacks actual = new Feedbacks();
		Feedback actualFeedback1 = new Feedback("test.code.1", "invalid data 1");
		Feedback actualFeedback2 = new Feedback("test.code.2", "invalid data 2");
		actual.addFeedback(actualFeedback1);
		actual.addFeedback(actualFeedback2);
		assertThat(actual.getFeedbacks(), is((Collection<Feedback>) Lists.newArrayList(actualFeedback1, actualFeedback2)));
	}
	
	@Test
	public void getPrimaryKeyNotFoundFeedbacks() {
		Feedbacks actual = new Feedbacks();
		PrimaryKeyNotFoundException ex1 = new PrimaryKeyNotFoundException(Object.class, "1");
		Feedback actualFeedback1 = new Feedback(ex1.getErrorCode(), ex1.getArguments());
		PrimaryKeyNotFoundException ex2 = new PrimaryKeyNotFoundException(String.class, "2");
		Feedback actualFeedback2 = new Feedback(ex2.getErrorCode(), ex2.getArguments());
		actual.addFeedback(actualFeedback1);
		actual.addFeedback(actualFeedback2);
		assertThat(actual.getFeedbacks(), is((Collection<Feedback>) Lists.newArrayList(actualFeedback1, actualFeedback2)));
	}
}
