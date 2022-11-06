package com.heybys.optimusamicus.user.service;

import com.heybys.optimusamicus.common.annotation.LogExecutionTime;
import com.heybys.optimusamicus.user.domain.entity.UserGroup;
import com.heybys.optimusamicus.user.domain.repository.UserGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@LogExecutionTime
@RequiredArgsConstructor
public class UserGroupService {

  private final UserGroupRepository userGroupRepository;

  @Transactional(readOnly = true)
  public UserGroup retrieveUserGroup(Long userGroupId) {
    return userGroupRepository.findById(userGroupId).orElseThrow();
  }

  @Transactional
  public UserGroup createUserGroup(UserGroup userGroup) {
    return userGroupRepository.save(userGroup);
  }
}
