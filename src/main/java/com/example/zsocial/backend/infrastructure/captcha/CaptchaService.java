package com.example.zsocial.backend.infrastructure.captcha;

public interface CaptchaService {
    boolean verify(String recaptchaToken);
}

