package com.anthony.springboot.util;


import com.sun.xml.bind.marshaller.NamespacePrefixMapper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.bind.*;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bl03435 on 2015/9/21.
 */
public class JAXBUtil {
    private static final Log logger = LogFactory.getLog(JAXBUtil.class);

    // key为packageName, value为JAXBContext
    private static Map<String, JAXBContext> jaxbContextHolder = new HashMap<String, JAXBContext>();

    private static JAXBContext getJaxbContext(String packageName) throws JAXBException {
        JAXBContext inst = jaxbContextHolder.get(packageName);
        if (inst == null) {
            inst = JAXBContext.newInstance(packageName);
            jaxbContextHolder.put(packageName, inst);
            return inst;
        }
        return inst;
    }

    public static <T> T formXML(Class<T> clazz, String message) {
        JAXBContext context;
        Unmarshaller u;
        JAXBElement<T> element;
        try {
            context = getJaxbContext(clazz.getPackage().getName());
            u = context.createUnmarshaller();
            //element = (JAXBElement<T>) u.unmarshal(new StreamSource(new StringReader(message)));
            element = u.unmarshal(new StreamSource(new StringReader(message)), clazz);

        } catch (JAXBException e) {
            logger.error("error when unmarshalling from a xml string");
            throw new RuntimeException(e);
        }
        return element.getValue();

    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> String toXML(Object target, String encoding, String schemaLocation,
                                   QName qName, NamespacePrefixMapper npMapper) {
        try {

            JAXBContext context = getJaxbContext(target.getClass().getPackage().getName());

            JAXBElement<T> element = new JAXBElement(qName, target.getClass(), null, target);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            if (encoding == null) {
                encoding = "UTF-8";
            }

            m.setProperty(Marshaller.JAXB_ENCODING, encoding);

            if (schemaLocation != null) {
                m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, schemaLocation);
            }
            m.setProperty("com.sun.xml.bind.namespacePrefixMapper", npMapper);

            ByteArrayOutputStream ot = new ByteArrayOutputStream();
            m.marshal(element, ot);
            byte[] buf = ot.toByteArray();
            return new String(buf, 0, buf.length, encoding);
        } catch (Exception e) {
            logger.error("error when create an element for marshalling", e);
        }
        return null;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> String toXML(Object target, String schemaLocation, QName qName,
                                   NamespacePrefixMapper npMapper) {
        ByteArrayOutputStream ot = null;
        try {

            JAXBContext context = getJaxbContext(target.getClass().getPackage().getName());

            JAXBElement<T> element = new JAXBElement(qName, target.getClass(), null, target);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            if (schemaLocation != null) {
                m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, schemaLocation);
            }
            m.setProperty("com.sun.xml.bind.namespacePrefixMapper", npMapper);

            ot = new ByteArrayOutputStream();
            m.marshal(element, ot);
            byte[] buf = ot.toByteArray();
            return new String(buf, 0, buf.length, "UTF-8");
        } catch (Exception e) {
            logger.error("error when create an element for marshalling", e);
        } finally {
            IOUtils.closeQuietly(ot);
        }
        return null;
    }

    public static Date fromXMLCalendar(XMLGregorianCalendar xmlCalendar) {
        if (xmlCalendar == null) {
            return null;
        }
        Calendar cal = xmlCalendar.toGregorianCalendar();
        return cal.getTime();
    }

    public static XMLGregorianCalendar toXMLCalendar(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        XMLGregorianCalendar cal = null;
        try {
            cal = DatatypeFactory.newInstance().newXMLGregorianCalendar();
            cal.setYear(calendar.get(Calendar.YEAR));
            cal.setMonth(calendar.get(Calendar.MONTH) + 1);
            cal.setDay(calendar.get(Calendar.DAY_OF_MONTH));
            cal.setHour(calendar.get(Calendar.HOUR_OF_DAY));
            cal.setMinute(calendar.get(Calendar.MINUTE));
            cal.setSecond(calendar.get(Calendar.SECOND));
        } catch (DatatypeConfigurationException e) {
            logger.error("error when generate XML Calendar");
            throw new RuntimeException(e);
        }
        return cal;
    }

    public static void main(String... s) {
        XMLGregorianCalendar cal = JAXBUtil.toXMLCalendar(new Date());
        System.out.print(cal.getXMLSchemaType());
    }
}
