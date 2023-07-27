package com.wzu.remote.appoint.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wzu.remote.model.appoint.AppointReservation;

public interface AppointReservationService extends IService<AppointReservation> {
    AppointReservation getAvailableReservation(Long userId);
}
