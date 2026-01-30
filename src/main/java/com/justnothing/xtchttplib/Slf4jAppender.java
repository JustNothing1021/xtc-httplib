package com.justnothing.xtchttplib;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Slf4jAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

    private static TextAreaLogger textAreaLogger;

    public static void initialize(TextAreaLogger logger) {
        textAreaLogger = logger;

        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

        Logger rootLogger = loggerContext.getLogger(Logger.ROOT_LOGGER_NAME);

        Slf4jAppender appender = new Slf4jAppender();
        appender.setContext(loggerContext);
        appender.setName("TextAreaAppender");
        appender.start();
        rootLogger.addAppender(appender);
    }

    @Override
    protected void append(ILoggingEvent eventObject) {
        if (!isStarted()) {
            return;
        }

        if (textAreaLogger != null) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            String level = eventObject.getLevel().toString();
            String message = eventObject.getFormattedMessage();
            String loggerName = eventObject.getLoggerName();

            String shortLoggerName = loggerName != null ? 
                loggerName.substring(loggerName.lastIndexOf('.') + 1) : "ROOT";

            String logMessage = String.format("[%s] [%s] [%s] %s", 
                timestamp, level, shortLoggerName, message);

            textAreaLogger.log(logMessage);
        }
    }

    public interface TextAreaLogger {
        void log(String message);
    }
}
