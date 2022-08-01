package com.jaeun.dantong.service;

import com.jaeun.dantong.domain.dto.UserDto;
import com.jaeun.dantong.domain.dto.response.UserResponse;
import com.jaeun.dantong.domain.entity.User;
import com.jaeun.dantong.email.EmailSender;
import com.jaeun.dantong.repository.UserRepository;
import com.jaeun.dantong.token.ConfirmationToken;
import com.jaeun.dantong.token.ConfirmationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class UserService {

    private final ConfirmationTokenService confirmationTokenService;

    private final EmailSender emailSender;

    private final UserRepository userRepository;


    public String singup(UserDto userDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userDto.setPassword(encoder.encode(userDto.getPassword()));

        String token = signUpUser(User.builder()
                .email(userDto.getEmail())
                .name(userDto.getName())
                .password(userDto.getPassword())
                .major(userDto.getMajor())
                .studentId(userDto.getStudentId()).build());
        String link = "http://localhost:8080/users/confirm?token=" + token;
        emailSender.send(userDto.getEmail(), emailSender.buildEmail(userDto.getEmail(), link));
        return token;
    }

    @Transactional
    public String signUpUser(User user) {
        Optional<User> userExists = userRepository.findByEmail(user.getEmail());

        if (userExists.isPresent()) {
            // throw new EmailDuplicatedException(member.getEmail());
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setEncodedPassword(encodedPassword);

        userRepository.save(user);
        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = ConfirmationToken.of(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }

    //TODO 이메일 인증 프로세스
    @Transactional
    public UserResponse confirmToken(String token) {
        if (token.isEmpty()) {
//            throw new TokenNotExistException();
        }
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token).orElseThrow();
//                .orElseThrow(InvalidTokenException::new);

        if (confirmationToken.getConfirmedAt() != null) {
//            throw new EmailConfirmedException();
        }
        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now())) {
//            throw new TokenExpiredException();
        }
        confirmationTokenService.setConfirmedAt(token);
        userRepository.enableMember(confirmationToken.getUser().getEmail());
        return new UserResponse(confirmationToken.getUser());
    }
}
