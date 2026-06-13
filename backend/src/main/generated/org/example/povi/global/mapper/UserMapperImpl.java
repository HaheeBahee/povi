package org.example.povi.global.mapper;

import javax.annotation.processing.Generated;
import org.example.povi.domain.user.dto.ProfileRes;
import org.example.povi.domain.user.dto.ProfileUpdateReq;
import org.example.povi.domain.user.entity.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-06T01:34:14+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public void updateUserFromDto(User user, ProfileUpdateReq reqDto) {
        if ( reqDto == null ) {
            return;
        }

        applyCustomUpdates( user, reqDto );
    }

    @Override
    public ProfileRes toProfileRes(User user) {
        if ( user == null ) {
            return null;
        }

        String nickname = null;
        String profileImgUrl = null;
        String bio = null;

        nickname = user.getNickname();
        profileImgUrl = user.getProfileImgUrl();
        bio = user.getBio();

        ProfileRes profileRes = new ProfileRes( nickname, profileImgUrl, bio );

        return profileRes;
    }
}
