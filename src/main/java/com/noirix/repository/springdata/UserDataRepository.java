package com.noirix.repository.springdata;

import com.noirix.domain.hibernate.HibernateUser;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserDataRepository extends CrudRepository<HibernateUser, Long>, PagingAndSortingRepository<HibernateUser, Long>, JpaRepository<HibernateUser, Long> {

    List<HibernateUser> findByIdIn(List<Long> ids);

    Optional<HibernateUser> findByNameAndLogin(String name, String login);

    @Cacheable("users")
    @Query(value = "select u from HibernateUser u where u.id > :userID")
    List<HibernateUser> findByIdHQLVersion(@Param("userID") Long id);

    @Query(value = "select * from users where id > :userID", nativeQuery = true)
    List<HibernateUser> findByIdSQLVersion(@Param("userID") Long id);

    // В данном случае нужно будет самостоятельно парсить и обрабатывать данный массив
    @Query(value = "select u.id, u.name from HibernateUser u where u.id = :userID")
    Object[] findByIdHQLVersionSimplified(@Param("userID") Long id);

    @Query(value = "select u.id, u.name from HibernateUser u where u.id > :userID")
    List<Object[]> findByIdHQLVersionSimplified2(@Param("userID") Long id);

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = SQLException.class)
    @Modifying
    @Query(value = "insert into user_roles(user_id, role_id) values (:user_id, :role_id)", nativeQuery = true)
    int createSomeRow(@Param("user_id") Long userId, @Param("role_id") Long roleId);
}
