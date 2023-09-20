package org.example.jep442;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

import static java.lang.foreign.ValueLayout.ADDRESS;
import static java.lang.foreign.ValueLayout.JAVA_INT;

public class SimpleCall {
	public static void main(String[] args) throws Throwable {
		String payload = "21";
		var functionName = "strlen";

		Linker linker = Linker.nativeLinker();
		SymbolLookup linkerLookup = linker.defaultLookup();

		MemorySegment memSegment = linkerLookup.find(functionName).get();

		FunctionDescriptor funcDescriptor = FunctionDescriptor.of(JAVA_INT, ADDRESS);
		MethodHandle funcHandle = linker.downcallHandle(memSegment, funcDescriptor);

		try (var arena = Arena.ofConfined()) {
			var segment = arena.allocateUtf8String(payload);
			int result = (int) funcHandle.invoke(segment);
			System.out.println("The answer is " + result);
		}

	}
}
