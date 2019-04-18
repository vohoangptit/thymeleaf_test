package jp.sbpayment.bpr.bl.repository;

import java.util.List;
import jp.sbpayment.bpr.bl.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends BaseRespository<User> {

  User findByUsername(@Param("username") String username);

  @Query("select u from User u where (u.firstName LIKE %:name% or u.lastName LIKE %:name%)"
          + " and u.email LIKE %:email%")
  List<User> findByNameAndEmail(@Param("name") String name,
                                @Param("email") String email);

}

