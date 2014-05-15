package com.miracle.framework.webmvc.vo;

import java.util.ArrayList;
import java.util.Collection;

import com.miracle.framework.lang.BaseObject;

public final class Feedbacks extends BaseObject {
	
	private final Collection<Feedback> feedbacks = new ArrayList<>();
	
	public void addFeedback(final Feedback feedback) {
		feedbacks.add(feedback);
	}
	
	public Collection<Feedback> getFeedbacks() {
		return feedbacks;
	}
}
