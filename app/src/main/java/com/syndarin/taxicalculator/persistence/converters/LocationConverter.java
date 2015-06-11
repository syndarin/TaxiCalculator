package com.syndarin.taxicalculator.persistence.converters;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.raizlabs.android.dbflow.converter.TypeConverter;

/**
 * Created by syndarin on 6/6/15.
 */
@com.raizlabs.android.dbflow.annotation.TypeConverter
public class LocationConverter extends TypeConverter<String, LatLng> {

    private final static String DATA_DELIMITER = "|";

    @Override
    public String getDBValue(LatLng model) {
        return model != null ?  new StringBuilder().append(model.latitude).append(DATA_DELIMITER).append(model.longitude).toString() : null;
    }

    @Override
    public LatLng getModelValue(String data) {
        String[] dataParts = data.split(DATA_DELIMITER);

        double latitude = Double.valueOf(dataParts[0]);
        double longitude = Double.valueOf(dataParts[1]);

        return new LatLng(latitude, longitude);
    }
}
