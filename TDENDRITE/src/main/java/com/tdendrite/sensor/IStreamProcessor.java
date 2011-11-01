package com.tdendrite.sensor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public interface IStreamProcessor {
	public ByteArrayOutputStream returnOutputStream() throws IOException;
}
