package com.jaeun.dantong.service;

import com.jaeun.dantong.domain.dto.UserDto;
import com.jaeun.dantong.domain.entity.User;
import com.jaeun.dantong.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public Long singup(UserDto userDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userDto.setPassword(encoder.encode(userDto.getPassword()));

        return userRepository.save(User.builder()
                .email(userDto.getEmail())
                .auth(userDto.getAuth())
                .password(userDto.getPassword())
                .major(userDto.getMajor())
                .studentId(userDto.getStudentId()).build()).getId();
    }

//    private void checkEmailValidationToken(String token, String classId){
//        if(token==null){
//            throw new ReissueAccessTokenNotCorrectException("유효하지 않은 토큰입니다. 이메일 재전송 해주세요");
//        }
//
//        if(!jwtProvider.validateEmailValidationToken(token, classId)){
//            throw new RefreshTokenNotValidateException("학번이 조작되었습니다.");
//        }
//    }
}
