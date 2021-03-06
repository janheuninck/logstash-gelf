package biz.paluch.logging.gelf.jboss7;

import java.util.logging.LogRecord;

import org.apache.log4j.MDC;

import biz.paluch.logging.gelf.MdcMessageField;
import biz.paluch.logging.gelf.MessageField;
import biz.paluch.logging.gelf.jul.JulLogEvent;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 26.09.13 18:32
 */
public class JBoss7JulLogEvent extends JulLogEvent {

    public JBoss7JulLogEvent(LogRecord logRecord) {
        super(logRecord);
    }

    @Override
    public String getValue(MessageField field) {

        if (field instanceof MdcMessageField) {
            return getValue((MdcMessageField) field);

        }

        return super.getValue(field);
    }

    private String getValue(MdcMessageField field) {

        return getMdc(field.getMdcName());
    }

    @Override
    public String getMdc(String mdcName) {
        Object value = MDC.get(mdcName);
        if (value != null) {
            return value.toString();
        }
        String slf4jValue = org.slf4j.MDC.get(mdcName);
        return slf4jValue;
    }
}
