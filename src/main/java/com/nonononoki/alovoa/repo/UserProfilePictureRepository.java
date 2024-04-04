package com.nonononoki.alovoa.repo;

import com.nonononoki.alovoa.entity.user.UserImage;
import com.nonononoki.alovoa.entity.user.UserProfilePicture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserProfilePictureRepository extends JpaRepository<UserProfilePicture, Long> {
    UserProfilePicture findByUuid(UUID uuid);
}

