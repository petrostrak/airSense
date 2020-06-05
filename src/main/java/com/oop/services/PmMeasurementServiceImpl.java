package com.oop.services;

import com.oop.dao.IPmMeasurementDao;
import com.oop.dao.ISensorLocationDao;
import com.oop.dao.ISoldSensorDao;
import com.oop.entities.PmMeasurement;
import com.oop.entities.SensorLocation;
import com.oop.entities.SoldSensor;
import com.oop.exceptions.SensorLocationNotFoundException;
import com.oop.exceptions.SoldSensorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PmMeasurementServiceImpl implements IPmMeasurementService {

    @Autowired
    IPmMeasurementDao pmMeasurementDao;

    @Autowired
    ISensorLocationDao sensorLocationDao;

    @Autowired
    ISoldSensorDao soldSensorDao;

    @Override
    public PmMeasurement save(PmMeasurement pm) {
        SensorLocation sensorLocation = pm.getSensorLocation();
        sensorLocation = sensorLocationDao.findById(sensorLocation.getId()).orElseThrow(SensorLocationNotFoundException::new);
        SoldSensor soldSensor = sensorLocation.getSoldSensor();
        long soldSensorId;
        try {
            soldSensorId = soldSensor.getId();
        } catch (java.lang.NullPointerException e) {
            throw new SoldSensorNotFoundException();
        }
        soldSensor = soldSensorDao.findById(soldSensorId).orElseThrow(SoldSensorNotFoundException::new);
        sensorLocation.setSoldSensor(soldSensor);
        pm.setSensorLocation(sensorLocation);
        return pmMeasurementDao.save(pm);
    }

}
