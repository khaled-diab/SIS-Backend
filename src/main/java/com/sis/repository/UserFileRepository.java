package com.sis.repository;

import com.sis.entity.UserFile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserFileRepository extends BaseRepository<UserFile> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_file (directories, file_name, user_id, type) VALUES (?1, ?2, ?3, ?4)", nativeQuery = true)
    void saveUserFile(String directories, String fileName, Long userID, String fileType);

    @Transactional
    @Modifying
    @Query(value = " delete from user_file where type=?1 and user_id=?2", nativeQuery = true)
    void deletePreviousPictures(String type, long userID);
}
