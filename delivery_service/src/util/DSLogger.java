package util;

public class DSLogger {
	public static void debug(String msg) {
        StackTraceElement element = Thread.currentThread().getStackTrace()[2];
        System.out.printf("[%s : %d lines] %s\n", element.getFileName(), element.getLineNumber(), msg);        
    }
	
    public static void debug(String format, Object... args) {
        StackTraceElement element = Thread.currentThread().getStackTrace()[2];
        System.out.printf("[%s : %d lines] ", element.getFileName(), element.getLineNumber());
        System.out.printf(format, args);
    }
}