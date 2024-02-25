package io.github.realyusufismail.logger;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.color.ANSIConstants;
import ch.qos.logback.core.pattern.color.ForegroundCompositeConverterBase;

public class LoggerColorScheme extends ForegroundCompositeConverterBase<ILoggingEvent> {

    @Override
    protected String getForegroundColorCode(ILoggingEvent iLoggingEvent) {
        return switch (iLoggingEvent.getLevel().levelInt) {
            case Level.DEBUG_INT -> ANSIConstants.CYAN_FG;
            case Level.INFO_INT -> ANSIConstants.GREEN_FG;
            case Level.WARN_INT -> ANSIConstants.YELLOW_FG;
            case Level.ERROR_INT -> ANSIConstants.RED_FG;
            default -> ANSIConstants.DEFAULT_FG;
        };
    }
}
