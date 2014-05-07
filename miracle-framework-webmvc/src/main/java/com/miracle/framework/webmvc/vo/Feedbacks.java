package com.miracle.framework.webmvc.vo;

import java.util.ArrayList;
import java.util.Collection;

public final class Feedbacks {
	
	private final Collection<Feedback> feedbacks = new ArrayList<>();
	
	public void addFeedback(final Feedback feedback) {
		feedbacks.add(feedback);
	}
	
	public Collection<Feedback> getFeedbacks() {
		return feedbacks;
	}
}
