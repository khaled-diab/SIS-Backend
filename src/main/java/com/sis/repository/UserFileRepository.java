package com.sis.repository;

import com.sis.entity.UserFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface UserFileRepository extends BaseRepository<UserFile> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_file (directories, file_name, user_id, type ,upload_date) VALUES (?1, ?2, ?3, ?4,?5)", nativeQuery = true)
    void saveUserFile(String directories, String fileName, Long userID, String fileType, Date date);

    @Transactional
    @Modifying
    @Query(value = " delete from user_file where type=?1 and user_id=?2", nativeQuery = true)
    void deletePreviousPictures(String type, long userID);

    Page<UserFile> findAllByFileNameContainingIgnoreCaseAndTypeIn(String fileName, List<String> filesTypes, Pageable pageable);

    Page<UserFile> findAllByTypeIn(List<String> filesTypes, Pageable pageable);

}
