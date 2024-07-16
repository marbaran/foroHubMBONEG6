package com.marcelobaranowski.ForoHubMB.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository <UserDB, Long> {

}
