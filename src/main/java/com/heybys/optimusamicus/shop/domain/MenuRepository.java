package com.heybys.optimusamicus.shop.domain;

import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

  @EntityGraph(attributePaths = {"optionGroups"})
  List<Menu> findByShopId(Long shopId);

}
