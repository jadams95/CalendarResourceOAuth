package com.jaddy.calendarresourceoauth.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.jaddy.calendarresourceoauth.ds.Appointment;

import java.io.IOException;

public class AppointmentSerializer extends StdSerializer<Appointment> {
    public AppointmentSerializer(){
        this(null);
    }
    public AppointmentSerializer(Class<Appointment> t){
        super(t);
    }

    @Override
    public void serialize(Appointment value, JsonGenerator gen, SerializerProvider provider) throws IOException {

    }
}
