package com.binar.kelompok3.secondhand.controller;

import com.binar.kelompok3.secondhand.model.entity.Notification;
import com.binar.kelompok3.secondhand.model.response.MessageResponse;
import com.binar.kelompok3.secondhand.model.response.NotificationResponse;
import com.binar.kelompok3.secondhand.service.notification.INotificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/notification")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NotificationController {

    private INotificationService iNotificationService;

    @PutMapping("/read/{id}")
    public ResponseEntity<MessageResponse> readNotif(@PathVariable Integer id) {
        iNotificationService.updateIsRead(id);
        return ResponseEntity.ok(new MessageResponse("Sukses membaca notfi"));
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<List<NotificationResponse>> getNotification(@PathVariable Integer userId) {
        List<Notification> notification = iNotificationService.getNotification(userId);
        List<NotificationResponse> notificationResponses =
                notification.stream()
                        .map(notification1 -> {
                            if (notification1.getOfferId() == null) {
                                return new NotificationResponse(notification1
                                        , notification1.getProductId());
                            } else return new NotificationResponse(notification1
                                    , notification1.getProductId(), notification1.getOfferId());
                        })
                        .collect(Collectors.toList());
        return new ResponseEntity<>(notificationResponses, HttpStatus.OK);
    }
}