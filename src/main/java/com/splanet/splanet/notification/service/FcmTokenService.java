<<<<<<< HEAD
// src/main/java/com/splanet/splanet/notification/service/FcmTokenService.java
=======
>>>>>>> weekly/11
package com.splanet.splanet.notification.service;

import com.splanet.splanet.core.exception.BusinessException;
import com.splanet.splanet.core.exception.ErrorCode;
import com.splanet.splanet.notification.dto.FcmTokenUpdateRequest;
import com.splanet.splanet.notification.entity.FcmToken;
import com.splanet.splanet.notification.repository.FcmTokenRepository;
import com.splanet.splanet.user.entity.User;
import com.splanet.splanet.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FcmTokenService {

    private final FcmTokenRepository fcmTokenRepository;
    private final UserRepository userRepository;

    @Transactional
    public void registerFcmToken(Long userId, String token) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        FcmToken fcmToken = fcmTokenRepository.findByUserIdAndToken(userId, token)
                .orElse(FcmToken.builder()
                        .user(user)
                        .token(token)
                        .build());

        fcmTokenRepository.save(fcmToken);
    }

    @Transactional
<<<<<<< HEAD
    public void updateFcmTokenSettings(Long userId, FcmTokenUpdateRequest request) {
        FcmToken fcmToken = fcmTokenRepository.findByUserIdAndToken(userId, request.token())
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        fcmToken = fcmToken.toBuilder()
                .isNotificationEnabled(request.isNotificationEnabled() != null
                        ? request.isNotificationEnabled()
                        : fcmToken.getIsNotificationEnabled())
                .notificationOffset(request.notificationOffset() != null
                        ? request.notificationOffset()
                        : fcmToken.getNotificationOffset())
=======
    public void updateNotificationEnabled(String token, Boolean isNotificationEnabled) {
        FcmToken fcmToken = fcmTokenRepository.findByToken(token)
                .orElseThrow(() -> new BusinessException(ErrorCode.TOKEN_NOT_FOUND));

        fcmToken = fcmToken.toBuilder()
                .isNotificationEnabled(isNotificationEnabled != null ? isNotificationEnabled : fcmToken.getIsNotificationEnabled())
>>>>>>> weekly/11
                .build();

        fcmTokenRepository.save(fcmToken);
    }

    @Transactional
<<<<<<< HEAD
    public void deleteFcmToken(Long userId, String token) {
        FcmToken fcmToken = fcmTokenRepository.findByUserIdAndToken(userId, token)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
=======
    public void updateNotificationOffset(String token, Integer notificationOffset) {
        FcmToken fcmToken = fcmTokenRepository.findByToken(token)
                .orElseThrow(() -> new BusinessException(ErrorCode.TOKEN_NOT_FOUND));

        fcmToken = fcmToken.toBuilder()
                .notificationOffset(notificationOffset != null ? notificationOffset : fcmToken.getNotificationOffset())
                .build();

        fcmTokenRepository.save(fcmToken);
    }

    @Transactional
    public void deleteFcmToken(String token) {
        FcmToken fcmToken = fcmTokenRepository.findByToken(token)
                .orElseThrow(() -> new BusinessException(ErrorCode.TOKEN_NOT_FOUND));
>>>>>>> weekly/11
        fcmTokenRepository.delete(fcmToken);
    }
}
