package com.miracle.test.service.fixture.internal;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.miracle.test.service.fixture.UnmanagedService;

public final class UnmanagedServiceImpl implements UnmanagedService {
	
	@Override
	public String getDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}
}
