package com.heybys.optimusamicus.user.domain.repository;

import com.heybys.optimusamicus.user.controller.dto.search.UserSearch;
import com.heybys.optimusamicus.user.domain.entity.User;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomUserRepository {

  Page<User> retrieveUsers(UserSearch.Request request, Pageable pageable);

  void patchUser(Long userId, Map<String, Object> params);

  List<User> batchInsert(List<User> users);
}